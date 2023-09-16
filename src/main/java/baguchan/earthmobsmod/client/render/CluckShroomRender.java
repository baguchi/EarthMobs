package baguchan.earthmobsmod.client.render;

import baguchan.earthmobsmod.EarthMobsMod;
import baguchan.earthmobsmod.client.ModModelLayers;
import baguchan.earthmobsmod.client.model.CluckShroomModel;
import baguchan.earthmobsmod.entity.CluckShroom;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class CluckShroomRender<T extends CluckShroom> extends MobRenderer<T, CluckShroomModel<T>> {
	private static final ResourceLocation TEXTURE = new ResourceLocation(EarthMobsMod.MODID, "textures/entity/cluck_shroom/cluck_shroom.png");
	private static final ResourceLocation TEXTURE_BROWN = new ResourceLocation(EarthMobsMod.MODID, "textures/entity/cluck_shroom/brown_cluck_shroom.png");

	public CluckShroomRender(EntityRendererProvider.Context p_173952_) {
		super(p_173952_, new CluckShroomModel<>(p_173952_.bakeLayer(ModModelLayers.CLUCK_SHROOM)), 0.3F);
	}

	protected float getBob(T p_114000_, float p_114001_) {
		float f = Mth.lerp(p_114001_, p_114000_.oFlap, p_114000_.flap);
		float f1 = Mth.lerp(p_114001_, p_114000_.oFlapSpeed, p_114000_.flapSpeed);
		return (Mth.sin(f) + 1.0F) * f1;
	}

	@Override
	public ResourceLocation getTextureLocation(T p_110775_1_) {
		return p_110775_1_.getCluckShroomType() == CluckShroom.CluckShroomType.BROWN ? TEXTURE_BROWN : TEXTURE;
	}
}