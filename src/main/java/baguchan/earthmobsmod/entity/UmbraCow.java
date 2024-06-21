package baguchan.earthmobsmod.entity;

import baguchan.earthmobsmod.EarthMobsMod;
import baguchan.earthmobsmod.registry.ModEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.Cow;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.storage.loot.LootTable;

import java.util.List;

public class UmbraCow extends WoolyCow {
	public static final ResourceKey<LootTable> UMBRA_COW_SHEARD_LOOT_TABLE = ResourceKey.create(Registries.LOOT_TABLE, ResourceLocation.fromNamespaceAndPath(EarthMobsMod.MODID, "entities/umbra_cow_sheared"));

	public UmbraCow(EntityType<? extends Cow> p_28285_, Level p_28286_) {
		super(p_28285_, p_28286_);
	}


	public ResourceKey<LootTable> getDefaultLootTable() {
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
	public List<ItemStack> onSheared(@org.jetbrains.annotations.Nullable Player player, ItemStack item, Level level, BlockPos pos) {
		level.playSound(null, this, SoundEvents.SHEEP_SHEAR, player == null ? SoundSource.BLOCKS : SoundSource.PLAYERS, 1.0F, 1.0F);
		if (!level.isClientSide) {
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
