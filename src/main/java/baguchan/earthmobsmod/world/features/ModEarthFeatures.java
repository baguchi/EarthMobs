package baguchan.earthmobsmod.world.features;

import baguchan.earthmobsmod.EarthMobsMod;
import baguchan.earthmobsmod.registry.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.LakeFeature;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;

public class ModEarthFeatures {
    public static final ResourceKey<Feature> MUD_LAKE = registerKey("mud_spring");

    public static ResourceKey<Feature> registerKey(String name) {
        return ResourceKey.create(Registries.FEATURE, EarthMobsMod.prefix(name));
	}

    public static void bootstrap(BootstrapContext<Feature> context) {
        context.register(MUD_LAKE, new LakeFeature(BlockStateProvider.holderOf(ModBlocks.MUD.get()), BlockStateProvider.holderOf(Blocks.PACKED_MUD),
				BlockPredicate.alwaysTrue(),
				BlockPredicate.not(BlockPredicate.matchesTag(BlockTags.FEATURES_CANNOT_REPLACE)),
                BlockPredicate.not(BlockPredicate.matchesTag(BlockTags.LAVA_POOL_STONE_CANNOT_REPLACE))
        ));
	}

}