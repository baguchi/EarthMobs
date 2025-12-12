package baguchan.earthmobsmod.entity;

import baguchan.earthmobsmod.entity.goal.RangedAndMeleeAttack;
import baguchan.earthmobsmod.entity.projectile.BoneShard;
import baguchan.earthmobsmod.registry.ModEffects;
import baguchan.earthmobsmod.registry.ModItems;
import net.minecraft.core.Holder;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Difficulty;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.golem.IronGolem;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.RangedAttackMob;
import net.minecraft.world.entity.monster.skeleton.Skeleton;
import net.minecraft.world.entity.monster.spider.Spider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import org.jspecify.annotations.Nullable;

import java.util.Collection;
import java.util.EnumSet;

public class BoneSpider extends Spider implements RangedAttackMob {
	private static final EntityDataAccessor<Boolean> DATA_STRAY_CONVERSION_ID = SynchedEntityData.defineId(BoneSpider.class, EntityDataSerializers.BOOLEAN);

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
        this.goalSelector.addGoal(4, new BoneSpiderAttackGoal(this, 0));
        this.goalSelector.addGoal(5, new WaterAvoidingRandomStrollGoal(this, 0.8D));
        this.goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(6, new RandomLookAroundGoal(this));
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
        this.targetSelector.addGoal(2, new SpiderTargetGoal<>(this, Player.class));
        this.targetSelector.addGoal(3, new SpiderTargetGoal<>(this, IronGolem.class));
    }

    @Override
	public boolean canFreeze() {
		return false;
	}

	@Override
	public boolean isInvertedHealAndHarm() {
		return true;
	}

    public @Nullable SpawnGroupData finalizeSpawn(ServerLevelAccessor p_478692_, DifficultyInstance p_479487_, EntitySpawnReason p_481376_, @Nullable SpawnGroupData p_480575_) {
        p_480575_ = super.finalizeSpawn(p_478692_, p_479487_, p_481376_, p_480575_);
        RandomSource randomsource = p_478692_.getRandom();
        if (randomsource.nextInt(100) == 0) {
            Skeleton skeleton = (Skeleton) EntityType.SKELETON.create(this.level(), EntitySpawnReason.JOCKEY);
            if (skeleton != null) {
                skeleton.snapTo(this.getX(), this.getY(), this.getZ(), this.getYRot(), 0.0F);
                skeleton.finalizeSpawn(p_478692_, p_479487_, p_481376_, (SpawnGroupData) null);
                skeleton.startRiding(this, false, false);
            }
        }

        if (p_480575_ == null || p_480575_ instanceof SpiderEffectsGroupData) {
            p_480575_ = new BoneSpiderEffectsGroupData();
            if (p_478692_.getDifficulty() == Difficulty.HARD && randomsource.nextFloat() < 0.125F * p_479487_.getSpecialMultiplier()) {
                ((BoneSpiderEffectsGroupData) p_480575_).setRandomEffect(randomsource);
            }
        }

        if (p_480575_ instanceof SpiderEffectsGroupData spider$spidereffectsgroupdata) {
            Holder<MobEffect> holder = spider$spidereffectsgroupdata.effect;
            if (holder != null) {
                this.addEffect(new MobEffectInstance(holder, -1));
            }
        }

        return p_480575_;
    }


    public static class BoneSpiderEffectsGroupData implements SpawnGroupData {
        public @Nullable Holder<MobEffect> effect;

        public void setRandomEffect(RandomSource p_481946_) {
            int i = p_481946_.nextInt(6);
            if (i <= 1) {
                this.effect = MobEffects.STRENGTH;
            } else if (i <= 2) {
                this.effect = MobEffects.REGENERATION;
            } else if (i <= 3) {
                this.effect = ModEffects.ZOMBIFIED;
            } else if (i <= 4) {
                this.effect = ModEffects.UNDEAD_BODY;
            }

        }
    }

    static class BoneSpiderAttackGoal extends RangedAndMeleeAttack {
        private final BoneSpider spider;

        public BoneSpiderAttackGoal(BoneSpider p_32247_, int extraDuration) {
            super(p_32247_, 1.0F, 60 + extraDuration, 100 + extraDuration, 14);
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
		BoneShard bone = new BoneShard(this.level(), this, ModItems.BONE_SHARD.toStack());
        double x = p_29912_.getX() - this.getX();
        double y = p_29912_.getEyeY() - this.getEyeY();
        double z = p_29912_.getZ() - this.getZ();
        double length = Math.sqrt(x * x + z * z);
        bone.shoot(x, y + (length * 0.275F), z, 0.75F, 2.0F);
		Collection<MobEffectInstance> collection = this.getActiveEffects();
		if (!collection.isEmpty()) {
			for (MobEffectInstance mobEffectInstance : this.getActiveEffects()) {
                if (!mobEffectInstance.getEffect().value().isBeneficial()) {
                    if (mobEffectInstance.getEffect().value().isInstantenous()) {
                        bone.addEffect(new MobEffectInstance(mobEffectInstance.getEffect(), 1, 0));
                    } else if (mobEffectInstance.isInfiniteDuration()) {
                        bone.addEffect(new MobEffectInstance(mobEffectInstance.getEffect(), 100, 0));
                    } else {
                        bone.addEffect(new MobEffectInstance(mobEffectInstance.getEffect(), mobEffectInstance.getDuration() / 4, 0));
                    }
                }
            }
        }
		this.level().addFreshEntity(bone);
    }

	@Override
	public float getAgeScale() {
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
