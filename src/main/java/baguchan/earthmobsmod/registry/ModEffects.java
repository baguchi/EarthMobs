package baguchan.earthmobsmod.registry;

import baguchan.earthmobsmod.EarthMobsMod;
import baguchan.earthmobsmod.effect.HardBodyEffect;
import baguchan.earthmobsmod.effect.HyperSparkEffect;
import baguchan.earthmobsmod.effect.UndeadBodyEffect;
import baguchan.earthmobsmod.effect.ZombifiedEffect;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionBrewing;
import net.minecraft.world.item.alchemy.Potions;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.Objects;
import java.util.function.Supplier;


public class ModEffects {
	public static final DeferredRegister<MobEffect> MOB_EFFECTS = DeferredRegister.create(BuiltInRegistries.MOB_EFFECT, EarthMobsMod.MODID);
	public static final DeferredRegister<Potion> POTION = DeferredRegister.create(BuiltInRegistries.POTION, EarthMobsMod.MODID);


	public static final Supplier<MobEffect> HYPER_SPARK = MOB_EFFECTS.register("hyper_spark", () -> new HyperSparkEffect(MobEffectCategory.BENEFICIAL, 0xDA784A));

	public static final Supplier<MobEffect> UNDEAD_BODY = MOB_EFFECTS.register("undead_body", () -> new UndeadBodyEffect(MobEffectCategory.NEUTRAL, 0xFFFFFF));
	public static final Supplier<MobEffect> ZOMBIFIED = MOB_EFFECTS.register("zombified", () -> new ZombifiedEffect(MobEffectCategory.BENEFICIAL, 0x2A5131));
	public static final Supplier<MobEffect> HARD_BODY = MOB_EFFECTS.register("hard_body", () -> new HardBodyEffect(MobEffectCategory.BENEFICIAL, 0x4D575A).addAttributeModifier(Attributes.KNOCKBACK_RESISTANCE, "b33143e1-cb4c-3198-4372-8065044a29f4", 0.25, AttributeModifier.Operation.ADDITION));

	public static final Supplier<Potion> HYPER_SPARK_POTION = POTION.register("hyper_spark", () -> new Potion(new MobEffectInstance(Objects.requireNonNull(HYPER_SPARK.get()), 3600)));
	public static final Supplier<Potion> LONG_HYPER_SPARK_POTION = POTION.register("long_hyper_spark", () -> new Potion(new MobEffectInstance(Objects.requireNonNull(HYPER_SPARK.get()), 9600)));

	public static final Supplier<Potion> UNDEAD_BODY_POTION = POTION.register("undead_body", () -> new Potion(new MobEffectInstance(Objects.requireNonNull(UNDEAD_BODY.get()), 3600)));
	public static final Supplier<Potion> LONG_UNDEAD_BODY_POTION = POTION.register("long_undead_body", () -> new Potion(new MobEffectInstance(Objects.requireNonNull(UNDEAD_BODY.get()), 9600)));
	public static final Supplier<Potion> ZOMBIFIED_POTION = POTION.register("zombified", () -> new Potion(new MobEffectInstance(Objects.requireNonNull(ZOMBIFIED.get()), 600)));
	public static final Supplier<Potion> HARD_BODY_POTION = POTION.register("hardness", () -> new Potion(new MobEffectInstance(Objects.requireNonNull(HARD_BODY.get()), 3600)));
	public static final Supplier<Potion> LONG_HARD_BODY_POTION = POTION.register("long_hardness", () -> new Potion(new MobEffectInstance(Objects.requireNonNull(HARD_BODY.get()), 10800)));
	public static final Supplier<Potion> STRONG_HARD_BODY_POTION = POTION.register("strong_hardness", () -> new Potion(new MobEffectInstance(Objects.requireNonNull(HARD_BODY.get()), 1200, 1)));

	public static void init() {
		PotionBrewing.addMix(Potions.SWIFTNESS, ModItems.HYPER_RABBIT_FOOT.get(), HYPER_SPARK_POTION.get());
		PotionBrewing.addMix(HYPER_SPARK_POTION.get(), Items.REDSTONE, LONG_HYPER_SPARK_POTION.get());
		PotionBrewing.addMix(Potions.AWKWARD, ModItems.BONE_SPIDER_EYE.get(), UNDEAD_BODY_POTION.get());
		PotionBrewing.addMix(UNDEAD_BODY_POTION.get(), Items.REDSTONE, LONG_UNDEAD_BODY_POTION.get());
		PotionBrewing.addMix(Potions.AWKWARD, ModItems.ZOMBIFIED_RABBIT_FOOT.get(), ZOMBIFIED_POTION.get());
		PotionBrewing.addMix(Potions.STRENGTH, ModItems.HARDER_FLESH.get(), HARD_BODY_POTION.get());
		PotionBrewing.addMix(HARD_BODY_POTION.get(), Items.REDSTONE, LONG_HARD_BODY_POTION.get());
		PotionBrewing.addMix(HARD_BODY_POTION.get(), Items.GLOWSTONE, STRONG_HARD_BODY_POTION.get());
	}
}
