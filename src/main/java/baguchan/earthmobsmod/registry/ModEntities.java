package baguchan.earthmobsmod.registry;

import baguchan.earthmobsmod.EarthMobsMod;
import baguchan.earthmobsmod.entity.*;
import baguchan.earthmobsmod.entity.projectile.*;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.Chicken;
import net.minecraft.world.entity.animal.Cow;
import net.minecraft.world.entity.animal.horse.Llama;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.entity.raid.Raid;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.entity.SpawnPlacementRegisterEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

@Mod.EventBusSubscriber(modid = EarthMobsMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEntities {
	public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, EarthMobsMod.MODID);

	public static final RegistryObject<EntityType<CluckShroom>> CLUCK_SHROOM = ENTITIES.register("cluck_shroom", () -> EntityType.Builder.of(CluckShroom::new, MobCategory.CREATURE).sized(0.4F, 0.7F).clientTrackingRange(10).build(prefix("cluck_shroom")));
	public static final RegistryObject<EntityType<FancyChicken>> FANCY_CHICKEN = ENTITIES.register("fancy_chicken", () -> EntityType.Builder.of(FancyChicken::new, MobCategory.CREATURE).sized(0.4F, 0.85F).clientTrackingRange(10).build(prefix("fancy_chicken")));
	public static final RegistryObject<EntityType<WoolyCow>> WOOLY_COW = ENTITIES.register("wooly_cow", () -> EntityType.Builder.of(WoolyCow::new, MobCategory.CREATURE).sized(0.9F, 1.4F).clientTrackingRange(10).build(prefix("wooly_cow")));
	public static final RegistryObject<EntityType<UmbraCow>> UMBRA_COW = ENTITIES.register("umbra_cow", () -> EntityType.Builder.of(UmbraCow::new, MobCategory.CREATURE).sized(0.9F, 1.4F).clientTrackingRange(10).build(prefix("umbra_cow")));
	public static final RegistryObject<EntityType<AlbinoCow>> ALBINO_COW = ENTITIES.register("albino_cow", () -> EntityType.Builder.of(AlbinoCow::new, MobCategory.CREATURE).sized(0.9F, 1.4F).clientTrackingRange(10).build(prefix("albino_cow")));
	public static final RegistryObject<EntityType<CreamCow>> CREAM_COW = ENTITIES.register("cream_cow", () -> EntityType.Builder.of(CreamCow::new, MobCategory.CREATURE).sized(0.9F, 1.4F).clientTrackingRange(10).build(prefix("cream_cow")));
	public static final RegistryObject<EntityType<TeaCupPig>> TEACUP_PIG = ENTITIES.register("teacup_pig", () -> EntityType.Builder.of(TeaCupPig::new, MobCategory.CREATURE).sized(0.4F, 0.4F).clientTrackingRange(10).build(prefix("teacup_pig")));
	public static final RegistryObject<EntityType<MagmaCow>> MAGMA_COW = ENTITIES.register("magma_cow", () -> EntityType.Builder.of(MagmaCow::new, MobCategory.CREATURE).sized(0.9F, 1.4F).clientTrackingRange(10).fireImmune().build(prefix("magma_cow")));


	public static final RegistryObject<EntityType<HornedSheep>> HORNED_SHEEP = ENTITIES.register("horned_sheep", () -> EntityType.Builder.of(HornedSheep::new, MobCategory.CREATURE).sized(0.9F, 1.3F).clientTrackingRange(10).build(prefix("horned_sheep")));
	public static final RegistryObject<EntityType<HyperRabbit>> HYPER_RABBIT = ENTITIES.register("hyper_rabbit", () -> EntityType.Builder.of(HyperRabbit::new, MobCategory.CREATURE).sized(0.4F, 0.6F).clientTrackingRange(8).build(prefix("hyper_rabbit")));
	public static final RegistryObject<EntityType<Moobloom>> MOOBLOOM = ENTITIES.register("moobloom", () -> EntityType.Builder.of(Moobloom::new, MobCategory.CREATURE).sized(0.9F, 1.4F).clientTrackingRange(8).build(prefix("moobloom")));
	public static final RegistryObject<EntityType<Moolip>> MOOLIP = ENTITIES.register("moolip", () -> EntityType.Builder.of(Moolip::new, MobCategory.CREATURE).sized(0.9F, 1.4F).clientTrackingRange(8).build(prefix("moolip")));
	public static final RegistryObject<EntityType<JumboRabbit>> JUMBO_RABBIT = ENTITIES.register("jumbo_rabbit", () -> EntityType.Builder.of(JumboRabbit::new, MobCategory.CREATURE).sized(0.7F, 1.2F).clientTrackingRange(8).build(prefix("jumbo_rabbit")));
	public static final RegistryObject<EntityType<ZombifiedPig>> ZOMBIFIED_PIG = ENTITIES.register("zombified_pig", () -> EntityType.Builder.of(ZombifiedPig::new, MobCategory.CREATURE).sized(0.6F, 0.85F).fireImmune().build(prefix("zombified_pig")));
    public static final RegistryObject<EntityType<Duck>> DUCK = ENTITIES.register("duck", () -> EntityType.Builder.of(Duck::new, MobCategory.CREATURE).sized(0.4F, 0.7F).build(prefix("duck")));
    public static final RegistryObject<EntityType<JollyLlama>> JOLLY_LLAMA = ENTITIES.register("jolly_llama", () -> EntityType.Builder.of(JollyLlama::new, MobCategory.CREATURE).sized(0.9F, 1.87F).clientTrackingRange(10).build(prefix("jolly_llama")));


	public static final RegistryObject<EntityType<MelonGolem>> MELON_GOLEM = ENTITIES.register("melon_golem", () -> EntityType.Builder.of(MelonGolem::new, MobCategory.MISC).sized(0.7F, 1.9F).immuneTo(Blocks.POWDER_SNOW).clientTrackingRange(8).build(prefix("melon_golem")));
	public static final RegistryObject<EntityType<FurnaceGolem>> FURNACE_GOLEM = ENTITIES.register("furnace_golem", () -> EntityType.Builder.of(FurnaceGolem::new, MobCategory.MISC).sized(1.4F, 2.7F).clientTrackingRange(10).build(prefix("furnace_golem")));


	public static final RegistryObject<EntityType<BoneSpider>> BONE_SPIDER = ENTITIES.register("bone_spider", () -> EntityType.Builder.of(BoneSpider::new, MobCategory.MONSTER).sized(1.4F, 0.9F).build(prefix("bone_spider")));
	public static final RegistryObject<EntityType<StrayBoneSpider>> STRAY_BONE_SPIDER = ENTITIES.register("stray_bone_spider", () -> EntityType.Builder.of(StrayBoneSpider::new, MobCategory.MONSTER).sized(1.4F, 0.9F).build(prefix("stray_bone_spider")));

	public static final RegistryObject<EntityType<VilerWitch>> VILER_WITCH = ENTITIES.register("viler_witch", () -> EntityType.Builder.of(VilerWitch::new, MobCategory.MONSTER).sized(0.6F, 1.95F).build(prefix("viler_witch")));

	public static final RegistryObject<EntityType<BoulderingZombie>> BOULDERING_ZOMBIE = ENTITIES.register("bouldering_zombie", () -> EntityType.Builder.of(BoulderingZombie::new, MobCategory.MONSTER).sized(0.6F, 1.95F).build(prefix("bouldering_zombie")));
	public static final RegistryObject<EntityType<LobberZombie>> LOBBER_ZOMBIE = ENTITIES.register("lobber_zombie", () -> EntityType.Builder.of(LobberZombie::new, MobCategory.MONSTER).sized(0.6F, 1.95F).build(prefix("lobber_zombie")));

	public static final RegistryObject<EntityType<BoulderingDrowned>> BOULDERING_DROWNED = ENTITIES.register("bouldering_drowned", () -> EntityType.Builder.of(BoulderingDrowned::new, MobCategory.MONSTER).sized(0.6F, 1.95F).build(prefix("bouldering_drowned")));
	public static final RegistryObject<EntityType<LobberDrowned>> LOBBER_DROWNED = ENTITIES.register("lobber_drowned", () -> EntityType.Builder.of(LobberDrowned::new, MobCategory.MONSTER).sized(0.6F, 1.95F).build(prefix("lobber_drowned")));

	public static final RegistryObject<EntityType<TropicalSlime>> TROPICAL_SLIME = ENTITIES.register("tropical_slime", () -> EntityType.Builder.of(TropicalSlime::new, MobCategory.MONSTER).sized(2.04F, 2.04F).clientTrackingRange(10).build(prefix("tropical_slime")));

	public static final RegistryObject<EntityType<SkeletonWolf>> SKELETON_WOLF = ENTITIES.register("skeleton_wolf", () -> EntityType.Builder.of(SkeletonWolf::new, MobCategory.MONSTER).sized(0.6F, 0.85F).build(prefix("skeleton_wolf")));
	public static final RegistryObject<EntityType<WitherSkeletonWolf>> WITHER_SKELETON_WOLF = ENTITIES.register("wither_skeleton_wolf", () -> EntityType.Builder.of(WitherSkeletonWolf::new, MobCategory.MONSTER).sized(0.6F, 0.85F).fireImmune().immuneTo(Blocks.WITHER_ROSE).build(prefix("wither_skeleton_wolf")));
	public static final RegistryObject<EntityType<BabyGhast>> BABY_GHAST = ENTITIES.register("baby_ghast", () -> EntityType.Builder.of(BabyGhast::new, MobCategory.CREATURE).sized(0.45F, 0.45F).fireImmune().build(prefix("baby_ghast")));


	public static final RegistryObject<EntityType<SmellyEgg>> SMELLY_EGG = ENTITIES.register("smelly_egg", () -> EntityType.Builder.<SmellyEgg>of(SmellyEgg::new, MobCategory.MISC).sized(0.25F, 0.25F).clientTrackingRange(4).updateInterval(10).build(prefix("smelly_egg")));
	public static final RegistryObject<EntityType<FancyEgg>> FANCY_EGG = ENTITIES.register("fancy_egg", () -> EntityType.Builder.<FancyEgg>of(FancyEgg::new, MobCategory.MISC).sized(0.25F, 0.25F).clientTrackingRange(4).updateInterval(10).build(prefix("fancy_egg")));
	public static final RegistryObject<EntityType<DuckEgg>> DUCK_EGG = ENTITIES.register("duck_egg", () -> EntityType.Builder.<DuckEgg>of(DuckEgg::new, MobCategory.MISC).sized(0.25F, 0.25F).clientTrackingRange(4).updateInterval(10).build(prefix("duck_egg")));

	public static final RegistryObject<EntityType<BoneShard>> BONE_SHARD = ENTITIES.register("bone_shard", () -> EntityType.Builder.<BoneShard>of(BoneShard::new, MobCategory.MISC).sized(0.25F, 0.25F).clientTrackingRange(4).updateInterval(10).build(prefix("bone_shard")));
	public static final RegistryObject<EntityType<StrayBoneShard>> STRAY_BONE_SHARD = ENTITIES.register("stray_bone_shard", () -> EntityType.Builder.<StrayBoneShard>of(StrayBoneShard::new, MobCategory.MISC).sized(0.25F, 0.25F).clientTrackingRange(4).updateInterval(10).build(prefix("stray_bone_shard")));

	public static final RegistryObject<EntityType<MelonSeed>> MELON_SEED = ENTITIES.register("melon_seeds", () -> EntityType.Builder.<MelonSeed>of(MelonSeed::new, MobCategory.MISC).sized(0.25F, 0.25F).clientTrackingRange(4).updateInterval(10).build(prefix("melon_seeds")));
	public static final RegistryObject<EntityType<ZombieFlesh>> ZOMBIE_FLESH = ENTITIES.register("zombie_flesh", () -> EntityType.Builder.<ZombieFlesh>of(ZombieFlesh::new, MobCategory.MISC).sized(0.25F, 0.25F).clientTrackingRange(4).updateInterval(10).build(prefix("zombie_flesh")));


	private static String prefix(String path) {
		return EarthMobsMod.MODID + "." + path;
	}

	@SubscribeEvent
	public static void registerSpawnPlacement(SpawnPlacementRegisterEvent event) {
        Raid.RaiderType.create("viler_witch", VILER_WITCH.get(), new int[]{0, 0, 1, 0, 1, 1, 2, 1});

        event.register(CLUCK_SHROOM.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, CluckShroom::checkCluckShroomSpawnRules, SpawnPlacementRegisterEvent.Operation.REPLACE);
        event.register(FANCY_CHICKEN.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Animal::checkAnimalSpawnRules, SpawnPlacementRegisterEvent.Operation.REPLACE);
        event.register(WOOLY_COW.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Animal::checkAnimalSpawnRules, SpawnPlacementRegisterEvent.Operation.REPLACE);
        event.register(UMBRA_COW.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Animal::checkAnimalSpawnRules, SpawnPlacementRegisterEvent.Operation.REPLACE);
        event.register(ALBINO_COW.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Animal::checkAnimalSpawnRules, SpawnPlacementRegisterEvent.Operation.REPLACE);
        event.register(CREAM_COW.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Animal::checkAnimalSpawnRules, SpawnPlacementRegisterEvent.Operation.REPLACE);
		event.register(TEACUP_PIG.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Animal::checkAnimalSpawnRules, SpawnPlacementRegisterEvent.Operation.REPLACE);
		event.register(MAGMA_COW.get(), SpawnPlacements.Type.NO_RESTRICTIONS, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, MagmaCow::checkMagmaSpawnRules, SpawnPlacementRegisterEvent.Operation.REPLACE);


		event.register(HORNED_SHEEP.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Animal::checkAnimalSpawnRules, SpawnPlacementRegisterEvent.Operation.REPLACE);
		event.register(HYPER_RABBIT.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, HyperRabbit::checkHyperSpawnRules, SpawnPlacementRegisterEvent.Operation.REPLACE);
		event.register(MOOBLOOM.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Animal::checkAnimalSpawnRules, SpawnPlacementRegisterEvent.Operation.REPLACE);
		event.register(MOOLIP.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Animal::checkAnimalSpawnRules, SpawnPlacementRegisterEvent.Operation.REPLACE);
		event.register(JUMBO_RABBIT.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, JumboRabbit::checkJumboSpawnRules, SpawnPlacementRegisterEvent.Operation.REPLACE);
		event.register(ZOMBIFIED_PIG.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Animal::checkAnimalSpawnRules, SpawnPlacementRegisterEvent.Operation.REPLACE);
        event.register(DUCK.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Animal::checkAnimalSpawnRules, SpawnPlacementRegisterEvent.Operation.REPLACE);
        event.register(JOLLY_LLAMA.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Animal::checkAnimalSpawnRules, SpawnPlacementRegisterEvent.Operation.REPLACE);


		event.register(MELON_GOLEM.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Mob::checkMobSpawnRules, SpawnPlacementRegisterEvent.Operation.REPLACE);
		event.register(FURNACE_GOLEM.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Mob::checkMobSpawnRules, SpawnPlacementRegisterEvent.Operation.REPLACE);

		event.register(BONE_SPIDER.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkMonsterSpawnRules, SpawnPlacementRegisterEvent.Operation.REPLACE);
		event.register(STRAY_BONE_SPIDER.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkMonsterSpawnRules, SpawnPlacementRegisterEvent.Operation.REPLACE);
		event.register(VILER_WITCH.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkMonsterSpawnRules, SpawnPlacementRegisterEvent.Operation.REPLACE);
		event.register(BOULDERING_ZOMBIE.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkMonsterSpawnRules, SpawnPlacementRegisterEvent.Operation.REPLACE);
		event.register(LOBBER_ZOMBIE.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkMonsterSpawnRules, SpawnPlacementRegisterEvent.Operation.REPLACE);
		event.register(BOULDERING_DROWNED.get(), SpawnPlacements.Type.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, BoulderingDrowned::checkBoulderingDrownedSpawnRules, SpawnPlacementRegisterEvent.Operation.REPLACE);
		event.register(LOBBER_DROWNED.get(), SpawnPlacements.Type.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, LobberDrowned::checkLobberDrownedSpawnRules, SpawnPlacementRegisterEvent.Operation.REPLACE);

		event.register(TROPICAL_SLIME.get(), SpawnPlacements.Type.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, TropicalSlime::checkTropicalSpawnRules, SpawnPlacementRegisterEvent.Operation.REPLACE);
		event.register(SKELETON_WOLF.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, SkeletonWolf::checkSkeletonWolfSpawnRules, SpawnPlacementRegisterEvent.Operation.REPLACE);
		event.register(WITHER_SKELETON_WOLF.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, SkeletonWolf::checkSkeletonWolfSpawnRules, SpawnPlacementRegisterEvent.Operation.REPLACE);
		event.register(BABY_GHAST.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, BabyGhast::checkGhastSpawnRules, SpawnPlacementRegisterEvent.Operation.REPLACE);
	}

	@SubscribeEvent
	public static void registerEntityAttribute(EntityAttributeCreationEvent event) {
		event.put(CLUCK_SHROOM.get(), Chicken.createAttributes().build());
		event.put(FANCY_CHICKEN.get(), Chicken.createAttributes().build());
		event.put(WOOLY_COW.get(), Cow.createAttributes().build());
		event.put(UMBRA_COW.get(), Cow.createAttributes().build());
		event.put(ALBINO_COW.get(), Cow.createAttributes().build());
        event.put(CREAM_COW.get(), Cow.createAttributes().build());
        event.put(TEACUP_PIG.get(), TeaCupPig.createAttributes().build());
        event.put(MAGMA_COW.get(), MagmaCow.createAttributes().build());

		event.put(HORNED_SHEEP.get(), HornedSheep.createAttributes().build());
		event.put(HYPER_RABBIT.get(), HyperRabbit.createAttributes().build());
		event.put(MOOBLOOM.get(), Cow.createAttributes().build());
		event.put(MOOLIP.get(), Cow.createAttributes().build());
		event.put(JUMBO_RABBIT.get(), JumboRabbit.createAttributes().build());
		event.put(ZOMBIFIED_PIG.get(), ZombifiedPig.createAttributes().build());
        event.put(DUCK.get(), Chicken.createAttributes().build());
        event.put(JOLLY_LLAMA.get(), Llama.createAttributes().build());

		event.put(MELON_GOLEM.get(), MelonGolem.createAttributes().build());
		event.put(FURNACE_GOLEM.get(), FurnaceGolem.createAttributes().build());


		event.put(BONE_SPIDER.get(), BoneSpider.createAttributes().build());
		event.put(STRAY_BONE_SPIDER.get(), BoneSpider.createAttributes().build());
		event.put(VILER_WITCH.get(), VilerWitch.createAttributes().build());
		event.put(BOULDERING_ZOMBIE.get(), BoulderingZombie.createAttributes().build());
		event.put(LOBBER_ZOMBIE.get(), Zombie.createAttributes().build());
		event.put(BOULDERING_DROWNED.get(), BoulderingDrowned.createAttributes().build());
		event.put(LOBBER_DROWNED.get(), Zombie.createAttributes().build());
		event.put(TROPICAL_SLIME.get(), Monster.createMonsterAttributes().build());
		event.put(SKELETON_WOLF.get(), SkeletonWolf.createAttributes().build());
		event.put(WITHER_SKELETON_WOLF.get(), SkeletonWolf.createAttributes().build());
		event.put(BABY_GHAST.get(), BabyGhast.createAttributes().build());
	}
}