package baguchan.earthmobsmod.registry;

import baguchan.earthmobsmod.EarthMobsMod;
import baguchan.earthmobsmod.data.CustomTagGenerator;
import baguchan.earthmobsmod.item.BoneShardItem;
import baguchan.earthmobsmod.item.MobPotItem;
import baguchan.earthmobsmod.item.ModEggItem;
import baguchan.earthmobsmod.item.TropicalBallItem;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.TagKey;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.ComposterBlock;
import net.minecraft.world.level.material.Fluids;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD, modid = EarthMobsMod.MODID)
public class ModItems {
	public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(EarthMobsMod.MODID);

	public static final DeferredItem<Item> RUBY = ITEMS.registerItem("ruby", (prop) -> new Item((prop)));
	public static final DeferredItem<Item> TROPICAL_BALL = ITEMS.registerItem("tropical_ball", (prop) -> new TropicalBallItem((prop.food(new FoodProperties(0, 0, true), ModConsumables.JELLY))));

	public static final DeferredItem<Item> SMELLY_EGG = ITEMS.registerItem("smelly_egg", (prop) -> new ModEggItem(ModEntities.SMELLY_EGG, (prop.stacksTo(16))));
	public static final DeferredItem<Item> FANCY_EGG = ITEMS.registerItem("fancy_egg", (prop) -> new ModEggItem(ModEntities.FANCY_EGG, (prop.stacksTo(16))));

	public static final DeferredItem<Item> BONE_SHARD = ITEMS.registerItem("bone_shard", (prop) -> new BoneShardItem((prop)));
	public static final DeferredItem<Item> FANCY_FEATHER = ITEMS.registerItem("fancy_feather", (prop) -> new Item((prop)));
	public static final DeferredItem<Item> HARDER_FLESH = ITEMS.registerItem("harder_flesh", (prop) -> new Item((prop.food(ModFoods.HARDER_FLESH, ModConsumables.HARDEN_FOOD))));

	public static final DeferredItem<Item> BONE_SPIDER_EYE = ITEMS.registerItem("bone_spider_eye", (prop) -> new Item((prop)));
	public static final DeferredItem<Item> HORN = ITEMS.registerItem("horn", (prop) -> new Item((prop.stacksTo(1))));
	public static final DeferredItem<Item> HORN_FLUTE = ITEMS.registerItem("horn_flute", (prop) -> new InstrumentItem(CustomTagGenerator.InstrumentTagGenerator.HORNED_SHEEP, (prop.stacksTo(1))));

	public static final DeferredItem<Item> HYPER_RABBIT_FOOT = ITEMS.registerItem("hyper_rabbit_foot", (prop) -> new Item((prop)));
	public static final DeferredItem<Item> ZOMBIFIED_RABBIT_FOOT = ITEMS.registerItem("zombified_rabbit_foot", (prop) -> new Item((prop)));
	public static final DeferredItem<Item> MUD_BUCKET = ITEMS.registerItem("mud_bucket", (prop) -> new BucketItem(ModFluids.MUD.value(), (prop).stacksTo(1).craftRemainder(Items.BUCKET)));
	public static final DeferredItem<Item> TROPICAL_SLIME_BUCKET = ITEMS.registerItem("tropical_slime_bucket", (prop) -> new MobBucketItem(ModEntities.TROPICAL_SLIME.get(), Fluids.WATER, SoundEvents.BUCKET_EMPTY_FISH, (prop).stacksTo(1).craftRemainder(Items.BUCKET)));
	public static final DeferredItem<Item> TEACUP_PIG_POT = ITEMS.registerItem("teacup_pig_pot", (prop) -> new MobPotItem(ModEntities.TEACUP_PIG.get(), Fluids.EMPTY, SoundEvents.ARMOR_EQUIP_GENERIC.value(), (prop).stacksTo(1).craftRemainder(Items.FLOWER_POT)));

