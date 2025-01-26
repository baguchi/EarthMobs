package baguchan.earthmobsmod.data;

import baguchan.earthmobsmod.registry.ModItems;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.ItemModelOutput;
import net.minecraft.client.data.models.model.ModelInstance;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.resources.ResourceLocation;

import java.util.function.BiConsumer;

public class EarthItemModels extends ItemModelGenerators {
    public EarthItemModels(ItemModelOutput p_387620_, BiConsumer<ResourceLocation, ModelInstance> p_387848_) {
        super(p_387620_, p_387848_);
    }

    @Override
    public void run() {
        this.createFlatItemModel(ModItems.BONE_SHARD.get(), ModelTemplates.FLAT_ITEM);
        this.createFlatItemModel(ModItems.BONE_SPIDER_EYE.get(), ModelTemplates.FLAT_ITEM);
        this.createFlatItemModel(ModItems.FANCY_FEATHER.get(), ModelTemplates.FLAT_ITEM);
        this.createFlatItemModel(ModItems.HARDER_FLESH.get(), ModelTemplates.FLAT_ITEM);
        this.createFlatItemModel(ModItems.SMELLY_EGG.get(), ModelTemplates.FLAT_ITEM);
        this.createFlatItemModel(ModItems.FANCY_EGG.get(), ModelTemplates.FLAT_ITEM);
        this.createFlatItemModel(ModItems.HORN.get(), ModelTemplates.FLAT_ITEM);
        this.createFlatItemModel(ModItems.HORN_FLUTE.get(), ModelTemplates.FLAT_ITEM);
        this.createFlatItemModel(ModItems.HYPER_RABBIT_FOOT.get(), ModelTemplates.FLAT_ITEM);
        this.createFlatItemModel(ModItems.ZOMBIFIED_RABBIT_FOOT.get(), ModelTemplates.FLAT_ITEM);
        this.createFlatItemModel(ModItems.MUD_BUCKET.get(), ModelTemplates.FLAT_ITEM);
        this.createFlatItemModel(ModItems.TROPICAL_SLIME_BUCKET.get(), ModelTemplates.FLAT_ITEM);
        this.createFlatItemModel(ModItems.TEACUP_PIG_POT.get(), ModelTemplates.FLAT_ITEM);
        this.createFlatItemModel(ModItems.RUBY.get(), ModelTemplates.FLAT_ITEM);
        this.createFlatItemModel(ModItems.TROPICAL_BALL.get(), ModelTemplates.FLAT_ITEM);

        this.generateSpawnEgg(ModItems.CLUCK_SHROOM_SPAWNEGG.get(), 0xB52C17, 0xDC883B);
        this.generateSpawnEgg(ModItems.FANCY_CHICKEN_SPAWNEGG.get(), 0xF4A213, 0x202F22);
        this.generateSpawnEgg(ModItems.WOOLY_COW_SPAWNEGG.get(), 0xDB8948, 0xFFDBB);
        this.generateSpawnEgg(ModItems.UMBRA_COW_SPAWNEGG.get(), 0x403E57, 0x0A0B1D);

        this.generateSpawnEgg(ModItems.TEACUP_PIG_SPAWNEGG.get(), 0xEEC9C1, 0xDD5555);
        this.generateSpawnEgg(ModItems.HORNED_SHEEP_SPAWNEGG.get(), 15198183, 16758197);
        this.generateSpawnEgg(ModItems.HYPER_RABBIT_SPAWNEGG.get(), 0xDA784A, 0xF4BF83);
        this.generateSpawnEgg(ModItems.MOOBLOOM_SPAWNEGG.get(), 0xFDCA00, 0xF7EDC1);
        this.generateSpawnEgg(ModItems.MOOLIP_SPAWNEGG.get(), 0xD882B0, 0xF1DFE8);
        this.generateSpawnEgg(ModItems.JUMBO_RABBIT_SPAWNEGG.get(), 0x9E5C48, 0xE5B2A3);
        this.generateSpawnEgg(ModItems.ZOMBIFILED_PIG_SPAWNEGG.get(), 15373203, 5009705);
        this.generateSpawnEgg(ModItems.JOLLY_LAMMA_SPAWNEGG.get(), 0x673727, 0xD2BFB2);
        this.generateSpawnEgg(ModItems.BONE_SPIDER_SPAWNEGG.get(), 0x461C2E, 0x6130B7);
        this.generateSpawnEgg(ModItems.STRAY_BONE_SPIDER_SPAWNEGG.get(), 0x20112F, 0x30B6B2);
        this.generateSpawnEgg(ModItems.ZOMBIFIED_RABBIT_SPAWNEGG.get(), 0x79AD69, 0x2A5131);

        this.generateSpawnEgg(ModItems.BOULDERING_ZOMBIE_SPAWNEGG.get(), 0x384242, 0x52261A);
        this.generateSpawnEgg(ModItems.BOULDERING_DROWNED_SPAWNEGG.get(), 0x56847E, 0x52261A);
        this.generateSpawnEgg(ModItems.BOULDERING_FROZEN_ZOMBIE_SPAWNEGG.get(), 0x679A90, 0x154954);
        this.generateSpawnEgg(ModItems.LOBBER_ZOMBIE_SPAWNEGG.get(), 0x899274, 0x436858);
        this.generateSpawnEgg(ModItems.LOBBER_DROWNED_SPAWNEGG.get(), 0x739274, 0x3E5F51);
        this.generateSpawnEgg(ModItems.LOBBER_HUSK_SPAWNEGG.get(), 0x6C6454, 0xCFCFCF);

        this.generateSpawnEgg(ModItems.TROPICAL_SLIME_SPAWNEGG.get(), 0x5B83AD, 0x90B1D3);
        this.generateSpawnEgg(ModItems.SKELETON_WOLF_SPAWNEGG.get(), 12698049, 480288);
        this.generateSpawnEgg(ModItems.VILER_WITCH_SPAWNEGG.get(), 0x111322, 0x37464D);
        this.generateSpawnEgg(ModItems.WITHER_SKELETON_WOLF_SPAWNEGG.get(), 1315860, 4672845);
        this.generateSpawnEgg(ModItems.MAGMA_COW_SPAWNEGG.get(), 0x2C2C33, 0xFBAA59);
        this.generateSpawnEgg(ModItems.MELON_GOLEM_SPAWNEGG.get(), 14283506, 0x34791);
        this.generateSpawnEgg(ModItems.FURNACE_GOLEM_SPAWNEGG.get(), 14405058, 0x8F584);
    }
}
