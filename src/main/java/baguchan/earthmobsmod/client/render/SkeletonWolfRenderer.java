package baguchan.earthmobsmod.client.render;

import baguchan.earthmobsmod.EarthMobsMod;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.WolfRenderer;
import net.minecraft.client.renderer.entity.state.WolfRenderState;
import net.minecraft.resources.ResourceLocation;

public class SkeletonWolfRenderer extends WolfRenderer {
    private static final ResourceLocation WOLF_LOCATION = ResourceLocation.fromNamespaceAndPath(EarthMobsMod.MODID, "textures/entity/skeleton_wolf/skeleton_wolf.png");
    private static final ResourceLocation WOLF_ANGRY_LOCATION = ResourceLocation.fromNamespaceAndPath(EarthMobsMod.MODID, "textures/entity/skeleton_wolf/skeleton_wolf_angry.png");


	public SkeletonWolfRenderer(EntityRendererProvider.Context p_174452_) {
		super(p_174452_);
	}

	@Override
	public ResourceLocation getTextureLocation(WolfRenderState p_116526_) {
		return p_116526_.isAngry ? WOLF_ANGRY_LOCATION : WOLF_LOCATION;
	}
}
