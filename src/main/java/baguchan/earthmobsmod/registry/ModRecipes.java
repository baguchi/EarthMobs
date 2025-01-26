package baguchan.earthmobsmod.registry;

import baguchan.earthmobsmod.EarthMobsMod;
import baguchan.earthmobsmod.recipe.TippedArrowWithBoneRecipe;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.crafting.CustomRecipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModRecipes {
	public static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZERS = DeferredRegister.create(BuiltInRegistries.RECIPE_SERIALIZER, EarthMobsMod.MODID);
	public static final Supplier<RecipeSerializer<? extends TippedArrowWithBoneRecipe>> RECIPE_TIPPED_ARROW_WITH_BONE = RECIPE_SERIALIZERS.register("tipped_arrow_with_bone", () -> new CustomRecipe.Serializer<>(TippedArrowWithBoneRecipe::new));
}