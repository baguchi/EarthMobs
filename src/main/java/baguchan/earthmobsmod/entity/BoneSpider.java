package baguchan.earthmobsmod.entity;

import baguchan.earthmobsmod.entity.goal.RangedAndMeleeAttack;
import baguchan.earthmobsmod.entity.projectile.BoneShard;
import baguchan.earthmobsmod.registry.ModEntities;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.RangedAttackMob;
import net.minecraft.world.entity.monster.Spider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

import java.util.Collection;
import java.util.EnumSet;

public class BoneSpider extends Spider implements RangedAttackMob {
	private static final EntityDataAccessor<Boolean> DATA_STRAY_CONVERSION_ID = SynchedEntityData.defineId(BoneSpider.class, EntityDataSerializers.BOOLEAN);
	private int inPowderSnowTime;
	private int conversionTime;

	public BoneSpider(EntityType<? extends BoneSpider> p_33786_, Level p_33787_) {
		super(p_33786_, p_33787_);
		this.xpReward = 10;
	}

	@Override
	protected void defineSynchedData(SynchedEntityData.Builder builder) {
		super.defineSynchedData(builder);
		builder.define(DATA_STRAY_CONVERSION_ID, false);
	}

	public static AttributeSupplier.Builder createAttributes() {
		return Monster.createMonsterAttributes().add(Attributes.MAX_HEALTH, 24.0D).add(Attributes.ATTACK_DAMAGE, 3.0F).add(Attributes.MOVEMENT_SPEED, (double) 0.3F).add(Attributes.ARMOR, 10.0F).add(Attributes.FOLLOW_RANGE, 18.0F);
	}

	@Override
	protected void registerGoals() {
        this.goalSelector.addGoal(1, new FloatGoal(this));
        this.goalSelector.addGoal(4, new BoneSpiderAttackGoal(this));
        this.goalSelector.addGoal(5, new WaterAvoidingRandomStrollGoal(this, 0.8D));
        this.goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(6, new RandomLookAroundGoal(this));
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
        this.targetSelector.addGoal(2, new SpiderTargetGoal<>(this, Player.class));
        this.targetSelector.addGoal(3, new SpiderTargetGoal<>(this, IronGolem.class));
    }

	public boolean isFreezeConverting() {
		return this.getEntityData().get(DATA_STRAY_CONVERSION_ID);
	}

	public void setFreezeConverting(boolean p_149843_) {
		this.entityData.set(DATA_STRAY_CONVERSION_ID, p_149843_);
	}

	public boolean isShaking() {
		return this.isFreezeConverting();
	}

	public void tick() {
		if (!this.level().isClientSide && this.isAlive() && !this.isNoAi()) {
			if (this.isFreezeConverting()) {
				--this.conversionTime;
				if (this.conversionTime < 0) {
					this.doFreezeConversion();
				}
			} else if (this.isInPowderSnow) {
				++this.inPowderSnowTime;
				if (this.inPowderSnowTime >= 140) {
					this.startFreezeConversion(300);
				}
			} else {
				this.inPowderSnowTime = -1;
			}
		}

		super.tick();
	}

	public void addAdditionalSaveData(CompoundTag p_149836_) {
		super.addAdditionalSaveData(p_149836_);
		p_149836_.putInt("StrayConversionTime", this.isFreezeConverting() ? this.conversionTime : -1);
	}

	public void readAdditionalSaveData(CompoundTag p_149833_) {
		super.readAdditionalSaveData(p_149833_);
		if (p_149833_.contains("StrayConversionTime", 99) && p_149833_.getInt("StrayConversionTime") > -1) {
			this.startFreezeConversion(p_149833_.getInt("StrayConversionTime"));
		}

	}

	public void startFreezeConversion(int p_149831_) {
		this.conversionTime = p_149831_;
		this.entityData.set(DATA_STRAY_CONVERSION_ID, true);
	}

	protected void doFreezeConversion() {
		this.convertTo(ModEntities.STRAY_BONE_SPIDER.get(), true);
		if (!this.isSilent()) {
			this.level().levelEvent((Player) null, 1048, this.blockPosition(), 0);
		}

	}

	public boolean canFreeze() {
		return false;
	}

	@Override
	public boolean isInvertedHealAndHarm() {
		return true;
	}

    static class BoneSpiderAttackGoal extends RangedAndMeleeAttack {
        private final BoneSpider spider;

        public BoneSpiderAttackGoal(BoneSpider p_32247_) {
            super(p_32247_, 1.0F, 40, 80, 14);
            this.spider = p_32247_;
            this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
        }

        public void tick() {
            super.tick();
            if (this.attackTime == 15) {
                this.spider.playSound(SoundEvents.LLAMA_SPIT, this.spider.getSoundVolume(), 0.4F / (this.spider.getRandom().nextFloat() * 0.4F + 0.8F));
            }
        }

        @Override
        public boolean canUse() {
            return super.canUse();
        }

        public boolean canContinueToUse() {
            float f = this.mob.getLightLevelDependentMagicValue();
            if (f >= 0.5F && this.mob.getRandom().nextInt(100) == 0) {
                this.mob.setTarget((LivingEntity) null);
                return false;
            } else {
                return super.canContinueToUse();
            }
        }

        protected double getAttackReachSqr(LivingEntity p_33825_) {
            return (double) (4.0F + p_33825_.getBbWidth());
        }
    }

	public void performRangedAttack(LivingEntity p_29912_, float p_29913_) {
		BoneShard bone = new BoneShard(this.level(), this);
		double d1 = p_29912_.getX() - this.getX();
		double d2 = p_29912_.getEyeY() - this.getEyeY();
		double d3 = p_29912_.getZ() - this.getZ();
		bone.shoot(d1, d2, d3, 1.4F, 2.0F + p_29913_);
		Collection<MobEffectInstance> collection = this.getActiveEffects();
		if (!collection.isEmpty()) {
			for (MobEffectInstance mobEffectInstance : this.getActiveEffects()) {
                bone.addEffect(new MobEffectInstance(mobEffectInstance.getEffect(), mobEffectInstance.getDuration() / 4, 0));
            }
        }
		this.level().addFreshEntity(bone);
    }

    @Override
    public float getScale() {
        return this.isBaby() ? 0.6F : 1.0F;
    }

    static class SpiderTargetGoal<T extends LivingEntity> extends NearestAttackableTargetGoal<T> {
        public SpiderTargetGoal(Spider p_33832_, Class<T> p_33833_) {
            super(p_33832_, p_33833_, true);
        }

        public boolean canUse() {
            float f = this.mob.getLightLevelDependentMagicValue();
            return f >= 0.5F ? false : super.canUse();
        }
    }
}
