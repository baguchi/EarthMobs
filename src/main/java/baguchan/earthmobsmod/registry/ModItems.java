package baguchan.earthmobsmod.registry;

import baguchan.earthmobsmod.EarthMobsMod;
import baguchan.earthmobsmod.data.CustomTagGenerator;
import baguchan.earthmobsmod.item.*;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.ComposterBlock;
import net.minecraft.world.level.material.Fluids;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.DeferredSpawnEggItem;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, modid = EarthMobsMod.MODID)
public class ModItems {
	public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(BuiltInRegistries.ITEM, EarthMobsMod.MODID);

	public static final Supplier<Item> RUBY = ITEMS.register("ruby", () -> new Item((new Item.Properties())));
	public static final Supplier<Item> TROPICAL_BALL = ITEMS.register("tropical_ball", () -> new TropicalBallItem((new Item.Properties())));

	public static final Supplier<Item> SMELLY_EGG = ITEMS.register("smelly_egg", () -> new ModEggItem(ModEntities.SMELLY_EGG, (new Item.Properties().stacksTo(16))));
	public static final Supplier<Item> FANCY_EGG = ITEMS.register("fancy_egg", () -> new ModEggItem(ModEntities.FANCY_EGG, (new Item.Properties().stacksTo(16))));
	public static final Supplier<Item> DUCK_EGG = ITEMS.register("duck_egg", () -> new ModEggItem(ModEntities.DUCK_EGG, (new Item.Properties().stacksTo(16))));

	public static final Supplier<Item> BONE_SHARD = ITEMS.register("bone_shard", () -> new BoneShardItem((new Item.Properties())));
	public static final Supplier<Item> FANCY_FEATHER = ITEMS.register("fancy_feather", () -> new Item((new Item.Properties())));
	public static final Supplier<Item> HARDER_FLESH = ITEMS.register("harder_flesh", () -> new Item((new Item.Properties().food(ModFoods.HARDER_FLESH))));

	public static final Supplier<Item> BONE_SPIDER_EYE = ITEMS.register("bone_spider_eye", () -> new Item((new Item.Properties())));
	public static final Supplier<Item> HORN = ITEMS.register("horn", () -> new Item((new Item.Properties().stacksTo(1))));
	public static final Supplier<Item> HORN_FLUTE = ITEMS.register("horn_flute", () -> new FixedInstrumentItem((new Item.Properties().stacksTo(1)), CustomTagGenerator.InstrumentTagGenerator.HORNED_SHEEP));

	public static final Supplier<Item> HYPER_RABBIT_FOOT = ITEMS.register("hyper_rabbit_foot", () -> new Item((new Item.Properties())));
	public static final Supplier<Item> ZOMBIFIED_RABBIT_FOOT = ITEMS.register("zombified_rabbit_foot", () -> new Item((new Item.Properties())));
	public static final Supplier<Item> MUD_BUCKET = ITEMS.register("mud_bucket", () -> new BucketItem(ModFluids.MUD, (new Item.Properties()).stacksTo(1).craftRemainder(Items.BUCKET)));
	public static final Supplier<Item> TROPICAL_SLIME_BUCKET = ITEMS.register("tropical_slime_bucket", () -> new MobBucketItem(ModEntities.TROPICAL_SLIME, () -> Fluids.WATER, () -> SoundEvents.BUCKET_EMPTY_FISH, (new Item.Properties()).stacksTo(1).craftRemainder(Items.BUCKET)));
	public static final Supplier<Item> TEACUP_PIG_POT = ITEMS.register("teacup_pig_pot", () -> new MobPotItem(ModEntities.TEACUP_PIG, () -> Fluids.EMPTY, () -> SoundEvents.ARMOR_EQUIP_GENERIC, (new Item.Properties()).stacksTo(1).craftRemainder(Items.FLOWER_POT)));

