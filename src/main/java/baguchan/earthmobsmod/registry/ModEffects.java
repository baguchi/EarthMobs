package baguchan.earthmobsmod.registry;

import baguchan.earthmobsmod.EarthMobsMod;
import baguchan.earthmobsmod.effect.HardBodyEffect;
import baguchan.earthmobsmod.effect.HyperSparkEffect;
import baguchan.earthmobsmod.effect.UndeadBodyEffect;
import baguchan.earthmobsmod.effect.ZombifiedEffect;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.Potions;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.brewing.RegisterBrewingRecipesEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;


@EventBusSubscriber(modid = EarthMobsMod.MODID, bus = EventBusSubscriber.Bus.GAME)
public class ModEffects {
	public static final DeferredRegister<MobEffect> MOB_EFFECTS = DeferredRegister.create(BuiltInRegistries.MOB_EFFECT, EarthMobsMod.MODID);
	public static final DeferredRegister<Potion> POTION = DeferredRegister.create(BuiltInRegistries.POTION, EarthMobsMod.MODID);


	public static final DeferredHolder<MobEffect, MobEffect> HYPER_SPARK = MOB_EFFECTS.register("hyper_spark", () -> new HyperSparkEffect(MobEffectCategory.BENEFICIAL, 0xDA784A));

	public static final DeferredHolder<MobEffect, MobEffect> UNDEAD_BODY = MOB_EFFECTS.register("undead_body", () -> new UndeadBodyEffect(MobEffectCategory.NEUTRAL, 0xFFFFFF));
	public static final DeferredHolder<MobEffect, MobEffect> ZOMBIFIED = MOB_EFFECTS.register("zombified", () -> new ZombifiedEffect(MobEffectCategory.BENEFICIAL, 0x2A5131));
	public static final DeferredHolder<MobEffect, MobEffect> HARD_BODY = MOB_EFFECTS.register("toughness", () -> new HardBodyEffect(MobEffectCategory.BENEFICIAL, 0x4D575A).addAttributeModifier(Attributes.KNOCKBACK_RESISTANCE, ResourceLocation.fromNamespaceAndPath(EarthMobsMod.MODID, "effect.tough"), 0.25, AttributeModifier.Operation.ADD_VALUE).addAttributeModifier(Attributes.ARMOR_TOUGHNESS, ResourceLocation.fromNamespaceAndPath(EarthMobsMod.MODID, "effect.tough"), 0.5F, AttributeModifier.Operation.ADD_VALUE));

	public static final DeferredHolder<Potion, Potion> HYPER_SPARK_POTION = POTION.register("hyper_spark", () -> new Potion(new MobEffectInstance(HYPER_SPARK, 3600)));
	public static final DeferredHolder<Potion, Potion> LONG_HYPER_SPARK_POTION = POTION.register("long_hyper_spark", () -> new Potion(new MobEffectInstance(HYPER_SPARK, 9600)));

	public static final DeferredHolder<Potion, Potion> UNDEAD_BODY_POTION = POTION.register("undead_body", () -> new Potion(new MobEffectInstance(UNDEAD_BODY, 3600)));
	public static final DeferredHolder<Potion, Potion> LONG_UNDEAD_BODY_POTION = POTION.register("long_undead_body", () -> new Potion(new MobEffectInstance(UNDEAD_BODY, 9600)));
	public static final DeferredHolder<Potion, Potion> ZOMBIFIED_POTION = POTION.register("zombified", () -> new Potion(new MobEffectInstance(ZOMBIFIED, 600)));
	public static final DeferredHolder<Potion, Potion> HARD_BODY_POTION = POTION.register("toughness", () -> new Potion(new MobEffectInstance(HARD_BODY, 3600)));
	public static final DeferredHolder<Potion, Potion> LONG_HARD_BODY_POTION = POTION.register("long_toughness", () -> new Potion(new MobEffectInstance(HARD_BODY, 10800)));
	public static final DeferredHolder<Potion, Potion> STRONG_HARD_BODY_POTION = POTION.register("strong_toughness", () -> new Potion(new MobEffectInstance(HARD_BODY, 1200, 1)));

	@SubscribeEvent
	public static void init(RegisterBrewingRecipesEvent event) {
		event.getBuilder().addMix(Potions.SWIFTNESS, ModItems.HYPER_RABBIT_FOOT.get(), HYPER_SPARK_POTION);
		event.getBuilder().addMix(HYPER_SPARK_POTION, Items.REDSTONE, LONG_HYPER_SPARK_POTION);
		event.getBuilder().addMix(Potions.AWKWARD, ModItems.BONE_SPIDER_EYE.get(), UNDEAD_BODY_POTION);
		event.getBuilder().addMix(UNDEAD_BODY_POTION, Items.REDSTONE, LONG_UNDEAD_BODY_POTION);
		event.getBuilder().addMix(Potions.AWKWARD, ModItems.ZOMBIFIED_RABBIT_FOOT.get(), ZOMBIFIED_POTION);
		event.getBuilder().addMix(Potions.STRENGTH, ModItems.HARDER_FLESH.get(), HARD_BODY_POTION);
		event.getBuilder().addMix(HARD_BODY_POTION, Items.REDSTONE, LONG_HARD_BODY_POTION);
		event.getBuilder().addMix(HARD_BODY_POTION, Items.GLOWSTONE, STRONG_HARD_BODY_POTION);
	}
}