	public static final DeferredItem<Item> CLUCK_SHROOM_SPAWNEGG = ITEMS.registerItem("cluck_shroom_spawn_egg", (prop) -> new SpawnEggItem(ModEntities.CLUCK_SHROOM.get(), (prop)));
	public static final DeferredItem<Item> FANCY_CHICKEN_SPAWNEGG = ITEMS.registerItem("fancy_chicken_spawn_egg", (prop) -> new SpawnEggItem(ModEntities.FANCY_CHICKEN.get(), (prop)));
	public static final DeferredItem<Item> WOOLY_COW_SPAWNEGG = ITEMS.registerItem("wooly_cow_spawn_egg", (prop) -> new SpawnEggItem(ModEntities.WOOLY_COW.get(), (prop)));
	public static final DeferredItem<Item> UMBRA_COW_SPAWNEGG = ITEMS.registerItem("umbra_cow_spawn_egg", (prop) -> new SpawnEggItem(ModEntities.UMBRA_COW.get(), (prop)));

	public static final DeferredItem<Item> TEACUP_PIG_SPAWNEGG = ITEMS.registerItem("teacup_pig_spawn_egg", (prop) -> new SpawnEggItem(ModEntities.TEACUP_PIG.get(), (prop)));


	public static final DeferredItem<Item> HORNED_SHEEP_SPAWNEGG = ITEMS.registerItem("horned_sheep_spawn_egg", (prop) -> new SpawnEggItem(ModEntities.HORNED_SHEEP.get(), (prop)));
	public static final DeferredItem<Item> HYPER_RABBIT_SPAWNEGG = ITEMS.registerItem("hyper_rabbit_spawn_egg", (prop) -> new SpawnEggItem(ModEntities.HYPER_RABBIT.get(), (prop)));
	public static final DeferredItem<Item> MOOBLOOM_SPAWNEGG = ITEMS.registerItem("moobloom_spawn_egg", (prop) -> new SpawnEggItem(ModEntities.MOOBLOOM.get(), (prop)));
	public static final DeferredItem<Item> MOOLIP_SPAWNEGG = ITEMS.registerItem("moolip_spawn_egg", (prop) -> new SpawnEggItem(ModEntities.MOOLIP.get(), (prop)));
	public static final DeferredItem<Item> JUMBO_RABBIT_SPAWNEGG = ITEMS.registerItem("jumbo_rabbit_spawn_egg", (prop) -> new SpawnEggItem(ModEntities.JUMBO_RABBIT.get(), (prop)));
	public static final DeferredItem<Item> ZOMBIFILED_PIG_SPAWNEGG = ITEMS.registerItem("zombified_pig_spawn_egg", (prop) -> new SpawnEggItem(ModEntities.ZOMBIFIED_PIG.get(), (prop)));
	public static final DeferredItem<Item> JOLLY_LAMMA_SPAWNEGG = ITEMS.registerItem("jolly_llama_spawn_egg", (prop) -> new SpawnEggItem(ModEntities.JOLLY_LLAMA.get(), (prop)));


	public static final DeferredItem<Item> BONE_SPIDER_SPAWNEGG = ITEMS.registerItem("bone_spider_spawn_egg", (prop) -> new SpawnEggItem(ModEntities.BONE_SPIDER.get(), (prop)));
	public static final DeferredItem<Item> STRAY_BONE_SPIDER_SPAWNEGG = ITEMS.registerItem("stray_bone_spider_spawn_egg", (prop) -> new SpawnEggItem(ModEntities.STRAY_BONE_SPIDER.get(), (prop)));
	public static final DeferredItem<Item> VILER_WITCH_SPAWNEGG = ITEMS.registerItem("viler_witch_spawn_egg", (prop) -> new SpawnEggItem(ModEntities.VILER_WITCH.get(), (prop)));

	public static final DeferredItem<Item> BOULDERING_ZOMBIE_SPAWNEGG = ITEMS.registerItem("bouldering_zombie_spawn_egg", (prop) -> new SpawnEggItem(ModEntities.BOULDERING_ZOMBIE.get(), (prop)));
	public static final DeferredItem<Item> LOBBER_ZOMBIE_SPAWNEGG = ITEMS.registerItem("lobber_zombie_spawn_egg", (prop) -> new SpawnEggItem(ModEntities.LOBBER_ZOMBIE.get(), (prop)));

