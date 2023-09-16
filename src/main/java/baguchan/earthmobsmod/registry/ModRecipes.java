package baguchan.earthmobsmod.registry;

import baguchan.earthmobsmod.EarthMobsMod;
import baguchan.earthmobsmod.recipe.TippedArrowWithBoneRecipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.SimpleCraftingRecipeSerializer;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModRecipes {
	public static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZERS = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, EarthMobsMod.MODID);
	public static final RegistryObject<RecipeSerializer<? extends TippedArrowWithBoneRecipe>> RECIPE_TIPPED_ARROW_WITH_BONE = RECIPE_SERIALIZERS.register("tipped_arrow_with_bone", () -> new SimpleCraftingRecipeSerializer<>(TippedArrowWithBoneRecipe::new));
}