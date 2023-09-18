package baguchan.earthmobsmod.world.features;

import baguchan.earthmobsmod.registry.ModBlocks;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.LakeFeature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;

public class ModEarthFeatures {
	public static final Holder<ConfiguredFeature<LakeFeature.Configuration, ?>> MUD_LAKE = FeatureUtils.register("earthmobsmod:mud_spring", Feature.LAKE, new LakeFeature.Configuration(BlockStateProvider.simple(ModBlocks.MUD.get().defaultBlockState()), BlockStateProvider.simple(Blocks.MUD.defaultBlockState())));

	private static <FC extends FeatureConfiguration> ConfiguredFeature<FC, ?> register(String name, ConfiguredFeature<FC, ?> p_243968_1_) {
		return Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, name, p_243968_1_);
	}

	public static void init() {
	}
}