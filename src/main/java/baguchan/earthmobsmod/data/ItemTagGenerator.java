package baguchan.earthmobsmod.data;

import baguchan.earthmobsmod.EarthMobsMod;
import baguchan.earthmobsmod.registry.ModBlocks;
import baguchan.earthmobsmod.registry.ModItems;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ItemTagGenerator extends ItemTagsProvider {
    public ItemTagGenerator(DataGenerator generator, BlockTagsProvider provider, ExistingFileHelper exFileHelper) {
        super(generator, provider, EarthMobsMod.MODID, exFileHelper);
	}

	@SuppressWarnings("unchecked")
	@Override
    protected void addTags() {
		tag(ItemTags.SMALL_FLOWERS).add(ModBlocks.BUTTERCUP.get().asItem(), ModBlocks.PINK_DAISY.get().asItem());
		tag(Tags.Items.FEATHERS).add(ModItems.FANCY_FEATHER.get());
		tag(Tags.Items.EGGS).add(ModItems.SMELLY_EGG.get(), ModItems.DUCK_EGG.get(), ModItems.FANCY_EGG.get());
	}
}