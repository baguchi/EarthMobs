package baguchan.earthmobsmod.registry;

import baguchan.earthmobsmod.EarthMobsMod;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.Instrument;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModInstruments {
    public static final DeferredRegister<Instrument> INSTRUMENTS = DeferredRegister.create(BuiltInRegistries.INSTRUMENT, EarthMobsMod.MODID);
    public static final Supplier<Instrument> WOODEN_HORN = INSTRUMENTS.register("wooden_horn", () -> new Instrument(Holder.direct(ModSounds.HORN_WOODEN.get()), 140, 256F));
    public static final Supplier<Instrument> BATTLE_HORN = INSTRUMENTS.register("battle_horn", () -> new Instrument(Holder.direct(ModSounds.HORN_BATTLE.get()), 140, 256F));
}
