package baguchan.earthmobsmod.entity;

import baguchan.earthmobsmod.registry.ModEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.cow.AbstractCow;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.neoforged.neoforge.common.IShearable;

import java.util.List;

public class WoolyCow extends AbstractCow implements IShearable {
	private static final EntityDataAccessor<Boolean> SHEARED = SynchedEntityData.defineId(WoolyCow.class, EntityDataSerializers.BOOLEAN);
	private int eatAnimationTick;
	private EatBlockGoal eatBlockGoal;

	public WoolyCow(EntityType<? extends WoolyCow> p_28285_, Level p_28286_) {
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

	protected void defineSynchedData(SynchedEntityData.Builder builder) {
		super.defineSynchedData(builder);
		builder.define(SHEARED, false);
	}

	public WoolyCow getBreedOffspring(ServerLevel p_148884_, AgeableMob p_148885_) {
		return ModEntities.WOOLY_COW.get().create(p_148884_, EntitySpawnReason.BREEDING);
	}

	@Override
	public void addAdditionalSaveData(ValueOutput p_29864_) {
		super.addAdditionalSaveData(p_29864_);
		p_29864_.putBoolean("Sheared", this.isSheared());
	}

	@Override
	public void readAdditionalSaveData(ValueInput p_29845_) {
		super.readAdditionalSaveData(p_29845_);
		this.setSheared(p_29845_.getBooleanOr("Sheared", false));
	}

	protected void customServerAiStep(ServerLevel serverLevel) {
		this.eatAnimationTick = this.eatBlockGoal.getEatAnimationTick();
		super.customServerAiStep(serverLevel);
	}

	public void aiStep() {
        if (this.level().isClientSide()) {
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

	public boolean readyForShearing() {
		return this.isAlive() && !this.isSheared() && !this.isBaby();
	}


	@Override
	public boolean isShearable(@org.jetbrains.annotations.Nullable Player player, ItemStack item, Level level, BlockPos pos) {
		return readyForShearing();
	}

	@Override
	public List<ItemStack> onSheared(@org.jetbrains.annotations.Nullable Player player, ItemStack item, Level level, BlockPos pos) {
		level.playSound(null, this, SoundEvents.SHEEP_SHEAR, player == null ? SoundSource.BLOCKS : SoundSource.PLAYERS, 1.0F, 1.0F);
        if (!level.isClientSide()) {
			this.setSheared(true);
			int i = 1 + this.random.nextInt(3);

			java.util.List<ItemStack> items = new java.util.ArrayList<>();
			for (int j = 0; j < i; ++j) {
				items.add(new ItemStack(Blocks.WOOL.brown()));
			}
			return items;
		}
		return java.util.Collections.emptyList();
	}

	@Override
	public boolean canMate(Animal p_27569_) {
		if (p_27569_ == this) {
			return false;
		} else {
			if (p_27569_ instanceof WoolyCow) {
				return true;
			}
			return p_27569_.getClass() != this.getClass() ? false : this.isInLove() && p_27569_.isInLove();
		}
	}
}
