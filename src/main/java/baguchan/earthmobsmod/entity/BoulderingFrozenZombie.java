package baguchan.earthmobsmod.entity;

import baguchan.earthmobsmod.registry.ModEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;

public class BoulderingFrozenZombie extends BoulderingZombie {
    public BoulderingFrozenZombie(EntityType<? extends BoulderingFrozenZombie> p_34271_, Level p_34272_) {
        super(p_34271_, p_34272_);
        this.xpReward = 5;
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes().add(Attributes.FOLLOW_RANGE, 35.0D).add(Attributes.MOVEMENT_SPEED, (double) 0.215F).add(Attributes.ATTACK_DAMAGE, 4.0D).add(Attributes.ARMOR, 4.0D).add(Attributes.ARMOR_TOUGHNESS, 2.0D).add(Attributes.KNOCKBACK_RESISTANCE, 0.5D).add(Attributes.SPAWN_REINFORCEMENTS_CHANCE);
    }

    @Override
    protected void doUnderWaterConversion() {
        this.convertToZombieType(ModEntities.BOULDERING_DROWNED.get());
        if (!this.isSilent()) {
            this.level().levelEvent((Player) null, 1040, this.blockPosition(), 0);
        }
    }

    @Override
    public boolean doHurtTarget(Entity entity) {
        boolean flag = super.doHurtTarget(entity);
        if (flag && entity instanceof LivingEntity) {
            float f = this.level().getCurrentDifficultyAt(this.blockPosition()).getEffectiveDifficulty();
            ((LivingEntity) entity).setTicksFrozen((int) (entity.getTicksFrozen() + 40 + 120 * f));
        }

        return flag;
    }

    public static boolean checkFrozenZombieSpawnRules(
            EntityType<BoulderingFrozenZombie> husk, ServerLevelAccessor level, MobSpawnType spawnType, BlockPos pos, RandomSource random
    ) {
        return checkMonsterSpawnRules(husk, level, spawnType, pos, random)
                && (MobSpawnType.isSpawner(spawnType) || level.canSeeSky(pos));
    }
}
