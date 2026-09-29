package baguchan.earthmobsmod.world.biome;

import baguchan.earthmobsmod.EarthMobsConfig;
import baguchan.earthmobsmod.registry.ModBiomeModifiers;
import baguchan.earthmobsmod.registry.ModEntities;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.Holder;
import net.minecraft.core.RegistryAccess;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.world.BiomeModifier;
import net.neoforged.neoforge.common.world.ModifiableBiomeInfo;

public class EarthBiomeModifier implements BiomeModifier {
	public static final EarthBiomeModifier INSTANCE = new EarthBiomeModifier();


	@Override
	public void modify(RegistryAccess registries, Holder<Biome> biome, Phase phase, ModifiableBiomeInfo.BiomeInfo.Builder builder) {
		if (phase == Phase.ADD) {
			if (biome.is(BiomeTags.IS_OVERWORLD) && !biome.is(Biomes.DEEP_DARK) && !biome.is(Tags.Biomes.IS_VOID)) {

				if (EarthMobsConfig.COMMON.cluckshroomSpawnRate.get() > 0) {
					if (biome.is(Biomes.MUSHROOM_FIELDS)) {
						builder.getMobSpawnSettings().addSpawn(ModEntities.CLUCK_SHROOM.get(), EarthMobsConfig.COMMON.cluckshroomSpawnRate.get(), 3, 4);
					}
				}

				if (EarthMobsConfig.COMMON.fancyChickenSpawnRate.get() > 0) {
					if (biome.is(Tags.Biomes.IS_PLAINS) && !biome.is(Tags.Biomes.IS_COLD) && !biome.is(Tags.Biomes.IS_HOT)) {
						builder.getMobSpawnSettings().addSpawn(ModEntities.FANCY_CHICKEN.get(), EarthMobsConfig.COMMON.fancyChickenSpawnRate.get(), 3, 4);
					}
				}

				if (EarthMobsConfig.COMMON.woolyCowSpawnRate.get() > 0) {
                    if (biome.is(Tags.Biomes.IS_COLD)) {
						builder.getMobSpawnSettings().addSpawn(ModEntities.WOOLY_COW.get(), EarthMobsConfig.COMMON.woolyCowSpawnRate.get(), 3, 4);
					}
				}

				if (EarthMobsConfig.COMMON.jollyLLamaSpawnRate.get() > 0) {
                    if (biome.is(BiomeTags.SPAWNS_COLD_VARIANT_FARM_ANIMALS) && (biome.is(Tags.Biomes.IS_MOUNTAIN_PEAK) || biome.is(Tags.Biomes.IS_MOUNTAIN_SLOPE))) {
						builder.getMobSpawnSettings().addSpawn(ModEntities.JOLLY_LLAMA.get(), EarthMobsConfig.COMMON.jollyLLamaSpawnRate.get(), 3, 4);
					}
				}

				if (EarthMobsConfig.COMMON.umbraCowSpawnRate.get() > 0) {
                    if (biome.is(Tags.Biomes.IS_COLD)) {
						builder.getMobSpawnSettings().addSpawn(ModEntities.UMBRA_COW.get(), EarthMobsConfig.COMMON.umbraCowSpawnRate.get(), 3, 4);

					}
				}

				if (EarthMobsConfig.COMMON.teacupPigSpawnRate.get() > 0) {
					if (biome.is(Tags.Biomes.IS_PLAINS)) {
						builder.getMobSpawnSettings().addSpawn(ModEntities.TEACUP_PIG.get(), EarthMobsConfig.COMMON.teacupPigSpawnRate.get(), 3, 4);
					}
				}

				if (EarthMobsConfig.COMMON.hornedSheepSpawnRate.get() > 0) {
					if (biome.is(Tags.Biomes.IS_MOUNTAIN)) {
						builder.getMobSpawnSettings().addSpawn(ModEntities.HORNED_SHEEP.get(), EarthMobsConfig.COMMON.hornedSheepSpawnRate.get(), 3, 6);

					}
				}

				if (EarthMobsConfig.COMMON.hyperRabbitSpawnRate.get() > 0) {
					if (biome.is(Tags.Biomes.IS_PLAINS) || biome.is(BiomeTags.IS_FOREST)) {
						builder.getMobSpawnSettings().addSpawn(ModEntities.HYPER_RABBIT.get(), EarthMobsConfig.COMMON.hyperRabbitSpawnRate.get(), 3, 4);

					}
				}

				if (!biome.is(Biomes.MUSHROOM_FIELDS)) {
					if (EarthMobsConfig.COMMON.boneSpiderSpawnRate.get() > 0) {
						if (biome.is(Tags.Biomes.IS_SPOOKY)) {
							builder.getMobSpawnSettings().addSpawn(ModEntities.BONE_SPIDER.get(), EarthMobsConfig.COMMON.boneSpiderSpawnRate.get(), 1, 1);

						}
					}

					if (EarthMobsConfig.COMMON.lobberZombieSpawnRate.get() > 0) {
						if (biome.is(BiomeTags.IS_FOREST)) {
							builder.getMobSpawnSettings().addSpawn(ModEntities.LOBBER_ZOMBIE.get(), EarthMobsConfig.COMMON.lobberZombieSpawnRate.get(), 3, 4);
						}

					}

					if (EarthMobsConfig.COMMON.boulderingZombieSpawnRate.get() > 0) {
						builder.getMobSpawnSettings().addSpawn(ModEntities.BOULDERING_ZOMBIE.get(), EarthMobsConfig.COMMON.boulderingZombieSpawnRate.get(), 3, 4);
					}

					if (EarthMobsConfig.COMMON.zombifiedRabbitSpawnRate.get() > 0) {
						builder.getMobSpawnSettings().addSpawn(ModEntities.ZOMBIFIED_RABBIT.get(), EarthMobsConfig.COMMON.zombifiedRabbitSpawnRate.get(), 3, 4);
                        if (biome.is(Tags.Biomes.IS_DESERT)) {
							builder.getMobSpawnSettings().addSpawn(ModEntities.HUSK_RABBIT.get(), EarthMobsConfig.COMMON.zombifiedRabbitSpawnRate.get(), 3, 4);
                        }
                    }

                    if (biome.is(Tags.Biomes.IS_DESERT)) {
                        if (EarthMobsConfig.COMMON.zombifiedRabbitSpawnRate.get() > 0) {
							builder.getMobSpawnSettings().addSpawn(ModEntities.HUSK_RABBIT.get(), EarthMobsConfig.COMMON.zombifiedRabbitSpawnRate.get(), 3, 4);
                        }
                    }

					if (EarthMobsConfig.COMMON.boulderingZombieSpawnRate.get() > 0) {
						if (biome.is(Biomes.DRIPSTONE_CAVES)) {
							builder.getMobSpawnSettings().addSpawn(ModEntities.BOULDERING_DROWNED.get(), EarthMobsConfig.COMMON.boulderingZombieSpawnRate.get(), 3, 4);
						}
                    }


					if (biome.is(BiomeTags.IS_DEEP_OCEAN) || biome.is(BiomeTags.IS_OCEAN)) {
						if (EarthMobsConfig.COMMON.tropicalSlimeSpawnRate.get() > 0) {
							builder.getMobSpawnSettings().addSpawn(ModEntities.TROPICAL_SLIME.get(), EarthMobsConfig.COMMON.tropicalSlimeSpawnRate.get(), 3, 4);

						}
					}
				}
				if (biome.is(Biomes.FLOWER_FOREST)) {
					if (EarthMobsConfig.COMMON.moobloomSpawnRate.get() > 0) {
						builder.getMobSpawnSettings().addSpawn(ModEntities.MOOBLOOM.get(), EarthMobsConfig.COMMON.moobloomSpawnRate.get(), 3, 4);
					}
					if (EarthMobsConfig.COMMON.moolipSpawnRate.get() > 0) {
						builder.getMobSpawnSettings().addSpawn(ModEntities.MOOLIP.get(), EarthMobsConfig.COMMON.moolipSpawnRate.get(), 3, 4);
					}
				}

				if (biome.is(BiomeTags.IS_FOREST)) {
					if (EarthMobsConfig.COMMON.skeletonWolfOverWorldSpawnRate.get() > 0) {
						builder.getMobSpawnSettings().addSpawn(ModEntities.SKELETON_WOLF.get(), EarthMobsConfig.COMMON.skeletonWolfOverWorldSpawnRate.get(), 3, 4);
					}
				}

				if (EarthMobsConfig.COMMON.jumboRabbitSpawnRate.get() > 0) {
					if (biome.is(Tags.Biomes.IS_PLAINS) || biome.is(BiomeTags.IS_FOREST) || biome.is(Tags.Biomes.IS_SANDY)) {
						builder.getMobSpawnSettings().addSpawn(ModEntities.JUMBO_RABBIT.get(), EarthMobsConfig.COMMON.jumboRabbitSpawnRate.get(), 3, 4);
					}
				}


			}

			if (EarthMobsConfig.COMMON.skeletonWolfNetherSpawnRate.get() > 0) {
				if (biome.is(Biomes.SOUL_SAND_VALLEY)) {
					builder.getMobSpawnSettings().addSpawn(ModEntities.SKELETON_WOLF.get(), EarthMobsConfig.COMMON.skeletonWolfNetherSpawnRate.get(), 2, 3);
					builder.getMobSpawnSettings().addMobSpawnCost(ModEntities.SKELETON_WOLF.get(), 0.7, 0.15F);
				}
			}

			if (EarthMobsConfig.COMMON.magmaCowSpawnRate.get() > 0) {
				if (biome.is(Biomes.BASALT_DELTAS)) {
					builder.getMobSpawnSettings().addSpawn(ModEntities.MAGMA_COW.get(), EarthMobsConfig.COMMON.magmaCowSpawnRate.get(), 4, 6);

				}
			}


			if (EarthMobsConfig.COMMON.witherSkeletonWolfNetherSpawnRate.get() > 0) {
				if (biome.is(Biomes.SOUL_SAND_VALLEY)) {
					builder.getMobSpawnSettings().addSpawn(ModEntities.WITHER_SKELETON_WOLF.get(), EarthMobsConfig.COMMON.witherSkeletonWolfNetherSpawnRate.get(), 2, 3);
					builder.getMobSpawnSettings().addMobSpawnCost(ModEntities.WITHER_SKELETON_WOLF.get(), 0.7, 0.15F);
				}
			}
		}
	}

	@Override
	public MapCodec<? extends BiomeModifier> codec() {
		return ModBiomeModifiers.EARTH_ENTITY_MODIFIER_TYPE.get();
	}
}
