package baguchan.earthmobsmod.world.biome;

import baguchan.earthmobsmod.EarthMobsConfig;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.Holder;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.world.BiomeModifier;
import net.neoforged.neoforge.common.world.ModifiableBiomeInfo;

public class MudBiomeModifier implements BiomeModifier {
	private final Holder<PlacedFeature> features;


	public MudBiomeModifier(Holder<PlacedFeature> features) {
		this.features = features;
	}

	@Override
	public void modify(Holder<Biome> biome, Phase phase, ModifiableBiomeInfo.BiomeInfo.Builder builder) {
		if (phase == Phase.ADD && EarthMobsConfig.COMMON.mudSpawnInOverworld.get() && (biome.is(Biomes.MANGROVE_SWAMP) || biome.is(Tags.Biomes.IS_SWAMP) && biome.is(BiomeTags.IS_OVERWORLD))) {
			builder.getGenerationSettings().addFeature(GenerationStep.Decoration.LAKES, this.features);
		}
	}

	@Override
	public MapCodec<? extends BiomeModifier> codec() {
		return MudBiomeModifier.makeCodec();
	}

	public static MapCodec<MudBiomeModifier> makeCodec() {
		return RecordCodecBuilder.mapCodec((config) -> {
			return config.group(PlacedFeature.CODEC.fieldOf("feature").forGetter((otherConfig) -> {
				return otherConfig.features;
			})).apply(config, MudBiomeModifier::new);
		});
	}
}
