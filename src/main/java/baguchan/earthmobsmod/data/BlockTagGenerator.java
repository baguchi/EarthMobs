package baguchan.earthmobsmod.data;

import baguchan.earthmobsmod.EarthMobsMod;
import baguchan.earthmobsmod.registry.ModBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.neoforged.neoforge.common.data.BlockTagsProvider;

import java.util.concurrent.CompletableFuture;

public class BlockTagGenerator extends BlockTagsProvider {
	public BlockTagGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
		super(output, lookupProvider, EarthMobsMod.MODID);
	}

	@Override
	protected void addTags(HolderLookup.Provider p_256380_) {
		this.tag(BlockTags.MINEABLE_WITH_AXE).add(ModBlocks.CARVED_MELON.get(), ModBlocks.CARVED_MELON_SHOOT.get());
		this.tag(BlockTags.SMALL_FLOWERS).add(ModBlocks.BUTTERCUP.get()).add(ModBlocks.PINK_DAISY.get());
	}
}
