package baguchan.earthmobsmod.client.render;

import baguchan.earthmobsmod.EarthMobsMod;
import baguchan.earthmobsmod.client.ModModelLayers;
import baguchan.earthmobsmod.client.model.FancyChickenModel;
import baguchan.earthmobsmod.entity.FancyChicken;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class FancyChickenRenderer<T extends FancyChicken> extends MobRenderer<T, FancyChickenModel<T>> {
	private static final ResourceLocation TEXTURE = new ResourceLocation(EarthMobsMod.MODID, "textures/entity/fancy_chicken.png");

	public FancyChickenRenderer(EntityRendererProvider.Context p_173952_) {
		super(p_173952_, new FancyChickenModel<>(p_173952_.bakeLayer(ModModelLayers.FANCY_CHICKEN)), 0.3F);
	}

	protected float getBob(T p_114000_, float p_114001_) {
		float f = Mth.lerp(p_114001_, p_114000_.oFlap, p_114000_.flap);
		float f1 = Mth.lerp(p_114001_, p_114000_.oFlapSpeed, p_114000_.flapSpeed);
		return (Mth.sin(f) + 1.0F) * f1;
	}

	@Override
	public ResourceLocation getTextureLocation(T p_110775_1_) {
		return TEXTURE;
	}
}