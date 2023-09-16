package baguchan.earthmobsmod.client.render;

import baguchan.earthmobsmod.EarthMobsMod;
import baguchan.earthmobsmod.client.render.layer.SkeletonWolfEyesLayer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.WolfRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.animal.Wolf;

public class SkeletonWolfRenderer extends WolfRenderer {
	private static final ResourceLocation WOLF_LOCATION = new ResourceLocation(EarthMobsMod.MODID, "textures/entity/skeleton_wolf/skeleton_wolf.png");
	private static final ResourceLocation WOLF_ANGRY_LOCATION = new ResourceLocation(EarthMobsMod.MODID, "textures/entity/skeleton_wolf/skeleton_wolf_angry.png");


	public SkeletonWolfRenderer(EntityRendererProvider.Context p_174452_) {
		super(p_174452_);
		this.addLayer(new SkeletonWolfEyesLayer<>(this));
	}

	@Override
	public ResourceLocation getTextureLocation(Wolf p_116526_) {
		return p_116526_.isAngry() ? WOLF_ANGRY_LOCATION : WOLF_LOCATION;
	}
}
