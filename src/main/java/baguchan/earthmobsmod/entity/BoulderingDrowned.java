package baguchan.earthmobsmod.entity;

import com.google.common.collect.Maps;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Difficulty;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.navigation.WallClimberNavigation;
import net.minecraft.world.entity.ai.navigation.WaterBoundPathNavigation;
import net.minecraft.world.entity.monster.Drowned;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.phys.Vec3;

import java.util.Map;

public class BoulderingDrowned extends Drowned {
	private static final EntityDataAccessor<Byte> DATA_FLAGS_ID = SynchedEntityData.defineId(BoulderingDrowned.class, EntityDataSerializers.BYTE);

	protected final WaterBoundPathNavigation waterNavigation;
	protected final WallClimberNavigation groundNavigation;
	boolean searchingForLand;


	public BoulderingDrowned(EntityType<? extends BoulderingDrowned> p_34271_, Level p_34272_) {
		super(p_34271_, p_34272_);
		this.waterNavigation = new WaterBoundPathNavigation(this, p_34272_);
		this.groundNavigation = new WallClimberNavigation(this, p_34272_);
	}

	public static AttributeSupplier.Builder createAttributes() {
		return Monster.createMonsterAttributes().add(Attributes.FOLLOW_RANGE, 35.0D).add(Attributes.MOVEMENT_SPEED, (double) 0.22F).add(Attributes.ATTACK_DAMAGE, 4.0D).add(Attributes.ARMOR, 3.0D).add(Attributes.KNOCKBACK_RESISTANCE, 0.15D).add(Attributes.SPAWN_REINFORCEMENTS_CHANCE);
	}

	protected void defineSynchedData() {
		super.defineSynchedData();
		this.entityData.define(DATA_FLAGS_ID, (byte) 0);
	}

	public void tick() {
		super.tick();
		if (!this.level().isClientSide) {
			this.setClimbing(this.horizontalCollision);
		}
	}

	boolean wantsToSwim() {
		if (this.searchingForLand) {
			return true;
		} else {
			LivingEntity livingentity = this.getTarget();
			return livingentity != null && livingentity.isInWater();
		}
	}

	public void updateSwimming() {
		if (!this.level().isClientSide) {
			if (this.isEffectiveAi() && this.isInWater() && this.wantsToSwim()) {
				this.navigation = this.waterNavigation;
				this.setSwimming(true);
			} else {
				this.navigation = this.groundNavigation;
				this.setSwimming(false);
			}
		}

	}

	public boolean onClimbable() {
		return this.isClimbing();
	}

	public boolean isClimbing() {
		return (this.entityData.get(DATA_FLAGS_ID) & 1) != 0;
	}

	public void setClimbing(boolean p_33820_) {
		byte b0 = this.entityData.get(DATA_FLAGS_ID);
		if (p_33820_) {
			b0 = (byte) (b0 | 1);
		} else {
			b0 = (byte) (b0 & -2);
		}

		this.entityData.set(DATA_FLAGS_ID, b0);
	}


	@Override
	protected void populateDefaultEquipmentSlots(RandomSource p_218953_, DifficultyInstance p_218954_) {
		if ((double) p_218953_.nextFloat() > 0.9D) {
			int i = p_218953_.nextInt(16);
			if (i < 10) {
				ItemStack stack = new ItemStack(Items.TRIDENT);
				Map<Enchantment, Integer> map = Maps.newHashMap();
				map.put(Enchantments.RIPTIDE, 1);
				EnchantmentHelper.setEnchantments(map, stack);
				this.setItemSlot(EquipmentSlot.MAINHAND, stack);
			} else {
				this.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(Items.FISHING_ROD));
			}
		}
	}

	public static boolean checkBoulderingDrownedSpawnRules(EntityType<BoulderingDrowned> p_32350_, ServerLevelAccessor p_32351_, MobSpawnType p_32352_, BlockPos p_32353_, RandomSource p_32354_) {
		if (!p_32351_.getFluidState(p_32353_.below()).is(FluidTags.WATER)) {
			return false;
		} else {
			Holder<Biome> holder = p_32351_.getBiome(p_32353_);
			boolean flag = p_32351_.getDifficulty() != Difficulty.PEACEFUL && isDarkEnoughToSpawn(p_32351_, p_32353_, p_32354_) && (p_32352_ == MobSpawnType.SPAWNER || p_32351_.getFluidState(p_32353_).is(FluidTags.WATER));
			if (!holder.is(Biomes.RIVER) && !holder.is(Biomes.FROZEN_RIVER)) {
				return p_32354_.nextInt(40) == 0 && isDeepEnoughToSpawn(p_32351_, p_32353_) && flag;
			} else {
				return p_32354_.nextInt(15) == 0 && flag;
			}
		}
	}

	private static boolean isDeepEnoughToSpawn(LevelAccessor p_32367_, BlockPos p_32368_) {
		return p_32368_.getY() < p_32367_.getSeaLevel() - 5;
	}

	public void performRangedAttack(LivingEntity p_32356_, float p_32357_) {
		if (this.distanceToSqr(p_32356_) < 42) {
			int j = EnchantmentHelper.getRiptide(this.getMainHandItem());
			int k = EnchantmentHelper.getRiptide(this.getOffhandItem());
			if (j > 0 || k > 0) {

				double f1 = p_32356_.getX() - this.getX();
				double f2 = p_32356_.getY() - this.getY();
				double f3 = p_32356_.getZ() - this.getZ();
				float f4 = Mth.sqrt((float) (f1 * f1 + f2 * f2 + f3 * f3));
				float f5 = 3.0F * ((1.0F + (float) j) / 4.0F);
				f1 *= f5 / f4;
				f2 *= f5 / f4;
				f3 *= f5 / f4;
				this.push((double) f1, (double) f2, (double) f3);
				this.startAutoSpinAttack(30);
				if (this.onGround()) {
					float f6 = 1.1999999F;
					this.move(MoverType.SELF, new Vec3(0.0D, (double) 1.1999999F, 0.0D));
				}

				SoundEvent soundevent;
				if (j >= 3 || k >= 3) {
					soundevent = SoundEvents.TRIDENT_RIPTIDE_3;
				} else if (j == 2 || k == 2) {
					soundevent = SoundEvents.TRIDENT_RIPTIDE_2;
				} else {
					soundevent = SoundEvents.TRIDENT_RIPTIDE_1;
				}

				this.playSound(soundevent, 1.0F, 1.0F);
			}
		} else {
			super.performRangedAttack(p_32356_, p_32357_);
		}
	}

	@Override
	public void travel(Vec3 p_32394_) {
		if (this.isControlledByLocalInstance() && this.isInWater() && this.wantsToSwim()) {
			this.moveRelative(0.0175F, p_32394_);
			this.move(MoverType.SELF, this.getDeltaMovement());
			this.setDeltaMovement(this.getDeltaMovement().scale(0.9D));
		} else {
			super.travel(p_32394_);
		}

	}


	public void startAutoSpinAttack(int p_204080_) {
		this.autoSpinAttackTicks = p_204080_;
		if (!this.level().isClientSide) {
			this.setLivingEntityFlag(4, true);
		}
	}

	protected void doAutoAttackOnTouch(LivingEntity p_36355_) {
		p_36355_.hurt(this.damageSources().mobAttack(this), 9);
	}
}
