package baguchan.earthmobsmod.client.render;

import baguchan.earthmobsmod.EarthMobsMod;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.animal.Wolf;

public class WitherSkeletonWolfRenderer extends SkeletonWolfRenderer {
	private static final ResourceLocation WOLF_LOCATION = new ResourceLocation(EarthMobsMod.MODID, "textures/entity/wither_skeleton_wolf/wither_skeleton_wolf.png");
	private static final ResourceLocation WOLF_ANGRY_LOCATION = new ResourceLocation(EarthMobsMod.MODID, "textures/entity/wither_skeleton_wolf/wither_skeleton_wolf_angry.png");


	public WitherSkeletonWolfRenderer(EntityRendererProvider.Context p_174452_) {
		super(p_174452_);
	}

	@Override
	public ResourceLocation getTextureLocation(Wolf p_116526_) {
		return p_116526_.isAngry() ? WOLF_ANGRY_LOCATION : WOLF_LOCATION;
	}
}
