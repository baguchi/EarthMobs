package baguchan.earthmobsmod.data.recipe;

import net.minecraft.advancements.Advancement;
import net.minecraft.data.recipes.BrewingProvider;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.world.item.crafting.Recipe;

public class ModRecipeProvider extends RecipeProvider {

    private final BrewingProvider brewingProvider = new ModBrewingProvider(this.output);

    public ModRecipeProvider(BootstrapContext<Recipe<?>> recipeOutput, BootstrapContext<Advancement> advancementOutput) {
        super(recipeOutput, advancementOutput);
    }

    @Override
    protected void buildRecipes() {
        this.brewingProvider.buildRecipes();
    }
}
