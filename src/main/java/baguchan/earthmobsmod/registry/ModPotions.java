package baguchan.earthmobsmod.registry;

import baguchan.earthmobsmod.EarthMobsMod;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.alchemy.Potion;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModPotions {
    public static final DeferredRegister<Potion> POTION = DeferredRegister.create(BuiltInRegistries.POTION, EarthMobsMod.MODID);


    public static final DeferredHolder<Potion, Potion> HYPER_SPARK = POTION.register("hyper_spark", () -> new Potion("hyper_spark", new MobEffectInstance(ModEffects.HYPER_SPARK, 1200)));
    public static final DeferredHolder<Potion, Potion> LONG_HYPER_SPARK = POTION.register("long_hyper_spark", () -> new Potion("long_hyper_spark", new MobEffectInstance(ModEffects.HYPER_SPARK, 2400)));

    public static final DeferredHolder<Potion, Potion> UNDEAD_BODY = POTION.register("undead_body", () -> new Potion("undead_body", new MobEffectInstance(ModEffects.UNDEAD_BODY, 3600)));
    public static final DeferredHolder<Potion, Potion> LONG_UNDEAD_BODY = POTION.register("long_undead_body", () -> new Potion("long_undead_body", new MobEffectInstance(ModEffects.UNDEAD_BODY, 9600)));
    public static final DeferredHolder<Potion, Potion> ZOMBIFIED = POTION.register("zombified", () -> new Potion("zombified", new MobEffectInstance(ModEffects.ZOMBIFIED, 600)));
    public static final DeferredHolder<Potion, Potion> HARD_BODY = POTION.register("toughness", () -> new Potion("toughness", new MobEffectInstance(ModEffects.HARD_BODY, 3600)));
    public static final DeferredHolder<Potion, Potion> LONG_HARD_BODY = POTION.register("long_toughness", () -> new Potion("long_toughness", new MobEffectInstance(ModEffects.HARD_BODY, 10800)));
    public static final DeferredHolder<Potion, Potion> STRONG_HARD_BODY = POTION.register("strong_toughness", () -> new Potion("strong_toughness", new MobEffectInstance(ModEffects.HARD_BODY, 1200, 1)));

}
