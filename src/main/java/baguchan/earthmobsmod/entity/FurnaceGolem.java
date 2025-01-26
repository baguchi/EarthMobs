package baguchan.earthmobsmod.entity;

import baguchan.earthmobsmod.registry.ModDamageSource;
import com.google.common.collect.ImmutableList;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.AbstractGolem;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.NaturalSpawner;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

public class FurnaceGolem extends AbstractGolem {
    private static final EntityDataAccessor<Boolean> FURNACE_ACTIVE = SynchedEntityData.defineId(FurnaceGolem.class, EntityDataSerializers.BOOLEAN);

    private int attackAnimationTick;
    private int activeTime;
    private int cooldownTime;
    public FurnaceGolem(EntityType<? extends AbstractGolem> p_27508_, Level p_27509_) {
        super(p_27508_, p_27509_);
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(FURNACE_ACTIVE, false);
    }

    @Override
    public void addAdditionalSaveData(CompoundTag p_28867_) {
        super.addAdditionalSaveData(p_28867_);
        p_28867_.putBoolean("FurnaceActive", this.isFurnaceActive());
        p_28867_.putInt("ActiveTick", this.activeTime);
        p_28867_.putInt("CooldownTick", this.cooldownTime);
    }

    @Override
    public void readAdditionalSaveData(CompoundTag p_28857_) {
        super.readAdditionalSaveData(p_28857_);
        this.setFurnaceActive(p_28857_.getBoolean("FurnaceActive"));
        this.activeTime = p_28857_.getInt("ActiveTick");
        this.cooldownTime = p_28857_.getInt("CooldownTick");
    }

