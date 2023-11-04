package baguchan.earthmobsmod.registry;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.common.NeoForgeMod;
import net.neoforged.neoforge.event.EventHooks;
import net.neoforged.neoforge.fluids.FluidInteractionRegistry;

public class ModInteractionInformations {
	public static void init() {
        FluidInteractionRegistry.addInteraction(NeoForgeMod.LAVA_TYPE.get(), new FluidInteractionRegistry.InteractionInformation(
				ModFluidTypes.MUD.get(),
				fluidState -> Blocks.DIRT.defaultBlockState()
		));
        FluidInteractionRegistry.addInteraction(NeoForgeMod.WATER_TYPE.get(), new FluidInteractionRegistry.InteractionInformation(
				(level, currentPos, relativePos, currentState) -> level.getFluidState(relativePos).getFluidType() == ModFluidTypes.MUD.get(), (level, currentPos, relativePos, currentState) ->
		{
            level.setBlockAndUpdate(currentPos, EventHooks.fireFluidPlaceBlockEvent(level, currentPos, currentPos, Blocks.MUD.defaultBlockState()));
			level.levelEvent(2001, currentPos, Block.getId(Blocks.MUD.defaultBlockState()));
		}));
	}
}
