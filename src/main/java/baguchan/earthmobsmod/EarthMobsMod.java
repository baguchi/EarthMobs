package baguchan.earthmobsmod;

import baguchan.earthmobsmod.client.ClientRegistrar;
import baguchan.earthmobsmod.message.MossMessage;
import baguchan.earthmobsmod.message.MudMessage;
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
import net.neoforged.fml.loading.FMLEnvironment;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlerEvent;
import net.neoforged.neoforge.network.registration.IPayloadRegistrar;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Locale;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(EarthMobsMod.MODID)
public class EarthMobsMod {
	public static final String MODID = "earthmobsmod";
	// Directly reference a log4j logger.
	private static final Logger LOGGER = LogManager.getLogger(MODID);

	public EarthMobsMod(IEventBus modBus) {
		// Register the setup method for modloading
		modBus.addListener(this::setup);
		modBus.addListener(this::setupPackets);
		IEventBus forgeBus = NeoForge.EVENT_BUS;
		ModBlocks.BLOCKS.register(modBus);
        ModBlockEntitys.BLOCK_ENTITIES.register(modBus);
		ModEntities.ENTITIES.register(modBus);
		ModFluidTypes.FLUID_TYPES.register(modBus);
		ModFluids.FLUIDS.register(modBus);
		ModBiomeModifiers.BIOME_MODIFIER_SERIALIZERS.register(modBus);
		ModEffects.MOB_EFFECTS.register(modBus);
		ModEffects.POTION.register(modBus);
		ModItems.ITEMS.register(modBus);
		ModSounds.SOUND_EVENTS.register(modBus);
		ModCapability.ATTACHMENT_TYPES.register(modBus);
		ModInstruments.INSTRUMENTS.register(modBus);
		ModRecipes.RECIPE_SERIALIZERS.register(modBus);

		ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, EarthMobsConfig.COMMON_SPEC);

		if (FMLEnvironment.dist == Dist.CLIENT) {
			modBus.addListener(ClientRegistrar::setup);
		}
	}

	public void setupPackets(RegisterPayloadHandlerEvent event) {
		IPayloadRegistrar registrar = event.registrar(MODID).versioned("1.0.0").optional();
		registrar.play(MossMessage.ID, MossMessage::new, payload -> payload.client(MossMessage::handle));
		registrar.play(MudMessage.ID, MudMessage::new, payload -> payload.client(MudMessage::handle));
	}

	private void setup(final FMLCommonSetupEvent event) {
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