    protected void registerGoals() {
        this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 1.0D, true));
        this.goalSelector.addGoal(2, new MoveTowardsTargetGoal(this, 0.9D, 32.0F));
        this.goalSelector.addGoal(2, new MoveBackToVillageGoal(this, 0.6D, false));
        this.goalSelector.addGoal(4, new GolemRandomStrollInVillageGoal(this, 0.6D));
        this.goalSelector.addGoal(7, new LookAtPlayerGoal(this, Player.class, 6.0F));
        this.goalSelector.addGoal(8, new RandomLookAroundGoal(this));
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Mob.class, 5, false, false, (p_28879_, serverLevel) -> {
            return p_28879_ instanceof Enemy && !(p_28879_ instanceof Creeper);
        }));
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 100.0D).add(Attributes.MOVEMENT_SPEED, 0.25D).add(Attributes.KNOCKBACK_RESISTANCE, 1.0D).add(Attributes.STEP_HEIGHT, 1.0D).add(Attributes.ATTACK_DAMAGE, 10.0D);
    }

    protected int decreaseAirSupply(int p_28882_) {
        return p_28882_;
    }

    protected void doPush(Entity p_28839_) {
        if (p_28839_ instanceof Enemy && !(p_28839_ instanceof Creeper) && this.cooldownTime <= 0) {
            if (this.getRandom().nextInt(20) == 0) {
                this.setTarget((LivingEntity) p_28839_);
            }
            if (!this.isFurnaceActive()) {
                this.playSound(SoundEvents.FIRE_EXTINGUISH, 2.0f, 1.0f);
                this.setFurnaceActive(true);
            }
        }

        super.doPush(p_28839_);
    }

    public void aiStep() {
        super.aiStep();
        if (this.attackAnimationTick > 0) {
            --this.attackAnimationTick;
        }
        if (this.cooldownTime > 0) {
            --this.cooldownTime;
        }

        if (this.isAlive() && this.isFurnaceActive()) {
            ++this.activeTime;
            this.checkFurnaceAttack(this.getBoundingBox(), this.getBoundingBox().inflate(2.0F));

            if (this.activeTime >= 200 && this.onGround()) {
                this.playSound(SoundEvents.FIRE_EXTINGUISH, 2.0f, 1.0f);
                this.setFurnaceActive(false);
                this.activeTime = 0;
                this.cooldownTime = 100;
            }
        }

        if (this.getDeltaMovement().horizontalDistanceSqr() > (double) 2.5000003E-7F && this.random.nextInt(5) == 0) {
            int i = Mth.floor(this.getX());
            int j = Mth.floor(this.getY() - (double) 0.2F);
            int k = Mth.floor(this.getZ());
            BlockPos pos = new BlockPos(i, j, k);
            BlockState blockstate = this.level().getBlockState(pos);
            if (!blockstate.isAir()) {
                this.level().addParticle(new BlockParticleOption(ParticleTypes.BLOCK, blockstate), this.getX() + ((double) this.random.nextFloat() - 0.5D) * (double) this.getBbWidth(), this.getY() + 0.1D, this.getZ() + ((double) this.random.nextFloat() - 0.5D) * (double) this.getBbWidth(), 4.0D * ((double) this.random.nextFloat() - 0.5D), 0.5D, ((double) this.random.nextFloat() - 0.5D) * 4.0D);
            }
        }

    }

    protected void checkFurnaceAttack(AABB p_21072_, AABB p_21073_) {
        AABB aabb = p_21072_.minmax(p_21073_);
        List<Entity> list = this.level().getEntities(this, aabb);
        if (!list.isEmpty()) {
            for (Entity entity : list) {
                if (entity != this) {
                    this.furnaceAttack(entity);
                }
            }
        }
    }

    public void furnaceAttack(Entity p_36347_) {
        if (p_36347_ instanceof Enemy && !(p_36347_ instanceof Creeper)) {
            if (p_36347_.isAttackable() && !this.isAlliedTo(p_36347_)) {
                p_36347_.hurt(this.damageSources().source(ModDamageSource.BURNING, this), 6.0F);
                p_36347_.igniteForSeconds(5);
            }
        }
    }

    public boolean canAttackType(EntityType<?> p_28851_) {
        if (p_28851_ == EntityType.PLAYER) {
            return false;
        } else {
            return p_28851_ == EntityType.CREEPER ? false : super.canAttackType(p_28851_);
        }
    }
    public void setFurnaceActive(boolean active) {
        this.entityData.set(FURNACE_ACTIVE, active);
    }

    public boolean isFurnaceActive() {
        return this.entityData.get(FURNACE_ACTIVE);
    }


    private float getAttackDamage() {
        return (float) this.getAttributeValue(Attributes.ATTACK_DAMAGE);
    }

    @Override
    public boolean doHurtTarget(ServerLevel p_376718_, Entity p_28837_) {
        this.attackAnimationTick = 10;
        p_376718_.broadcastEntityEvent(this, (byte) 4);
        float f = this.getAttackDamage();
        float f1 = (int) f > 0 ? f / 2.0F + (float) this.random.nextInt((int) f) : f;
        DamageSource damagesource = this.damageSources().mobAttack(this);
        boolean flag = p_28837_.hurtServer(p_376718_, damagesource, f1);
        if (flag) {
            double d0 = p_28837_ instanceof LivingEntity livingentity ? livingentity.getAttributeValue(Attributes.KNOCKBACK_RESISTANCE) : 0.0;
            double d1 = Math.max(0.0, 1.0 - d0);
            p_28837_.setDeltaMovement(p_28837_.getDeltaMovement().add(0.0, 0.4F * d1, 0.0));
            EnchantmentHelper.doPostAttackEffects(p_376718_, p_28837_, damagesource);
        }

        this.playSound(SoundEvents.IRON_GOLEM_ATTACK, 1.0F, 1.0F);
        return flag;
    }

    @Override
    public boolean hurtServer(ServerLevel p_376593_, DamageSource p_376434_, float p_376366_) {
        Crackiness.Level crackiness$level = this.getCrackiness();
        boolean flag = super.hurtServer(p_376593_, p_376434_, p_376366_);
        if (flag && this.getCrackiness() != crackiness$level) {
            this.playSound(SoundEvents.IRON_GOLEM_DAMAGE, 1.0F, 1.0F);
        }

        return flag;
    }

    public net.minecraft.world.entity.Crackiness.Level getCrackiness() {
        return net.minecraft.world.entity.Crackiness.GOLEM.byFraction(this.getHealth() / this.getMaxHealth());
    }

    public void handleEntityEvent(byte p_28844_) {
        if (p_28844_ == 4) {
            this.attackAnimationTick = 10;
            this.playSound(SoundEvents.IRON_GOLEM_ATTACK, 1.0F, 1.0F);
        } else {
            super.handleEntityEvent(p_28844_);
        }
    }

    public int getAttackAnimationTick() {
        return this.attackAnimationTick;
    }


    protected SoundEvent getHurtSound(DamageSource p_28872_) {
        return SoundEvents.IRON_GOLEM_HURT;
    }

    protected SoundEvent getDeathSound() {
        return SoundEvents.IRON_GOLEM_DEATH;
    }

    protected InteractionResult mobInteract(Player p_28861_, InteractionHand p_28862_) {
        ItemStack itemstack = p_28861_.getItemInHand(p_28862_);
        if (!itemstack.is(Items.SMOOTH_STONE)) {
            return InteractionResult.PASS;
        } else {
            float f = this.getHealth();
            this.heal(20.0F);
            if (this.getHealth() == f) {
                return InteractionResult.PASS;
            } else {
                float f1 = 1.0F + (this.random.nextFloat() - this.random.nextFloat()) * 0.2F;
                this.playSound(SoundEvents.IRON_GOLEM_REPAIR, 1.0F, f1);
                if (!p_28861_.getAbilities().instabuild) {
                    itemstack.shrink(1);
                }

                return InteractionResult.SUCCESS;
            }
        }
    }

    protected void playStepSound(BlockPos p_28864_, BlockState p_28865_) {
        this.playSound(SoundEvents.IRON_GOLEM_STEP, 1.0F, 1.0F);
    }

    public void die(DamageSource p_28846_) {
        super.die(p_28846_);
    }

    public boolean checkSpawnObstruction(LevelReader p_28853_) {
        BlockPos blockpos = this.blockPosition();
        BlockPos blockpos1 = blockpos.below();
        BlockState blockstate = p_28853_.getBlockState(blockpos1);
        if (!blockstate.entityCanStandOn(p_28853_, blockpos1, this)) {
            return false;
        } else {
            for (int i = 1; i < 3; ++i) {
                BlockPos blockpos2 = blockpos.above(i);
                BlockState blockstate1 = p_28853_.getBlockState(blockpos2);
                if (!NaturalSpawner.isValidEmptySpawnBlock(p_28853_, blockpos2, blockstate1, blockstate1.getFluidState(), EntityType.IRON_GOLEM)) {
                    return false;
                }
            }

            return NaturalSpawner.isValidEmptySpawnBlock(p_28853_, blockpos, p_28853_.getBlockState(blockpos), Fluids.EMPTY.defaultFluidState(), EntityType.IRON_GOLEM) && p_28853_.isUnobstructed(this);
        }
    }

    public Vec3 getLeashOffset() {
        return new Vec3(0.0D, (double) (0.875F * this.getEyeHeight()), (double) (this.getBbWidth() * 0.4F));
    }
}
