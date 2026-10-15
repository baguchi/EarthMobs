package baguchan.earthmobsmod.data;

import baguchan.earthmobsmod.EarthMobsMod;
import baguchan.earthmobsmod.data.loot.ModLootTableProvider;
import baguchan.earthmobsmod.data.recipe.ModRecipeProvider;
import baguchan.earthmobsmod.registry.ModInstruments;
import baguchan.earthmobsmod.world.features.ModEarthFeatures;
import baguchan.earthmobsmod.world.features.ModEarthPlacements;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import net.neoforged.neoforge.data.event.GatherDataRegistryEntriesEvent;

import java.util.concurrent.CompletableFuture;

@EventBusSubscriber(modid = EarthMobsMod.MODID)
public class DataGenerators {
    @SubscribeEvent
    public static void gatherData(GatherDataEvent.Client event) {
        DataGenerator generator = event.getGenerator();
        PackOutput packOutput = generator.getPackOutput();


        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getWorldLookupProvider();
        event.getGenerator().addProvider(true, new EarthModelData(packOutput));
        BlockTagsProvider blocktags = new BlockTagGenerator(event.getGenerator().getPackOutput(), lookupProvider);
        event.getGenerator().addProvider(true, blocktags);
        event.getGenerator().addProvider(true, new ItemTagGenerator(event.getGenerator().getPackOutput(), lookupProvider));
        event.getGenerator().addProvider(true, new EntityTagGenerator(event.getGenerator().getPackOutput(), lookupProvider));
        event.getGenerator().addProvider(true, new CustomTagGenerator.InstrumentTagGenerator(event.getGenerator().getPackOutput(), lookupProvider));
    }

    @SubscribeEvent
    public static void gatherRegistry(GatherDataRegistryEntriesEvent event) {
        event.add(Registries.INSTRUMENT, ModInstruments::bootstrap)
                .add(Registries.FEATURE, ModEarthFeatures::bootstrap)
                .add(Registries.PLACED_FEATURE, ModEarthPlacements::bootstrap)
                .add(Registries.LOOT_TABLE, ModLootTableProvider.create())
                .add(RecipeProvider.asBootstrap(ModRecipeProvider::new));
    }
}