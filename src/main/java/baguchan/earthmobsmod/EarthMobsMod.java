package baguchan.earthmobsmod;

import baguchan.earthmobsmod.capability.ShadowCapability;
import baguchan.earthmobsmod.client.ClientRegistrar;
import baguchan.earthmobsmod.message.ModPackets;
import baguchan.earthmobsmod.registry.*;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FlowerPotBlock;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModLoadingContext;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.fml.javafmlmod.FMLJavaModLoadingContext;
import net.neoforged.fml.loading.FMLEnvironment;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.common.capabilities.Capability;
import net.neoforged.neoforge.common.capabilities.CapabilityManager;
import net.neoforged.neoforge.common.capabilities.CapabilityToken;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Locale;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(EarthMobsMod.MODID)
public class EarthMobsMod {
	public static final String MODID = "earthmobsmod";
	// Directly reference a log4j logger.
	private static final Logger LOGGER = LogManager.getLogger(MODID);

	public static Capability<ShadowCapability> SHADOW_CAP = CapabilityManager.get(new CapabilityToken<>() {
	});


	public EarthMobsMod() {
		// Register the setup method for modloading
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
		IEventBus modBus = FMLJavaModLoadingContext.get().getModEventBus();
		IEventBus forgeBus = NeoForge.EVENT_BUS;
		ModBlocks.BLOCKS.register(modBus);
		ModEntities.ENTITIES.register(modBus);
		ModFluidTypes.FLUID_TYPES.register(modBus);
		ModFluids.FLUIDS.register(modBus);
		ModBiomeModifiers.BIOME_MODIFIER_SERIALIZERS.register(modBus);
		ModEffects.MOB_EFFECTS.register(modBus);
		ModEffects.POTION.register(modBus);
		ModItems.ITEMS.register(modBus);
		ModSounds.SOUND_EVENTS.register(modBus);
		ModInstruments.INSTRUMENTS.register(modBus);
		ModRecipes.RECIPE_SERIALIZERS.register(modBus);

		ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, EarthMobsConfig.COMMON_SPEC);

		if (FMLEnvironment.dist == Dist.CLIENT) {
			FMLJavaModLoadingContext.get().getModEventBus().addListener(ClientRegistrar::setup);
		}
	}

	private void setup(final FMLCommonSetupEvent event) {
		ModPackets.setupMessages();
		ModEffects.init();
		ModInteractionInformations.init();
		ModItems.composterInit();
		ModBlocks.initFire();
		((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(BuiltInRegistries.BLOCK.getKey(ModBlocks.BUTTERCUP.get()), ModBlocks.POTTED_BUTTERCUP);
		((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(BuiltInRegistries.BLOCK.getKey(ModBlocks.PINK_DAISY.get()), ModBlocks.POTTED_PINK_DAISY);
	}


	public static ResourceLocation prefix(String name) {
		return new ResourceLocation(EarthMobsMod.MODID, name.toLowerCase(Locale.ROOT));
	}
}
