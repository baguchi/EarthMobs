package baguchan.earthmobsmod.entity;

import baguchan.earthmobsmod.entity.projectile.ZombieFlesh;
import baguchan.earthmobsmod.registry.ModEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;

public class LobberHusk extends LobberZombie {
    public LobberHusk(EntityType<? extends LobberHusk> p_34271_, Level p_34272_) {
        super(p_34271_, p_34272_);
        this.xpReward = 5;
    }

    public static boolean checkHuskSpawnRules(
            EntityType<LobberHusk> husk, ServerLevelAccessor level, EntitySpawnReason spawnType, BlockPos pos, RandomSource random
    ) {
        return checkMonsterSpawnRules(husk, level, spawnType, pos, random)
                && (EntitySpawnReason.isSpawner(spawnType) || level.canSeeSky(pos));
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes().add(Attributes.FOLLOW_RANGE, 35.0D).add(Attributes.MAX_HEALTH, 24.0D).add(Attributes.MOVEMENT_SPEED, (double) 0.22F).add(Attributes.ATTACK_DAMAGE, 3.0D).add(Attributes.ARMOR, 2.0D).add(Attributes.SPAWN_REINFORCEMENTS_CHANCE);
    }

    @Override
    protected boolean isSunSensitive() {
        return false;
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return SoundEvents.HUSK_AMBIENT;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSource) {
        return SoundEvents.HUSK_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.HUSK_DEATH;
    }

    @Override
    protected SoundEvent getStepSound() {
        return SoundEvents.HUSK_STEP;
    }

    @Override
    public boolean doHurtTarget(ServerLevel serverLevel, Entity entity) {
        boolean flag = super.doHurtTarget(serverLevel, entity);
        if (flag && this.getMainHandItem().isEmpty() && entity instanceof LivingEntity) {
            float f = this.level().getCurrentDifficultyAt(this.blockPosition()).getEffectiveDifficulty();
            ((LivingEntity) entity).addEffect(new MobEffectInstance(MobEffects.HUNGER, 140 * (int) f), this);
        }

        return flag;
    }

    @Override
    protected void doUnderWaterConversion() {
        this.convertToZombieType(ModEntities.LOBBER_ZOMBIE.get());
        if (!this.isSilent()) {
            this.level().levelEvent((Player) null, 1041, this.blockPosition(), 0);
        }
    }

    @Override
    public void performRangedAttack(LivingEntity p_29912_, float p_29913_) {
        ZombieFlesh zombieFlesh = new ZombieFlesh(this.level(), this, Items.ROTTEN_FLESH.getDefaultInstance());
        double d0 = p_29912_.getEyeY() - this.getEyeY();
        double d1 = p_29912_.getX() - this.getX();
        double d3 = p_29912_.getZ() - this.getZ();
        double d4 = Math.sqrt(d1 * d1 + d3 * d3) * (double) 0.1F;
        float f = this.level().getCurrentDifficultyAt(this.blockPosition()).getEffectiveDifficulty();
        zombieFlesh.shoot(d1, d0 + d4, d3, 0.8F, 0.1F);
        zombieFlesh.addEffect(new MobEffectInstance(MobEffects.HUNGER, (int) (40 + 80 * f)));
        this.playSound(SoundEvents.SNOW_GOLEM_SHOOT, 1.0F, 0.4F / (this.getRandom().nextFloat() * 0.4F + 0.8F));
        this.level().addFreshEntity(zombieFlesh);
    }
}
