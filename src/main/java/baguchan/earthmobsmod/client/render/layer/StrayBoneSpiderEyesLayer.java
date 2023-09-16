package baguchan.earthmobsmod.client.render.layer;

import baguchan.earthmobsmod.EarthMobsMod;
import baguchan.earthmobsmod.client.model.BoneSpiderModel;
import baguchan.earthmobsmod.entity.BoneSpider;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.EyesLayer;
import net.minecraft.resources.ResourceLocation;

public class StrayBoneSpiderEyesLayer<T extends BoneSpider, M extends BoneSpiderModel<T>> extends EyesLayer<T, M> {
	private static final RenderType SPIDER_EYES = RenderType.eyes(new ResourceLocation(EarthMobsMod.MODID, "textures/entity/bone_spider/stray_bone_spider_eye.png"));

	public StrayBoneSpiderEyesLayer(RenderLayerParent<T, M> p_117507_) {
		super(p_117507_);
	}

	public RenderType renderType() {
		return SPIDER_EYES;
	}
}