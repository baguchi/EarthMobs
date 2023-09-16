package baguchan.earthmobsmod.data;

import baguchan.earthmobsmod.EarthMobsMod;
import baguchan.earthmobsmod.registry.ModBlocks;
import baguchan.earthmobsmod.registry.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;

import static baguchan.earthmobsmod.EarthMobsMod.prefix;

public class ItemModelGenerator extends ItemModelProvider {
    public ItemModelGenerator(PackOutput generator, ExistingFileHelper existingFileHelper) {
        super(generator, EarthMobsMod.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        this.toBlock(ModBlocks.RUBY.get());
        this.itemBlockFlat(ModBlocks.BUTTERCUP.get());
        this.itemBlockFlat(ModBlocks.PINK_DAISY.get());
        this.toBlock(ModBlocks.CARVED_MELON.get());
        this.toBlock(ModBlocks.CARVED_MELON_SHOOT.get());
        this.toBlock(ModBlocks.TROPICAL_SLIME_BLOCK.get());

        this.basicItem(ModItems.BONE_SHARD.get());
        this.basicItem(ModItems.BONE_SPIDER_EYE.get());
        this.basicItem(ModItems.FANCY_FEATHER.get());
        this.basicItem(ModItems.SMELLY_EGG.get());
        this.basicItem(ModItems.DUCK_EGG.get());
        this.basicItem(ModItems.FANCY_EGG.get());
        this.basicItem(ModItems.HORN.get());
        this.basicItem(ModItems.HORN_FLUTE.get());
        this.basicItem(ModItems.HYPER_RABBIT_FOOT.get());
        this.basicItem(ModItems.MUD_BUCKET.get());
        this.basicItem(ModItems.TROPICAL_SLIME_BUCKET.get());
        this.basicItem(ModItems.TEACUP_PIG_POT.get());
        this.basicItem(ModItems.RUBY.get());
        this.basicItem(ModItems.TROPICAL_BALL.get());

        this.spawnEgg(ModItems.CLUCK_SHROOM_SPAWNEGG.get());
        this.spawnEgg(ModItems.FANCY_CHICKEN_SPAWNEGG.get());
        this.spawnEgg(ModItems.WOOLY_COW_SPAWNEGG.get());
        this.spawnEgg(ModItems.UMBRA_COW_SPAWNEGG.get());
        this.spawnEgg(ModItems.ALBINO_COW_SPAWNEGG.get());
        this.spawnEgg(ModItems.CREAM_COW_SPAWNEGG.get());
        this.spawnEgg(ModItems.TEACUP_PIG_SPAWNEGG.get());
        this.spawnEgg(ModItems.HORNED_SHEEP_SPAWNEGG.get());
        this.spawnEgg(ModItems.HYPER_RABBIT_SPAWNEGG.get());
        this.spawnEgg(ModItems.MOOBLOOM_SPAWNEGG.get());
        this.spawnEgg(ModItems.MOOLIP_SPAWNEGG.get());
        this.spawnEgg(ModItems.JUMBO_RABBIT_SPAWNEGG.get());
        this.spawnEgg(ModItems.ZOMBIFILED_PIG_SPAWNEGG.get());
        this.spawnEgg(ModItems.DUCK_SPAWNEGG.get());
        this.spawnEgg(ModItems.JOLLY_LAMMA_SPAWNEGG.get());
        this.spawnEgg(ModItems.BONE_SPIDER_SPAWNEGG.get());
        this.spawnEgg(ModItems.STRAY_BONE_SPIDER_SPAWNEGG.get());
        this.spawnEgg(ModItems.BABY_GHAST_SPAWNEGG.get());
        this.spawnEgg(ModItems.BOULDERING_ZOMBIE_SPAWNEGG.get());
        this.spawnEgg(ModItems.BOULDERING_DROWNED_SPAWNEGG.get());
        this.spawnEgg(ModItems.LOBBER_ZOMBIE_SPAWNEGG.get());
        this.spawnEgg(ModItems.LOBBER_DROWNED_SPAWNEGG.get());
        this.spawnEgg(ModItems.TROPICAL_SLIME_SPAWNEGG.get());
        this.spawnEgg(ModItems.SKELETON_WOLF_SPAWNEGG.get());
        this.spawnEgg(ModItems.VILER_WITCH_SPAWNEGG.get());
        this.spawnEgg(ModItems.WITHER_SKELETON_WOLF_SPAWNEGG.get());
        this.spawnEgg(ModItems.MAGMA_COW_SPAWNEGG.get());
        this.spawnEgg(ModItems.MELON_GOLEM_SPAWNEGG.get());
        this.spawnEgg(ModItems.FURNACE_GOLEM_SPAWNEGG.get());
    }

    public ItemModelBuilder itemBlockFlat(Block block) {
        return itemBlockFlat(block, blockName(block));
    }

    public ItemModelBuilder itemBlockFlat(Block block, String name) {
        return withExistingParent(blockName(block), mcLoc("item/generated"))
                .texture("layer0", modLoc("block/" + name));
    }

    private void toBlock(Block b) {
        toBlockModel(b, ForgeRegistries.BLOCKS.getKey(b).getPath());
    }

    private void toBlockModel(Block b, String model) {
        toBlockModel(b, prefix("block/" + model));
    }

    private void toBlockModel(Block b, ResourceLocation model) {
        withExistingParent(ForgeRegistries.BLOCKS.getKey(b).getPath(), model);
    }

    public String blockName(Block block) {
        return ForgeRegistries.BLOCKS.getKey(block).getPath();
    }

    public ItemModelBuilder spawnEgg(Item item) {
        return withExistingParent(ForgeRegistries.ITEMS.getKey(item).getPath(), mcLoc("item/template_spawn_egg"));
    }
}
