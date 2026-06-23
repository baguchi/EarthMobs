package baguchan.earthmobsmod.registry;

import baguchan.earthmobsmod.EarthMobsMod;
import baguchan.earthmobsmod.entity.*;
import baguchan.earthmobsmod.entity.projectile.*;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.SpawnPlacementTypes;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.chicken.Chicken;
import net.minecraft.world.entity.animal.cow.Cow;
import net.minecraft.world.entity.animal.equine.Llama;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.phys.Vec3;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import net.neoforged.neoforge.event.entity.RegisterSpawnPlacementsEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

@EventBusSubscriber(modid = EarthMobsMod.MODID)
public class ModEntities {
    public static final DeferredRegister.Entities ENTITIES = DeferredRegister.createEntities(EarthMobsMod.MODID);

    public static final DeferredHolder<EntityType<?>, EntityType<CluckShroom>> CLUCK_SHROOM = ENTITIES.registerEntityType("cluck_shroom", CluckShroom::new, MobCategory.CREATURE, (builder) -> builder.sized(0.4F, 0.7F).eyeHeight(0.644F)
            .passengerAttachments(new Vec3(0.0, 0.7, -0.1)).clientTrackingRange(10));
    public static final DeferredHolder<EntityType<?>, EntityType<FancyChicken>> FANCY_CHICKEN = ENTITIES.registerEntityType("fancy_chicken", FancyChicken::new, MobCategory.CREATURE, (builder) -> builder.eyeHeight(0.799F)
            .passengerAttachments(new Vec3(0.0, 0.85, -0.1)).sized(0.4F, 0.85F).clientTrackingRange(10));
    public static final DeferredHolder<EntityType<?>, EntityType<WoolyCow>> WOOLY_COW = ENTITIES.registerEntityType("wooly_cow", WoolyCow::new, MobCategory.CREATURE, (builder) -> builder.sized(0.9F, 1.4F).clientTrackingRange(10));
    public static final DeferredHolder<EntityType<?>, EntityType<UmbraCow>> UMBRA_COW = ENTITIES.registerEntityType("umbra_cow", UmbraCow::new, MobCategory.CREATURE, (builder) -> builder.sized(0.9F, 1.4F).clientTrackingRange(10));
    public static final DeferredHolder<EntityType<?>, EntityType<TeaCupPig>> TEACUP_PIG = ENTITIES.registerEntityType("teacup_pig", TeaCupPig::new, MobCategory.CREATURE, (builder) -> builder.sized(0.4F, 0.4F).clientTrackingRange(10));
    public static final DeferredHolder<EntityType<?>, EntityType<MagmaCow>> MAGMA_COW = ENTITIES.registerEntityType("magma_cow", MagmaCow::new, MobCategory.CREATURE, (builder) -> builder.sized(0.9F, 1.4F).clientTrackingRange(10).fireImmune());


    public static final DeferredHolder<EntityType<?>, EntityType<HornedSheep>> HORNED_SHEEP = ENTITIES.registerEntityType("horned_sheep", HornedSheep::new, MobCategory.CREATURE, (builder) -> builder.sized(0.9F, 1.3F).clientTrackingRange(10));
    public static final DeferredHolder<EntityType<?>, EntityType<HyperRabbit>> HYPER_RABBIT = ENTITIES.registerEntityType("hyper_rabbit", HyperRabbit::new, MobCategory.CREATURE, (builder) -> builder.sized(0.4F, 0.6F).clientTrackingRange(8));
    public static final DeferredHolder<EntityType<?>, EntityType<Moobloom>> MOOBLOOM = ENTITIES.registerEntityType("moobloom", Moobloom::new, MobCategory.CREATURE, (builder) -> builder.sized(0.9F, 1.4F).clientTrackingRange(8));
    public static final DeferredHolder<EntityType<?>, EntityType<Moolip>> MOOLIP = ENTITIES.registerEntityType("moolip", Moolip::new, MobCategory.CREATURE, (builder) -> builder.sized(0.9F, 1.4F).clientTrackingRange(8));
    public static final DeferredHolder<EntityType<?>, EntityType<JumboRabbit>> JUMBO_RABBIT = ENTITIES.registerEntityType("jumbo_rabbit", JumboRabbit::new, MobCategory.CREATURE, (builder) -> builder.sized(0.7F, 0.9F).eyeHeight(0.89F).clientTrackingRange(8));
    public static final DeferredHolder<EntityType<?>, EntityType<ZombifiedPig>> ZOMBIFIED_PIG = ENTITIES.registerEntityType("zombified_pig", ZombifiedPig::new, MobCategory.CREATURE, (builder) -> builder.sized(0.6F, 0.85F).fireImmune());
    public static final DeferredHolder<EntityType<?>, EntityType<JollyLlama>> JOLLY_LLAMA = ENTITIES.registerEntityType("jolly_llama", JollyLlama::new, MobCategory.CREATURE, (builder) -> builder.sized(0.9F, 1.87F).eyeHeight(1.7765F)
            .passengerAttachments(new Vec3(0.0, 1.37, -0.3))
            .clientTrackingRange(10));


