package baguchan.earthmobsmod.registry;

import baguchan.earthmobsmod.EarthMobsMod;
import baguchan.earthmobsmod.fluidtype.MudFluidType;
import net.neoforged.neoforge.fluids.FluidType;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.ForgeRegistries;
import net.neoforged.neoforge.registries.RegistryObject;

public class ModFluidTypes {
	public static final DeferredRegister<FluidType> FLUID_TYPES = DeferredRegister.create(ForgeRegistries.Keys.FLUID_TYPES, EarthMobsMod.MODID);

	public static final RegistryObject<FluidType> MUD = FLUID_TYPES.register("mud", () -> new MudFluidType(FluidType.Properties.create().canExtinguish(true).motionScale(0.005F).viscosity(1400).fallDistanceModifier(0F).supportsBoating(true)));
}
