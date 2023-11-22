package baguchan.earthmobsmod.registry;

import baguchan.earthmobsmod.EarthMobsMod;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModSounds {
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS = DeferredRegister.create(BuiltInRegistries.SOUND_EVENT, EarthMobsMod.MODID);

    public static final Supplier<SoundEvent> HORN_WOODEN = createEvent("item.horn_flute.wooden");
    public static final Supplier<SoundEvent> HORN_BATTLE = createEvent("item.horn_flute.battle_horn");

    private static Supplier<SoundEvent> createEvent(String sound) {
        ResourceLocation name = new ResourceLocation(EarthMobsMod.MODID, sound);
        return SOUND_EVENTS.register(sound, () -> SoundEvent.createVariableRangeEvent(name));
    }
}