    public static final DeferredHolder<EntityType<?>, EntityType<MelonGolem>> MELON_GOLEM = ENTITIES.registerEntityType("melon_golem", MelonGolem::new, MobCategory.MISC, (builder) -> builder.sized(0.7F, 1.9F).immuneTo(BlockTags.SNOW_GOLEM_IMMUNE_TO).clientTrackingRange(8));
    public static final DeferredHolder<EntityType<?>, EntityType<FurnaceGolem>> FURNACE_GOLEM = ENTITIES.registerEntityType("furnace_golem", FurnaceGolem::new, MobCategory.MISC, (builder) -> builder.sized(1.4F, 2.7F).clientTrackingRange(10));


    public static final DeferredHolder<EntityType<?>, EntityType<BoneSpider>> BONE_SPIDER = ENTITIES.registerEntityType("bone_spider", BoneSpider::new, MobCategory.MONSTER, (builder) -> builder.notInPeaceful().sized(1.4F, 0.9F));

    public static final DeferredHolder<EntityType<?>, EntityType<VilerWitch>> VILER_WITCH = ENTITIES.registerEntityType("viler_witch", VilerWitch::new, MobCategory.MONSTER, (builder) -> builder.notInPeaceful().sized(0.6F, 1.95F).eyeHeight(1.74F)
            .passengerAttachments(2.0125F)
            .ridingOffset(-0.7F)
            .clientTrackingRange(8));

    public static final DeferredHolder<EntityType<?>, EntityType<BoulderingZombie>> BOULDERING_ZOMBIE = ENTITIES.registerEntityType("bouldering_zombie", BoulderingZombie::new, MobCategory.MONSTER, (builder) -> builder.notInPeaceful().sized(0.6F, 1.95F).eyeHeight(1.74F)
            .passengerAttachments(2.0125F)
            .ridingOffset(-0.7F)
            .clientTrackingRange(8));
    public static final DeferredHolder<EntityType<?>, EntityType<LobberZombie>> LOBBER_ZOMBIE = ENTITIES.registerEntityType("lobber_zombie", LobberZombie::new, MobCategory.MONSTER, (builder) -> builder.notInPeaceful().sized(0.6F, 1.95F).eyeHeight(1.74F)
            .passengerAttachments(2.0125F)
            .ridingOffset(-0.7F)
            .clientTrackingRange(8));

    public static final DeferredHolder<EntityType<?>, EntityType<BoulderingDrowned>> BOULDERING_DROWNED = ENTITIES.registerEntityType("bouldering_drowned", BoulderingDrowned::new, MobCategory.MONSTER, (builder) -> builder.notInPeaceful().sized(0.6F, 1.95F).eyeHeight(1.74F)
            .passengerAttachments(2.0125F)
            .ridingOffset(-0.7F)
            .clientTrackingRange(8));
    public static final DeferredHolder<EntityType<?>, EntityType<LobberDrowned>> LOBBER_DROWNED = ENTITIES.registerEntityType("lobber_drowned", LobberDrowned::new, MobCategory.MONSTER, (builder) -> builder.notInPeaceful().sized(0.6F, 1.95F).eyeHeight(1.74F)
            .passengerAttachments(2.0125F)
            .ridingOffset(-0.7F)
            .clientTrackingRange(8));

