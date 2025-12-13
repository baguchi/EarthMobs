package baguchan.earthmobsmod.entity;

import baguchan.earthmobsmod.registry.ModEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Difficulty;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.PanicGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.rabbit.Rabbit;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.monster.zombie.Zombie;
import net.minecraft.world.entity.npc.villager.AbstractVillager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LightLayer;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

public class ZombifiedRabbit extends Rabbit implements Enemy {

    private int conversionTime;
    @javax.annotation.Nullable
    private UUID conversionStarter;
    public ZombifiedRabbit(EntityType<? extends Rabbit> p_29656_, Level p_29657_) {
        super(p_29656_, p_29657_);
        this.xpReward = 3;
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.getAvailableGoals().stream().map(it -> it.getGoal()).filter(it -> it instanceof PanicGoal || it instanceof AvoidEntityGoal<?>).findFirst().ifPresent(goal -> {
            this.goalSelector.removeGoal(goal);
        });
        this.goalSelector.addGoal(4, new RabbitAttackGoal(this));
        this.targetSelector.addGoal(1, (new HurtByTargetGoal(this)).setAlertOthers());
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, AbstractVillager.class, true));
    }

    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Rabbit.createAttributes().add(Attributes.MAX_HEALTH, 8.0D);
    }

    @Override
    public void addAdditionalSaveData(ValueOutput p_34397_) {
        super.addAdditionalSaveData(p_34397_);
    }

    @Override
    public void readAdditionalSaveData(ValueInput p_34387_) {
        super.readAdditionalSaveData(p_34387_);
    }

    @Override
    public @org.jspecify.annotations.Nullable SpawnGroupData finalizeSpawn(
            ServerLevelAccessor p_479493_, DifficultyInstance p_481210_, EntitySpawnReason p_482098_, @org.jspecify.annotations.Nullable SpawnGroupData p_481475_
    ) {
        if (p_482098_ == EntitySpawnReason.NATURAL) {
            if (p_479493_.getRandom().nextFloat() < 0.1F) {
                this.makeRider(p_479493_, p_481210_, p_482098_);
            }
        }
        //this.makeRider(p_479493_, p_481210_, p_482098_);

        return super.finalizeSpawn(p_479493_, p_481210_, p_482098_, p_481475_);
    }

    public void makeRider(ServerLevelAccessor p_479493_, DifficultyInstance p_481210_, EntitySpawnReason p_482098_) {
        Zombie zombie = EntityType.ZOMBIE.create(this.level(), EntitySpawnReason.JOCKEY);
        if (zombie != null) {
            zombie.setBaby(true);
            zombie.snapTo(this.getX(), this.getY(), this.getZ(), this.getYRot(), 0.0F);
            zombie.finalizeSpawn(p_479493_, p_481210_, p_482098_, null);
            zombie.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(Items.IRON_SPEAR));
            zombie.startRiding(this, false, false);
        }
    }

    public static boolean isDarkEnoughToSpawn(ServerLevelAccessor p_219010_, BlockPos p_219011_, RandomSource p_219012_) {
        if (p_219010_.getBrightness(LightLayer.SKY, p_219011_) > p_219012_.nextInt(32)) {
            return false;
        } else {
            DimensionType dimensiontype = p_219010_.dimensionType();
            int i = dimensiontype.monsterSpawnBlockLightLimit();
            if (i < 15 && p_219010_.getBrightness(LightLayer.BLOCK, p_219011_) > i) {
                return false;
            } else {
                int j = p_219010_.getLevel().isThundering() ? p_219010_.getMaxLocalRawBrightness(p_219011_, 10) : p_219010_.getMaxLocalRawBrightness(p_219011_);
                return j <= dimensiontype.monsterSpawnLightTest().sample(p_219012_);
            }
        }
    }

    public static boolean checkMonsterSpawnRules(EntityType<? extends ZombifiedRabbit> p_219014_, ServerLevelAccessor p_219015_, EntitySpawnReason p_219016_, BlockPos p_219017_, RandomSource p_219018_) {
        return p_219015_.getBlockState(p_219017_.below()).is(BlockTags.RABBITS_SPAWNABLE_ON) && p_219015_.getDifficulty() != Difficulty.PEACEFUL && isDarkEnoughToSpawn(p_219015_, p_219017_, p_219018_) && checkMobSpawnRules(p_219014_, p_219015_, p_219016_, p_219017_, p_219018_);
    }

    @Override
    public void setLandingDelay() {
        if (this.hasControllingPassenger()) {
            this.jumpDelayTicks = 5;
        } else {
            super.setLandingDelay();
        }
    }

    @Override
    public boolean removeWhenFarAway(double p_27598_) {
        return true;
    }

    static class RabbitAttackGoal extends MeleeAttackGoal {
        public RabbitAttackGoal(Rabbit p_29738_) {
            super(p_29738_, 1.4D, true);
        }
    }

    @Nullable
    @Override
    public Rabbit getBreedOffspring(ServerLevel p_149035_, AgeableMob p_149036_) {
        Rabbit rabbit = ModEntities.ZOMBIFIED_RABBIT.get().create(p_149035_, EntitySpawnReason.BREEDING);
        if (rabbit != null) {

            //rabbit.setVariant(rabbit$variant);
        }

        return rabbit;
    }
}
