package baguchan.earthmobsmod.world.biome;

import baguchan.earthmobsmod.EarthMobsConfig;
import baguchan.earthmobsmod.registry.ModBiomeModifiers;
import baguchan.earthmobsmod.registry.ModEntities;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.Holder;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.world.BiomeModifier;
import net.neoforged.neoforge.common.world.ModifiableBiomeInfo;

public class EarthBiomeModifier implements BiomeModifier {
	public static final EarthBiomeModifier INSTANCE = new EarthBiomeModifier();


	@Override
	public void modify(Holder<Biome> biome, Phase phase, ModifiableBiomeInfo.BiomeInfo.Builder builder) {
		if (phase == Phase.ADD) {
			if (biome.is(BiomeTags.IS_OVERWORLD) && !biome.is(Biomes.DEEP_DARK) && !biome.is(Tags.Biomes.IS_VOID)) {

				if (EarthMobsConfig.COMMON.cluckshroomSpawnRate.get() > 0) {
					if (biome.is(Biomes.MUSHROOM_FIELDS)) {
						builder.getMobSpawnSettings().addSpawn(MobCategory.CREATURE, EarthMobsConfig.COMMON.cluckshroomSpawnRate.get(), new MobSpawnSettings.SpawnerData(ModEntities.CLUCK_SHROOM.get(), 3, 4));
					}
				}

				if (EarthMobsConfig.COMMON.fancyChickenSpawnRate.get() > 0) {
					if (biome.is(Tags.Biomes.IS_PLAINS) && !biome.is(Tags.Biomes.IS_COLD) && !biome.is(Tags.Biomes.IS_HOT)) {
						builder.getMobSpawnSettings().addSpawn(MobCategory.CREATURE, EarthMobsConfig.COMMON.fancyChickenSpawnRate.get(), new MobSpawnSettings.SpawnerData(ModEntities.FANCY_CHICKEN.get(), 3, 4));
					}
				}

				if (EarthMobsConfig.COMMON.woolyCowSpawnRate.get() > 0) {
                    if (biome.is(Tags.Biomes.IS_COLD)) {
						builder.getMobSpawnSettings().addSpawn(MobCategory.CREATURE, EarthMobsConfig.COMMON.woolyCowSpawnRate.get(), new MobSpawnSettings.SpawnerData(ModEntities.WOOLY_COW.get(), 3, 4));
					}
				}

				if (EarthMobsConfig.COMMON.jollyLLamaSpawnRate.get() > 0) {
                    if (biome.is(BiomeTags.SPAWNS_COLD_VARIANT_FARM_ANIMALS) && (biome.is(Tags.Biomes.IS_MOUNTAIN_PEAK) || biome.is(Tags.Biomes.IS_MOUNTAIN_SLOPE))) {
						builder.getMobSpawnSettings().addSpawn(MobCategory.CREATURE, EarthMobsConfig.COMMON.jollyLLamaSpawnRate.get(), new MobSpawnSettings.SpawnerData(ModEntities.JOLLY_LLAMA.get(), 3, 4));
					}
				}

				if (EarthMobsConfig.COMMON.umbraCowSpawnRate.get() > 0) {
                    if (biome.is(Tags.Biomes.IS_COLD)) {
						builder.getMobSpawnSettings().addSpawn(MobCategory.CREATURE, EarthMobsConfig.COMMON.umbraCowSpawnRate.get(), new MobSpawnSettings.SpawnerData(ModEntities.UMBRA_COW.get(), 3, 4));

					}
				}

				if (EarthMobsConfig.COMMON.teacupPigSpawnRate.get() > 0) {
					if (biome.is(Tags.Biomes.IS_PLAINS)) {
						builder.getMobSpawnSettings().addSpawn(MobCategory.CREATURE, EarthMobsConfig.COMMON.teacupPigSpawnRate.get(), new MobSpawnSettings.SpawnerData(ModEntities.TEACUP_PIG.get(), 3, 4));
					}
				}

				if (EarthMobsConfig.COMMON.hornedSheepSpawnRate.get() > 0) {
					if (biome.is(Tags.Biomes.IS_MOUNTAIN)) {
						builder.getMobSpawnSettings().addSpawn(MobCategory.CREATURE, EarthMobsConfig.COMMON.hornedSheepSpawnRate.get(), new MobSpawnSettings.SpawnerData(ModEntities.HORNED_SHEEP.get(), 3, 6));

					}
				}

				if (EarthMobsConfig.COMMON.hyperRabbitSpawnRate.get() > 0) {
					if (biome.is(Tags.Biomes.IS_PLAINS) || biome.is(BiomeTags.IS_FOREST)) {
						builder.getMobSpawnSettings().addSpawn(MobCategory.CREATURE, EarthMobsConfig.COMMON.hyperRabbitSpawnRate.get(), new MobSpawnSettings.SpawnerData(ModEntities.HYPER_RABBIT.get(), 3, 4));

					}
				}

				if (!biome.is(Biomes.MUSHROOM_FIELDS)) {
					if (EarthMobsConfig.COMMON.boneSpiderSpawnRate.get() > 0) {
						if (biome.is(Tags.Biomes.IS_SPOOKY)) {
							builder.getMobSpawnSettings().addSpawn(MobCategory.MONSTER, EarthMobsConfig.COMMON.boneSpiderSpawnRate.get(), new MobSpawnSettings.SpawnerData(ModEntities.BONE_SPIDER.get(), 1, 1));

						}
					}

					if (EarthMobsConfig.COMMON.strayBoneSpiderSpawnRate.get() > 0) {
						if (biome.is(Tags.Biomes.IS_COLD_OVERWORLD)) {
							builder.getMobSpawnSettings().addSpawn(MobCategory.MONSTER, EarthMobsConfig.COMMON.strayBoneSpiderSpawnRate.get(), new MobSpawnSettings.SpawnerData(ModEntities.STRAY_BONE_SPIDER.get(), 1, 1));
						}
					}

					if (EarthMobsConfig.COMMON.lobberZombieSpawnRate.get() > 0) {
						if (biome.is(BiomeTags.IS_FOREST)) {
							builder.getMobSpawnSettings().addSpawn(MobCategory.MONSTER, EarthMobsConfig.COMMON.lobberZombieSpawnRate.get(), new MobSpawnSettings.SpawnerData(ModEntities.LOBBER_ZOMBIE.get(), 3, 4));
						}
                        if (biome.is(Tags.Biomes.IS_DESERT)) {
							builder.getMobSpawnSettings().addSpawn(MobCategory.MONSTER, EarthMobsConfig.COMMON.lobberZombieSpawnRate.get(), new MobSpawnSettings.SpawnerData(ModEntities.LOBBER_HUSK.get(), 3, 4));
                        }
					}

					if (EarthMobsConfig.COMMON.boulderingZombieSpawnRate.get() > 0) {
                        if (biome.is(Tags.Biomes.IS_COLD) && biome.is(Tags.Biomes.IS_MOUNTAIN)) {
							builder.getMobSpawnSettings().addSpawn(MobCategory.MONSTER, EarthMobsConfig.COMMON.boulderingZombieSpawnRate.get(), new MobSpawnSettings.SpawnerData(ModEntities.BOULDERING_FROZEN_ZOMBIE.get(), 3, 4));
                        } else {
							builder.getMobSpawnSettings().addSpawn(MobCategory.MONSTER, EarthMobsConfig.COMMON.boulderingZombieSpawnRate.get(), new MobSpawnSettings.SpawnerData(ModEntities.BOULDERING_ZOMBIE.get(), 3, 4));
						}
					}

					if (EarthMobsConfig.COMMON.zombifiedRabbitSpawnRate.get() > 0) {
						builder.getMobSpawnSettings().addSpawn(MobCategory.MONSTER, EarthMobsConfig.COMMON.zombifiedRabbitSpawnRate.get(), new MobSpawnSettings.SpawnerData(ModEntities.ZOMBIFIED_RABBIT.get(), 3, 4));
					}

					if (EarthMobsConfig.COMMON.boulderingZombieSpawnRate.get() > 0) {
						if (biome.is(Biomes.DRIPSTONE_CAVES)) {
							builder.getMobSpawnSettings().addSpawn(MobCategory.MONSTER, EarthMobsConfig.COMMON.boulderingZombieSpawnRate.get(), new MobSpawnSettings.SpawnerData(ModEntities.BOULDERING_FROZEN_ZOMBIE.get(), 3, 4));
						}

                    }


					if (biome.is(BiomeTags.IS_DEEP_OCEAN) || biome.is(BiomeTags.IS_OCEAN)) {
						if (EarthMobsConfig.COMMON.tropicalSlimeSpawnRate.get() > 0) {
							builder.getMobSpawnSettings().addSpawn(MobCategory.MONSTER, EarthMobsConfig.COMMON.tropicalSlimeSpawnRate.get(), new MobSpawnSettings.SpawnerData(ModEntities.TROPICAL_SLIME.get(), 3, 4));

						}
					}
				}
				if (biome.is(Biomes.FLOWER_FOREST)) {
					if (EarthMobsConfig.COMMON.moobloomSpawnRate.get() > 0) {
						builder.getMobSpawnSettings().addSpawn(MobCategory.CREATURE, EarthMobsConfig.COMMON.moobloomSpawnRate.get(), new MobSpawnSettings.SpawnerData(ModEntities.MOOBLOOM.get(), 3, 4));
					}
					if (EarthMobsConfig.COMMON.moolipSpawnRate.get() > 0) {
						builder.getMobSpawnSettings().addSpawn(MobCategory.CREATURE, EarthMobsConfig.COMMON.moolipSpawnRate.get(), new MobSpawnSettings.SpawnerData(ModEntities.MOOLIP.get(), 3, 4));
					}
				}

				if (biome.is(BiomeTags.IS_FOREST)) {
					if (EarthMobsConfig.COMMON.skeletonWolfOverWorldSpawnRate.get() > 0) {
						builder.getMobSpawnSettings().addSpawn(MobCategory.MONSTER, EarthMobsConfig.COMMON.skeletonWolfOverWorldSpawnRate.get(), new MobSpawnSettings.SpawnerData(ModEntities.SKELETON_WOLF.get(), 3, 4));
					}
				}

				if (EarthMobsConfig.COMMON.jumboRabbitSpawnRate.get() > 0) {
					if (biome.is(Tags.Biomes.IS_PLAINS) || biome.is(BiomeTags.IS_FOREST) || biome.is(Tags.Biomes.IS_SANDY)) {
						builder.getMobSpawnSettings().addSpawn(MobCategory.CREATURE, EarthMobsConfig.COMMON.jumboRabbitSpawnRate.get(), new MobSpawnSettings.SpawnerData(ModEntities.JUMBO_RABBIT.get(), 3, 4));
					}
				}


			}

			if (EarthMobsConfig.COMMON.skeletonWolfNetherSpawnRate.get() > 0) {
				if (biome.is(Biomes.SOUL_SAND_VALLEY)) {
					builder.getMobSpawnSettings().addSpawn(MobCategory.MONSTER, EarthMobsConfig.COMMON.skeletonWolfNetherSpawnRate.get(), new MobSpawnSettings.SpawnerData(ModEntities.SKELETON_WOLF.get(), 2, 3));
					builder.getMobSpawnSettings().addMobCharge(ModEntities.SKELETON_WOLF.get(), 0.7, 0.15F);
				}
			}

			if (EarthMobsConfig.COMMON.magmaCowSpawnRate.get() > 0) {
				if (biome.is(Biomes.BASALT_DELTAS)) {
					builder.getMobSpawnSettings().addSpawn(MobCategory.CREATURE, EarthMobsConfig.COMMON.magmaCowSpawnRate.get(), new MobSpawnSettings.SpawnerData(ModEntities.MAGMA_COW.get(), 4, 6));

				}
			}


			if (EarthMobsConfig.COMMON.witherSkeletonWolfNetherSpawnRate.get() > 0) {
				if (biome.is(Biomes.SOUL_SAND_VALLEY)) {
					builder.getMobSpawnSettings().addSpawn(MobCategory.MONSTER, EarthMobsConfig.COMMON.witherSkeletonWolfNetherSpawnRate.get(), new MobSpawnSettings.SpawnerData(ModEntities.WITHER_SKELETON_WOLF.get(), 2, 3));
					builder.getMobSpawnSettings().addMobCharge(ModEntities.WITHER_SKELETON_WOLF.get(), 0.7, 0.15F);
				}
			}
		}
	}

	@Override
	public MapCodec<? extends BiomeModifier> codec() {
		return ModBiomeModifiers.EARTH_ENTITY_MODIFIER_TYPE.get();
	}
}
