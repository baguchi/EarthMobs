package baguchan.earthmobsmod.entity.projectile;

import baguchan.earthmobsmod.registry.ModEntities;
import net.minecraft.core.Vec3i;
import net.minecraft.core.particles.ItemParticleOption;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;

public class ZombieFlesh extends ThrowableItemProjectile {
	private static final EntityDataAccessor<Boolean> DATA_DROWNED = SynchedEntityData.defineId(ZombieFlesh.class, EntityDataSerializers.BOOLEAN);


	public ZombieFlesh(EntityType<? extends ZombieFlesh> p_37391_, Level p_37392_) {
		super(p_37391_, p_37392_);
	}

	public ZombieFlesh(Level p_37399_, LivingEntity p_37400_) {
		super(ModEntities.ZOMBIE_FLESH.get(), p_37400_, p_37399_);
	}

	public ZombieFlesh(Level p_37394_, double p_37395_, double p_37396_, double p_37397_) {
		super(ModEntities.ZOMBIE_FLESH.get(), p_37395_, p_37396_, p_37397_, p_37394_);
	}

	protected Item getDefaultItem() {
		return Items.ROTTEN_FLESH;
	}

	private ParticleOptions getParticle() {
		return new ItemParticleOption(ParticleTypes.ITEM, Items.ROTTEN_FLESH.getDefaultInstance());
	}

	public void handleEntityEvent(byte p_37402_) {
		if (p_37402_ == 3) {
			ParticleOptions particleoptions = this.getParticle();

			for (int i = 0; i < 8; ++i) {
				this.level().addParticle(particleoptions, this.getX(), this.getY(), this.getZ(), 0.0D, 0.0D, 0.0D);
			}
		}

	}

	protected void defineSynchedData() {
		super.defineSynchedData();
		this.entityData.define(DATA_DROWNED, false);
	}

	public void setDrowned(boolean flag) {
		this.entityData.set(DATA_DROWNED, flag);
	}

	public boolean isDrowned() {
		return this.entityData.get(DATA_DROWNED);
	}

	public void addAdditionalSaveData(CompoundTag p_36881_) {
		super.addAdditionalSaveData(p_36881_);
		p_36881_.putBoolean("Drowned", this.isDrowned());
	}

	public void readAdditionalSaveData(CompoundTag p_36875_) {
		super.readAdditionalSaveData(p_36875_);
		this.setDrowned(p_36875_.getBoolean("Drowned"));
	}

	protected void onHitEntity(EntityHitResult p_37404_) {
		super.onHitEntity(p_37404_);
		Entity entity = p_37404_.getEntity();
		Vec3 projectileMovement = this.getDeltaMovement();

		int damage = Mth.ceil((3 * projectileMovement.length()));
		if (damage > 0) {
			if (entity.hurt(this.damageSources().thrown(this, this.getOwner()), damage)) {

				if (!this.level().isClientSide) {
					this.level().broadcastEntityEvent(this, (byte) 3);
					this.playSound(SoundEvents.SLIME_BLOCK_BREAK, 0.4F, 1.0F);
					this.discard();
				}
				if (entity instanceof LivingEntity living) {
					(living).addEffect(new MobEffectInstance(MobEffects.HUNGER, 60), entity);
				}
			} else {
				this.setDeltaMovement(projectileMovement.multiply(-0.8, -0.8, -0.8));
			}
		} else {
			this.setDeltaMovement(projectileMovement.multiply(-0.8, -0.8, -0.8));
		}
	}

	@Override
	protected void onHitBlock(BlockHitResult blockHitResult) {
		super.onHitBlock(blockHitResult);
		Vec3 projectileMovement = this.getDeltaMovement();
		if (projectileMovement.length() > 0.3) {
			Vec3i direction = blockHitResult.getDirection().getNormal();
			switch (blockHitResult.getDirection()) {
				case UP, SOUTH, EAST:
					direction = direction.multiply(-1);
				default:
			}
			direction = new Vec3i(direction.getX() == 0 ? 1 : direction.getX(), direction.getY() == 0 ? 1 : direction.getY(), direction.getZ() == 0 ? 1 : direction.getZ());
			this.setDeltaMovement(projectileMovement.multiply(new Vec3(direction.getX(), direction.getY(), direction.getZ())).multiply(0.75, 0.65, 0.75));
			this.playSound(SoundEvents.SLIME_BLOCK_BREAK, 0.4F, 1.0F);
		} else {
			if (!this.level().isClientSide) {
				this.level().broadcastEntityEvent(this, (byte) 3);
				this.playSound(SoundEvents.SLIME_BLOCK_BREAK, 0.4F, 1.0F);
				this.discard();
			}
		}
	}

	@Override
	public void tick() {
		super.tick();

		if (this.isInWater() && this.isDrowned()) {
			this.setDeltaMovement(this.getDeltaMovement().scale(1.125F));
		}
	}

	@Override
	protected float getGravity() {
		return this.isInWater() && this.isDrowned() ? 0.01F : super.getGravity();
	}
}