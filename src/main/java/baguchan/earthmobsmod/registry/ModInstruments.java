package baguchan.earthmobsmod.registry;

import baguchan.earthmobsmod.EarthMobsMod;
import net.minecraft.Util;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.item.Instrument;

public class ModInstruments {
    public static final ResourceKey<Instrument> WOODEN_HORN = create("wooden_horn");
    public static final ResourceKey<Instrument> BATTLE_HORN = create("battle_horn");


    public static void bootstrap(BootstrapContext<Instrument> p_362126_) {

        register(p_362126_, WOODEN_HORN, p_362126_.lookup(Registries.SOUND_EVENT).get(ModSounds.HORN_WOODEN.getKey()).orElseThrow(), 7.0F, 256.0F);
        register(p_362126_, BATTLE_HORN, p_362126_.lookup(Registries.SOUND_EVENT).get(ModSounds.HORN_BATTLE.getKey()).orElseThrow(), 7.0F, 256.0F);
    }

    private static ResourceKey<Instrument> create(String p_220151_) {
        return ResourceKey.create(Registries.INSTRUMENT, ResourceLocation.fromNamespaceAndPath(EarthMobsMod.MODID, p_220151_));
    }

    static void register(
            BootstrapContext<Instrument> p_365273_, ResourceKey<Instrument> p_360712_, Holder<SoundEvent> p_362988_, float p_362590_, float p_363159_
    ) {
        MutableComponent mutablecomponent = Component.translatable(Util.makeDescriptionId("instrument", p_360712_.location()));
        p_365273_.register(p_360712_, new Instrument(p_362988_, p_362590_, p_363159_, mutablecomponent));
    }

}
