package baguchan.earthmobsmod.data;

import baguchan.earthmobsmod.EarthMobsMod;
import baguchan.earthmobsmod.registry.ModInstruments;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Instrument;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class CustomTagGenerator {
    public static class InstrumentTagGenerator extends TagsProvider<Instrument> {

        public static final TagKey<Instrument> HORNED_SHEEP = create("horned_sheep");

        public InstrumentTagGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> provider, @Nullable ExistingFileHelper existingFileHelper) {
            super(output, Registries.INSTRUMENT, provider, EarthMobsMod.MODID, existingFileHelper);
        }

        private static TagKey<Instrument> create(String name) {
            return TagKey.create(Registries.INSTRUMENT, EarthMobsMod.prefix(name));
        }

        @Override
        protected void addTags(HolderLookup.Provider p_256380_) {
            tag(HORNED_SHEEP).add(BuiltInRegistries.INSTRUMENT.getResourceKey(ModInstruments.WOODEN_HORN.get()).get());
            tag(HORNED_SHEEP).add(BuiltInRegistries.INSTRUMENT.getResourceKey(ModInstruments.BATTLE_HORN.get()).get());
        }
    }
}