	public static final Supplier<Item> CLUCK_SHROOM_SPAWNEGG = ITEMS.register("cluck_shroom_spawn_egg", () -> new DeferredSpawnEggItem(ModEntities.CLUCK_SHROOM, 0xB52C17, 0xDC883B, (new Item.Properties())));
	public static final Supplier<Item> FANCY_CHICKEN_SPAWNEGG = ITEMS.register("fancy_chicken_spawn_egg", () -> new DeferredSpawnEggItem(ModEntities.FANCY_CHICKEN, 0xF4A213, 0x202F22, (new Item.Properties())));
	public static final Supplier<Item> WOOLY_COW_SPAWNEGG = ITEMS.register("wooly_cow_spawn_egg", () -> new DeferredSpawnEggItem(ModEntities.WOOLY_COW, 0xDB8948, 0xFFDBB6, (new Item.Properties())));
	public static final Supplier<Item> UMBRA_COW_SPAWNEGG = ITEMS.register("umbra_cow_spawn_egg", () -> new DeferredSpawnEggItem(ModEntities.UMBRA_COW, 0x403E57, 0x0A0B1D, (new Item.Properties())));
	public static final Supplier<Item> ALBINO_COW_SPAWNEGG = ITEMS.register("albino_cow_spawn_egg", () -> new DeferredSpawnEggItem(ModEntities.ALBINO_COW, 0xECE2E2, 0xE1CFCF, (new Item.Properties())));
	public static final Supplier<Item> CREAM_COW_SPAWNEGG = ITEMS.register("cream_cow_spawn_egg", () -> new DeferredSpawnEggItem(ModEntities.CREAM_COW, 0xE2AB5B, 0xE8DCBB, (new Item.Properties())));
	public static final Supplier<Item> TEACUP_PIG_SPAWNEGG = ITEMS.register("teacup_pig_spawn_egg", () -> new DeferredSpawnEggItem(ModEntities.TEACUP_PIG, 0xEEC9C1, 0xDD5555, (new Item.Properties())));


	public static final Supplier<Item> HORNED_SHEEP_SPAWNEGG = ITEMS.register("horned_sheep_spawn_egg", () -> new DeferredSpawnEggItem(ModEntities.HORNED_SHEEP, 15198183, 16758197, (new Item.Properties())));
	public static final Supplier<Item> HYPER_RABBIT_SPAWNEGG = ITEMS.register("hyper_rabbit_spawn_egg", () -> new DeferredSpawnEggItem(ModEntities.HYPER_RABBIT, 0xDA784A, 0xF4BF83, (new Item.Properties())));
	public static final Supplier<Item> MOOBLOOM_SPAWNEGG = ITEMS.register("moobloom_spawn_egg", () -> new DeferredSpawnEggItem(ModEntities.MOOBLOOM, 0xFDCA00, 0xF7EDC1, (new Item.Properties())));
	public static final Supplier<Item> MOOLIP_SPAWNEGG = ITEMS.register("moolip_spawn_egg", () -> new DeferredSpawnEggItem(ModEntities.MOOLIP, 0xD882B0, 0xF1DFE8, (new Item.Properties())));
	public static final Supplier<Item> JUMBO_RABBIT_SPAWNEGG = ITEMS.register("jumbo_rabbit_spawn_egg", () -> new DeferredSpawnEggItem(ModEntities.JUMBO_RABBIT, 0x9E5C48, 0xE5B2A3, (new Item.Properties())));
	public static final Supplier<Item> ZOMBIFILED_PIG_SPAWNEGG = ITEMS.register("zombified_pig_spawn_egg", () -> new DeferredSpawnEggItem(ModEntities.ZOMBIFIED_PIG, 15373203, 5009705, (new Item.Properties())));
	public static final Supplier<Item> DUCK_SPAWNEGG = ITEMS.register("duck_spawn_egg", () -> new DeferredSpawnEggItem(ModEntities.DUCK, 0x64413A, 0x17951E, (new Item.Properties())));
	public static final Supplier<Item> JOLLY_LAMMA_SPAWNEGG = ITEMS.register("jolly_llama_spawn_egg", () -> new DeferredSpawnEggItem(ModEntities.JOLLY_LLAMA, 0x673727, 0xD2BFB2, (new Item.Properties())));


