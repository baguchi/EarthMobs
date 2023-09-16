package baguchan.earthmobsmod.registry;

import baguchan.earthmobsmod.EarthMobsMod;
import baguchan.earthmobsmod.world.biome.EarthBiomeModifier;
import baguchan.earthmobsmod.world.biome.MudBiomeModifier;
import com.mojang.serialization.Codec;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBiomeModifiers {
	public static final DeferredRegister<Codec<? extends BiomeModifier>> BIOME_MODIFIER_SERIALIZERS = DeferredRegister.create(ForgeRegistries.Keys.BIOME_MODIFIER_SERIALIZERS, EarthMobsMod.MODID);

	public static final RegistryObject<Codec<EarthBiomeModifier>> EARTH_ENTITY_MODIFIER_TYPE = BIOME_MODIFIER_SERIALIZERS.register("earth_entity_modifier", () -> Codec.unit(EarthBiomeModifier.INSTANCE));
	public static final RegistryObject<Codec<MudBiomeModifier>> EARTH_MUD_MODIFIER_TYPE = BIOME_MODIFIER_SERIALIZERS.register("earth_mud_modifier", MudBiomeModifier::makeCodec);

}
