package baguchan.earthmobsmod.entity;

import baguchan.earthmobsmod.registry.ModEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.tags.BiomeTags;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Difficulty;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.PanicGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.Rabbit;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.npc.AbstractVillager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LightLayer;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.dimension.DimensionType;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

public class ZombifiedRabbit extends Rabbit implements Enemy {
    private static final EntityDataAccessor<Boolean> DATA_CONVERTING_ID = SynchedEntityData.defineId(ZombifiedRabbit.class, EntityDataSerializers.BOOLEAN);

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
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_CONVERTING_ID, false);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Rabbit.createAttributes().add(Attributes.MAX_HEALTH, 8.0D);
    }

    public void addAdditionalSaveData(CompoundTag p_34397_) {
        super.addAdditionalSaveData(p_34397_);


        p_34397_.putInt("ConversionTime", this.isConverting() ? this.conversionTime : -1);
        if (this.conversionStarter != null) {
            p_34397_.putUUID("ConversionPlayer", this.conversionStarter);
        }
    }

    public void readAdditionalSaveData(CompoundTag p_34387_) {
        super.readAdditionalSaveData(p_34387_);

        if (p_34387_.contains("ConversionTime", 99) && p_34387_.getInt("ConversionTime") > -1) {
            this.startConverting(p_34387_.hasUUID("ConversionPlayer") ? p_34387_.getUUID("ConversionPlayer") : null, p_34387_.getInt("ConversionTime"));
        }

    }

    public void tick() {
        if (!this.level().isClientSide && this.isAlive() && this.isConverting()) {
            this.conversionTime -= 1;
            if (this.conversionTime <= 0 && net.minecraftforge.event.ForgeEventFactory.canLivingConvert(this, EntityType.VILLAGER, (timer) -> this.conversionTime = timer)) {
                this.finishConversion((ServerLevel) this.level());
            }
        }

        super.tick();
    }

    public InteractionResult mobInteract(Player p_34394_, InteractionHand p_34395_) {
        ItemStack itemstack = p_34394_.getItemInHand(p_34395_);
        if (itemstack.is(Items.GOLDEN_CARROT)) {
            if (this.hasEffect(MobEffects.WEAKNESS)) {
                if (!p_34394_.getAbilities().instabuild) {
                    itemstack.shrink(1);
                }

                if (!this.level().isClientSide) {
                    this.startConverting(p_34394_.getUUID(), this.random.nextInt(2401) + 3600);
                }

                return InteractionResult.SUCCESS;
            } else {
                return InteractionResult.CONSUME;
            }
        } else {
            return super.mobInteract(p_34394_, p_34395_);
        }
    }

    public boolean isConverting() {
        return this.getEntityData().get(DATA_CONVERTING_ID);
    }

    private void startConverting(@javax.annotation.Nullable UUID p_34384_, int p_34385_) {
        this.conversionStarter = p_34384_;
        this.conversionTime = p_34385_;
        this.getEntityData().set(DATA_CONVERTING_ID, true);
        this.removeEffect(MobEffects.WEAKNESS);
        this.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, p_34385_, Math.min(this.level().getDifficulty().getId() - 1, 0)));
        this.level().broadcastEntityEvent(this, (byte) 16);
    }

    private void finishConversion(ServerLevel p_34399_) {
        Rabbit rabbit = this.convertTo(EntityType.RABBIT, false);
        rabbit.setVariant(this.getVariant());
        rabbit.finalizeSpawn(p_34399_, p_34399_.getCurrentDifficultyAt(rabbit.blockPosition()), MobSpawnType.CONVERSION, (SpawnGroupData) null, (CompoundTag) null);
        if (this.conversionStarter != null) {
            Player player = p_34399_.getPlayerByUUID(this.conversionStarter);
            if (player instanceof ServerPlayer) {
                //CriteriaTriggers.CURED_ZOMBIE_VILLAGER.trigger((ServerPlayer)player, this, rabbit);
            }
        }

        rabbit.addEffect(new MobEffectInstance(MobEffects.CONFUSION, 200, 0));
        if (!this.isSilent()) {
            p_34399_.levelEvent((Player) null, 1027, this.blockPosition(), 0);
        }
        net.minecraftforge.event.ForgeEventFactory.onLivingConvert(this, rabbit);
    }

    @Override
    public void aiStep() {
        if (this.isAlive()) {
            boolean flag = this.isSunBurnTick();
            if (flag) {
                ItemStack itemstack = this.getItemBySlot(EquipmentSlot.HEAD);
                if (!itemstack.isEmpty()) {
                    if (itemstack.isDamageableItem()) {
                        itemstack.setDamageValue(itemstack.getDamageValue() + this.random.nextInt(2));
                        if (itemstack.getDamageValue() >= itemstack.getMaxDamage()) {
                            this.broadcastBreakEvent(EquipmentSlot.HEAD);
                            this.setItemSlot(EquipmentSlot.HEAD, ItemStack.EMPTY);
                        }
                    }

                    flag = false;
                }

                if (flag) {
                    this.setSecondsOnFire(8);
                }
            }
        }
        super.aiStep();
    }

    @Override
    public void setVariant(Rabbit.Variant p_262578_) {
        if (p_262578_ != Variant.EVIL) {
            this.getAttribute(Attributes.ARMOR).setBaseValue(2.0D);
            this.goalSelector.addGoal(4, new RabbitAttackGoal(this));
            this.targetSelector.addGoal(1, (new HurtByTargetGoal(this)).setAlertOthers());
            this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true));
        }
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, AbstractVillager.class, true));

        super.setVariant(p_262578_);
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

    public static boolean checkMonsterSpawnRules(EntityType<? extends ZombifiedRabbit> p_219014_, ServerLevelAccessor p_219015_, MobSpawnType p_219016_, BlockPos p_219017_, RandomSource p_219018_) {
        return p_219015_.getBlockState(p_219017_.below()).is(BlockTags.RABBITS_SPAWNABLE_ON) && p_219015_.getDifficulty() != Difficulty.PEACEFUL && isDarkEnoughToSpawn(p_219015_, p_219017_, p_219018_) && checkMobSpawnRules(p_219014_, p_219015_, p_219016_, p_219017_, p_219018_);
    }

    @Override
    public boolean removeWhenFarAway(double p_27598_) {
        return true;
    }

    @Override
    protected boolean shouldDespawnInPeaceful() {
        return false;
    }

    @Override
    public MobType getMobType() {
        return MobType.UNDEAD;
    }

    static class RabbitAttackGoal extends MeleeAttackGoal {
        public RabbitAttackGoal(Rabbit p_29738_) {
            super(p_29738_, 1.4D, true);
        }
    }

    @Nullable
    @Override
    public Rabbit getBreedOffspring(ServerLevel p_149035_, AgeableMob p_149036_) {
        Rabbit rabbit = ModEntities.ZOMBIFIED_RABBIT.get().create(p_149035_);
        if (rabbit != null) {
            Rabbit.Variant rabbit$variant = getRandomRabbitVariant(p_149035_, this.blockPosition());
            if (this.random.nextInt(20) != 0) {
                label22:
                {
                    if (p_149036_ instanceof Rabbit) {
                        Rabbit rabbit1 = (Rabbit) p_149036_;
                        if (this.random.nextBoolean()) {
                            rabbit$variant = rabbit1.getVariant();
                            break label22;
                        }
                    }

                    rabbit$variant = this.getVariant();
                }
            }

            rabbit.setVariant(rabbit$variant);
        }

        return rabbit;
    }

    private static Rabbit.Variant getRandomRabbitVariant(LevelAccessor p_262699_, BlockPos p_262700_) {
        Holder<Biome> holder = p_262699_.getBiome(p_262700_);
        int i = p_262699_.getRandom().nextInt(100);
        if (holder.is(BiomeTags.SPAWNS_WHITE_RABBITS)) {
            return i < 80 ? Rabbit.Variant.WHITE : Rabbit.Variant.WHITE_SPLOTCHED;
        } else if (holder.is(BiomeTags.SPAWNS_GOLD_RABBITS)) {
            return Rabbit.Variant.GOLD;
        } else {
            return i < 50 ? Rabbit.Variant.BROWN : (i < 90 ? Rabbit.Variant.SALT : Rabbit.Variant.BLACK);
        }
    }
}
