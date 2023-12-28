package baguchan.earthmobsmod.registry;

import baguchan.earthmobsmod.EarthMobsMod;
import baguchan.earthmobsmod.effect.HardBodyEffect;
import baguchan.earthmobsmod.effect.HyperSparkEffect;
import baguchan.earthmobsmod.effect.UndeadBodyEffect;
import baguchan.earthmobsmod.effect.ZombifiedEffect;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionBrewing;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.Objects;

@Mod.EventBusSubscriber(modid = EarthMobsMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEffects {
	public static final DeferredRegister<MobEffect> MOB_EFFECTS = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, EarthMobsMod.MODID);
	public static final DeferredRegister<Potion> POTION = DeferredRegister.create(ForgeRegistries.POTIONS, EarthMobsMod.MODID);


	public static final RegistryObject<MobEffect> HYPER_SPARK = MOB_EFFECTS.register("hyper_spark", () -> new HyperSparkEffect(MobEffectCategory.BENEFICIAL, 0xDA784A));

	public static final RegistryObject<MobEffect> UNDEAD_BODY = MOB_EFFECTS.register("undead_body", () -> new UndeadBodyEffect(MobEffectCategory.NEUTRAL, 0xFFFFFF));
	public static final RegistryObject<MobEffect> ZOMBIFIED = MOB_EFFECTS.register("zombified", () -> new ZombifiedEffect(MobEffectCategory.BENEFICIAL, 0x2A5131));
	public static final RegistryObject<MobEffect> HARD_BODY = MOB_EFFECTS.register("toughness", () -> new HardBodyEffect(MobEffectCategory.BENEFICIAL, 0x4D575A).addAttributeModifier(Attributes.KNOCKBACK_RESISTANCE, "b33143e1-cb4c-3198-4372-8065044a29f4", 0.25, AttributeModifier.Operation.ADDITION).addAttributeModifier(Attributes.ARMOR_TOUGHNESS, "941e9fc5-6e51-8365-f95e-c99d7a798a66", 0.5F, AttributeModifier.Operation.ADDITION));

	public static final RegistryObject<Potion> HYPER_SPARK_POTION = POTION.register("hyper_spark", () -> new Potion(new MobEffectInstance(Objects.requireNonNull(HYPER_SPARK.get()), 3600)));
	public static final RegistryObject<Potion> LONG_HYPER_SPARK_POTION = POTION.register("long_hyper_spark", () -> new Potion(new MobEffectInstance(Objects.requireNonNull(HYPER_SPARK.get()), 9600)));

	public static final RegistryObject<Potion> UNDEAD_BODY_POTION = POTION.register("undead_body", () -> new Potion(new MobEffectInstance(Objects.requireNonNull(UNDEAD_BODY.get()), 3600)));
	public static final RegistryObject<Potion> LONG_UNDEAD_BODY_POTION = POTION.register("long_undead_body", () -> new Potion(new MobEffectInstance(Objects.requireNonNull(UNDEAD_BODY.get()), 9600)));
	public static final RegistryObject<Potion> ZOMBIFIED_POTION = POTION.register("zombified", () -> new Potion(new MobEffectInstance(Objects.requireNonNull(ZOMBIFIED.get()), 600)));
	public static final RegistryObject<Potion> HARD_BODY_POTION = POTION.register("toughness", () -> new Potion(new MobEffectInstance(Objects.requireNonNull(HARD_BODY.get()), 3600)));
	public static final RegistryObject<Potion> LONG_HARD_BODY_POTION = POTION.register("long_toughness", () -> new Potion(new MobEffectInstance(Objects.requireNonNull(HARD_BODY.get()), 10800)));
	public static final RegistryObject<Potion> STRONG_HARD_BODY_POTION = POTION.register("strong_toughness", () -> new Potion(new MobEffectInstance(Objects.requireNonNull(HARD_BODY.get()), 1200, 1)));

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
