package baguchan.earthmobsmod.entity;

import baguchan.earthmobsmod.entity.goal.FollowOwnerEvenFlyGoal;
import baguchan.earthmobsmod.entity.goal.SitEvenFlying;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.players.OldUsersConverter;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.FlyingMoveControl;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.navigation.FlyingPathNavigation;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.SmallFireball;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraft.world.phys.Vec3;

import javax.annotation.Nullable;
import java.util.Optional;
import java.util.UUID;

public class BabyGhast extends PathfinderMob {
	private static final EntityDataAccessor<Boolean> DATA_IS_CHARGING = SynchedEntityData.defineId(BabyGhast.class, EntityDataSerializers.BOOLEAN);

	protected static final EntityDataAccessor<Optional<UUID>> DATA_OWNERUUID_ID = SynchedEntityData.defineId(BabyGhast.class, EntityDataSerializers.OPTIONAL_UUID);
	protected static final EntityDataAccessor<Boolean> DATA_SITTING = SynchedEntityData.defineId(BabyGhast.class, EntityDataSerializers.BOOLEAN);

	public final AnimationState idleAnimationState = new AnimationState();
	public final AnimationState shootAnimationState = new AnimationState();

	public BabyGhast(EntityType<? extends BabyGhast> p_21803_, Level p_21804_) {
		super(p_21803_, p_21804_);
		this.xpReward = 0;
		this.moveControl = new FlyingMoveControl(this, 20, true);
		this.setPathfindingMalus(BlockPathTypes.DANGER_FIRE, 0.0F);
		this.setPathfindingMalus(BlockPathTypes.DAMAGE_FIRE, 0.0F);
	}

