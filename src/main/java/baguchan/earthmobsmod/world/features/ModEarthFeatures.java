package baguchan.earthmobsmod.world.features;

import baguchan.earthmobsmod.EarthMobsMod;
import baguchan.earthmobsmod.registry.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.LakeFeature;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;

public class ModEarthFeatures {
	public static final ResourceKey<ConfiguredFeature<?, ?>> MUD_LAKE = registerKey("mud_spring");

	public static ResourceKey<ConfiguredFeature<?, ?>> registerKey(String name) {
		return ResourceKey.create(Registries.CONFIGURED_FEATURE, EarthMobsMod.prefix(name));
	}

	public static void bootstrap(BootstapContext<ConfiguredFeature<?, ?>> context) {
		FeatureUtils.register(context, MUD_LAKE, Feature.LAKE, new LakeFeature.Configuration(BlockStateProvider.simple(ModBlocks.MUD.get().defaultBlockState()), BlockStateProvider.simple(Blocks.MUD.defaultBlockState())));
	}

}