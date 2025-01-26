package baguchan.earthmobsmod.entity.projectile;

import baguchan.earthmobsmod.registry.ModEntities;
import baguchan.earthmobsmod.registry.ModItems;
import net.minecraft.core.particles.ItemParticleOption;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;

public class StrayBoneShard extends BoneShard {

    public StrayBoneShard(EntityType<? extends StrayBoneShard> p_37391_, Level p_37392_) {
        super(p_37391_, p_37392_);
    }

    public StrayBoneShard(Level p_37399_, LivingEntity p_37400_, ItemStack p_363259_) {
        super(ModEntities.STRAY_BONE_SHARD.get(), p_37399_, p_37400_, p_363259_);
    }

    public StrayBoneShard(Level p_37394_, double p_37395_, double p_37396_, double p_37397_, ItemStack p_363259_) {
        super(ModEntities.STRAY_BONE_SHARD.get(), p_37395_, p_37396_, p_37397_, p_37394_, p_363259_);
    }

    protected Item getDefaultItem() {
        return ModItems.BONE_SHARD.get();
    }

    private ParticleOptions getParticle() {
        return new ItemParticleOption(ParticleTypes.ITEM, ModItems.BONE_SHARD.get().getDefaultInstance());
    }

    public void handleEntityEvent(byte p_37402_) {
        super.handleEntityEvent(p_37402_);
        if (p_37402_ == 4) {
            this.level().addParticle(ParticleTypes.SNOWFLAKE, this.getX(), this.getY(), this.getZ(), 0.0D, 0.0D, 0.0D);
        }
    }

    public void tick() {
        super.tick();
        if (!this.level().isClientSide) {
            this.level().broadcastEntityEvent(this, (byte) 4);
        }
    }

    protected void onHitEntity(EntityHitResult p_37404_) {
        Entity entity = p_37404_.getEntity();
        if (this.level() instanceof ServerLevel serverLevel && entity.hurtServer(serverLevel, this.damageSources().thrown(this, this.getOwner()), 2)) {
            if (entity instanceof LivingEntity) {
                for (MobEffectInstance mobeffectinstance : this.getPotionContents().getAllEffects()) {
                    ((LivingEntity) entity).addEffect(new MobEffectInstance(mobeffectinstance.getEffect(), Math.max(mobeffectinstance.getDuration() / 8, 1), mobeffectinstance.getAmplifier(), mobeffectinstance.isAmbient(), mobeffectinstance.isVisible()), entity);
                }
                entity.setTicksFrozen(getTicksFrozen() + 200);
            }
        }
    }
}