package baguchan.earthmobsmod.data;

import baguchan.earthmobsmod.data.loot.ModLootTableProvider;
import baguchan.earthmobsmod.data.recipe.ModRecipeProvider;
import baguchan.earthmobsmod.registry.ModInstruments;
import baguchan.earthmobsmod.world.features.ModEarthFeatures;
import baguchan.earthmobsmod.world.features.ModEarthPlacements;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.recipes.RecipeProvider;

public class RegistryDataGenerator {

    public static final RegistrySetBuilder BUILDER = new RegistrySetBuilder()
            .add(Registries.INSTRUMENT, ModInstruments::bootstrap)
            .add(Registries.FEATURE, ModEarthFeatures::bootstrap)
            .add(Registries.PLACED_FEATURE, ModEarthPlacements::bootstrap);

    public static final RegistrySetBuilder RELOADABLE_BUILDER = new RegistrySetBuilder()
            .add(Registries.LOOT_TABLE, ModLootTableProvider.create())
            .add(RecipeProvider.asBootstrap(ModRecipeProvider::new));
}