	public static final Supplier<Item> BONE_SPIDER_SPAWNEGG = ITEMS.register("bone_spider_spawn_egg", () -> new DeferredSpawnEggItem(ModEntities.BONE_SPIDER, 0x461C2E, 0x6130B7, (new Item.Properties())));
	public static final Supplier<Item> STRAY_BONE_SPIDER_SPAWNEGG = ITEMS.register("stray_bone_spider_spawn_egg", () -> new DeferredSpawnEggItem(ModEntities.STRAY_BONE_SPIDER, 0x20112F, 0x30B6B2, (new Item.Properties())));
	public static final Supplier<Item> VILER_WITCH_SPAWNEGG = ITEMS.register("viler_witch_spawn_egg", () -> new DeferredSpawnEggItem(ModEntities.VILER_WITCH, 0x111322, 0x37464D, (new Item.Properties())));
	public static final Supplier<Item> BOULDERING_ZOMBIE_SPAWNEGG = ITEMS.register("bouldering_zombie_spawn_egg", () -> new DeferredSpawnEggItem(ModEntities.BOULDERING_ZOMBIE, 0x384242, 0x52261A, (new Item.Properties())));
	public static final Supplier<Item> LOBBER_ZOMBIE_SPAWNEGG = ITEMS.register("lobber_zombie_spawn_egg", () -> new DeferredSpawnEggItem(ModEntities.LOBBER_ZOMBIE, 0x899274, 0x436858, (new Item.Properties())));
	public static final Supplier<Item> BOULDERING_DROWNED_SPAWNEGG = ITEMS.register("bouldering_drowned_spawn_egg", () -> new DeferredSpawnEggItem(ModEntities.BOULDERING_DROWNED, 0x56847E, 0x52261A, (new Item.Properties())));
	public static final Supplier<Item> LOBBER_DROWNED_SPAWNEGG = ITEMS.register("lobber_drowned_spawn_egg", () -> new DeferredSpawnEggItem(ModEntities.LOBBER_DROWNED, 0x739274, 0x3E5F51, (new Item.Properties())));
	public static final Supplier<Item> TROPICAL_SLIME_SPAWNEGG = ITEMS.register("tropical_slime_spawn_egg", () -> new DeferredSpawnEggItem(ModEntities.TROPICAL_SLIME, 0x5B83AD, 0x90B1D3, (new Item.Properties())));
	public static final Supplier<Item> SKELETON_WOLF_SPAWNEGG = ITEMS.register("skeleton_wolf_spawn_egg", () -> new DeferredSpawnEggItem(ModEntities.SKELETON_WOLF, 12698049, 4802889, (new Item.Properties())));
	public static final Supplier<Item> WITHER_SKELETON_WOLF_SPAWNEGG = ITEMS.register("wither_skeleton_wolf_spawn_egg", () -> new DeferredSpawnEggItem(ModEntities.WITHER_SKELETON_WOLF, 1315860, 4672845, (new Item.Properties())));
	public static final Supplier<Item> BABY_GHAST_SPAWNEGG = ITEMS.register("baby_ghast_spawn_egg", () -> new DeferredSpawnEggItem(ModEntities.BABY_GHAST, 0xFFFFFF, 0xFF9393, (new Item.Properties())));
	public static final Supplier<Item> ZOMBIFIED_RABBIT_SPAWNEGG = ITEMS.register("zombified_rabbit_spawn_egg", () -> new DeferredSpawnEggItem(ModEntities.ZOMBIFIED_RABBIT, 0x79AD69, 0x2A5131, (new Item.Properties())));

	public static final Supplier<Item> MAGMA_COW_SPAWNEGG = ITEMS.register("magma_cow_spawn_egg", () -> new DeferredSpawnEggItem(ModEntities.MAGMA_COW, 0x2C2C33, 0xFBAA59, (new Item.Properties())));
	public static final Supplier<Item> MELON_GOLEM_SPAWNEGG = ITEMS.register("melon_golem_spawn_egg", () -> new DeferredSpawnEggItem(ModEntities.MELON_GOLEM, 14283506, 0x34791E, (new Item.Properties())));
	public static final Supplier<Item> FURNACE_GOLEM_SPAWNEGG = ITEMS.register("furnace_golem_spawn_egg", () -> new DeferredSpawnEggItem(ModEntities.FURNACE_GOLEM, 14405058, 0x8F5846, (new Item.Properties())));

	private static void generateInstrumentTypes(
			CreativeModeTab.Output p_270699_,
			HolderLookup<Instrument> p_270948_,
			Item p_270421_,
			TagKey<Instrument> p_270798_,
			CreativeModeTab.TabVisibility p_270817_
	) {
		p_270948_.get(p_270798_)
				.ifPresent(
						p_270021_ -> p_270021_.stream()
								.map(p_269995_ -> InstrumentItem.create(p_270421_, p_269995_))
								.forEach(p_270011_ -> p_270699_.accept(p_270011_, p_270817_))
				);
	}

