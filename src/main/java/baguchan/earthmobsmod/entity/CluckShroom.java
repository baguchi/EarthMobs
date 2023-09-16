package baguchan.earthmobsmod.entity;

import baguchan.earthmobsmod.registry.ModEntities;
import baguchan.earthmobsmod.registry.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.animal.Chicken;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FlowerBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.apache.commons.lang3.tuple.Pair;

import javax.annotation.Nullable;
import java.util.Optional;
import java.util.UUID;

public class CluckShroom extends Chicken implements Shearable, net.minecraftforge.common.IForgeShearable, IPlantMob {
	private static final EntityDataAccessor<String> DATA_TYPE = SynchedEntityData.defineId(CluckShroom.class, EntityDataSerializers.STRING);

	private UUID lastLightningBoltUUID;

	public CluckShroom(EntityType<? extends CluckShroom> p_28236_, Level p_28237_) {
		super(p_28236_, p_28237_);
	}

	@Nullable
	@Override
	public ItemEntity spawnAtLocation(ItemLike p_19999_) {
		//override to smelly egg
		if (p_19999_.asItem() == Items.EGG) {
			p_19999_ = ModItems.SMELLY_EGG.get();
		}

		return super.spawnAtLocation(p_19999_);
	}

	public static boolean checkCluckShroomSpawnRules(EntityType<CluckShroom> p_28949_, LevelAccessor p_28950_, MobSpawnType p_28951_, BlockPos p_28952_, RandomSource p_28953_) {
		return p_28950_.getBlockState(p_28952_.below()).is(Blocks.MYCELIUM) && p_28950_.getRawBrightness(p_28952_, 0) > 8;
	}

	public float getWalkTargetValue(BlockPos p_28933_, LevelReader p_28934_) {
		return p_28934_.getBlockState(p_28933_.below()).is(Blocks.MYCELIUM) ? 10.0F : p_28934_.getPathfindingCostFromLightLevels(p_28933_) - 0.5F;
	}

	public void thunderHit(ServerLevel p_28921_, LightningBolt p_28922_) {
		UUID uuid = p_28922_.getUUID();
		if (!uuid.equals(this.lastLightningBoltUUID)) {
			this.setCluckShroomType(this.getCluckShroomType() == CluckShroom.CluckShroomType.RED ? CluckShroom.CluckShroomType.BROWN : CluckShroom.CluckShroomType.RED);
			this.lastLightningBoltUUID = uuid;
			this.playSound(SoundEvents.ZOMBIE_VILLAGER_CONVERTED, 2.0F, 1.0F);
		}

	}

	protected void defineSynchedData() {
		super.defineSynchedData();
		this.entityData.define(DATA_TYPE, CluckShroom.CluckShroomType.RED.type);
	}

	@Override
	public java.util.List<ItemStack> onSheared(@javax.annotation.Nullable Player player, @javax.annotation.Nonnull ItemStack item, Level world, BlockPos pos, int fortune) {
		return shearInternal(player == null ? SoundSource.BLOCKS : SoundSource.PLAYERS);
	}

	public void shear(SoundSource p_28924_) {
		shearInternal(p_28924_).forEach(s -> this.level().addFreshEntity(new ItemEntity(this.level(), this.getX(), this.getY(1.0D), this.getZ(), s)));
	}

	private java.util.List<ItemStack> shearInternal(SoundSource p_28924_) {
		this.level().playSound((Player) null, this, SoundEvents.MOOSHROOM_SHEAR, p_28924_, 1.0F, 1.0F);
		if (!this.level().isClientSide()) {
			((ServerLevel) this.level()).sendParticles(ParticleTypes.EXPLOSION, this.getX(), this.getY(0.5D), this.getZ(), 1, 0.0D, 0.0D, 0.0D, 0.0D);
			this.discard();
			Chicken chickin = EntityType.CHICKEN.create(this.level());
			chickin.moveTo(this.getX(), this.getY(), this.getZ(), this.getYRot(), this.getXRot());
			chickin.setHealth(this.getHealth());
			chickin.yBodyRot = this.yBodyRot;
			if (this.hasCustomName()) {
				chickin.setCustomName(this.getCustomName());
				chickin.setCustomNameVisible(this.isCustomNameVisible());
			}

			if (this.isPersistenceRequired()) {
				chickin.setPersistenceRequired();
			}

			chickin.setInvulnerable(this.isInvulnerable());
			this.level().addFreshEntity(chickin);

			java.util.List<ItemStack> items = new java.util.ArrayList<>();
			for (int i = 0; i < 5; ++i) {
				items.add(new ItemStack(this.getCluckShroomType().blockState.getBlock()));
			}
			return items;
		}
		return java.util.Collections.emptyList();

	}

