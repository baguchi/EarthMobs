package baguchan.earthmobsmod.data;

import baguchan.earthmobsmod.EarthMobsMod;
import baguchan.earthmobsmod.registry.ModBlocks;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.data.ExistingFileHelper;

public class BlockTagGenerator extends BlockTagsProvider {
    public BlockTagGenerator(DataGenerator generator, ExistingFileHelper exFileHelper) {
        super(generator, EarthMobsMod.MODID, exFileHelper);
	}

	@Override
    protected void addTags() {
		this.tag(BlockTags.MINEABLE_WITH_AXE).add(ModBlocks.CARVED_MELON.get(), ModBlocks.CARVED_MELON_SHOOT.get());
        this.tag(BlockTags.MINEABLE_WITH_PICKAXE).add(ModBlocks.RUBY.get());
	}
}
