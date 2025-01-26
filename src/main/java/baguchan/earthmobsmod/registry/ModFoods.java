package baguchan.earthmobsmod.registry;

import net.minecraft.world.food.FoodProperties;

public class ModFoods {
    public static final FoodProperties HARDER_FLESH = new FoodProperties.Builder()
            .nutrition(4)
            .saturationModifier(0.1F)
            .build();
}
