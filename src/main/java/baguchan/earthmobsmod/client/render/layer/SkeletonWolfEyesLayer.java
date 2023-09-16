package baguchan.earthmobsmod.client.render.layer;

import baguchan.earthmobsmod.EarthMobsMod;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.WolfModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.EyesLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.animal.Wolf;

public class SkeletonWolfEyesLayer<T extends Wolf, M extends WolfModel<T>> extends EyesLayer<T, M> {
	private static final RenderType EYES = RenderType.eyes(new ResourceLocation(EarthMobsMod.MODID, "textures/entity/skeleton_wolf/skeleton_wolf_eyes.png"));

	public SkeletonWolfEyesLayer(RenderLayerParent<T, M> p_117507_) {
		super(p_117507_);
	}

	@Override
	public void render(PoseStack p_116983_, MultiBufferSource p_116984_, int p_116985_, T p_116986_, float p_116987_, float p_116988_, float p_116989_, float p_116990_, float p_116991_, float p_116992_) {

		if (p_116986_.isAngry()) {
			super.render(p_116983_, p_116984_, p_116985_, p_116986_, p_116987_, p_116988_, p_116989_, p_116990_, p_116991_, p_116992_);
		}
	}

	public RenderType renderType() {
		return EYES;
	}
}