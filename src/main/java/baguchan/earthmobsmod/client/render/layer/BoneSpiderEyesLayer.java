package baguchan.earthmobsmod.client.render.layer;

import baguchan.earthmobsmod.EarthMobsMod;
import baguchan.earthmobsmod.client.model.BoneSpiderModel;
import baguchan.earthmobsmod.entity.BoneSpider;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.EyesLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;

public class BoneSpiderEyesLayer<T extends BoneSpider, M extends BoneSpiderModel<T>> extends EyesLayer<T, M> {
    private static final RenderType SPIDER_EYES = RenderType.eyes(ResourceLocation.fromNamespaceAndPath(EarthMobsMod.MODID, "textures/entity/bone_spider/bone_spider_eye.png"));
	private static final RenderType COLOR_EYES = RenderType.eyes(ResourceLocation.fromNamespaceAndPath(EarthMobsMod.MODID, "textures/entity/bone_spider/bone_spider_color_eye.png"));

	public BoneSpiderEyesLayer(RenderLayerParent<T, M> p_117507_) {
		super(p_117507_);
	}

	@Override
	public void render(
			PoseStack p_116983_,
			MultiBufferSource p_116984_,
			int p_116985_,
			T p_116986_,
			float p_116987_,
			float p_116988_,
			float p_116989_,
			float p_116990_,
			float p_116991_,
			float p_116992_
	) {
		if (p_116986_.getPotionContents().hasEffects()) {
			VertexConsumer vertexconsumer = p_116984_.getBuffer(COLOR_EYES);
			this.getParentModel().renderToBuffer(p_116983_, vertexconsumer, p_116986_.getPotionContents().getColor(), OverlayTexture.NO_OVERLAY);
		} else {
			VertexConsumer vertexconsumer = p_116984_.getBuffer(this.renderType());
			this.getParentModel().renderToBuffer(p_116983_, vertexconsumer, 15728640, OverlayTexture.NO_OVERLAY);
		}
	}


	@Override
	public RenderType renderType() {
		return SPIDER_EYES;
	}
}