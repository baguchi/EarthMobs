package baguchan.earthmobsmod.registry;

import baguchan.earthmobsmod.EarthMobsMod;
import baguchan.earthmobsmod.fluid.MudFluid;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.material.Fluid;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModFluids {
    public static final DeferredRegister<Fluid> FLUIDS = DeferredRegister.create(BuiltInRegistries.FLUID, EarthMobsMod.MODID);

    public static final DeferredHolder<Fluid, MudFluid.Source> MUD = FLUIDS.register("mud", () -> new MudFluid.Source());
    public static final DeferredHolder<Fluid, MudFluid.Flowing> MUD_FLOW = FLUIDS.register("mud_flow", () -> new MudFluid.Flowing());

}
