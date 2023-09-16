package baguchan.earthmobsmod.data;

import baguchan.earthmobsmod.registry.ModBlocks;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootTable;

import java.util.HashSet;
import java.util.Set;

public class BlockLootTables extends BlockLootSubProvider {
    private final Set<Block> knownBlocks = new HashSet<>();
    // [VanillaCopy] super
    private static final float[] DEFAULT_SAPLING_DROP_RATES = new float[]{0.05F, 0.0625F, 0.083333336F, 0.1F};
    private static final float[] RARE_SAPLING_DROP_RATES = new float[]{0.025F, 0.027777778F, 0.03125F, 0.041666668F, 0.1F};

    private static final Set<Item> EXPLOSION_RESISTANT = Set.of();


    protected BlockLootTables() {
        super(EXPLOSION_RESISTANT, FeatureFlags.REGISTRY.allFlags());
    }

    @Override
    protected void add(Block block, LootTable.Builder builder) {
        super.add(block, builder);
        knownBlocks.add(block);
    }

    @Override
    protected void generate() {
        this.dropSelf(ModBlocks.BUTTERCUP.get());
        this.dropSelf(ModBlocks.PINK_DAISY.get());
        this.dropSelf(ModBlocks.CARVED_MELON_SHOOT.get());
        this.dropSelf(ModBlocks.CARVED_MELON.get());
        this.dropSelf(ModBlocks.RUBY.get());
        this.dropSelf(ModBlocks.TROPICAL_SLIME_BLOCK.get());
    }

    private void registerEmpty(Block b) {
        add(b, LootTable.lootTable());
    }

    private void registerSlab(Block b) {
        add(b, createSlabItemTable(b));
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return knownBlocks;
    }
}
