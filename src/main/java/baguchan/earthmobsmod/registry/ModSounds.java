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

    public static final DeferredHolder<SoundEvent, SoundEvent> SKELETON_WOLF_AMBIENT = createEvent("entity.skeleton_wolf.ambient");
    public static final DeferredHolder<SoundEvent, SoundEvent> SKELETON_WOLF_GROWL = createEvent("entity.skeleton_wolf.growl");
    public static final DeferredHolder<SoundEvent, SoundEvent> SKELETON_WOLF_PANT = createEvent("entity.skeleton_wolf.pant");
    public static final DeferredHolder<SoundEvent, SoundEvent> SKELETON_WOLF_WHINE = createEvent("entity.skeleton_wolf.whine");
    public static final DeferredHolder<SoundEvent, SoundEvent> SKELETON_WOLF_HOWL = createEvent("entity.skeleton_wolf.howl");
    public static final DeferredHolder<SoundEvent, SoundEvent> SKELETON_WOLF_SHAKE = createEvent("entity.skeleton_wolf.shake");
    public static final DeferredHolder<SoundEvent, SoundEvent> SKELETON_WOLF_STEP = createEvent("entity.skeleton_wolf.step");
    public static final DeferredHolder<SoundEvent, SoundEvent> SKELETON_WOLF_DEATH = createEvent("entity.skeleton_wolf.death");
    public static final DeferredHolder<SoundEvent, SoundEvent> SKELETON_WOLF_HURT = createEvent("entity.skeleton_wolf.hurt");
    private static DeferredHolder<SoundEvent, SoundEvent> createEvent(String sound) {
        ResourceLocation name = ResourceLocation.fromNamespaceAndPath(EarthMobsMod.MODID, sound);
        return SOUND_EVENTS.register(sound, () -> SoundEvent.createVariableRangeEvent(name));
    }
}