	public static AttributeSupplier.Builder createAttributes() {
		return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 6.0D).add(Attributes.FLYING_SPEED, (double) 0.14F).add(Attributes.MOVEMENT_SPEED, (double) 0.14F).add(Attributes.ATTACK_DAMAGE, 2.0D).add(Attributes.FOLLOW_RANGE, 48.0D);
	}

	@Override
	public void travel(Vec3 p_218382_) {
		if (this.isEffectiveAi() || this.isControlledByLocalInstance()) {
			if (this.isInWater()) {
				this.moveRelative(0.02F, p_218382_);
				this.move(MoverType.SELF, this.getDeltaMovement());
				this.setDeltaMovement(this.getDeltaMovement().scale((double) 0.8F));
			} else if (this.isInLava()) {
				this.moveRelative(0.02F, p_218382_);
				this.move(MoverType.SELF, this.getDeltaMovement());
				this.setDeltaMovement(this.getDeltaMovement().scale(0.5D));
			} else {
				this.moveRelative(this.getSpeed(), p_218382_);
				this.move(MoverType.SELF, this.getDeltaMovement());
				this.setDeltaMovement(this.getDeltaMovement().scale((double) 0.91F));
			}
		}

		this.calculateEntityAnimation(false);
	}

	protected PathNavigation createNavigation(Level p_218342_) {
		FlyingPathNavigation flyingpathnavigation = new FlyingPathNavigation(this, p_218342_);
		flyingpathnavigation.setCanOpenDoors(false);
		flyingpathnavigation.setCanFloat(true);
		flyingpathnavigation.setCanPassDoors(true);
		return flyingpathnavigation;
	}

	protected void registerGoals() {
		this.goalSelector.addGoal(1, new FloatGoal(this));
		this.goalSelector.addGoal(2, new SitEvenFlying(this));
		this.goalSelector.addGoal(3, new GhastShootFireballGoal(this));
		this.goalSelector.addGoal(4, new PanicGoal(this, 1.25D));
		this.goalSelector.addGoal(5, new FollowOwnerEvenFlyGoal(this, 1.15D, 6.0F, 2.0F, true));
		this.goalSelector.addGoal(6, new WaterAvoidingRandomFlyingGoal(this, 0.9F));
		this.goalSelector.addGoal(7, new LookAtPlayerGoal(this, Player.class, 8.0F));
		this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
	}

	protected void defineSynchedData() {
		super.defineSynchedData();
		this.entityData.define(DATA_OWNERUUID_ID, Optional.empty());
		this.entityData.define(DATA_SITTING, false);
		this.entityData.define(DATA_IS_CHARGING, false);
	}

	@Override
	public void tick() {
		if (this.level().isClientSide()) {
			if (!this.isInSittingPose()) {
				this.idleAnimationState.startIfStopped(this.tickCount);
			}
		}
		super.tick();
	}

	public void addAdditionalSaveData(CompoundTag p_21819_) {
		super.addAdditionalSaveData(p_21819_);

		if (this.getOwnerUUID() != null) {
			p_21819_.putUUID("Owner", this.getOwnerUUID());
		}
		p_21819_.putBoolean("Sitting", this.isInSittingPose());
	}

	public void readAdditionalSaveData(CompoundTag p_21815_) {
		super.readAdditionalSaveData(p_21815_);
		UUID uuid;
		if (p_21815_.hasUUID("Owner")) {
			uuid = p_21815_.getUUID("Owner");
		} else {
			String s = p_21815_.getString("Owner");
			uuid = OldUsersConverter.convertMobOwnerIfNecessary(this.getServer(), s);
		}

		if (uuid != null) {
			this.setOwnerUUID(uuid);
		}

		this.setInSittingPose(p_21815_.getBoolean("Sitting"));
	}

	public UUID getOwnerUUID() {
		return this.entityData.get(DATA_OWNERUUID_ID).orElse((UUID) null);
	}

	public void setOwnerUUID(@javax.annotation.Nullable UUID p_21817_) {
		this.entityData.set(DATA_OWNERUUID_ID, Optional.ofNullable(p_21817_));
	}

	public boolean isInSittingPose() {
		return this.entityData.get(DATA_SITTING);
	}

	public void setInSittingPose(boolean p_21838_) {
		this.entityData.set(DATA_SITTING, p_21838_);
	}

	public boolean isCharging() {
		return this.entityData.get(DATA_IS_CHARGING);
	}

	public void setCharging(boolean p_32759_) {
		this.entityData.set(DATA_IS_CHARGING, p_32759_);
	}

	@Nullable
	public LivingEntity getOwner() {
		try {
			UUID uuid = this.getOwnerUUID();
			return uuid == null ? null : this.level().getPlayerByUUID(uuid);
		} catch (IllegalArgumentException illegalargumentexception) {
			return null;
		}
	}

	protected void spawnTamingParticles(boolean p_21835_) {
		ParticleOptions particleoptions = ParticleTypes.HEART;
		if (!p_21835_) {
			particleoptions = ParticleTypes.SMOKE;
		}

		for (int i = 0; i < 7; ++i) {
			double d0 = this.random.nextGaussian() * 0.02D;
			double d1 = this.random.nextGaussian() * 0.02D;
			double d2 = this.random.nextGaussian() * 0.02D;
			this.level().addParticle(particleoptions, this.getRandomX(1.0D), this.getRandomY() + 0.5D, this.getRandomZ(1.0D), d0, d1, d2);
		}

	}

	public void handleEntityEvent(byte p_21807_) {
		if (p_21807_ == 7) {
			this.spawnTamingParticles(true);
		} else if (p_21807_ == 6) {
			this.spawnTamingParticles(false);
		} else {
			super.handleEntityEvent(p_21807_);
		}

	}

	public InteractionResult mobInteract(Player p_30412_, InteractionHand p_30413_) {
		ItemStack itemstack = p_30412_.getItemInHand(p_30413_);
		Item item = itemstack.getItem();
		if (this.level().isClientSide) {
			boolean flag = this.getOwner() != null || (itemstack.is(Items.CRIMSON_NYLIUM) || itemstack.is(Items.CRIMSON_FUNGUS));
			return flag ? InteractionResult.CONSUME : InteractionResult.PASS;
		} else {
			if (this.getOwner() != null) {
				if ((itemstack.is(Items.CRIMSON_NYLIUM) || itemstack.is(Items.CRIMSON_FUNGUS)) && this.getHealth() < this.getMaxHealth()) {
					if (!p_30412_.getAbilities().instabuild) {
						itemstack.shrink(1);
					}
					this.playSound(SoundEvents.GENERIC_EAT);

					this.heal(2);
					this.gameEvent(GameEvent.ITEM_INTERACT_START);
					return InteractionResult.SUCCESS;
				}

				InteractionResult interactionresult = super.mobInteract(p_30412_, p_30413_);
				if ((!interactionresult.consumesAction() || this.isBaby()) && this.isOwnedBy(p_30412_)) {
					this.setInSittingPose(!this.isInSittingPose());
					this.navigation.stop();
					this.setTarget((LivingEntity) null);
					p_30412_.displayClientMessage(this.isInSittingPose() ? Component.translatable("entity.earthmobsmod.baby_ghast.sit", this.getDisplayName()) : Component.translatable("entity.earthmobsmod.baby_ghast.standing", this.getDisplayName()), true);
					return InteractionResult.SUCCESS;
				}

				return interactionresult;

			} else if ((itemstack.is(Items.CRIMSON_NYLIUM) || itemstack.is(Items.CRIMSON_FUNGUS))) {
				if (!p_30412_.getAbilities().instabuild) {
					itemstack.shrink(1);
				}

				if (this.random.nextInt(3) == 0) {
					this.setOwnerUUID(p_30412_.getUUID());
					this.navigation.stop();
					this.setTarget((LivingEntity) null);
					this.setInSittingPose(true);
					this.level().broadcastEntityEvent(this, (byte) 7);
				} else {
					this.level().broadcastEntityEvent(this, (byte) 6);
				}

				return InteractionResult.SUCCESS;
			}

			return super.mobInteract(p_30412_, p_30413_);
		}
	}

	@Override
	protected boolean shouldDespawnInPeaceful() {
		return false;
	}

	@Override
	public boolean removeWhenFarAway(double p_21542_) {
		return false;
	}

	public boolean canAttack(LivingEntity p_21822_) {
		return this.isOwnedBy(p_21822_) ? false : super.canAttack(p_21822_);
	}

	public boolean isOwnedBy(LivingEntity p_21831_) {
		return p_21831_ == this.getOwner();
	}

	public static boolean checkGhastSpawnRules(EntityType<? extends BabyGhast> p_33018_, ServerLevelAccessor p_33019_, MobSpawnType p_33020_, BlockPos p_33021_, RandomSource p_33022_) {
		return p_33019_.getBlockState(p_33021_.below()).is(BlockTags.NYLIUM) && checkMobSpawnRules(p_33018_, p_33019_, p_33020_, p_33021_, p_33022_);
	}

	@Override
	public boolean causeFallDamage(float p_147105_, float p_147106_, DamageSource p_147107_) {
		return false;
	}

	@Override
	protected void checkFallDamage(double p_20809_, boolean p_20810_, BlockState p_20811_, BlockPos p_20812_) {
	}

	protected SoundEvent getAmbientSound() {
		return SoundEvents.GHAST_AMBIENT;
	}

	protected SoundEvent getHurtSound(DamageSource p_32750_) {
		return SoundEvents.GHAST_SCREAM;
	}

	protected SoundEvent getDeathSound() {
		return SoundEvents.GHAST_DEATH;
	}

	@Override
	protected float getSoundVolume() {
		return 0.6F;
	}

	@Override
	public float getVoicePitch() {
		return this.random.nextFloat() - this.random.nextFloat() * 0.2F + 1.5F;
	}

	static class GhastShootFireballGoal extends Goal {
		private final BabyGhast ghast;
		public int chargeTime;

		public GhastShootFireballGoal(BabyGhast p_32776_) {
			this.ghast = p_32776_;
		}

		public boolean canUse() {
			return this.ghast.getTarget() != null;
		}

		public void start() {
			this.chargeTime = -20;
		}

		public void stop() {
			this.ghast.setCharging(false);
		}

		public boolean requiresUpdateEveryTick() {
			return true;
		}

		public void tick() {
			LivingEntity livingentity = this.ghast.getTarget();
			if (livingentity != null) {
				double d0 = 64.0D;
				if (livingentity.distanceToSqr(this.ghast) < 128.0D && this.ghast.hasLineOfSight(livingentity)) {
					Level level = this.ghast.level();
					++this.chargeTime;
					if (this.chargeTime == 10 && !this.ghast.isSilent()) {
						this.ghast.playSound(SoundEvents.GHAST_SCREAM, 0.6F, 1.25F);
					}

					this.ghast.lookControl.setLookAt(livingentity);

					if (this.chargeTime == 20) {
						double d1 = 4.0D;
						Vec3 vec3 = this.ghast.getViewVector(1.0F);
						double d2 = livingentity.getX() - (this.ghast.getX() + vec3.x * 0.65D);
						double d3 = livingentity.getEyeY() - (this.ghast.getEyeY());
						double d4 = livingentity.getZ() - (this.ghast.getZ() + vec3.z * 0.65D);
						if (!this.ghast.isSilent()) {
							this.ghast.playSound(SoundEvents.GHAST_SHOOT, 0.6F, 1.25F);
						}

						SmallFireball fireball = new SmallFireball(level, this.ghast, d2, d3, d4);
						fireball.setPos(this.ghast.getX() + vec3.x * 0.65D, this.ghast.getEyeY(), fireball.getZ() + vec3.z * 0.65D);
						level.addFreshEntity(fireball);
						this.chargeTime = -80;
					}
				} else if (this.chargeTime > 0) {
					--this.chargeTime;
				}

				this.ghast.setCharging(this.chargeTime > 10);
			}
		}
	}
}
