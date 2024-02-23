package baguchan.earthmobsmod.registry;

import baguchan.earthmobsmod.EarthMobsMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.FluidTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.material.Fluid;

public class ModTags {
    public static class Biomes {
        public static final TagKey<Biome> NO_DEFAULT_MONSTERS = forgetag("no_default_monsters");

        private static TagKey<Biome> forgetag(String p_207631_) {
            return TagKey.create(Registries.BIOME, new ResourceLocation("forge", p_207631_));
        }
    }

	public static class Fluids {
		public static final TagKey<Fluid> MUD = tag("mud");

		private static TagKey<Fluid> tag(String name) {
			return FluidTags.create(new ResourceLocation(EarthMobsMod.MODID, name));
		}
	}

    public static class Entities {
        public static final TagKey<EntityType<?>> CAN_MUDDY = create("can_muddy");
        public static final TagKey<EntityType<?>> CAN_MOSS = create("can_moss");

        private static TagKey<EntityType<?>> create(String p_203849_) {
            return TagKey.create(Registries.ENTITY_TYPE, new ResourceLocation(EarthMobsMod.MODID, p_203849_));
        }
    }
}
