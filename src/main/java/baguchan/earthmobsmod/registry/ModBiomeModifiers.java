package baguchan.earthmobsmod.registry;

import baguchan.earthmobsmod.EarthMobsMod;
import baguchan.earthmobsmod.world.biome.EarthBiomeModifier;
import baguchan.earthmobsmod.world.biome.MudBiomeModifier;
import com.mojang.serialization.MapCodec;
import net.neoforged.neoforge.common.world.BiomeModifier;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.function.Supplier;

public class ModBiomeModifiers {
	public static final DeferredRegister<MapCodec<? extends BiomeModifier>> BIOME_MODIFIER_SERIALIZERS = DeferredRegister.create(NeoForgeRegistries.Keys.BIOME_MODIFIER_SERIALIZERS, EarthMobsMod.MODID);

	public static final Supplier<MapCodec<EarthBiomeModifier>> EARTH_ENTITY_MODIFIER_TYPE = BIOME_MODIFIER_SERIALIZERS.register("earth_entity_modifier", () -> MapCodec.unit(EarthBiomeModifier.INSTANCE));
	public static final Supplier<MapCodec<MudBiomeModifier>> EARTH_MUD_MODIFIER_TYPE = BIOME_MODIFIER_SERIALIZERS.register("earth_mud_modifier", MudBiomeModifier::makeCodec);

}
