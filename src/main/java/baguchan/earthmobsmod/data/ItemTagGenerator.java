package baguchan.earthmobsmod.data;

import baguchan.earthmobsmod.EarthMobsMod;
import baguchan.earthmobsmod.registry.ModBlocks;
import baguchan.earthmobsmod.registry.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;

import java.util.concurrent.CompletableFuture;

public class ItemTagGenerator extends ItemTagsProvider {
	public ItemTagGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, CompletableFuture<TagsProvider.TagLookup<Block>> provider, ExistingFileHelper exFileHelper) {
		super(output, lookupProvider, provider, EarthMobsMod.MODID, exFileHelper);
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void addTags(HolderLookup.Provider p_256380_) {
		tag(ItemTags.SMALL_FLOWERS).add(ModBlocks.BUTTERCUP.get().asItem(), ModBlocks.PINK_DAISY.get().asItem());
		tag(Tags.Items.FEATHERS).add(ModItems.FANCY_FEATHER.get());
		tag(Tags.Items.EGGS).add(ModItems.SMELLY_EGG.get(), ModItems.DUCK_EGG.get(), ModItems.FANCY_EGG.get());
	}
}