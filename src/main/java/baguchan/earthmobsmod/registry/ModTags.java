package baguchan.earthmobsmod.registry;

import baguchan.earthmobsmod.EarthMobsMod;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.FluidTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.material.Fluid;

public class ModTags {
	public static class Fluids {
		public static final TagKey<Fluid> MUD = tag("mud");

		private static TagKey<Fluid> tag(String name) {
			return FluidTags.create(new ResourceLocation(EarthMobsMod.MODID, name));
		}
	}
}
