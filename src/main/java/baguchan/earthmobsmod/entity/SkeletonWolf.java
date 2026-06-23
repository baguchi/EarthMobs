package baguchan.earthmobsmod.entity;

import baguchan.earthmobsmod.registry.ModEntities;
import baguchan.earthmobsmod.registry.ModSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.*;
import net.minecraft.world.entity.animal.turtle.Turtle;
import net.minecraft.world.entity.animal.wolf.Wolf;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.DyeItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.EnchantmentEffectComponents;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.equipment.Equippable;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.LightLayer;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.neoforged.neoforge.event.EventHooks;

import java.util.Optional;

public class SkeletonWolf extends Wolf {
	public SkeletonWolf(EntityType<? extends SkeletonWolf> p_30369_, Level p_30370_) {
		super(p_30369_, p_30370_);
	}

	protected void registerGoals() {
		this.goalSelector.addGoal(2, new SitWhenOrderedToGoal(this));
		this.goalSelector.addGoal(4, new LeapAtTargetGoal(this, 0.4F));
		this.goalSelector.addGoal(5, new MeleeAttackGoal(this, 1.0D, true));
		this.goalSelector.addGoal(6, new FollowOwnerGoal(this, 1.0D, 10.0F, 2.0F));
		this.goalSelector.addGoal(8, new WaterAvoidingRandomStrollGoal(this, 1.0D));
		this.goalSelector.addGoal(9, new BegGoal(this, 8.0F));
		this.goalSelector.addGoal(10, new LookAtPlayerGoal(this, Player.class, 8.0F));
		this.goalSelector.addGoal(10, new RandomLookAroundGoal(this));
		this.targetSelector.addGoal(1, new OwnerHurtByTargetGoal(this));
		this.targetSelector.addGoal(2, new OwnerHurtTargetGoal(this));
		this.targetSelector.addGoal(3, (new HurtByTargetGoal(this)).setAlertOthers());
		this.targetSelector.addGoal(4, new NearestAttackableTargetGoal<>(this, Player.class, 10, true, false, this::isAngryAt));
		this.targetSelector.addGoal(5, new NonTameRandomTargetGoal<>(this, Player.class, true, (livingEntity, serverLevel) -> {
			return this.isWorstCondition();
		}));
		this.targetSelector.addGoal(6, new NonTameRandomTargetGoal<>(this, Turtle.class, false, Turtle.BABY_ON_LAND_SELECTOR));
		this.targetSelector.addGoal(8, new ResetUniversalAngerTargetGoal<>(this, true));
	}

	public static AttributeSupplier.Builder createAttributes() {
		return Wolf.createAttributes().add(Attributes.MAX_HEALTH, 12.0F).add(Attributes.ATTACK_DAMAGE, 3.0D);
	}

	@Override
	public void playSound(SoundEvent p_216991_) {
		if (p_216991_ == SoundEvents.WOLF_SHAKE) {
			super.playSound(ModSounds.SKELETON_WOLF_SHAKE.get());
		} else {
			super.playSound(p_216991_);
		}
	}

	@Override
	protected SoundEvent getAmbientSound() {
		if (this.isAngry()) {
			return ModSounds.SKELETON_WOLF_GROWL.get();
		} else if (this.random.nextInt(3) != 0) {
			return ModSounds.SKELETON_WOLF_AMBIENT.get();
		} else {
			return this.isTame() && this.getHealth() < 20.0F ? ModSounds.SKELETON_WOLF_WHINE.get() : ModSounds.SKELETON_WOLF_PANT.get();
		}
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource p_406243_) {
		return ModSounds.SKELETON_WOLF_HURT.get();
	}

	@Override
	protected SoundEvent getDeathSound() {
		return ModSounds.SKELETON_WOLF_DEATH.get();
	}

	@Override
	protected void playStepSound(BlockPos p_406221_, BlockState p_406277_) {
		this.playSound(ModSounds.SKELETON_WOLF_STEP.get(), 0.15F, 1.0F);
	}