	public boolean readyForShearing() {
		return this.isAlive() && !this.isBaby();
	}

	public void addAdditionalSaveData(CompoundTag p_28944_) {
		super.addAdditionalSaveData(p_28944_);
		p_28944_.putString("Type", this.getCluckShroomType().type);
	}

	public void readAdditionalSaveData(CompoundTag p_28936_) {
		super.readAdditionalSaveData(p_28936_);
		this.setCluckShroomType(CluckShroom.CluckShroomType.byType(p_28936_.getString("Type")));

	}

	private Optional<Pair<MobEffect, Integer>> getEffectFromItemStack(ItemStack p_28957_) {
		Item item = p_28957_.getItem();
		if (item instanceof BlockItem) {
			Block block = ((BlockItem) item).getBlock();
			if (block instanceof FlowerBlock) {
				FlowerBlock flowerblock = (FlowerBlock) block;
				return Optional.of(Pair.of(flowerblock.getSuspiciousEffect(), flowerblock.getEffectDuration()));
			}
		}

		return Optional.empty();
	}

	public void setCluckShroomType(CluckShroom.CluckShroomType p_28929_) {
		this.entityData.set(DATA_TYPE, p_28929_.type);
	}

	public CluckShroom.CluckShroomType getCluckShroomType() {
		return CluckShroom.CluckShroomType.byType(this.entityData.get(DATA_TYPE));
	}

	public CluckShroom getBreedOffspring(ServerLevel p_148942_, AgeableMob p_148943_) {
		CluckShroom mushroomchickin = ModEntities.CLUCK_SHROOM.get().create(p_148942_);
		mushroomchickin.setCluckShroomType(this.getOffspringType((CluckShroom) p_148943_));
		return mushroomchickin;
	}

	private CluckShroom.CluckShroomType getOffspringType(CluckShroom p_28931_) {
		CluckShroom.CluckShroomType mushroomchickin$mushroomtype = this.getCluckShroomType();
		CluckShroom.CluckShroomType mushroomchickin$mushroomtype1 = p_28931_.getCluckShroomType();
		CluckShroom.CluckShroomType mushroomchickin$mushroomtype2;
		if (mushroomchickin$mushroomtype == mushroomchickin$mushroomtype1 && this.random.nextInt(1024) == 0) {
			mushroomchickin$mushroomtype2 = mushroomchickin$mushroomtype == CluckShroom.CluckShroomType.BROWN ? CluckShroom.CluckShroomType.RED : CluckShroom.CluckShroomType.BROWN;
		} else {
			mushroomchickin$mushroomtype2 = this.random.nextBoolean() ? mushroomchickin$mushroomtype : mushroomchickin$mushroomtype1;
		}

		return mushroomchickin$mushroomtype2;
	}

	@Override
	public boolean isShearable(@javax.annotation.Nonnull ItemStack item, Level world, BlockPos pos) {
		return readyForShearing();
	}

	@Override
	public Block getPlant() {
		return this.getCluckShroomType().blockState.getBlock();
	}

	public static enum CluckShroomType {
		RED("red", Blocks.RED_MUSHROOM.defaultBlockState()),
		BROWN("brown", Blocks.BROWN_MUSHROOM.defaultBlockState());

		final String type;
		final BlockState blockState;

		private CluckShroomType(String p_28967_, BlockState p_28968_) {
			this.type = p_28967_;
			this.blockState = p_28968_;
		}

		public BlockState getBlockState() {
			return this.blockState;
		}

		static CluckShroom.CluckShroomType byType(String p_28977_) {
			for (CluckShroom.CluckShroomType mushroomchickin$mushroomtype : values()) {
				if (mushroomchickin$mushroomtype.type.equals(p_28977_)) {
					return mushroomchickin$mushroomtype;
				}
			}

			return RED;
		}
	}
}
