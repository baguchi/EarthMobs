package baguchan.earthmobsmod.entity.projectile;

import baguchan.earthmobsmod.registry.ModEntities;
import baguchan.earthmobsmod.registry.ModItems;
import net.minecraft.core.particles.ItemParticleOption;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;

public class StrayBoneShard extends BoneShard {

    public StrayBoneShard(EntityType<? extends StrayBoneShard> p_37391_, Level p_37392_) {
        super(p_37391_, p_37392_);
    }

    public StrayBoneShard(Level p_37399_, LivingEntity p_37400_) {
        super(ModEntities.STRAY_BONE_SHARD.get(), p_37399_, p_37400_);
    }

    public StrayBoneShard(Level p_37394_, double p_37395_, double p_37396_, double p_37397_) {
        super(ModEntities.STRAY_BONE_SHARD.get(), p_37395_, p_37396_, p_37397_, p_37394_);
    }

    protected Item getDefaultItem() {
        return ModItems.BONE_SHARD.get();
    }

    private ParticleOptions getParticle() {
        return new ItemParticleOption(ParticleTypes.ITEM, ModItems.BONE_SHARD.get().getDefaultInstance());
    }

    public void handleEntityEvent(byte p_37402_) {
        if (p_37402_ == 3) {
            ParticleOptions particleoptions = this.getParticle();

            for (int i = 0; i < 8; ++i) {
                this.level.addParticle(particleoptions, this.getX(), this.getY(), this.getZ(), 0.0D, 0.0D, 0.0D);
            }
        }
        if (p_37402_ == 4) {
            this.level.addParticle(ParticleTypes.SNOWFLAKE, this.getX(), this.getY(), this.getZ(), 0.0D, 0.0D, 0.0D);
        }
    }

    public void tick() {
        super.tick();
        if (!this.level.isClientSide) {
            this.level.broadcastEntityEvent(this, (byte) 4);
        }
    }

    protected ItemStack getPickupItem() {
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
        if (entity.hurt(DamageSource.thrown(this, this.getOwner()), 2)) {
            if (entity instanceof LivingEntity) {
                for (MobEffectInstance mobeffectinstance : this.potion.getEffects()) {
                    ((LivingEntity) entity).addEffect(new MobEffectInstance(mobeffectinstance.getEffect(), Math.max(mobeffectinstance.getDuration() / 8, 1), mobeffectinstance.getAmplifier(), mobeffectinstance.isAmbient(), mobeffectinstance.isVisible()), entity);
                }

                if (!this.effects.isEmpty()) {
                    for (MobEffectInstance mobeffectinstance1 : this.effects) {
                        ((LivingEntity) entity).addEffect(mobeffectinstance1, entity);
                    }
                }
                entity.setTicksFrozen(getTicksFrozen() + 200);
            }
        }
    }
}