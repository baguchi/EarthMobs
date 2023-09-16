package baguchan.earthmobsmod.world.biome;

import baguchan.earthmobsmod.EarthMobsConfig;
import baguchan.earthmobsmod.registry.ModBiomeModifiers;
import baguchan.earthmobsmod.registry.ModEntities;
import com.mojang.serialization.Codec;
import net.minecraft.core.Holder;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.common.world.ModifiableBiomeInfo;

public class EarthBiomeModifier implements BiomeModifier {
	public static final EarthBiomeModifier INSTANCE = new EarthBiomeModifier();


	@Override
	public void modify(Holder<Biome> biome, Phase phase, ModifiableBiomeInfo.BiomeInfo.Builder builder) {
		if (phase == Phase.ADD && biome.containsTag(BiomeTags.IS_OVERWORLD) && !biome.is(Biomes.DEEP_DARK) && !biome.is(Tags.Biomes.IS_VOID)) {

			if (EarthMobsConfig.COMMON.cluckshroomSpawnRate.get() > 0) {
				if (biome.is(Biomes.MUSHROOM_FIELDS)) {
					builder.getMobSpawnSettings().getSpawner(MobCategory.CREATURE).add(new MobSpawnSettings.SpawnerData(ModEntities.CLUCK_SHROOM.get(), EarthMobsConfig.COMMON.cluckshroomSpawnRate.get(), 2, 3));
				}
			}

			if (EarthMobsConfig.COMMON.fancyChickenSpawnRate.get() > 0) {
				if (biome.is(Tags.Biomes.IS_PLAINS) && !biome.is(Tags.Biomes.IS_COLD) && !biome.is(Tags.Biomes.IS_HOT)) {
					builder.getMobSpawnSettings().getSpawner(MobCategory.CREATURE).add(new MobSpawnSettings.SpawnerData(ModEntities.FANCY_CHICKEN.get(), EarthMobsConfig.COMMON.fancyChickenSpawnRate.get(), 3, 6));
				}
			}

			if (EarthMobsConfig.COMMON.duckSpawnRate.get() > 0) {
				if (biome.is(Tags.Biomes.IS_PLAINS) && !biome.is(Tags.Biomes.IS_COLD) && !biome.is(Tags.Biomes.IS_HOT) || biome.is(Tags.Biomes.IS_SWAMP) || biome.is(Biomes.RIVER)) {
                    builder.getMobSpawnSettings().getSpawner(MobCategory.CREATURE).add(new MobSpawnSettings.SpawnerData(ModEntities.DUCK.get(), EarthMobsConfig.COMMON.duckSpawnRate.get(), 3, 6));
                }
            }

            if (EarthMobsConfig.COMMON.woolyCowSpawnRate.get() > 0) {
                if (biome.is(Tags.Biomes.IS_MOUNTAIN) && biome.is(Tags.Biomes.IS_COLD) || biome.is(Tags.Biomes.IS_COLD)) {
                    builder.getMobSpawnSettings().getSpawner(MobCategory.CREATURE).add(new MobSpawnSettings.SpawnerData(ModEntities.WOOLY_COW.get(), EarthMobsConfig.COMMON.woolyCowSpawnRate.get(), 3, 6));
                }
            }

            if (EarthMobsConfig.COMMON.jollyLLamaSpawnRate.get() > 0) {
                if (biome.is(Tags.Biomes.IS_MOUNTAIN) && biome.is(Tags.Biomes.IS_COLD)) {
                    builder.getMobSpawnSettings().getSpawner(MobCategory.CREATURE).add(new MobSpawnSettings.SpawnerData(ModEntities.WOOLY_COW.get(), EarthMobsConfig.COMMON.jollyLLamaSpawnRate.get(), 4, 6));
                }
            }

            if (EarthMobsConfig.COMMON.umbraCowSpawnRate.get() > 0) {
                if (biome.is(Tags.Biomes.IS_MOUNTAIN) && biome.is(Tags.Biomes.IS_COLD) || biome.is(Tags.Biomes.IS_COLD)) {
                    builder.getMobSpawnSettings().getSpawner(MobCategory.CREATURE).add(new MobSpawnSettings.SpawnerData(ModEntities.UMBRA_COW.get(), EarthMobsConfig.COMMON.umbraCowSpawnRate.get(), 3, 6));
                }
            }


            if (EarthMobsConfig.COMMON.albinoCowSpawnRate.get() > 0) {
                if (biome.is(Tags.Biomes.IS_PLAINS)) {
                    builder.getMobSpawnSettings().getSpawner(MobCategory.CREATURE).add(new MobSpawnSettings.SpawnerData(ModEntities.ALBINO_COW.get(), EarthMobsConfig.COMMON.albinoCowSpawnRate.get(), 3, 6));
                }
            }


            if (EarthMobsConfig.COMMON.creamCowSpawnRate.get() > 0) {
                if (biome.is(Tags.Biomes.IS_PLAINS)) {
                    builder.getMobSpawnSettings().getSpawner(MobCategory.CREATURE).add(new MobSpawnSettings.SpawnerData(ModEntities.CREAM_COW.get(), EarthMobsConfig.COMMON.creamCowSpawnRate.get(), 3, 6));
                }
            }

            if (EarthMobsConfig.COMMON.teacupPigSpawnRate.get() > 0) {
                if (biome.is(Tags.Biomes.IS_PLAINS)) {
                    builder.getMobSpawnSettings().getSpawner(MobCategory.CREATURE).add(new MobSpawnSettings.SpawnerData(ModEntities.TEACUP_PIG.get(), EarthMobsConfig.COMMON.teacupPigSpawnRate.get(), 3, 6));
                }
            }

            if (EarthMobsConfig.COMMON.hornedSheepSpawnRate.get() > 0) {
                if (biome.is(Tags.Biomes.IS_MOUNTAIN)) {
                    builder.getMobSpawnSettings().getSpawner(MobCategory.CREATURE).add(new MobSpawnSettings.SpawnerData(ModEntities.HORNED_SHEEP.get(), EarthMobsConfig.COMMON.hornedSheepSpawnRate.get(), 3, 6));
                }
            }

            if (EarthMobsConfig.COMMON.hyperRabbitSpawnRate.get() > 0) {
                if (biome.is(Tags.Biomes.IS_PLAINS) || biome.is(BiomeTags.IS_FOREST)) {
                    builder.getMobSpawnSettings().getSpawner(MobCategory.CREATURE).add(new MobSpawnSettings.SpawnerData(ModEntities.HYPER_RABBIT.get(), EarthMobsConfig.COMMON.hyperRabbitSpawnRate.get(), 3, 4));
				}
			}

			if (!biome.is(Biomes.MUSHROOM_FIELDS)) {
				if (EarthMobsConfig.COMMON.boneSpiderSpawnRate.get() > 0) {
					if (biome.is(Tags.Biomes.IS_SPOOKY)) {
						builder.getMobSpawnSettings().getSpawner(MobCategory.MONSTER).add(new MobSpawnSettings.SpawnerData(ModEntities.BONE_SPIDER.get(), EarthMobsConfig.COMMON.boneSpiderSpawnRate.get(), 1, 1));
					}
				}

				if (EarthMobsConfig.COMMON.strayBoneSpiderSpawnRate.get() > 0) {
					if (biome.is(Tags.Biomes.IS_COLD_OVERWORLD)) {
						builder.getMobSpawnSettings().getSpawner(MobCategory.MONSTER).add(new MobSpawnSettings.SpawnerData(ModEntities.STRAY_BONE_SPIDER.get(), EarthMobsConfig.COMMON.strayBoneSpiderSpawnRate.get(), 1, 1));
					}
				}

				if (EarthMobsConfig.COMMON.boulderingZombieSpawnRate.get() > 0) {
					if (biome.is(Tags.Biomes.IS_MOUNTAIN)) {
						builder.getMobSpawnSettings().getSpawner(MobCategory.MONSTER).add(new MobSpawnSettings.SpawnerData(ModEntities.BOULDERING_ZOMBIE.get(), EarthMobsConfig.COMMON.boulderingZombieSpawnRate.get(), 4, 4));
					}
				}

				if (EarthMobsConfig.COMMON.lobberZombieSpawnRate.get() > 0) {
					builder.getMobSpawnSettings().getSpawner(MobCategory.MONSTER).add(new MobSpawnSettings.SpawnerData(ModEntities.LOBBER_ZOMBIE.get(), EarthMobsConfig.COMMON.lobberZombieSpawnRate.get(), 2, 4));
				}

				if (EarthMobsConfig.COMMON.boulderingZombieSpawnRate.get() > 0) {
					if (biome.is(Biomes.DRIPSTONE_CAVES)) {
						builder.getMobSpawnSettings().getSpawner(MobCategory.MONSTER).add(new MobSpawnSettings.SpawnerData(ModEntities.BOULDERING_DROWNED.get(), EarthMobsConfig.COMMON.boulderingZombieSpawnRate.get(), 4, 4));
						builder.getMobSpawnSettings().getSpawner(MobCategory.MONSTER).add(new MobSpawnSettings.SpawnerData(ModEntities.BOULDERING_ZOMBIE.get(), EarthMobsConfig.COMMON.boulderingZombieSpawnRate.get(), 4, 4));
					}
				}


				if (biome.is(BiomeTags.IS_DEEP_OCEAN) || biome.is(BiomeTags.IS_OCEAN)) {
					if (EarthMobsConfig.COMMON.tropicalSlimeSpawnRate.get() > 0) {
						builder.getMobSpawnSettings().getSpawner(MobCategory.MONSTER).add(new MobSpawnSettings.SpawnerData(ModEntities.TROPICAL_SLIME.get(), EarthMobsConfig.COMMON.tropicalSlimeSpawnRate.get(), 1, 2));
					}
				}
			}
			if (biome.is(Biomes.FLOWER_FOREST)) {
				if (EarthMobsConfig.COMMON.moobloomSpawnRate.get() > 0) {
					builder.getMobSpawnSettings().getSpawner(MobCategory.CREATURE).add(new MobSpawnSettings.SpawnerData(ModEntities.MOOBLOOM.get(), EarthMobsConfig.COMMON.moobloomSpawnRate.get(), 3, 4));
				}
				if (EarthMobsConfig.COMMON.moolipSpawnRate.get() > 0) {
					builder.getMobSpawnSettings().getSpawner(MobCategory.CREATURE).add(new MobSpawnSettings.SpawnerData(ModEntities.MOOLIP.get(), EarthMobsConfig.COMMON.moolipSpawnRate.get(), 3, 4));
				}
			}

			if (biome.is(BiomeTags.IS_FOREST)) {
				if (EarthMobsConfig.COMMON.skeletonWolfOverWorldSpawnRate.get() > 0) {
					builder.getMobSpawnSettings().getSpawner(MobCategory.MONSTER).add(new MobSpawnSettings.SpawnerData(ModEntities.SKELETON_WOLF.get(), EarthMobsConfig.COMMON.skeletonWolfOverWorldSpawnRate.get(), 3, 4));
				}
			}

			if (EarthMobsConfig.COMMON.jumboRabbitSpawnRate.get() > 0) {
				if (biome.is(Tags.Biomes.IS_PLAINS) || biome.is(BiomeTags.IS_FOREST) || biome.is(Tags.Biomes.IS_SANDY)) {
					builder.getMobSpawnSettings().getSpawner(MobCategory.CREATURE).add(new MobSpawnSettings.SpawnerData(ModEntities.JUMBO_RABBIT.get(), EarthMobsConfig.COMMON.jumboRabbitSpawnRate.get(), 3, 4));
				}
			}


		}

		if (EarthMobsConfig.COMMON.skeletonWolfNetherSpawnRate.get() > 0) {
			if (biome.is(Biomes.SOUL_SAND_VALLEY)) {
				builder.getMobSpawnSettings().getSpawner(MobCategory.MONSTER).add(new MobSpawnSettings.SpawnerData(ModEntities.SKELETON_WOLF.get(), EarthMobsConfig.COMMON.skeletonWolfNetherSpawnRate.get(), 2, 3));
				builder.getMobSpawnSettings().addMobCharge(ModEntities.SKELETON_WOLF.get(), 0.7, 0.15F);
			}
		}

		if (EarthMobsConfig.COMMON.babyGhastSpawnRate.get() > 0) {
			if (biome.is(Biomes.CRIMSON_FOREST)) {
				builder.getMobSpawnSettings().getSpawner(MobCategory.CREATURE).add(new MobSpawnSettings.SpawnerData(ModEntities.BABY_GHAST.get(), EarthMobsConfig.COMMON.babyGhastSpawnRate.get(), 3, 4));
			}
		}

		if (EarthMobsConfig.COMMON.magmaCowSpawnRate.get() > 0) {
			if (biome.is(Biomes.BASALT_DELTAS)) {
				builder.getMobSpawnSettings().getSpawner(MobCategory.CREATURE).add(new MobSpawnSettings.SpawnerData(ModEntities.MAGMA_COW.get(), EarthMobsConfig.COMMON.magmaCowSpawnRate.get(), 3, 4));
			}
		}


		if (EarthMobsConfig.COMMON.witherSkeletonWolfNetherSpawnRate.get() > 0) {
			if (biome.is(Biomes.SOUL_SAND_VALLEY)) {
				builder.getMobSpawnSettings().getSpawner(MobCategory.MONSTER).add(new MobSpawnSettings.SpawnerData(ModEntities.WITHER_SKELETON_WOLF.get(), EarthMobsConfig.COMMON.witherSkeletonWolfNetherSpawnRate.get(), 2, 3));
				builder.getMobSpawnSettings().addMobCharge(ModEntities.WITHER_SKELETON_WOLF.get(), 0.7, 0.15F);
			}
		}
	}

	@Override
	public Codec<? extends BiomeModifier> codec() {
		return ModBiomeModifiers.EARTH_ENTITY_MODIFIER_TYPE.get();
	}
}