	public static final DeferredItem<Item> BOULDERING_DROWNED_SPAWNEGG = ITEMS.registerItem("bouldering_drowned_spawn_egg", (prop) -> new SpawnEggItem(ModEntities.BOULDERING_DROWNED.get(), (prop)));
	public static final DeferredItem<Item> LOBBER_DROWNED_SPAWNEGG = ITEMS.registerItem("lobber_drowned_spawn_egg", (prop) -> new SpawnEggItem(ModEntities.LOBBER_DROWNED.get(), (prop)));


	public static final DeferredItem<Item> BOULDERING_FROZEN_ZOMBIE_SPAWNEGG = ITEMS.registerItem("bouldering_frozen_zombie_spawn_egg", (prop) -> new SpawnEggItem(ModEntities.BOULDERING_FROZEN_ZOMBIE.get(), (prop)));
	public static final DeferredItem<Item> LOBBER_HUSK_SPAWNEGG = ITEMS.registerItem("lobber_husk_spawn_egg", (prop) -> new SpawnEggItem(ModEntities.LOBBER_HUSK.get(), (prop)));


	public static final DeferredItem<Item> TROPICAL_SLIME_SPAWNEGG = ITEMS.registerItem("tropical_slime_spawn_egg", (prop) -> new SpawnEggItem(ModEntities.TROPICAL_SLIME.get(), (prop)));
	public static final DeferredItem<Item> SKELETON_WOLF_SPAWNEGG = ITEMS.registerItem("skeleton_wolf_spawn_egg", (prop) -> new SpawnEggItem(ModEntities.SKELETON_WOLF.get(), (prop)));
	public static final DeferredItem<Item> WITHER_SKELETON_WOLF_SPAWNEGG = ITEMS.registerItem("wither_skeleton_wolf_spawn_egg", (prop) -> new SpawnEggItem(ModEntities.WITHER_SKELETON_WOLF.get(), (prop)));
	public static final DeferredItem<Item> ZOMBIFIED_RABBIT_SPAWNEGG = ITEMS.registerItem("zombified_rabbit_spawn_egg", (prop) -> new SpawnEggItem(ModEntities.ZOMBIFIED_RABBIT.get(), (prop)));

	public static final DeferredItem<Item> MAGMA_COW_SPAWNEGG = ITEMS.registerItem("magma_cow_spawn_egg", (prop) -> new SpawnEggItem(ModEntities.MAGMA_COW.get(), (prop)));
	public static final DeferredItem<Item> MELON_GOLEM_SPAWNEGG = ITEMS.registerItem("melon_golem_spawn_egg", (prop) -> new SpawnEggItem(ModEntities.MELON_GOLEM.get(), (prop)));
	public static final DeferredItem<Item> FURNACE_GOLEM_SPAWNEGG = ITEMS.registerItem("furnace_golem_spawn_egg", (prop) -> new SpawnEggItem(ModEntities.FURNACE_GOLEM.get(), (prop)));

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


			event.accept(TEACUP_PIG_SPAWNEGG.get());
			event.accept(HORNED_SHEEP_SPAWNEGG.get());
			event.accept(HYPER_RABBIT_SPAWNEGG.get());
			event.accept(MOOBLOOM_SPAWNEGG.get());
			event.accept(MOOLIP_SPAWNEGG.get());
			event.accept(JUMBO_RABBIT_SPAWNEGG.get());
			event.accept(ZOMBIFILED_PIG_SPAWNEGG.get());
			event.accept(JOLLY_LAMMA_SPAWNEGG.get());

			event.accept(BONE_SPIDER_SPAWNEGG.get());
			event.accept(STRAY_BONE_SPIDER_SPAWNEGG.get());
			event.accept(ZOMBIFIED_RABBIT_SPAWNEGG.get());
			event.accept(BOULDERING_ZOMBIE_SPAWNEGG.get());
			event.accept(BOULDERING_DROWNED_SPAWNEGG.get());
            event.accept(BOULDERING_FROZEN_ZOMBIE_SPAWNEGG.get());

			event.accept(LOBBER_ZOMBIE_SPAWNEGG.get());
			event.accept(LOBBER_DROWNED_SPAWNEGG.get());
            event.accept(LOBBER_HUSK_SPAWNEGG.get());

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