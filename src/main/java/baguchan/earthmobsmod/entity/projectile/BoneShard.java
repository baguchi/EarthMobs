package baguchan.earthmobsmod.entity.projectile;

import baguchan.earthmobsmod.registry.ModEntities;
import baguchan.earthmobsmod.registry.ModItems;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.particles.ColorParticleOption;
import net.minecraft.core.particles.ItemParticleOption;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

public class BoneShard extends ThrowableItemProjectile {
	private static final EntityDataAccessor<Integer> ID_EFFECT_COLOR = SynchedEntityData.defineId(BoneShard.class, EntityDataSerializers.INT);

	public BoneShard(EntityType<? extends BoneShard> p_37391_, Level p_37392_) {
		super(p_37391_, p_37392_);
	}

	public BoneShard(Level p_37399_, LivingEntity p_37400_, ItemStack p_363259_) {
		super(ModEntities.BONE_SHARD.get(), p_37400_, p_37399_, p_363259_);
	}

	public BoneShard(Level p_37394_, double p_37395_, double p_37396_, double p_37397_, ItemStack p_363259_) {
		super(ModEntities.BONE_SHARD.get(), p_37395_, p_37396_, p_37397_, p_37394_, p_363259_);
	}

	public BoneShard(EntityType<? extends BoneShard> entity, double x, double y, double z, Level level, ItemStack p_363259_) {
		super(entity, x, y, z, level, p_363259_);
	}

	public BoneShard(EntityType<? extends BoneShard> entity, Level level, LivingEntity livingEntity, ItemStack p_363259_) {
		super(entity, livingEntity, level, p_363259_);
	}

	protected Item getDefaultItem() {
		return ModItems.BONE_SHARD.get();
	}

	private ParticleOptions getParticle() {
		return new ItemParticleOption(ParticleTypes.ITEM, ModItems.BONE_SHARD.get().getDefaultInstance());
	}

	private void updateColor() {
		PotionContents potioncontents = this.getPotionContents();
		this.entityData.set(ID_EFFECT_COLOR, potioncontents.equals(PotionContents.EMPTY) ? -1 : potioncontents.getColor());
	}

	public void addEffect(MobEffectInstance p_36871_) {
		this.setPotionContents(this.getPotionContents().withEffectAdded(p_36871_));
	}

	@Override
	protected void defineSynchedData(SynchedEntityData.Builder p_326324_) {
		super.defineSynchedData(p_326324_);
		p_326324_.define(ID_EFFECT_COLOR, -1);
	}


	@Override
	public void handleEntityEvent(byte p_36869_) {
		if (p_36869_ == 0) {
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
		} else {
			super.handleEntityEvent(p_36869_);
		}
	}

	@Override
	public void tick() {
		super.tick();
		if (this.level().isClientSide) {
			this.makeParticle(2);
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

	protected void onHitEntity(EntityHitResult p_37404_) {
        Entity entity = p_37404_.getEntity();
        Vec3 projectileMovement = this.getDeltaMovement();

        int damage = Mth.ceil((3 * projectileMovement.length()));
		if (damage > 0 && this.level() instanceof ServerLevel serverLevel) {
			if (entity.hurtServer(serverLevel, this.damageSources().thrown(this, this.getOwner()), damage)) {

                if (entity instanceof LivingEntity) {
					for (MobEffectInstance mobeffectinstance : this.getPotionContents().getAllEffects()) {
                        ((LivingEntity) entity).addEffect(new MobEffectInstance(mobeffectinstance.getEffect(), Math.max(mobeffectinstance.getDuration() / 8, 1), mobeffectinstance.getAmplifier(), mobeffectinstance.isAmbient(), mobeffectinstance.isVisible()), entity);
                    }
                }
            }
		}
	}

	protected void onHit(HitResult p_37488_) {
		super.onHit(p_37488_);
		if (!this.level().isClientSide) {
			this.level().broadcastEntityEvent(this, (byte) 3);
			this.playSound(SoundEvents.TURTLE_EGG_BREAK, 0.4F, 1.25F);
			this.discard();
		}

	}

}