	protected boolean isWorstCondition() {
		return this.getLightLevelDependentMagicValue() < 0.4F;
	}

	@Override
	public InteractionResult mobInteract(Player p_406380_, InteractionHand p_406261_) {
		ItemStack itemstack = p_406380_.getItemInHand(p_406261_);
		Item item = itemstack.getItem();
		if (this.isTame()) {
			if ((itemstack.is(Items.BONE) || itemstack.is(Items.ROTTEN_FLESH)) && this.getHealth() < this.getMaxHealth()) {
				FoodProperties foodproperties = itemstack.get(DataComponents.FOOD);
				float f = foodproperties != null ? foodproperties.nutrition() : 2.0F;
				this.heal(2.0F * f);
				this.usePlayerItem(p_406380_, p_406261_, itemstack);
				this.gameEvent(GameEvent.EAT); // Neo: add EAT game event
				return InteractionResult.SUCCESS;
			}

			if (!(item instanceof DyeItem dyeitem && this.isOwnedBy(p_406380_))) {
				if (this.isEquippableInSlotEvenSkeleton(itemstack, EquipmentSlot.BODY) && !this.isWearingBodyArmor() && this.isOwnedBy(p_406380_) && !this.isBaby()) {
					this.setItemSlot(EquipmentSlot.BODY, itemstack.copyWithCount(1));
					itemstack.consume(1, p_406380_);
					return InteractionResult.SUCCESS;
				}

				if (!itemstack.canPerformAction(net.neoforged.neoforge.common.ItemAbilities.SHEARS_REMOVE_ARMOR)
						|| !this.isOwnedBy(p_406380_)
						|| !this.isWearingBodyArmor()
						|| EnchantmentHelper.has(this.getBodyArmorItem(), EnchantmentEffectComponents.PREVENT_ARMOR_CHANGE) && !p_406380_.isCreative()) {
					if (this.isInSittingPose()
							&& this.isWearingBodyArmor()
							&& this.isOwnedBy(p_406380_)
							&& this.getBodyArmorItem().isDamaged()
							&& this.getBodyArmorItem().isValidRepairItem(itemstack)) {
						itemstack.shrink(1);
						this.playSound(SoundEvents.WOLF_ARMOR_REPAIR);
						ItemStack itemstack2 = this.getBodyArmorItem();
						int i = (int) (itemstack2.getMaxDamage() * 0.125F);
						itemstack2.setDamageValue(Math.max(0, itemstack2.getDamageValue() - i));
						return InteractionResult.SUCCESS;
					}

					InteractionResult interactionresult = super.mobInteract(p_406380_, p_406261_);
					if (!interactionresult.consumesAction() && this.isOwnedBy(p_406380_)) {
						this.setOrderedToSit(!this.isOrderedToSit());
						this.jumping = false;
						this.navigation.stop();
						this.setTarget(null);
						return InteractionResult.SUCCESS.withoutItem();
					}

					return interactionresult;
				}

                itemstack.hurtAndBreak(1, p_406380_, p_406261_);
				this.playSound(SoundEvents.ARMOR_UNEQUIP_WOLF);
				ItemStack itemstack1 = this.getBodyArmorItem();
				this.setItemSlot(EquipmentSlot.BODY, ItemStack.EMPTY);
				if (this.level() instanceof ServerLevel serverlevel) {
					this.spawnAtLocation(serverlevel, itemstack1);
				}

				return InteractionResult.SUCCESS;
			}

			/*DyeColor dyecolor = dyeitem.getDyeColor();
			if (dyecolor != this.getCollarColor()) {
				this.setCollarColor(dyecolor);
				itemstack.consume(1, p_406380_);
				return InteractionResult.SUCCESS;
			}*/
        } else if (!this.level().isClientSide() && itemstack.is(Items.BONE) && !this.isAngry()) {
			itemstack.consume(1, p_406380_);
			this.tryToTame(p_406380_);
			return InteractionResult.SUCCESS_SERVER;
		}

		return super.mobInteract(p_406380_, p_406261_);
	}

