package baguchan.earthmobsmod.registry;

import baguchan.earthmobsmod.EarthMobsMod;
import baguchan.earthmobsmod.entity.*;
import baguchan.earthmobsmod.entity.projectile.*;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.SpawnPlacementTypes;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.Chicken;
import net.minecraft.world.entity.animal.Cow;
import net.minecraft.world.entity.animal.horse.Llama;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.Heightmap;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import net.neoforged.neoforge.event.entity.RegisterSpawnPlacementsEvent;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

@EventBusSubscriber(modid = EarthMobsMod.MODID, bus = EventBusSubscriber.Bus.MOD)
public class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(BuiltInRegistries.ENTITY_TYPE, EarthMobsMod.MODID);

    public static final Supplier<EntityType<CluckShroom>> CLUCK_SHROOM = ENTITIES.register("cluck_shroom", () -> EntityType.Builder.of(CluckShroom::new, MobCategory.CREATURE).sized(0.4F, 0.7F).clientTrackingRange(10).build(prefix("cluck_shroom")));
    public static final Supplier<EntityType<FancyChicken>> FANCY_CHICKEN = ENTITIES.register("fancy_chicken", () -> EntityType.Builder.of(FancyChicken::new, MobCategory.CREATURE).sized(0.4F, 0.85F).clientTrackingRange(10).build(prefix("fancy_chicken")));
    public static final Supplier<EntityType<WoolyCow>> WOOLY_COW = ENTITIES.register("wooly_cow", () -> EntityType.Builder.of(WoolyCow::new, MobCategory.CREATURE).sized(0.9F, 1.4F).clientTrackingRange(10).build(prefix("wooly_cow")));
    public static final Supplier<EntityType<UmbraCow>> UMBRA_COW = ENTITIES.register("umbra_cow", () -> EntityType.Builder.of(UmbraCow::new, MobCategory.CREATURE).sized(0.9F, 1.4F).clientTrackingRange(10).build(prefix("umbra_cow")));
	public static final Supplier<EntityType<TeaCupPig>> TEACUP_PIG = ENTITIES.register("teacup_pig", () -> EntityType.Builder.of(TeaCupPig::new, MobCategory.CREATURE).sized(0.4F, 0.4F).clientTrackingRange(10).build(prefix("teacup_pig")));
    public static final Supplier<EntityType<MagmaCow>> MAGMA_COW = ENTITIES.register("magma_cow", () -> EntityType.Builder.of(MagmaCow::new, MobCategory.CREATURE).sized(0.9F, 1.4F).clientTrackingRange(10).fireImmune().build(prefix("magma_cow")));


    public static final Supplier<EntityType<HornedSheep>> HORNED_SHEEP = ENTITIES.register("horned_sheep", () -> EntityType.Builder.of(HornedSheep::new, MobCategory.CREATURE).sized(0.9F, 1.3F).clientTrackingRange(10).build(prefix("horned_sheep")));
    public static final Supplier<EntityType<HyperRabbit>> HYPER_RABBIT = ENTITIES.register("hyper_rabbit", () -> EntityType.Builder.of(HyperRabbit::new, MobCategory.CREATURE).sized(0.4F, 0.6F).clientTrackingRange(8).build(prefix("hyper_rabbit")));
    public static final Supplier<EntityType<Moobloom>> MOOBLOOM = ENTITIES.register("moobloom", () -> EntityType.Builder.of(Moobloom::new, MobCategory.CREATURE).sized(0.9F, 1.4F).clientTrackingRange(8).build(prefix("moobloom")));
    public static final Supplier<EntityType<Moolip>> MOOLIP = ENTITIES.register("moolip", () -> EntityType.Builder.of(Moolip::new, MobCategory.CREATURE).sized(0.9F, 1.4F).clientTrackingRange(8).build(prefix("moolip")));
    public static final Supplier<EntityType<JumboRabbit>> JUMBO_RABBIT = ENTITIES.register("jumbo_rabbit", () -> EntityType.Builder.of(JumboRabbit::new, MobCategory.CREATURE).sized(0.7F, 1.2F).clientTrackingRange(8).build(prefix("jumbo_rabbit")));
    public static final Supplier<EntityType<ZombifiedPig>> ZOMBIFIED_PIG = ENTITIES.register("zombified_pig", () -> EntityType.Builder.of(ZombifiedPig::new, MobCategory.CREATURE).sized(0.6F, 0.85F).fireImmune().build(prefix("zombified_pig")));
    public static final Supplier<EntityType<JollyLlama>> JOLLY_LLAMA = ENTITIES.register("jolly_llama", () -> EntityType.Builder.of(JollyLlama::new, MobCategory.CREATURE).sized(0.9F, 1.87F).clientTrackingRange(10).build(prefix("jolly_llama")));


    public static final Supplier<EntityType<MelonGolem>> MELON_GOLEM = ENTITIES.register("melon_golem", () -> EntityType.Builder.of(MelonGolem::new, MobCategory.MISC).sized(0.7F, 1.9F).immuneTo(Blocks.POWDER_SNOW).clientTrackingRange(8).build(prefix("melon_golem")));
    public static final Supplier<EntityType<FurnaceGolem>> FURNACE_GOLEM = ENTITIES.register("furnace_golem", () -> EntityType.Builder.of(FurnaceGolem::new, MobCategory.MISC).sized(1.4F, 2.7F).clientTrackingRange(10).build(prefix("furnace_golem")));


    public static final Supplier<EntityType<BoneSpider>> BONE_SPIDER = ENTITIES.register("bone_spider", () -> EntityType.Builder.of(BoneSpider::new, MobCategory.MONSTER).sized(1.4F, 0.9F).build(prefix("bone_spider")));
    public static final Supplier<EntityType<StrayBoneSpider>> STRAY_BONE_SPIDER = ENTITIES.register("stray_bone_spider", () -> EntityType.Builder.of(StrayBoneSpider::new, MobCategory.MONSTER).sized(1.4F, 0.9F).build(prefix("stray_bone_spider")));

    public static final Supplier<EntityType<VilerWitch>> VILER_WITCH = ENTITIES.register("viler_witch", () -> EntityType.Builder.of(VilerWitch::new, MobCategory.MONSTER).sized(0.6F, 1.95F).build(prefix("viler_witch")));

    public static final Supplier<EntityType<BoulderingZombie>> BOULDERING_ZOMBIE = ENTITIES.register("bouldering_zombie", () -> EntityType.Builder.of(BoulderingZombie::new, MobCategory.MONSTER).sized(0.6F, 1.95F).build(prefix("bouldering_zombie")));
    public static final Supplier<EntityType<LobberZombie>> LOBBER_ZOMBIE = ENTITIES.register("lobber_zombie", () -> EntityType.Builder.of(LobberZombie::new, MobCategory.MONSTER).sized(0.6F, 1.95F).build(prefix("lobber_zombie")));

    public static final Supplier<EntityType<BoulderingDrowned>> BOULDERING_DROWNED = ENTITIES.register("bouldering_drowned", () -> EntityType.Builder.of(BoulderingDrowned::new, MobCategory.MONSTER).sized(0.6F, 1.95F).build(prefix("bouldering_drowned")));
    public static final Supplier<EntityType<LobberDrowned>> LOBBER_DROWNED = ENTITIES.register("lobber_drowned", () -> EntityType.Builder.of(LobberDrowned::new, MobCategory.MONSTER).sized(0.6F, 1.95F).build(prefix("lobber_drowned")));


    public static final Supplier<EntityType<BoulderingFrozenZombie>> BOULDERING_FROZEN_ZOMBIE = ENTITIES.register("bouldering_frozen_zombie", () -> EntityType.Builder.of(BoulderingFrozenZombie::new, MobCategory.MONSTER).sized(0.6F, 1.95F).build(prefix("bouldering_frozen_zombie")));
    public static final Supplier<EntityType<LobberHusk>> LOBBER_HUSK = ENTITIES.register("lobber_husk", () -> EntityType.Builder.of(LobberHusk::new, MobCategory.MONSTER).sized(0.6F, 1.95F).build(prefix("lobber_husk")));

	public static final Supplier<EntityType<TropicalSlime>> TROPICAL_SLIME = ENTITIES.register("tropical_slime", () -> EntityType.Builder.of(TropicalSlime::new, MobCategory.MONSTER).sized(0.52F, 0.52F).eyeHeight(0.325F).clientTrackingRange(10).build(prefix("tropical_slime")));

    public static final Supplier<EntityType<SkeletonWolf>> SKELETON_WOLF = ENTITIES.register("skeleton_wolf", () -> EntityType.Builder.of(SkeletonWolf::new, MobCategory.MONSTER).sized(0.6F, 0.85F).build(prefix("skeleton_wolf")));
    public static final Supplier<EntityType<WitherSkeletonWolf>> WITHER_SKELETON_WOLF = ENTITIES.register("wither_skeleton_wolf", () -> EntityType.Builder.of(WitherSkeletonWolf::new, MobCategory.MONSTER).sized(0.6F, 0.85F).fireImmune().immuneTo(Blocks.WITHER_ROSE).build(prefix("wither_skeleton_wolf")));

    public static final Supplier<EntityType<ZombifiedRabbit>> ZOMBIFIED_RABBIT = ENTITIES.register("zombified_rabbit", () -> EntityType.Builder.of(ZombifiedRabbit::new, MobCategory.MONSTER).sized(0.4F, 0.6F).clientTrackingRange(8).build(prefix("zombified_rabbit")));

    public static final Supplier<EntityType<SmellyEgg>> SMELLY_EGG = ENTITIES.register("smelly_egg", () -> EntityType.Builder.<SmellyEgg>of(SmellyEgg::new, MobCategory.MISC).sized(0.25F, 0.25F).clientTrackingRange(4).updateInterval(10).build(prefix("smelly_egg")));
    public static final Supplier<EntityType<FancyEgg>> FANCY_EGG = ENTITIES.register("fancy_egg", () -> EntityType.Builder.<FancyEgg>of(FancyEgg::new, MobCategory.MISC).sized(0.25F, 0.25F).clientTrackingRange(4).updateInterval(10).build(prefix("fancy_egg")));

    public static final Supplier<EntityType<BoneShard>> BONE_SHARD = ENTITIES.register("bone_shard", () -> EntityType.Builder.<BoneShard>of(BoneShard::new, MobCategory.MISC).sized(0.25F, 0.25F).clientTrackingRange(4).updateInterval(10).build(prefix("bone_shard")));
    public static final Supplier<EntityType<StrayBoneShard>> STRAY_BONE_SHARD = ENTITIES.register("stray_bone_shard", () -> EntityType.Builder.<StrayBoneShard>of(StrayBoneShard::new, MobCategory.MISC).sized(0.25F, 0.25F).clientTrackingRange(4).updateInterval(10).build(prefix("stray_bone_shard")));

    public static final Supplier<EntityType<MelonSeed>> MELON_SEED = ENTITIES.register("melon_seeds", () -> EntityType.Builder.<MelonSeed>of(MelonSeed::new, MobCategory.MISC).sized(0.25F, 0.25F).clientTrackingRange(4).updateInterval(10).build(prefix("melon_seeds")));
    public static final Supplier<EntityType<ZombieFlesh>> ZOMBIE_FLESH = ENTITIES.register("zombie_flesh", () -> EntityType.Builder.<ZombieFlesh>of(ZombieFlesh::new, MobCategory.MISC).sized(0.25F, 0.25F).clientTrackingRange(4).updateInterval(10).build(prefix("zombie_flesh")));


    private static ResourceKey<EntityType<?>> prefix(String path) {
        return ResourceKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(EarthMobsMod.MODID, path));
    }
	@SubscribeEvent
	public static void registerSpawnPlacement(RegisterSpawnPlacementsEvent event) {
        event.register(CLUCK_SHROOM.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, CluckShroom::checkCluckShroomSpawnRules, RegisterSpawnPlacementsEvent.Operation.OR);
        event.register(FANCY_CHICKEN.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Animal::checkAnimalSpawnRules, RegisterSpawnPlacementsEvent.Operation.OR);
        event.register(WOOLY_COW.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Animal::checkAnimalSpawnRules, RegisterSpawnPlacementsEvent.Operation.OR);
        event.register(UMBRA_COW.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Animal::checkAnimalSpawnRules, RegisterSpawnPlacementsEvent.Operation.OR);

        event.register(TEACUP_PIG.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Animal::checkAnimalSpawnRules, RegisterSpawnPlacementsEvent.Operation.OR);
        event.register(MAGMA_COW.get(), SpawnPlacementTypes.NO_RESTRICTIONS, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, MagmaCow::checkMagmaSpawnRules, RegisterSpawnPlacementsEvent.Operation.OR);


        event.register(HORNED_SHEEP.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Animal::checkAnimalSpawnRules, RegisterSpawnPlacementsEvent.Operation.OR);
        event.register(HYPER_RABBIT.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, HyperRabbit::checkHyperSpawnRules, RegisterSpawnPlacementsEvent.Operation.OR);
        event.register(MOOBLOOM.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Animal::checkAnimalSpawnRules, RegisterSpawnPlacementsEvent.Operation.OR);
        event.register(MOOLIP.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Animal::checkAnimalSpawnRules, RegisterSpawnPlacementsEvent.Operation.OR);
        event.register(JUMBO_RABBIT.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, JumboRabbit::checkJumboSpawnRules, RegisterSpawnPlacementsEvent.Operation.OR);
        event.register(ZOMBIFIED_PIG.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Animal::checkAnimalSpawnRules, RegisterSpawnPlacementsEvent.Operation.OR);
        event.register(JOLLY_LLAMA.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Animal::checkAnimalSpawnRules, RegisterSpawnPlacementsEvent.Operation.OR);


        event.register(MELON_GOLEM.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Mob::checkMobSpawnRules, RegisterSpawnPlacementsEvent.Operation.OR);
        event.register(FURNACE_GOLEM.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Mob::checkMobSpawnRules, RegisterSpawnPlacementsEvent.Operation.OR);

        event.register(BONE_SPIDER.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkMonsterSpawnRules, RegisterSpawnPlacementsEvent.Operation.OR);
        event.register(STRAY_BONE_SPIDER.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkMonsterSpawnRules, RegisterSpawnPlacementsEvent.Operation.OR);
        event.register(VILER_WITCH.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkMonsterSpawnRules, RegisterSpawnPlacementsEvent.Operation.OR);
        event.register(BOULDERING_ZOMBIE.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, BoulderingZombie::checkBoulderingSpawnRules, RegisterSpawnPlacementsEvent.Operation.OR);
        event.register(LOBBER_ZOMBIE.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkMonsterSpawnRules, RegisterSpawnPlacementsEvent.Operation.OR);
        event.register(BOULDERING_DROWNED.get(), SpawnPlacementTypes.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, BoulderingDrowned::checkBoulderingDrownedSpawnRules, RegisterSpawnPlacementsEvent.Operation.OR);
        event.register(LOBBER_DROWNED.get(), SpawnPlacementTypes.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, LobberDrowned::checkLobberDrownedSpawnRules, RegisterSpawnPlacementsEvent.Operation.OR);


        event.register(BOULDERING_FROZEN_ZOMBIE.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, BoulderingFrozenZombie::checkFrozenZombieSpawnRules, RegisterSpawnPlacementsEvent.Operation.OR);
        event.register(LOBBER_HUSK.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, LobberHusk::checkHuskSpawnRules, RegisterSpawnPlacementsEvent.Operation.OR);

        event.register(TROPICAL_SLIME.get(), SpawnPlacementTypes.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, TropicalSlime::checkTropicalSpawnRules, RegisterSpawnPlacementsEvent.Operation.OR);
        event.register(SKELETON_WOLF.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, SkeletonWolf::checkSkeletonWolfSpawnRules, RegisterSpawnPlacementsEvent.Operation.OR);
        event.register(WITHER_SKELETON_WOLF.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, SkeletonWolf::checkSkeletonWolfSpawnRules, RegisterSpawnPlacementsEvent.Operation.OR);

        event.register(ZOMBIFIED_RABBIT.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, ZombifiedRabbit::checkMonsterSpawnRules, RegisterSpawnPlacementsEvent.Operation.OR);
	}

	@SubscribeEvent
	public static void registerEntityAttribute(EntityAttributeCreationEvent event) {
		event.put(CLUCK_SHROOM.get(), Chicken.createAttributes().build());
		event.put(FANCY_CHICKEN.get(), Chicken.createAttributes().build());
		event.put(WOOLY_COW.get(), Cow.createAttributes().build());
		event.put(UMBRA_COW.get(), Cow.createAttributes().build());


		event.put(TEACUP_PIG.get(), TeaCupPig.createAttributes().build());
        event.put(MAGMA_COW.get(), MagmaCow.createAttributes().build());

		event.put(HORNED_SHEEP.get(), HornedSheep.createAttributes().build());
		event.put(HYPER_RABBIT.get(), HyperRabbit.createAttributes().build());
		event.put(MOOBLOOM.get(), Cow.createAttributes().build());
		event.put(MOOLIP.get(), Cow.createAttributes().build());
		event.put(JUMBO_RABBIT.get(), JumboRabbit.createAttributes().build());
		event.put(ZOMBIFIED_PIG.get(), ZombifiedPig.createAttributes().build());
        event.put(JOLLY_LLAMA.get(), Llama.createAttributes().build());

		event.put(MELON_GOLEM.get(), MelonGolem.createAttributes().build());
		event.put(FURNACE_GOLEM.get(), FurnaceGolem.createAttributes().build());


		event.put(BONE_SPIDER.get(), BoneSpider.createAttributes().build());
		event.put(STRAY_BONE_SPIDER.get(), BoneSpider.createAttributes().build());
		event.put(VILER_WITCH.get(), VilerWitch.createAttributes().build());
		event.put(BOULDERING_ZOMBIE.get(), BoulderingZombie.createAttributes().build());
        event.put(LOBBER_ZOMBIE.get(), LobberZombie.createAttributes().build());
		event.put(BOULDERING_DROWNED.get(), BoulderingDrowned.createAttributes().build());
        event.put(LOBBER_DROWNED.get(), LobberDrowned.createAttributes().build());

        event.put(BOULDERING_FROZEN_ZOMBIE.get(), BoulderingFrozenZombie.createAttributes().build());
        event.put(LOBBER_HUSK.get(), LobberHusk.createAttributes().build());

		event.put(TROPICAL_SLIME.get(), Monster.createMonsterAttributes().build());
		event.put(SKELETON_WOLF.get(), SkeletonWolf.createAttributes().build());
		event.put(WITHER_SKELETON_WOLF.get(), SkeletonWolf.createAttributes().build());

        event.put(ZOMBIFIED_RABBIT.get(), ZombifiedRabbit.createAttributes().build());
	}
}