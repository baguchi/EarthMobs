package baguchan.earthmobsmod.data;

import baguchan.earthmobsmod.EarthMobsMod;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.InstrumentTagsProvider;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Instrument;

import java.util.concurrent.CompletableFuture;

public class CustomTagGenerator {
    public static class InstrumentTagGenerator extends InstrumentTagsProvider {

        public static final TagKey<Instrument> HORNED_SHEEP = create("horned_sheep");

        public InstrumentTagGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> provider) {
            super(output, provider, EarthMobsMod.MODID);
        }

        private static TagKey<Instrument> create(String name) {
            return TagKey.create(Registries.INSTRUMENT, EarthMobsMod.prefix(name));
        }

        @Override
        protected void addTags(HolderLookup.Provider p_256380_) {
            //tag(HORNED_SHEEP).add(ModInstruments.WOODEN_HORN);
            //tag(HORNED_SHEEP).add(ModInstruments.BATTLE_HORN);
        }
    }
}
