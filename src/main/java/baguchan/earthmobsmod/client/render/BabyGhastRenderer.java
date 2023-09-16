package baguchan.earthmobsmod.client.render;

import baguchan.earthmobsmod.EarthMobsMod;
import baguchan.earthmobsmod.client.ModModelLayers;
import baguchan.earthmobsmod.client.model.BabyGhastModel;
import baguchan.earthmobsmod.client.render.layer.BabyGhastCoreLayer;
import baguchan.earthmobsmod.entity.BabyGhast;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class BabyGhastRenderer<T extends BabyGhast> extends MobRenderer<T, BabyGhastModel<T>> {
	private static final ResourceLocation TEXTURE = new ResourceLocation(EarthMobsMod.MODID, "textures/entity/baby_ghast/baby_ghast.png");
	private static final ResourceLocation TEXTURE_SHOOT = new ResourceLocation(EarthMobsMod.MODID, "textures/entity/baby_ghast/baby_ghast_shoot.png");

	public BabyGhastRenderer(EntityRendererProvider.Context p_173952_) {
		super(p_173952_, new BabyGhastModel<>(p_173952_.bakeLayer(ModModelLayers.BABY_GHAST)), 0.3F);
		this.addLayer(new BabyGhastCoreLayer<>(this, p_173952_.getModelSet()));

	}


	@Override
	public ResourceLocation getTextureLocation(T p_110775_1_) {
		return p_110775_1_.isCharging() ? TEXTURE_SHOOT : TEXTURE;
	}
}