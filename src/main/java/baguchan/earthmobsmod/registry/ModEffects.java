package baguchan.earthmobsmod.registry;

import baguchan.earthmobsmod.EarthMobsMod;
import baguchan.earthmobsmod.effect.HyperSparkEffect;
import baguchan.earthmobsmod.effect.UndeadBodyEffect;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
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

	public static final RegistryObject<Potion> HYPER_SPARK_POTION = POTION.register("hyper_spark", () -> new Potion(new MobEffectInstance(Objects.requireNonNull(HYPER_SPARK.get()), 3600)));
	public static final RegistryObject<Potion> LONG_HYPER_SPARK_POTION = POTION.register("long_hyper_spark", () -> new Potion(new MobEffectInstance(Objects.requireNonNull(HYPER_SPARK.get()), 9600)));

	public static final RegistryObject<Potion> UNDEAD_BODY_POTION = POTION.register("undead_body", () -> new Potion(new MobEffectInstance(Objects.requireNonNull(UNDEAD_BODY.get()), 3600)));
	public static final RegistryObject<Potion> LONG_UNDEAD_BODY_POTION = POTION.register("long_undead_body", () -> new Potion(new MobEffectInstance(Objects.requireNonNull(UNDEAD_BODY.get()), 9600)));

	public static void init() {
		PotionBrewing.addMix(Potions.SWIFTNESS, ModItems.HYPER_RABBIT_FOOT.get(), HYPER_SPARK_POTION.get());
		PotionBrewing.addMix(HYPER_SPARK_POTION.get(), Items.REDSTONE, LONG_HYPER_SPARK_POTION.get());
		PotionBrewing.addMix(Potions.AWKWARD, ModItems.BONE_SPIDER_EYE.get(), UNDEAD_BODY_POTION.get());
		PotionBrewing.addMix(UNDEAD_BODY_POTION.get(), Items.REDSTONE, LONG_UNDEAD_BODY_POTION.get());
	}
}
