package baguchan.earthmobsmod.data.recipe;

import baguchan.earthmobsmod.registry.ModItems;
import baguchan.earthmobsmod.registry.ModPotions;
import net.minecraft.data.recipes.BrewingProvider;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potions;

public class ModBrewingProvider extends BrewingProvider {
    protected ModBrewingProvider(RecipeOutput recipeOutput) {
        super(recipeOutput);
    }

    @Override
    protected void addContainers() {
    }

    @Override
    protected void addContainerTransformations() {
    }

    @Override
    protected void buildMixes() {
        this.buildMix(Potions.SWIFTNESS, ModItems.HYPER_RABBIT_FOOT.get(), ModPotions.HYPER_SPARK);
        this.buildMix(ModPotions.HYPER_SPARK, Items.REDSTONE, ModPotions.LONG_HYPER_SPARK);
        this.buildStartMix(ModItems.BONE_SPIDER_EYE.get(), ModPotions.UNDEAD_BODY);
        this.buildMix(ModPotions.UNDEAD_BODY, Items.REDSTONE, ModPotions.LONG_UNDEAD_BODY);
        this.buildStartMix(ModItems.ZOMBIFIED_RABBIT_FOOT.get(), ModPotions.ZOMBIFIED);
        this.buildStartMix(ModItems.HUSK_RABBIT_FOOT.get(), ModPotions.ZOMBIFIED);
        this.buildMix(Potions.STRENGTH, ModItems.HARDER_FLESH.get(), ModPotions.HARD_BODY);
        this.buildMix(ModPotions.HARD_BODY, Items.REDSTONE, ModPotions.LONG_HARD_BODY);
        this.buildMix(ModPotions.HARD_BODY, Items.GLOWSTONE_DUST, ModPotions.STRONG_HARD_BODY);
    }
}