	public boolean isEquippableInSlotEvenSkeleton(ItemStack p_371603_, EquipmentSlot p_371841_) {
		Equippable equippable = (Equippable) p_371603_.get(DataComponents.EQUIPPABLE);

		Optional<Holder.Reference<EntityType<?>>> entityType = BuiltInRegistries.ENTITY_TYPE.get(BuiltInRegistries.ENTITY_TYPE.getKey(EntityTypes.WOLF));
		return equippable == null ? p_371841_ == EquipmentSlot.MAINHAND && this.canUseSlot(EquipmentSlot.MAINHAND) : p_371841_ == equippable.slot() && this.canUseSlot(equippable.slot()) && entityType.isPresent() && equippable.canBeEquippedBy(entityType.get());
	}

	private void tryToTame(Player p_406358_) {
		if (this.random.nextInt(3) == 0 && !EventHooks.onAnimalTame(this, p_406358_)) {
			this.tame(p_406358_);
			this.navigation.stop();
			this.setTarget((LivingEntity) null);
			this.setOrderedToSit(true);
			this.level().broadcastEntityEvent(this, (byte) 7);
		} else {
			this.level().broadcastEntityEvent(this, (byte) 6);
		}

	}

	@Override
	public SkeletonWolf getBreedOffspring(ServerLevel p_149088_, AgeableMob p_149089_) {
		SkeletonWolf skeletonWolf = ModEntities.SKELETON_WOLF.get().create(p_149088_, EntitySpawnReason.BREEDING);

		if (this.isTame()) {
			skeletonWolf.setOwnerReference(this.getOwnerReference());
			skeletonWolf.setTame(true, true);
		}
		return skeletonWolf;
	}

	@Override
    public boolean isFood(ItemStack itemStack) {
        return itemStack.is(Items.BONE) || itemStack.is(Items.ROTTEN_FLESH);
	}

    @Override
    public boolean canBreed() {
        return false;
    }

    @Override
	public boolean removeWhenFarAway(double p_27598_) {
		return !isTame();
	}

	public static boolean isDarkEnoughToSpawn(ServerLevelAccessor p_33009_, BlockPos p_33010_, RandomSource p_33011_) {
		if (p_33009_.getBrightness(LightLayer.SKY, p_33010_) > p_33011_.nextInt(32)) {
			return false;
		} else if (p_33009_.getBrightness(LightLayer.BLOCK, p_33010_) > 0) {
			return false;
		} else {
			int i = p_33009_.getLevel().isThundering() ? p_33009_.getMaxLocalRawBrightness(p_33010_, 10) : p_33009_.getMaxLocalRawBrightness(p_33010_);
			return i <= p_33011_.nextInt(8);
		}
	}

	public float getWalkTargetValue(BlockPos p_27573_, LevelReader p_27574_) {
		return p_27574_.getBlockState(p_27573_.below()).is(Blocks.SOUL_SAND) || p_27574_.getBlockState(p_27573_.below()).is(Blocks.SOUL_SOIL) ? 10.0F : super.getWalkTargetValue(p_27573_, p_27574_);
	}

	public static boolean checkSkeletonWolfSpawnRules(EntityType<? extends SkeletonWolf> p_33018_, ServerLevelAccessor p_33019_, EntitySpawnReason p_33020_, BlockPos p_33021_, RandomSource p_33022_) {
		return (p_33019_.getBlockState(p_33021_.below()).is(Blocks.SOUL_SAND) || p_33019_.getBlockState(p_33021_.below()).is(Blocks.SOUL_SOIL) || p_33019_.getBlockState(p_33021_.below()).is(BlockTags.ANIMALS_SPAWNABLE_ON)) && isDarkEnoughToSpawn(p_33019_, p_33021_, p_33022_) && checkMobSpawnRules(p_33018_, p_33019_, p_33020_, p_33021_, p_33022_);
	}

    @Override
    public boolean requiresCustomPersistence() {
        return super.requiresCustomPersistence() || this.isTame();
    }
}
