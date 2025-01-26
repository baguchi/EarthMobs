package baguchan.earthmobsmod.registry;

import baguchan.earthmobsmod.EarthMobsMod;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModSounds {
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS = DeferredRegister.create(BuiltInRegistries.SOUND_EVENT, EarthMobsMod.MODID);

    public static final DeferredHolder<SoundEvent, SoundEvent> HORN_WOODEN = createEvent("item.horn_flute.wooden");
    public static final DeferredHolder<SoundEvent, SoundEvent> HORN_BATTLE = createEvent("item.horn_flute.battle_horn");

    private static DeferredHolder<SoundEvent, SoundEvent> createEvent(String sound) {
        ResourceLocation name = ResourceLocation.fromNamespaceAndPath(EarthMobsMod.MODID, sound);
        return SOUND_EVENTS.register(sound, () -> SoundEvent.createVariableRangeEvent(name));
    }
}
