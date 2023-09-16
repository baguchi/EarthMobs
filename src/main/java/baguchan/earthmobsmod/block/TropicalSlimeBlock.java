package baguchan.earthmobsmod.block;

import baguchan.earthmobsmod.registry.ModBlocks;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SlimeBlock;
import net.minecraft.world.level.block.state.BlockState;

public class TropicalSlimeBlock extends SlimeBlock {

    public TropicalSlimeBlock(Properties properties) {
        super(properties);
    }

    @Override
    public boolean canStickTo(BlockState state, BlockState other) {
        if (state.getBlock() == ModBlocks.TROPICAL_SLIME_BLOCK.get() && other.getBlock() == Blocks.SLIME_BLOCK)
            return false;
        if (state.getBlock() == ModBlocks.TROPICAL_SLIME_BLOCK.get() && other.getBlock() == Blocks.HONEY_BLOCK)
            return false;
        return super.canStickTo(state, other);
    }

    @Override
    public boolean isStickyBlock(BlockState state) {
        return true;
    }
}
