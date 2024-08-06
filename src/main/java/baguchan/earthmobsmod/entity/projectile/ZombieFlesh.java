package baguchan.earthmobsmod.entity.projectile;

import baguchan.earthmobsmod.registry.ModEntities;
import com.google.common.collect.Sets;
import net.minecraft.core.Vec3i;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.particles.ColorParticleOption;
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
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;

import java.util.Set;

public class ZombieFlesh extends ThrowableItemProjectile {
	private static final EntityDataAccessor<Boolean> DATA_DROWNED = SynchedEntityData.defineId(ZombieFlesh.class, EntityDataSerializers.BOOLEAN);

	private static final EntityDataAccessor<Integer> ID_EFFECT_COLOR = SynchedEntityData.defineId(ZombieFlesh.class, EntityDataSerializers.INT);


	protected final Set<MobEffectInstance> effects = Sets.newHashSet();


	public ZombieFlesh(EntityType<? extends ZombieFlesh> p_37391_, Level p_37392_) {
		super(p_37391_, p_37392_);
	}

	public ZombieFlesh(Level p_37399_, LivingEntity p_37400_) {
		super(ModEntities.ZOMBIE_FLESH.get(), p_37400_, p_37399_);
	}

	public ZombieFlesh(Level p_37394_, double p_37395_, double p_37396_, double p_37397_) {
		super(ModEntities.ZOMBIE_FLESH.get(), p_37395_, p_37396_, p_37397_, p_37394_);
	}

	@Override
	protected void defineSynchedData(SynchedEntityData.Builder builder) {
		super.defineSynchedData(builder);
		builder.define(ID_EFFECT_COLOR, -1);
		builder.define(DATA_DROWNED, false);
	}

	private void updateColor() {
		PotionContents potioncontents = this.getPotionContents();
		this.entityData.set(ID_EFFECT_COLOR, potioncontents.equals(PotionContents.EMPTY) ? -1 : potioncontents.getColor());
	}

	public void addEffect(MobEffectInstance p_36871_) {
		this.setPotionContents(this.getPotionContents().withEffectAdded(p_36871_));
	}

	protected Item getDefaultItem() {
		return Items.ROTTEN_FLESH;
	}

	private ParticleOptions getParticle() {
		return new ItemParticleOption(ParticleTypes.ITEM, Items.ROTTEN_FLESH.getDefaultInstance());
	}

	public int getColor() {
		return this.entityData.get(ID_EFFECT_COLOR);
	}

	public PotionContents getPotionContents() {
		return this.getItem().getOrDefault(DataComponents.POTION_CONTENTS, PotionContents.EMPTY);
	}

	public void setPotionContents(PotionContents p_331534_) {
		this.getItem().set(DataComponents.POTION_CONTENTS, p_331534_);
		this.updateColor();
	}

	public void handleEntityEvent(byte p_37402_) {
		if (p_37402_ == 0) {
			int i = this.getColor();
			if (i != -1) {
				float f = (float) (i >> 16 & 0xFF) / 255.0F;
				float f1 = (float) (i >> 8 & 0xFF) / 255.0F;
				float f2 = (float) (i >> 0 & 0xFF) / 255.0F;

				for (int j = 0; j < 20; j++) {
					this.level()
							.addParticle(
									ColorParticleOption.create(ParticleTypes.ENTITY_EFFECT, f, f1, f2),
									this.getRandomX(0.5),
									this.getRandomY(),
									this.getRandomZ(0.5),
									0.0,
									0.0,
									0.0
							);
				}
			}
		} else if (p_37402_ == 3) {
			ParticleOptions particleoptions = this.getParticle();

			for (int i = 0; i < 8; ++i) {
				this.level().addParticle(particleoptions, this.getX(), this.getY(), this.getZ(), 0.0D, 0.0D, 0.0D);
			}
		} else {
			super.handleEntityEvent(p_37402_);
		}
	}

	@Override
	public void tick() {
		super.tick();
		if (this.level().isClientSide) {
			this.makeParticle(2);
		}

		if (this.isInWater() && this.isDrowned()) {
			this.setDeltaMovement(this.getDeltaMovement().scale(1.1F));
		}
	}

	private void makeParticle(int p_36877_) {
		int i = this.getColor();
		if (i != -1 && p_36877_ > 0) {
			for (int j = 0; j < p_36877_; j++) {
				this.level()
						.addParticle(
								ColorParticleOption.create(ParticleTypes.ENTITY_EFFECT, i),
								this.getRandomX(0.5),
								this.getRandomY(),
								this.getRandomZ(0.5),
								0.0,
								0.0,
								0.0
						);
			}
		}
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
}