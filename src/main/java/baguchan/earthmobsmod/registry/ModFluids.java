package baguchan.earthmobsmod.registry;

import baguchan.earthmobsmod.EarthMobsMod;
import baguchan.earthmobsmod.fluid.MudFluid;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModFluids {
    public static final DeferredRegister<Fluid> FLUIDS = DeferredRegister.create(BuiltInRegistries.FLUID, EarthMobsMod.MODID);

    public static final Supplier<FlowingFluid> MUD = FLUIDS.register("mud", () -> new MudFluid.Source());
    public static final Supplier<FlowingFluid> MUD_FLOW = FLUIDS.register("mud_flow", () -> new MudFluid.Flowing());

}
