package baguchan.earthmobsmod.registry;

import baguchan.earthmobsmod.EarthMobsMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.FluidTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.material.Fluid;

public class ModTags {
	public static class Fluids {
		public static final TagKey<Fluid> MUD = tag("mud");

		private static TagKey<Fluid> tag(String name) {
            return FluidTags.create(Identifier.fromNamespaceAndPath(EarthMobsMod.MODID, name));
		}
	}

	public static class Entities {
		public static final TagKey<EntityType<?>> CAN_MUDDY = create("can_muddy");
        public static final TagKey<EntityType<?>> CAN_MOSS = create("can_moss");

		private static TagKey<EntityType<?>> create(String p_203849_) {
            return TagKey.create(Registries.ENTITY_TYPE, Identifier.fromNamespaceAndPath(EarthMobsMod.MODID, p_203849_));
		}
	}
}
