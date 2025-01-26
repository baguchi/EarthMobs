package baguchan.earthmobsmod.entity;

import baguchan.earthmobsmod.entity.goal.EatLavaGoal;
import baguchan.earthmobsmod.registry.ModEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.Cow;
import net.minecraft.world.entity.monster.MagmaCube;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUtils;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.pathfinder.PathType;

public class MagmaCow extends Cow {
    private static final EntityDataAccessor<Boolean> WEAKING = SynchedEntityData.defineId(MagmaCow.class, EntityDataSerializers.BOOLEAN);

    private int eatAnimationTick;
    private EatLavaGoal eatBlockGoal;

    public MagmaCow(EntityType<? extends MagmaCow> p_28285_, Level p_28286_) {
        super(p_28285_, p_28286_);
        this.setPathfindingMalus(PathType.LAVA, 8.0F);
        this.setPathfindingMalus(PathType.DANGER_FIRE, 0.0F);
        this.setPathfindingMalus(PathType.DAMAGE_FIRE, 0.0F);
        this.setPathfindingMalus(PathType.WATER, -1.0F);
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(WEAKING, false);
    }

    public void setWeaking(boolean weak) {
        this.entityData.set(WEAKING, weak);
    }

    public boolean isWeaking() {
        return this.entityData.get(WEAKING);
    }

    protected void registerGoals() {
        this.eatBlockGoal = new EatLavaGoal(this);
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 1.15D, false));
        this.goalSelector.addGoal(2, new BreedGoal(this, 1.0D));
        this.goalSelector.addGoal(3, new TemptGoal(this, 1.1D, Ingredient.of(Items.MAGMA_CREAM), false));
        this.goalSelector.addGoal(4, new FollowParentGoal(this, 1.1D));
        this.goalSelector.addGoal(5, this.eatBlockGoal);
        this.goalSelector.addGoal(6, new WaterAvoidingRandomStrollGoal(this, 1.0D));
        this.goalSelector.addGoal(7, new LookAtPlayerGoal(this, Player.class, 6.0F));
        this.goalSelector.addGoal(8, new RandomLookAroundGoal(this));
        this.targetSelector.addGoal(0, new HurtByTargetGoal(this).setAlertOthers());
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, MagmaCube.class, true));
    }

    @Override
    public void tick() {
        super.tick();
        if (this.isInWater() && !this.isWeaking()) {
            this.fizz();
        }
    }

    private void fizz() {
        if (this.level().isClientSide) {
            for (int i = 0; i < 8; i++) {
                this.level().addParticle(ParticleTypes.SMOKE, this.getRandomX(this.getBbWidth()), this.getRandomY(), this.getRandomZ(this.getBbWidth()), 0, 0, 0);
            }
        }
        this.playSound(SoundEvents.LAVA_EXTINGUISH);
        this.setWeaking(true);
    }

    public InteractionResult mobInteract(Player p_28941_, InteractionHand p_28942_) {
        ItemStack itemstack = p_28941_.getItemInHand(p_28942_);
        if (itemstack.is(Items.BUCKET) && !this.isBaby()) {
            ItemStack itemstack1 = new ItemStack(Items.LAVA_BUCKET);
            ItemStack itemstack2 = ItemUtils.createFilledResult(itemstack, p_28941_, itemstack1, false);
            p_28941_.setItemInHand(p_28942_, itemstack2);
            SoundEvent soundevent = SoundEvents.BUCKET_FILL_LAVA;
            this.playSound(soundevent, 1.0F, 1.0F);
            return InteractionResult.SUCCESS;
        } else {
            return super.mobInteract(p_28941_, p_28942_);
        }
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Animal.createAnimalAttributes().add(Attributes.MAX_HEALTH, 12.0D).add(Attributes.MOVEMENT_SPEED, (double) 0.2F).add(Attributes.ARMOR, 10F).add(Attributes.ATTACK_DAMAGE, 4F).add(Attributes.KNOCKBACK_RESISTANCE, 0.6F);
    }

    public Cow getBreedOffspring(ServerLevel p_148884_, AgeableMob p_148885_) {
        return ModEntities.MAGMA_COW.get().create(p_148884_, EntitySpawnReason.BREEDING);
    }

    public void addAdditionalSaveData(CompoundTag p_29864_) {
        super.addAdditionalSaveData(p_29864_);
        p_29864_.putBoolean("Weaking", this.isWeaking());
    }

    public void readAdditionalSaveData(CompoundTag p_29845_) {
        super.readAdditionalSaveData(p_29845_);
        this.setWeaking(p_29845_.getBoolean("Weaking"));
    }

    protected void customServerAiStep(ServerLevel serverLevel) {
        this.eatAnimationTick = this.eatBlockGoal.getEatAnimationTick();
        super.customServerAiStep(serverLevel);
    }

    public void aiStep() {
        this.updateSwingTime();
        if (this.level().isClientSide) {
            this.eatAnimationTick = Math.max(0, this.eatAnimationTick - 1);
        }

        super.aiStep();
    }

    public void handleEntityEvent(byte p_29814_) {
        if (p_29814_ == 10) {
            this.eatAnimationTick = 40;
        } else if (p_29814_ == 11) {
            for (int i = 0; i < 7; ++i) {
                double d0 = this.random.nextGaussian() * 0.02D;
                double d1 = this.random.nextGaussian() * 0.02D;
                double d2 = this.random.nextGaussian() * 0.02D;
                this.level().addParticle(ParticleTypes.LAVA, this.getRandomX(1.0D), this.getRandomY() + 0.5D, this.getRandomZ(1.0D), d0, d1, d2);
            }
        } else {
            super.handleEntityEvent(p_29814_);
        }

    }

    public float getHeadEatAngleScale(float p_29883_) {
        if (this.eatAnimationTick > 4 && this.eatAnimationTick <= 36) {
            float f = ((float) (this.eatAnimationTick - 4) - p_29883_) / 32.0F;
            return ((float) Math.PI / 5F) + 0.21991149F * Mth.sin(f * 28.7F);
        } else {
            return this.eatAnimationTick > 0 ? ((float) Math.PI / 5F) : this.getXRot() * ((float) Math.PI / 180F);
        }
    }

    @Override
    public boolean isFood(ItemStack p_27600_) {
        return p_27600_.is(Items.MAGMA_CREAM);
    }

    @Override
    public boolean canFreeze() {
        return super.canFreeze() && this.isWeaking();
    }

    public static boolean checkMagmaSpawnRules(EntityType<? extends Animal> p_218105_, LevelAccessor p_218106_, EntitySpawnReason p_218107_, BlockPos p_218108_, RandomSource p_218109_) {
        return true;
    }

    public float getWalkTargetValue(BlockPos p_27573_, LevelReader p_27574_) {
        if (p_27574_.getBlockState(p_27573_.below()).is(Blocks.BASALT) || p_27574_.getBlockState(p_27573_.below()).is(Blocks.BLACKSTONE)) {
            return 1.0F;
        }
        return p_27574_.getBlockState(p_27573_.below()).is(Blocks.MAGMA_BLOCK) ? 10.0F : p_27574_.getPathfindingCostFromLightLevels(p_27573_) - 0.5F;
    }

    @Override
    public int getMaxSpawnClusterSize() {
        return 12;
    }


    public void ate() {
        this.heal(2);
        this.setWeaking(false);
        if (this.isBaby()) {
            this.ageUp(60);
        }
        this.playSound(SoundEvents.FIRE_EXTINGUISH);
        this.level().broadcastEntityEvent(this, (byte) 11);
    }
}
