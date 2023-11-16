package baguchan.earthmobsmod.registry;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;

public class ModFoods {
    public static final FoodProperties HARDER_FLESH = new FoodProperties.Builder()
            .nutrition(4)
            .saturationMod(0.1F)
            .effect(new MobEffectInstance(MobEffects.HUNGER, 600, 0), 0.8F)
            .effect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 200, 0), 0.8F)
            .meat()
            .build();
}
