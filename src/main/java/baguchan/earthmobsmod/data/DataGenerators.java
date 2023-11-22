package baguchan.earthmobsmod.data;

import baguchan.earthmobsmod.EarthMobsMod;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;

import java.util.concurrent.CompletableFuture;

@Mod.EventBusSubscriber(modid = EarthMobsMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataGenerators {
    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        PackOutput packOutput = generator.getPackOutput();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();
        event.getGenerator().addProvider(event.includeClient(), new BlockstateGenerator(packOutput, event.getExistingFileHelper()));
        event.getGenerator().addProvider(event.includeClient(), new ItemModelGenerator(packOutput, event.getExistingFileHelper()));
        BlockTagsProvider blocktags = new BlockTagGenerator(event.getGenerator().getPackOutput(), event.getLookupProvider(), event.getExistingFileHelper());
        event.getGenerator().addProvider(event.includeServer(), blocktags);
        event.getGenerator().addProvider(event.includeServer(), new ItemTagGenerator(event.getGenerator().getPackOutput(), event.getLookupProvider(), blocktags.contentsGetter(), event.getExistingFileHelper()));
        event.getGenerator().addProvider(event.includeServer(), new EntityTagGenerator(event.getGenerator().getPackOutput(), event.getLookupProvider(), event.getExistingFileHelper()));
        event.getGenerator().addProvider(event.includeServer(), ModLootTableProvider.create(packOutput));
        event.getGenerator().addProvider(event.includeServer(), new WorldGenerator(event.getGenerator().getPackOutput(), event.getLookupProvider()));
        event.getGenerator().addProvider(event.includeServer(), new CustomTagGenerator.InstrumentTagGenerator(event.getGenerator().getPackOutput(), event.getLookupProvider(), existingFileHelper));
    }
}