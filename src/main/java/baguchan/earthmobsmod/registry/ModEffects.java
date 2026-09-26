package baguchan.earthmobsmod.registry;

import baguchan.earthmobsmod.EarthMobsMod;
import baguchan.earthmobsmod.effect.HardBodyEffect;
import baguchan.earthmobsmod.effect.HyperSparkEffect;
import baguchan.earthmobsmod.effect.UndeadBodyEffect;
import baguchan.earthmobsmod.effect.ZombifiedEffect;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;


@EventBusSubscriber(modid = EarthMobsMod.MODID)
public class ModEffects {
	public static final DeferredRegister<MobEffect> MOB_EFFECTS = DeferredRegister.create(BuiltInRegistries.MOB_EFFECT, EarthMobsMod.MODID);

    public static final DeferredHolder<MobEffect, MobEffect> HYPER_SPARK = MOB_EFFECTS.register("hyper_spark", () -> new HyperSparkEffect(MobEffectCategory.BENEFICIAL, 0xDA784A).addAttributeModifier(Attributes.STEP_HEIGHT, Identifier.fromNamespaceAndPath(EarthMobsMod.MODID, "effect.hyper_spark"), 0.5, AttributeModifier.Operation.ADD_VALUE));

	public static final DeferredHolder<MobEffect, MobEffect> UNDEAD_BODY = MOB_EFFECTS.register("undead_body", () -> new UndeadBodyEffect(MobEffectCategory.NEUTRAL, 0xFFFFFF));
	public static final DeferredHolder<MobEffect, MobEffect> ZOMBIFIED = MOB_EFFECTS.register("zombified", () -> new ZombifiedEffect(MobEffectCategory.BENEFICIAL, 0x2A5131));
    public static final DeferredHolder<MobEffect, MobEffect> HARD_BODY = MOB_EFFECTS.register("toughness", () -> new HardBodyEffect(MobEffectCategory.BENEFICIAL, 0x4D575A).addAttributeModifier(Attributes.KNOCKBACK_RESISTANCE, Identifier.fromNamespaceAndPath(EarthMobsMod.MODID, "effect.tough"), 0.25, AttributeModifier.Operation.ADD_VALUE).addAttributeModifier(Attributes.ARMOR_TOUGHNESS, Identifier.fromNamespaceAndPath(EarthMobsMod.MODID, "effect.tough"), 0.5F, AttributeModifier.Operation.ADD_VALUE));

}