	@SubscribeEvent
	public static void registerCreativeTabsItem(BuildCreativeModeTabContentsEvent event) {
		if (event.getTabKey() == CreativeModeTabs.SPAWN_EGGS) {
			event.accept(CLUCK_SHROOM_SPAWNEGG.get());
			event.accept(FANCY_CHICKEN_SPAWNEGG.get());
			event.accept(WOOLY_COW_SPAWNEGG.get());
			event.accept(UMBRA_COW_SPAWNEGG.get());
			event.accept(ALBINO_COW_SPAWNEGG.get());
			event.accept(CREAM_COW_SPAWNEGG.get());
			event.accept(TEACUP_PIG_SPAWNEGG.get());
			event.accept(HORNED_SHEEP_SPAWNEGG.get());
			event.accept(HYPER_RABBIT_SPAWNEGG.get());
			event.accept(MOOBLOOM_SPAWNEGG.get());
			event.accept(MOOLIP_SPAWNEGG.get());
			event.accept(JUMBO_RABBIT_SPAWNEGG.get());
			event.accept(ZOMBIFILED_PIG_SPAWNEGG.get());
			event.accept(DUCK_SPAWNEGG.get());
			event.accept(JOLLY_LAMMA_SPAWNEGG.get());

			event.accept(BONE_SPIDER_SPAWNEGG.get());
			event.accept(STRAY_BONE_SPIDER_SPAWNEGG.get());
			event.accept(BABY_GHAST_SPAWNEGG.get());
			event.accept(ZOMBIFIED_RABBIT_SPAWNEGG.get());
			event.accept(BOULDERING_ZOMBIE_SPAWNEGG.get());
			event.accept(BOULDERING_DROWNED_SPAWNEGG.get());
			event.accept(LOBBER_ZOMBIE_SPAWNEGG.get());
			event.accept(LOBBER_DROWNED_SPAWNEGG.get());
			event.accept(TROPICAL_SLIME_SPAWNEGG.get());
			event.accept(SKELETON_WOLF_SPAWNEGG.get());
			event.accept(VILER_WITCH_SPAWNEGG.get());
			event.accept(WITHER_SKELETON_WOLF_SPAWNEGG.get());
			event.accept(MAGMA_COW_SPAWNEGG.get());
			event.accept(MELON_GOLEM_SPAWNEGG.get());
			event.accept(FURNACE_GOLEM_SPAWNEGG.get());
		}
		if (event.getTabKey() == CreativeModeTabs.INGREDIENTS) {
			event.accept(RUBY.get());
			event.accept(TROPICAL_BALL.get());
			event.accept(SMELLY_EGG.get());
			event.accept(FANCY_EGG.get());
			event.accept(DUCK_EGG.get());
			event.accept(BONE_SHARD.get());
			event.accept(FANCY_FEATHER.get());
			event.accept(HARDER_FLESH.get());
			event.accept(BONE_SPIDER_EYE.get());
			event.accept(HORN.get());
			event.getParameters().holders()
					.lookup(Registries.INSTRUMENT)
					.ifPresent(
							p_270036_ -> generateInstrumentTypes(
									event, p_270036_, ModItems.HORN_FLUTE.get(), CustomTagGenerator.InstrumentTagGenerator.HORNED_SHEEP, CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS
							)
					);
			event.accept(HYPER_RABBIT_FOOT.get());
			event.accept(ZOMBIFIED_RABBIT_FOOT.get());
		}
        if (event.getTabKey() == CreativeModeTabs.REDSTONE_BLOCKS) {
			event.accept(ModBlocks.TROPICAL_SLIME_BLOCK.get());
        }

		if (event.getTabKey() == CreativeModeTabs.TOOLS_AND_UTILITIES) {
			event.accept(MUD_BUCKET.get());
			event.accept(TROPICAL_SLIME_BUCKET.get());
			event.accept(TEACUP_PIG_POT.get());
		}
		if (event.getTabKey() == CreativeModeTabs.NATURAL_BLOCKS) {
			event.accept(ModBlocks.CARVED_MELON.get());
			event.accept(ModBlocks.CARVED_MELON_SHOOT.get());
			event.accept(ModBlocks.BUTTERCUP.get());
			event.accept(ModBlocks.PINK_DAISY.get());
		}

		if (event.getTabKey() == CreativeModeTabs.BUILDING_BLOCKS) {
			event.accept(ModBlocks.RUBY.get());
		}
	}

	public static void composterInit() {
		ComposterBlock.COMPOSTABLES.put(ModBlocks.BUTTERCUP.get(), 0.1F);
		ComposterBlock.COMPOSTABLES.put(ModBlocks.PINK_DAISY.get(), 0.1F);
		ComposterBlock.COMPOSTABLES.put(ModBlocks.CARVED_MELON.get(), 0.65F);
		ComposterBlock.COMPOSTABLES.put(ModBlocks.CARVED_MELON_SHOOT.get(), 0.65F);
	}
}