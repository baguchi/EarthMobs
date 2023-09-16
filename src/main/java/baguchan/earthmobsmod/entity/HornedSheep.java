package baguchan.earthmobsmod.entity;

import baguchan.earthmobsmod.registry.ModEntities;
import baguchan.earthmobsmod.registry.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.InstrumentTags;
import net.minecraft.tags.TagKey;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.animal.Sheep;
import net.minecraft.world.entity.animal.goat.Goat;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Instrument;
import net.minecraft.world.item.InstrumentItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

import java.util.EnumSet;

public class HornedSheep extends Sheep {
    private static final EntityDataAccessor<Boolean> DATA_HAS_HORN = SynchedEntityData.defineId(HornedSheep.class, EntityDataSerializers.BOOLEAN);

    private float aggressiveScale;
    private float aggressiveScaleOld;

    public HornedSheep(EntityType<? extends Sheep> p_29806_, Level p_29807_) {
        super(p_29806_, p_29807_);
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_HAS_HORN, true);
    }

    @Override
    protected void registerGoals() {
        this.eatBlockGoal = new EatBlockGoal(this);
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new PanicGoal(this, 1.3F) {
            @Override
            public boolean canUse() {
                return !hasHorn() && super.canUse();
            }

            @Override
            public boolean canContinueToUse() {
                return !hasHorn() && super.canContinueToUse();
            }
        });
        this.goalSelector.addGoal(1, new AvoidEntityGoal<>(this, Goat.class, 12.0F, 1.3D, 1.35D) {
            @Override
            public boolean canUse() {
                return !hasHorn() && super.canUse();
            }

            @Override
            public boolean canContinueToUse() {
                return !hasHorn() && super.canContinueToUse();
            }
        });
        this.goalSelector.addGoal(2, new HornedSheepAttackGoal(this));
        this.goalSelector.addGoal(4, new BreedGoal(this, 1.0D));
        this.goalSelector.addGoal(5, new TemptGoal(this, 1.1D, Ingredient.of(Items.WHEAT), false));
        this.goalSelector.addGoal(6, new FollowParentGoal(this, 1.1D));
        this.goalSelector.addGoal(7, this.eatBlockGoal);
        this.goalSelector.addGoal(8, new WaterAvoidingRandomStrollGoal(this, 1.0D));
        this.goalSelector.addGoal(9, new LookAtPlayerGoal(this, Player.class, 6.0F));
        this.goalSelector.addGoal(10, new RandomLookAroundGoal(this));

        this.targetSelector.addGoal(1, (new HurtByTargetGoal(this) {
            @Override
            public boolean canUse() {
                return super.canUse() && hasHorn();
            }

            @Override
            public boolean canContinueToUse() {
                return super.canContinueToUse() && hasHorn();
            }
        }).setAlertOthers());
    }

    public boolean hasHorn() {
        return this.entityData.get(DATA_HAS_HORN);
    }

    public boolean dropHorn() {
        boolean flag = this.hasHorn();
        if (!flag) {
            return false;
        } else {
            this.entityData.set(DATA_HAS_HORN, false);
            Vec3 vec3 = this.position();
            ItemStack itemstack = this.createHorn();
            double d0 = (double) Mth.randomBetween(this.random, -0.2F, 0.2F);
            double d1 = (double) Mth.randomBetween(this.random, 0.3F, 0.7F);
            double d2 = (double) Mth.randomBetween(this.random, -0.2F, 0.2F);
            ItemEntity itementity = new ItemEntity(this.level(), vec3.x(), vec3.y(), vec3.z(), itemstack, d0, d1, d2);
            this.level().addFreshEntity(itementity);
            ItemEntity itementity2 = new ItemEntity(this.level(), vec3.x(), vec3.y(), vec3.z(), itemstack, d0, d1, d2);
            this.level().addFreshEntity(itementity2);
            return true;
        }
    }

    public ItemStack createHorn() {
        RandomSource randomsource = RandomSource.create((long) this.getUUID().hashCode());
        TagKey<Instrument> tagkey = InstrumentTags.REGULAR_GOAT_HORNS;
        HolderSet<Instrument> holderset = BuiltInRegistries.INSTRUMENT.getOrCreateTag(tagkey);
        return InstrumentItem.create(ModItems.HORN_FLUTE.get(), holderset.getRandomElement(randomsource).get());
    }

    public void addHorns() {
        this.entityData.set(DATA_HAS_HORN, true);
    }

    public void addAdditionalSaveData(CompoundTag p_149385_) {
        super.addAdditionalSaveData(p_149385_);
        p_149385_.putBoolean("HasHorn", this.hasHorn());
    }

    public void readAdditionalSaveData(CompoundTag p_149373_) {
        super.readAdditionalSaveData(p_149373_);
        this.entityData.set(DATA_HAS_HORN, p_149373_.getBoolean("HasHorn"));
    }

    public void aiStep() {
        this.updateSwingTime();
        super.aiStep();

        if (this.level().isClientSide) {

            this.aggressiveScaleOld = this.aggressiveScale;
            if (this.isAggressive()) {
                this.aggressiveScale = Mth.clamp(this.aggressiveScale + 0.2F, 0.0F, 1.0F);
            } else {
                this.aggressiveScale = Mth.clamp(this.aggressiveScale - 0.2F, 0.0F, 1.0F);
            }
        }
    }

    public float getAggressiveAnimationScale(float p_29570_) {
        return Mth.lerp(p_29570_, this.aggressiveScaleOld, this.aggressiveScale);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Sheep.createAttributes().add(Attributes.ATTACK_DAMAGE, 4.0D).add(Attributes.ATTACK_KNOCKBACK, (double) 0.9F);
    }

    @Override
    public HornedSheep getBreedOffspring(ServerLevel p_149035_, AgeableMob p_149036_) {
        return ModEntities.HORNED_SHEEP.get().create(p_149035_);
    }

    @Override
    public boolean hurt(DamageSource damagesource, float p_27568_) {
        Entity entity1 = damagesource.getEntity();
        if (entity1 != null) {
            if (entity1 instanceof LivingEntity) {
                this.setLastHurtByMob((LivingEntity) entity1);
            }
        }
        return super.hurt(damagesource, p_27568_);
    }

    public static class HornedSheepAttackGoal extends Goal {
        private final HornedSheep hornedSheep;
        private boolean rushing = true;
        private BlockPos targetPos;
        private int rushTick;
        private int rushCooldowmTick;
        public int ticksUntilNextAttack;
        private boolean attack;

        public HornedSheepAttackGoal(HornedSheep hornedSheep) {
            super();
            this.hornedSheep = hornedSheep;
            this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
        }

        @Override
        public boolean canUse() {
            LivingEntity livingentity = this.hornedSheep.getTarget();
            if (livingentity == null) {
                return false;
            } else if (!livingentity.isAlive()) {
                return false;
            } else {
                return true;
            }
        }

        @Override
        public boolean canContinueToUse() {
            return this.hornedSheep.getTarget() != null && this.hornedSheep.getTarget().isAlive();
        }

        @Override
        public void start() {
            super.start();
            this.targetPos = null;
            this.rushTick = 200;
            this.hornedSheep.setAggressive(true);
            this.rushing = true;
        }

        @Override
        public void stop() {
            super.stop();
            this.attack = false;
            this.hornedSheep.setAggressive(false);
        }

        public void tick() {
            LivingEntity livingentity = this.hornedSheep.getTarget();


            if (livingentity != null) {
                double d0 = this.hornedSheep.getPerceivedTargetDistanceSquareForMeleeAttack(livingentity);
                this.ticksUntilNextAttack = Math.max(this.ticksUntilNextAttack - 1, 0);
                if (this.rushing) {

                    if (this.rushTick == 140) {

                        targetPos = livingentity.blockPosition();
                    }
                    if (this.rushTick <= 140) {

                        if (this.targetPos != null) {
                            this.hornedSheep.getLookControl().setLookAt(this.targetPos.getX(), this.targetPos.getY(), this.targetPos.getZ(), 30.0F, 30.0F);

                            this.hornedSheep.getMoveControl().setWantedPosition(this.targetPos.getX(), this.targetPos.getY(), this.targetPos.getZ(), 2.5F);
                        }

                        if (this.hasRammedHornBreakingBlock(this.hornedSheep.level(), this.hornedSheep)) {
                            boolean flag = this.hornedSheep.dropHorn();
                            if (flag) {
                                this.hornedSheep.level().playSound((Player) null, this.hornedSheep, SoundEvents.GOAT_HORN_BREAK, SoundSource.NEUTRAL, 1.0F, 1.0F);
                            }
                            this.rushCooldowmTick = 200 + this.hornedSheep.random.nextInt(200);
                            this.rushing = false;
                        }
                        if (this.hornedSheep.position().closerThan(this.targetPos.getCenter(), 0.25F)) {
                            this.rushCooldowmTick = 200 + this.hornedSheep.random.nextInt(200);
                            this.rushing = false;
                        }
                    }
                    if (this.rushTick > 0) {
                        --this.rushTick;
                    }
                    if (this.rushing && this.rushTick <= 0 || this.attack && this.rushTick > 0) {
                        this.rushCooldowmTick = 200 + this.hornedSheep.random.nextInt(200);
                        this.rushing = false;
                    }
                } else {
                    if (this.rushCooldowmTick > 0) {
                        --this.rushCooldowmTick;
                    }
                    if (this.rushCooldowmTick <= 0) {
                        this.rushing = true;
                        this.rushTick = 200;
                    }
                    this.hornedSheep.getNavigation().moveTo(livingentity, 1.1F);
                }

                if (this.rushing && this.rushTick <= 140 || !this.rushing && this.ticksUntilNextAttack <= 0) {
                    this.checkAndPerformAttack(livingentity, d0);
                }

            }
        }

        protected void checkAndPerformAttack(LivingEntity p_25557_, double p_25558_) {
            double d0 = this.getAttackReachSqr(p_25557_);
            if (p_25558_ <= d0 && (!this.attack || !this.rushing)) {
                this.hornedSheep.swing(InteractionHand.MAIN_HAND);
                if (!this.rushing) {
                    this.hornedSheep.doHurtTarget(p_25557_);
                    this.attack = true;
                } else {
                    int i = this.hornedSheep.hasEffect(MobEffects.MOVEMENT_SPEED) ? this.hornedSheep.getEffect(MobEffects.MOVEMENT_SPEED).getAmplifier() + 1 : 0;
                    int j = this.hornedSheep.hasEffect(MobEffects.MOVEMENT_SLOWDOWN) ? this.hornedSheep.getEffect(MobEffects.MOVEMENT_SLOWDOWN).getAmplifier() + 1 : 0;
                    float f = 0.25F * (float) (i - j);
                    float f1 = Mth.clamp(this.hornedSheep.getSpeed() * 1.65F, 0.2F, 3.0F) + f;
                    float f2 = p_25557_.isDamageSourceBlocked(this.hornedSheep.damageSources().mobAttack(this.hornedSheep)) ? 0.5F : 1.0F;

                    p_25557_.knockback((double) (f2 * f1) * (this.hornedSheep.isBaby() ? 0.2F : 1.5F), this.hornedSheep.getX() - p_25557_.getX(), this.hornedSheep.getZ() - p_25557_.getZ());
                    p_25557_.hurt(this.hornedSheep.damageSources().mobAttack(this.hornedSheep), (float) this.hornedSheep.getAttributeValue(Attributes.ATTACK_DAMAGE) + 2.0F);
                }
                this.ticksUntilNextAttack = 30;
            }

        }

        protected double getAttackReachSqr(LivingEntity p_25556_) {
            return (double) (this.hornedSheep.getBbWidth() * 2.0F * this.hornedSheep.getBbWidth() * 2.0F + p_25556_.getBbWidth());
        }

        @Override
        public boolean requiresUpdateEveryTick() {
            return true;
        }

        private boolean hasRammedHornBreakingBlock(Level p_217363_, HornedSheep p_217364_) {
            Vec3 vec3 = p_217364_.getDeltaMovement().multiply(1.0D, 0.0D, 1.0D).normalize();
            BlockPos blockpos = BlockPos.containing(p_217364_.position().add(vec3));
            return p_217363_.getBlockState(blockpos).is(BlockTags.SNAPS_GOAT_HORN) || p_217363_.getBlockState(blockpos.above()).is(BlockTags.SNAPS_GOAT_HORN);
        }
    }


}
