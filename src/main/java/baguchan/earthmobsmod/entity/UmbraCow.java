package baguchan.earthmobsmod.entity;

import baguchan.earthmobsmod.EarthMobsMod;
import baguchan.earthmobsmod.registry.ModEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.Cow;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;

import javax.annotation.Nullable;

public class UmbraCow extends WoolyCow {
	public static final ResourceLocation UMBRA_COW_SHEARD_LOOT_TABLE = new ResourceLocation(EarthMobsMod.MODID, "entities/umbra_cow_sheared");

	public UmbraCow(EntityType<? extends Cow> p_28285_, Level p_28286_) {
		super(p_28285_, p_28286_);
	}


	public ResourceLocation getDefaultLootTable() {
		if (this.isSheared()) {
			return UMBRA_COW_SHEARD_LOOT_TABLE;
		} else {
			return this.getType().getDefaultLootTable();
		}
	}

	public Cow getBreedOffspring(ServerLevel p_148884_, AgeableMob p_148885_) {
		return ModEntities.UMBRA_COW.get().create(p_148884_);
	}

	@Override
	public void shear(SoundSource p_29819_) {
		this.level().playSound((Player) null, this, SoundEvents.SHEEP_SHEAR, p_29819_, 1.0F, 1.0F);
		this.setSheared(true);
		int i = 1 + this.random.nextInt(3);

		for (int j = 0; j < i; ++j) {
			ItemEntity itementity = this.spawnAtLocation(Blocks.BLACK_WOOL, 1);
			if (itementity != null) {
				itementity.setDeltaMovement(itementity.getDeltaMovement().add((double) ((this.random.nextFloat() - this.random.nextFloat()) * 0.1F), (double) (this.random.nextFloat() * 0.05F), (double) ((this.random.nextFloat() - this.random.nextFloat()) * 0.1F)));
			}
		}

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
				items.add(new ItemStack(Blocks.BLACK_WOOL));
			}
			return items;
		}
		return java.util.Collections.emptyList();
	}
}