    public static final DeferredHolder<EntityType<?>, EntityType<TropicalSlime>> TROPICAL_SLIME = ENTITIES.registerEntityType("tropical_slime", TropicalSlime::new, MobCategory.MONSTER, (builder) -> builder.notInPeaceful().sized(0.52F, 0.52F).eyeHeight(0.325F).clientTrackingRange(10));

    public static final DeferredHolder<EntityType<?>, EntityType<SkeletonWolf>> SKELETON_WOLF = ENTITIES.registerEntityType("skeleton_wolf", SkeletonWolf::new, MobCategory.MONSTER, (builder) -> builder.notInPeaceful().sized(0.6F, 0.85F));
    public static final DeferredHolder<EntityType<?>, EntityType<WitherSkeletonWolf>> WITHER_SKELETON_WOLF = ENTITIES.registerEntityType("wither_skeleton_wolf", WitherSkeletonWolf::new, MobCategory.MONSTER, (builder) -> builder.notInPeaceful().sized(0.6F, 0.85F).fireImmune().immuneTo(BlockTags.WITHER_SKELETON_IMMUNE_TO));

    public static final DeferredHolder<EntityType<?>, EntityType<ZombifiedRabbit>> ZOMBIFIED_RABBIT = ENTITIES.registerEntityType("zombified_rabbit", ZombifiedRabbit::new, MobCategory.MONSTER, (builder) -> builder.notInPeaceful().sized(0.4F, 0.6F).eyeHeight(0.59F).clientTrackingRange(8));
    public static final DeferredHolder<EntityType<?>, EntityType<HuskRabbit>> HUSK_RABBIT = ENTITIES.registerEntityType("husk_rabbit", HuskRabbit::new, MobCategory.MONSTER, (builder) -> builder.notInPeaceful().sized(0.4F, 0.6F).eyeHeight(0.59F).clientTrackingRange(8));

    public static final DeferredHolder<EntityType<?>, EntityType<SmellyEgg>> SMELLY_EGG = ENTITIES.register("smelly_egg", () -> EntityType.Builder.<SmellyEgg>of(SmellyEgg::new, MobCategory.MISC).sized(0.25F, 0.25F).clientTrackingRange(4).updateInterval(10).build(prefix("smelly_egg")));
    public static final DeferredHolder<EntityType<?>, EntityType<FancyEgg>> FANCY_EGG = ENTITIES.register("fancy_egg", () -> EntityType.Builder.<FancyEgg>of(FancyEgg::new, MobCategory.MISC).sized(0.25F, 0.25F).clientTrackingRange(4).updateInterval(10).build(prefix("fancy_egg")));

    public static final DeferredHolder<EntityType<?>, EntityType<BoneShard>> BONE_SHARD = ENTITIES.register("bone_shard", () -> EntityType.Builder.<BoneShard>of(BoneShard::new, MobCategory.MISC).sized(0.25F, 0.25F).clientTrackingRange(4).updateInterval(10).build(prefix("bone_shard")));
    public static final DeferredHolder<EntityType<?>, EntityType<StrayBoneShard>> STRAY_BONE_SHARD = ENTITIES.register("stray_bone_shard", () -> EntityType.Builder.<StrayBoneShard>of(StrayBoneShard::new, MobCategory.MISC).sized(0.25F, 0.25F).clientTrackingRange(4).updateInterval(10).build(prefix("stray_bone_shard")));

    public static final DeferredHolder<EntityType<?>, EntityType<MelonSeed>> MELON_SEED = ENTITIES.register("melon_seeds", () -> EntityType.Builder.<MelonSeed>of(MelonSeed::new, MobCategory.MISC).sized(0.25F, 0.25F).clientTrackingRange(4).updateInterval(10).build(prefix("melon_seeds")));
    public static final DeferredHolder<EntityType<?>, EntityType<ZombieFlesh>> ZOMBIE_FLESH = ENTITIES.register("zombie_flesh", () -> EntityType.Builder.<ZombieFlesh>of(ZombieFlesh::new, MobCategory.MISC).sized(0.25F, 0.25F).clientTrackingRange(4).updateInterval(10).build(prefix("zombie_flesh")));


