package baguchan.earthmobsmod.entity.projectile;

import baguchan.earthmobsmod.registry.ModEntities;
import baguchan.earthmobsmod.registry.ModItems;
import com.google.common.collect.Sets;
import net.minecraft.core.particles.ItemParticleOption;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

import java.util.Set;

public class BoneShard extends ThrowableItemProjectile {
	protected Potion potion = Potions.EMPTY;
	protected final Set<MobEffectInstance> effects = Sets.newHashSet();

	private static final EntityDataAccessor<Integer> ID_EFFECT_COLOR = SynchedEntityData.defineId(BoneShard.class, EntityDataSerializers.INT);

	public BoneShard(EntityType<? extends BoneShard> p_37391_, Level p_37392_) {
		super(p_37391_, p_37392_);
	}

	public BoneShard(Level p_37399_, LivingEntity p_37400_) {
		super(ModEntities.BONE_SHARD.get(), p_37400_, p_37399_);
	}

	public BoneShard(Level p_37394_, double p_37395_, double p_37396_, double p_37397_) {
		super(ModEntities.BONE_SHARD.get(), p_37395_, p_37396_, p_37397_, p_37394_);
	}

	public BoneShard(EntityType<? extends BoneShard> entity, double x, double y, double z, Level level) {
		super(entity, x, y, z, level);
	}

	public BoneShard(EntityType<? extends BoneShard> entity, Level level, LivingEntity livingEntity) {
		super(entity, livingEntity, level);
	}

	protected Item getDefaultItem() {
		return ModItems.BONE_SHARD.get();
	}

	private ParticleOptions getParticle() {
		return new ItemParticleOption(ParticleTypes.ITEM, ModItems.BONE_SHARD.get().getDefaultInstance());
	}

	private void makeParticle(int p_36877_) {
		int i = this.getColor();
		if (i != -1 && p_36877_ > 0) {
			double d0 = (double) (i >> 16 & 255) / 255.0D;
			double d1 = (double) (i >> 8 & 255) / 255.0D;
			double d2 = (double) (i >> 0 & 255) / 255.0D;

			for (int j = 0; j < p_36877_; ++j) {
				this.level().addParticle(ParticleTypes.ENTITY_EFFECT, this.getRandomX(0.5D), this.getRandomY(), this.getRandomZ(0.5D), d0, d1, d2);
			}

		}
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
		this.entityData.define(ID_EFFECT_COLOR, -1);
	}

	private void updateColor() {
		if (this.potion == Potions.EMPTY && this.effects.isEmpty()) {
			this.entityData.set(ID_EFFECT_COLOR, -1);
		} else {
			this.entityData.set(ID_EFFECT_COLOR, PotionUtils.getColor(PotionUtils.getAllEffects(this.potion, this.effects)));
		}

	}

	public void addEffect(MobEffectInstance p_36871_) {
		this.effects.add(p_36871_);
		this.getEntityData().set(ID_EFFECT_COLOR, PotionUtils.getColor(PotionUtils.getAllEffects(this.potion, this.effects)));
	}

	public void tick() {
		super.tick();
		if (this.level().isClientSide) {
			if (this.onGround()) {
			} else {
				this.makeParticle(2);
			}
		}
	}

	public int getColor() {
		return this.entityData.get(ID_EFFECT_COLOR);
	}

	public void addAdditionalSaveData(CompoundTag p_36881_) {
		super.addAdditionalSaveData(p_36881_);
		if (this.potion != Potions.EMPTY) {
			p_36881_.putString("Potion", BuiltInRegistries.POTION.getKey(this.potion).toString());
		}

		if (!this.effects.isEmpty()) {
			ListTag listtag = new ListTag();

			for (MobEffectInstance mobeffectinstance : this.effects) {
				listtag.add(mobeffectinstance.save(new CompoundTag()));
			}

			p_36881_.put("CustomPotionEffects", listtag);
		}

	}

	public void readAdditionalSaveData(CompoundTag p_36875_) {
		super.readAdditionalSaveData(p_36875_);
		if (p_36875_.contains("Potion", 8)) {
            this.potion = PotionUtils.getPotion(p_36875_);
        }

        for (MobEffectInstance mobeffectinstance : PotionUtils.getCustomEffects(p_36875_)) {
            this.addEffect(mobeffectinstance);
        }

        this.updateColor();
    }

    @Nullable
    @Override
    public ItemStack getPickResult() {
        if (this.effects.isEmpty() && this.potion == Potions.EMPTY) {
            return new ItemStack(ModItems.BONE_SHARD.get());
        } else {
            ItemStack itemstack = new ItemStack(ModItems.BONE_SHARD.get());
            PotionUtils.setPotion(itemstack, this.potion);
            PotionUtils.setCustomEffects(itemstack, this.effects);

            return itemstack;
        }
    }

	protected void onHitEntity(EntityHitResult p_37404_) {
        Entity entity = p_37404_.getEntity();
        Vec3 projectileMovement = this.getDeltaMovement();

        int damage = Mth.ceil((3 * projectileMovement.length()));
        if (damage > 0) {
            if (entity.hurt(this.damageSources().thrown(this, this.getOwner()), damage)) {

                if (entity instanceof LivingEntity) {
                    for (MobEffectInstance mobeffectinstance : this.potion.getEffects()) {
                        ((LivingEntity) entity).addEffect(new MobEffectInstance(mobeffectinstance.getEffect(), Math.max(mobeffectinstance.getDuration() / 8, 1), mobeffectinstance.getAmplifier(), mobeffectinstance.isAmbient(), mobeffectinstance.isVisible()), entity);
                    }

                    if (!this.effects.isEmpty()) {
                        for (MobEffectInstance mobeffectinstance1 : this.effects) {
                            ((LivingEntity) entity).addEffect(mobeffectinstance1, entity);
                        }
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