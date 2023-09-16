package baguchan.earthmobsmod.data;

import baguchan.earthmobsmod.EarthMobsMod;
import baguchan.earthmobsmod.registry.ModBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

import java.util.concurrent.CompletableFuture;

public class BlockTagGenerator extends BlockTagsProvider {
	public BlockTagGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, ExistingFileHelper existingFileHelper) {
		super(output, lookupProvider, EarthMobsMod.MODID, existingFileHelper);
	}

	@Override
	protected void addTags(HolderLookup.Provider p_256380_) {
		this.tag(BlockTags.MINEABLE_WITH_AXE).add(ModBlocks.CARVED_MELON.get(), ModBlocks.CARVED_MELON_SHOOT.get());
        this.tag(BlockTags.MINEABLE_WITH_PICKAXE).add(ModBlocks.RUBY.get());
	}
}
