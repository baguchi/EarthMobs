package baguchan.earthmobsmod.data;

import baguchan.earthmobsmod.EarthMobsMod;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.DatapackBuiltinEntriesProvider;
import net.neoforged.neoforge.data.event.GatherDataEvent;

import java.util.concurrent.CompletableFuture;

@EventBusSubscriber(modid = EarthMobsMod.MODID, bus = EventBusSubscriber.Bus.MOD)
public class DataGenerators {
    @SubscribeEvent
    public static void gatherData(GatherDataEvent.Client event) {
        DataGenerator generator = event.getGenerator();
        PackOutput packOutput = generator.getPackOutput();
        DatapackBuiltinEntriesProvider datapackProvider = new RegistryDataGenerator(packOutput, event.getLookupProvider());

        CompletableFuture<HolderLookup.Provider> lookupProvider = datapackProvider.getRegistryProvider();
        event.getGenerator().addProvider(true, new EarthModelData(packOutput));
        BlockTagsProvider blocktags = new BlockTagGenerator(event.getGenerator().getPackOutput(), lookupProvider);
        event.getGenerator().addProvider(true, blocktags);
        event.getGenerator().addProvider(true, new ItemTagGenerator(event.getGenerator().getPackOutput(), lookupProvider, blocktags.contentsGetter()));
        event.getGenerator().addProvider(true, new EntityTagGenerator(event.getGenerator().getPackOutput(), lookupProvider));
        event.getGenerator().addProvider(true, ModLootTableProvider.create(packOutput, lookupProvider));
        event.getGenerator().addProvider(true, new WorldGenerator(event.getGenerator().getPackOutput(), lookupProvider));
        event.getGenerator().addProvider(true, new CustomTagGenerator.InstrumentTagGenerator(event.getGenerator().getPackOutput(), lookupProvider));
    }
}