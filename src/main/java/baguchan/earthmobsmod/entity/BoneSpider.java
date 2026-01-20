package baguchan.earthmobsmod.entity;

import baguchan.earthmobsmod.EarthMobsMod;
import baguchan.earthmobsmod.entity.goal.RangedAndMeleeAttack;
import baguchan.earthmobsmod.entity.projectile.BoneShard;
import baguchan.earthmobsmod.registry.ModEffects;
import baguchan.earthmobsmod.registry.ModEntityDatas;
import net.minecraft.core.particles.ColorParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtOps;
import net.minecraft.nbt.Tag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.RegistryOps;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.FastColor;
import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.SpawnGroupData;
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
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;

import javax.annotation.Nullable;
import java.util.EnumSet;

public class BoneSpider extends Spider implements RangedAttackMob {
	private static final EntityDataAccessor<Boolean> DATA_STRAY_CONVERSION_ID = SynchedEntityData.defineId(BoneSpider.class, EntityDataSerializers.BOOLEAN);
	private static final EntityDataAccessor<PotionContents> DATA_POTION = SynchedEntityData.defineId(BoneSpider.class, ModEntityDatas.POTIONS.get());
	private static final ColorParticleOption DEFAULT_PARTICLE = ColorParticleOption.create(ParticleTypes.ENTITY_EFFECT, -1);

	public BoneSpider(EntityType<? extends BoneSpider> p_33786_, Level p_33787_) {
		super(p_33786_, p_33787_);
		this.xpReward = 10;
	}

	@Override
	protected void defineSynchedData(SynchedEntityData.Builder builder) {
		super.defineSynchedData(builder);
		builder.define(DATA_STRAY_CONVERSION_ID, false);
		builder.define(DATA_POTION, PotionContents.EMPTY);
	}

	public static AttributeSupplier.Builder createAttributes() {
		return Monster.createMonsterAttributes().add(Attributes.MAX_HEALTH, 24.0D).add(Attributes.ATTACK_DAMAGE, 3.0F).add(Attributes.MOVEMENT_SPEED, (double) 0.3F).add(Attributes.ARMOR, 10.0F).add(Attributes.FOLLOW_RANGE, 18.0F);
	}

	public void setPotionContents(PotionContents p_330869_) {
		this.entityData.set(DATA_POTION, p_330869_);
	}

	public PotionContents getPotionContents() {
		return this.entityData.get(DATA_POTION);
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
		if (this.level().isClientSide()) {
			for (int j = 0; j < 2; j++) {
				int i = FastColor.ARGB32.opaque(this.getPotionContents().getColor());
				double d0 = this.getX();
				double d1 = this.getY();
				double d2 = this.getZ();

				this.level().addParticle(ColorParticleOption.create(DEFAULT_PARTICLE.getType(), i), d0, d1, d2, (0.5 - this.random.nextDouble()) * 0.15, 0.01F, (0.5 - this.random.nextDouble()) * 0.15);
			}
		}
		super.tick();
	}

	@Override
	public void addAdditionalSaveData(CompoundTag p_149836_) {
		super.addAdditionalSaveData(p_149836_);
		RegistryOps<Tag> registryops = this.registryAccess().createSerializationContext(NbtOps.INSTANCE);

		if (!this.getPotionContents().equals(PotionContents.EMPTY)) {
			Tag tag = PotionContents.CODEC.encodeStart(registryops, this.getPotionContents()).getOrThrow();
			p_149836_.put("potion_contents", tag);
		}
	}

	@Override
	public void readAdditionalSaveData(CompoundTag p_149833_) {
		super.readAdditionalSaveData(p_149833_);
		RegistryOps<Tag> registryops = this.registryAccess().createSerializationContext(NbtOps.INSTANCE);
		if (p_149833_.contains("potion_contents")) {
			PotionContents.CODEC
					.parse(registryops, p_149833_.get("potion_contents"))
					.resultOrPartial(p_340707_ -> EarthMobsMod.LOGGER.warn("Failed to parse potions: '{}'", p_340707_))
					.ifPresent(this::setPotionContents);
		}
	}

	public boolean canFreeze() {
		return false;
	}

	@Override
	public boolean isInvertedHealAndHarm() {
		return true;
	}

	public @Nullable SpawnGroupData finalizeSpawn(ServerLevelAccessor p_478692_, DifficultyInstance p_479487_, MobSpawnType p_481376_, @Nullable SpawnGroupData p_480575_) {
		p_480575_ = super.finalizeSpawn(p_478692_, p_479487_, p_481376_, p_480575_);
		RandomSource randomsource = p_478692_.getRandom();

		if (randomsource.nextFloat() < 0.01F) {
			int i = randomsource.nextInt(4);
			MobEffectInstance mobEffect;

			if (i == 0) {
				mobEffect = new MobEffectInstance(ModEffects.UNDEAD_BODY, 400, 0);

			} else if (i == 1) {
				mobEffect = new MobEffectInstance(ModEffects.ZOMBIFIED, 200, 0);

			} else if (i == 2) {
				mobEffect = new MobEffectInstance(MobEffects.SLOW_FALLING, 200, 0);
			} else if (i == 3) {
				mobEffect = new MobEffectInstance(MobEffects.DAMAGE_BOOST, 1, 0);
			} else {
				mobEffect = new MobEffectInstance(MobEffects.POISON, 60, 0);
			}

			this.setPotionContents(this.getPotionContents().withEffectAdded(mobEffect));
		}

		return p_480575_;
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

	@Override
	public void performRangedAttack(LivingEntity p_29912_, float p_29913_) {
		BoneShard bone = new BoneShard(this.level(), this);
        double x = p_29912_.getX() - this.getX();
        double y = p_29912_.getEyeY() - this.getEyeY();
        double z = p_29912_.getZ() - this.getZ();
        double length = Math.sqrt(x * x + z * z);
		bone.shoot(x, y + (length * 0.275F), z, 0.75F, 2.0F);
		PotionContents collection = this.getPotionContents();
		if (collection.hasEffects()) {
			for (MobEffectInstance mobEffectInstance : this.getPotionContents().getAllEffects()) {
				bone.addEffect(new MobEffectInstance(mobEffectInstance.getEffect(), mobEffectInstance.getDuration(), 0));
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
