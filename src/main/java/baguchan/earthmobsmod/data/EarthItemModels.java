package baguchan.earthmobsmod.data;

import baguchan.earthmobsmod.registry.ModItems;
import net.minecraft.client.color.item.ItemTintSource;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.ItemModelOutput;
import net.minecraft.client.data.models.model.ItemModelUtils;
import net.minecraft.client.data.models.model.ModelInstance;
import net.minecraft.client.data.models.model.ModelLocationUtils;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.Item;

import java.util.function.BiConsumer;

public class EarthItemModels extends ItemModelGenerators {
    public EarthItemModels(ItemModelOutput p_387620_, BiConsumer<Identifier, ModelInstance> p_387848_) {
        super(p_387620_, p_387848_);
    }

    @Override
    public void run() {
        this.generateFlatItem(ModItems.BONE_SHARD.get(), ModelTemplates.FLAT_ITEM);
        this.generateFlatItem(ModItems.BONE_SPIDER_EYE.get(), ModelTemplates.FLAT_ITEM);
        this.generateFlatItem(ModItems.FANCY_FEATHER.get(), ModelTemplates.FLAT_ITEM);
        this.generateFlatItem(ModItems.HARDER_FLESH.get(), ModelTemplates.FLAT_ITEM);
        this.generateFlatItem(ModItems.SMELLY_EGG.get(), ModelTemplates.FLAT_ITEM);
        this.generateFlatItem(ModItems.FANCY_EGG.get(), ModelTemplates.FLAT_ITEM);
        this.generateFlatItem(ModItems.HORN.get(), ModelTemplates.FLAT_ITEM);
        this.generateFlatItem(ModItems.HORN_FLUTE.get(), ModelTemplates.FLAT_ITEM);
        this.generateFlatItem(ModItems.HYPER_RABBIT_FOOT.get(), ModelTemplates.FLAT_ITEM);
        this.generateFlatItem(ModItems.ZOMBIFIED_RABBIT_FOOT.get(), ModelTemplates.FLAT_ITEM);
        this.generateFlatItem(ModItems.HUSK_RABBIT_FOOT.get(), ModelTemplates.FLAT_ITEM);
        this.generateFlatItem(ModItems.MUD_BUCKET.get(), ModelTemplates.FLAT_ITEM);
        this.generateFlatItem(ModItems.TROPICAL_SLIME_BUCKET.get(), ModelTemplates.FLAT_ITEM);
        this.generateFlatItem(ModItems.TEACUP_PIG_POT.get(), ModelTemplates.FLAT_ITEM);
        this.generateFlatItem(ModItems.RUBY.get(), ModelTemplates.FLAT_ITEM);
        this.generateFlatItem(ModItems.TROPICAL_BALL.get(), ModelTemplates.FLAT_ITEM);

        this.generateFlatItem(ModItems.CLUCK_SHROOM_SPAWNEGG.get(), ModelTemplates.FLAT_ITEM);
        this.generateFlatItem(ModItems.FANCY_CHICKEN_SPAWNEGG.get(), ModelTemplates.FLAT_ITEM);
        this.generateFlatItem(ModItems.WOOLY_COW_SPAWNEGG.get(), ModelTemplates.FLAT_ITEM);
        this.generateFlatItem(ModItems.UMBRA_COW_SPAWNEGG.get(), ModelTemplates.FLAT_ITEM);

        this.generateFlatItem(ModItems.TEACUP_PIG_SPAWNEGG.get(), ModelTemplates.FLAT_ITEM);
        this.generateFlatItem(ModItems.HORNED_SHEEP_SPAWNEGG.get(), ModelTemplates.FLAT_ITEM);
        this.generateFlatItem(ModItems.HYPER_RABBIT_SPAWNEGG.get(), ModelTemplates.FLAT_ITEM);
        this.generateFlatItem(ModItems.MOOBLOOM_SPAWNEGG.get(), ModelTemplates.FLAT_ITEM);
        this.generateFlatItem(ModItems.MOOLIP_SPAWNEGG.get(), ModelTemplates.FLAT_ITEM);
        this.generateFlatItem(ModItems.JUMBO_RABBIT_SPAWNEGG.get(), ModelTemplates.FLAT_ITEM);
        this.generateFlatItem(ModItems.ZOMBIFILED_PIG_SPAWNEGG.get(), ModelTemplates.FLAT_ITEM);
        this.generateFlatItem(ModItems.JOLLY_LAMMA_SPAWNEGG.get(), ModelTemplates.FLAT_ITEM);
        this.generateFlatItem(ModItems.BONE_SPIDER_SPAWNEGG.get(), ModelTemplates.FLAT_ITEM);
        this.generateFlatItem(ModItems.ZOMBIFIED_RABBIT_SPAWNEGG.get(), ModelTemplates.FLAT_ITEM);
        this.generateSpawnEgg(ModItems.HUSK_RABBIT_SPAWNEGG.get(), 0x544937, 0x1A1714);

        this.generateFlatItem(ModItems.BOULDERING_ZOMBIE_SPAWNEGG.get(), ModelTemplates.FLAT_ITEM);
        this.generateFlatItem(ModItems.BOULDERING_DROWNED_SPAWNEGG.get(), ModelTemplates.FLAT_ITEM);
        this.generateFlatItem(ModItems.LOBBER_ZOMBIE_SPAWNEGG.get(), ModelTemplates.FLAT_ITEM);
        this.generateFlatItem(ModItems.LOBBER_DROWNED_SPAWNEGG.get(), ModelTemplates.FLAT_ITEM);

        this.generateFlatItem(ModItems.TROPICAL_SLIME_SPAWNEGG.get(), ModelTemplates.FLAT_ITEM);
        this.generateFlatItem(ModItems.SKELETON_WOLF_SPAWNEGG.get(), ModelTemplates.FLAT_ITEM);
        this.generateFlatItem(ModItems.VILER_WITCH_SPAWNEGG.get(), ModelTemplates.FLAT_ITEM);
        this.generateFlatItem(ModItems.WITHER_SKELETON_WOLF_SPAWNEGG.get(), ModelTemplates.FLAT_ITEM);
        this.generateFlatItem(ModItems.MAGMA_COW_SPAWNEGG.get(), ModelTemplates.FLAT_ITEM);
        this.generateFlatItem(ModItems.MELON_GOLEM_SPAWNEGG.get(), ModelTemplates.FLAT_ITEM);
        this.generateFlatItem(ModItems.FURNACE_GOLEM_SPAWNEGG.get(), ModelTemplates.FLAT_ITEM);
    }

    public void generateSpawnEgg(Item p_387114_, int p_387737_, int p_387138_) {
        Identifier resourcelocation = ModelLocationUtils.decorateItemModelLocation("bagus_lib:template_spawn_egg");
        this.itemModelOutput.accept(p_387114_, ItemModelUtils.tintedModel(resourcelocation, new ItemTintSource[]{ItemModelUtils.constantTint(p_387737_), ItemModelUtils.constantTint(p_387138_)}));
    }
}
