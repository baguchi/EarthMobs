package baguchan.earthmobsmod.entity;

import baguchan.earthmobsmod.EarthMobsMod;
import baguchan.earthmobsmod.registry.ModEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Shearable;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.animal.Cow;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;

import javax.annotation.Nullable;

public class WoolyCow extends Cow implements Shearable, net.minecraftforge.common.IForgeShearable {
	private static final EntityDataAccessor<Boolean> SHEARED = SynchedEntityData.defineId(WoolyCow.class, EntityDataSerializers.BOOLEAN);
	private int eatAnimationTick;
	private EatBlockGoal eatBlockGoal;

	public static final ResourceLocation WOOLY_COW_SHEARD_LOOT_TABLE = new ResourceLocation(EarthMobsMod.MODID, "entities/wooly_cow_sheared");

	public WoolyCow(EntityType<? extends Cow> p_28285_, Level p_28286_) {
		super(p_28285_, p_28286_);
	}

	protected void registerGoals() {
		this.eatBlockGoal = new EatBlockGoal(this);
		this.goalSelector.addGoal(0, new FloatGoal(this));
		this.goalSelector.addGoal(1, new PanicGoal(this, 1.25D));
		this.goalSelector.addGoal(2, new BreedGoal(this, 1.0D));
		this.goalSelector.addGoal(3, new TemptGoal(this, 1.1D, Ingredient.of(Items.WHEAT), false));
		this.goalSelector.addGoal(4, new FollowParentGoal(this, 1.1D));
		this.goalSelector.addGoal(5, this.eatBlockGoal);
		this.goalSelector.addGoal(6, new WaterAvoidingRandomStrollGoal(this, 1.0D));
		this.goalSelector.addGoal(7, new LookAtPlayerGoal(this, Player.class, 6.0F));
		this.goalSelector.addGoal(8, new RandomLookAroundGoal(this));
	}

	protected void defineSynchedData() {
		super.defineSynchedData();
		this.entityData.define(SHEARED, false);
	}

	public ResourceLocation getDefaultLootTable() {
		if (this.isSheared()) {
			return WOOLY_COW_SHEARD_LOOT_TABLE;
		} else {
			return this.getType().getDefaultLootTable();
		}
	}

	public Cow getBreedOffspring(ServerLevel p_148884_, AgeableMob p_148885_) {
		return ModEntities.WOOLY_COW.get().create(p_148884_);
	}

	public void addAdditionalSaveData(CompoundTag p_29864_) {
		super.addAdditionalSaveData(p_29864_);
		p_29864_.putBoolean("Sheared", this.isSheared());
	}

	public void readAdditionalSaveData(CompoundTag p_29845_) {
		super.readAdditionalSaveData(p_29845_);
		this.setSheared(p_29845_.getBoolean("Sheared"));
	}

	protected void customServerAiStep() {
		this.eatAnimationTick = this.eatBlockGoal.getEatAnimationTick();
		super.customServerAiStep();
	}

	public void aiStep() {
		if (this.level().isClientSide) {
			this.eatAnimationTick = Math.max(0, this.eatAnimationTick - 1);
		}

		super.aiStep();
	}

	public void handleEntityEvent(byte p_29814_) {
		if (p_29814_ == 10) {
			this.eatAnimationTick = 40;
		} else {
			super.handleEntityEvent(p_29814_);
		}

	}

	public float getHeadEatPositionScale(float p_29881_) {
		if (this.eatAnimationTick <= 0) {
			return 0.0F;
		} else if (this.eatAnimationTick >= 4 && this.eatAnimationTick <= 36) {
			return 1.0F;
		} else {
			return this.eatAnimationTick < 4 ? ((float) this.eatAnimationTick - p_29881_) / 4.0F : -((float) (this.eatAnimationTick - 40) - p_29881_) / 4.0F;
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

	public boolean isSheared() {
		return this.entityData.get(SHEARED);
	}

	public void setSheared(boolean p_29879_) {
		this.entityData.set(SHEARED, p_29879_);
	}

	public void ate() {
		this.setSheared(false);
		if (this.isBaby()) {
			this.ageUp(60);
		}

	}

	@Override
	public void shear(SoundSource p_29819_) {
		this.level().playSound((Player) null, this, SoundEvents.SHEEP_SHEAR, p_29819_, 1.0F, 1.0F);
		this.setSheared(true);
		int i = 1 + this.random.nextInt(3);

		for (int j = 0; j < i; ++j) {
			ItemEntity itementity = this.spawnAtLocation(Blocks.BROWN_WOOL, 1);
			if (itementity != null) {
				itementity.setDeltaMovement(itementity.getDeltaMovement().add((double) ((this.random.nextFloat() - this.random.nextFloat()) * 0.1F), (double) (this.random.nextFloat() * 0.05F), (double) ((this.random.nextFloat() - this.random.nextFloat()) * 0.1F)));
			}
		}

	}

	public boolean readyForShearing() {
		return this.isAlive() && !this.isSheared() && !this.isBaby();
	}

	@Override
	public boolean isShearable(@javax.annotation.Nonnull ItemStack item, Level world, BlockPos pos) {
		return readyForShearing();
	}

	@javax.annotation.Nonnull
	@Override
	public java.util.List<ItemStack> onSheared(@Nullable Player player, @javax.annotation.Nonnull ItemStack item, Level world, BlockPos pos, int fortune) {
		world.playSound(null, this, SoundEvents.SHEEP_SHEAR, player == null ? SoundSource.BLOCKS : SoundSource.PLAYERS, 1.0F, 1.0F);
		if (!world.isClientSide) {
			this.setSheared(true);
			int i = 1 + this.random.nextInt(3);

			java.util.List<ItemStack> items = new java.util.ArrayList<>();
			for (int j = 0; j < i; ++j) {
				items.add(new ItemStack(Blocks.BROWN_WOOL));
			}
			return items;
		}
		return java.util.Collections.emptyList();
	}
}