    private static ResourceKey<EntityType<?>> prefix(String path) {
        return ResourceKey.create(Registries.ENTITY_TYPE, Identifier.fromNamespaceAndPath(EarthMobsMod.MODID, path));
    }
	@SubscribeEvent
	public static void registerSpawnPlacement(RegisterSpawnPlacementsEvent event) {
        event.register(CLUCK_SHROOM.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, CluckShroom::checkCluckShroomSpawnRules, RegisterSpawnPlacementsEvent.Operation.OR);
        event.register(FANCY_CHICKEN.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Animal::checkAnimalSpawnRules, RegisterSpawnPlacementsEvent.Operation.OR);
        event.register(WOOLY_COW.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Animal::checkAnimalSpawnRules, RegisterSpawnPlacementsEvent.Operation.OR);
        event.register(UMBRA_COW.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Animal::checkAnimalSpawnRules, RegisterSpawnPlacementsEvent.Operation.OR);

        event.register(TEACUP_PIG.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Animal::checkAnimalSpawnRules, RegisterSpawnPlacementsEvent.Operation.OR);
        event.register(MAGMA_COW.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, MagmaCow::checkMagmaSpawnRules, RegisterSpawnPlacementsEvent.Operation.OR);


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
        event.register(VILER_WITCH.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkMonsterSpawnRules, RegisterSpawnPlacementsEvent.Operation.OR);
        event.register(BOULDERING_ZOMBIE.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, BoulderingZombie::checkBoulderingSpawnRules, RegisterSpawnPlacementsEvent.Operation.OR);
        event.register(LOBBER_ZOMBIE.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkMonsterSpawnRules, RegisterSpawnPlacementsEvent.Operation.OR);
        event.register(BOULDERING_DROWNED.get(), SpawnPlacementTypes.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, BoulderingDrowned::checkBoulderingDrownedSpawnRules, RegisterSpawnPlacementsEvent.Operation.OR);
        event.register(LOBBER_DROWNED.get(), SpawnPlacementTypes.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, LobberDrowned::checkLobberDrownedSpawnRules, RegisterSpawnPlacementsEvent.Operation.OR);

        event.register(TROPICAL_SLIME.get(), SpawnPlacementTypes.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, TropicalSlime::checkTropicalSpawnRules, RegisterSpawnPlacementsEvent.Operation.OR);
        event.register(SKELETON_WOLF.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, SkeletonWolf::checkSkeletonWolfSpawnRules, RegisterSpawnPlacementsEvent.Operation.OR);
        event.register(WITHER_SKELETON_WOLF.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, SkeletonWolf::checkSkeletonWolfSpawnRules, RegisterSpawnPlacementsEvent.Operation.OR);

        event.register(ZOMBIFIED_RABBIT.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, ZombifiedRabbit::checkMonsterSpawnRules, RegisterSpawnPlacementsEvent.Operation.OR);
        event.register(HUSK_RABBIT.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, ZombifiedRabbit::checkMonsterSpawnRules, RegisterSpawnPlacementsEvent.Operation.OR);
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
		event.put(VILER_WITCH.get(), VilerWitch.createAttributes().build());
		event.put(BOULDERING_ZOMBIE.get(), BoulderingZombie.createAttributes().build());
        event.put(LOBBER_ZOMBIE.get(), LobberZombie.createAttributes().build());
		event.put(BOULDERING_DROWNED.get(), BoulderingDrowned.createAttributes().build());
        event.put(LOBBER_DROWNED.get(), LobberDrowned.createAttributes().build());

		event.put(TROPICAL_SLIME.get(), Monster.createMonsterAttributes().build());
		event.put(SKELETON_WOLF.get(), SkeletonWolf.createAttributes().build());
		event.put(WITHER_SKELETON_WOLF.get(), SkeletonWolf.createAttributes().build());

        event.put(ZOMBIFIED_RABBIT.get(), ZombifiedRabbit.createAttributes().build());
        event.put(HUSK_RABBIT.get(), ZombifiedRabbit.createAttributes().build());
    }
}