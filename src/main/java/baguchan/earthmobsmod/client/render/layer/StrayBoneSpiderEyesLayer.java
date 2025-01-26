package baguchan.earthmobsmod.client.render.layer;

import baguchan.earthmobsmod.EarthMobsMod;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.EyesLayer;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.resources.ResourceLocation;

public class StrayBoneSpiderEyesLayer<T extends LivingEntityRenderState> extends EyesLayer<T, EntityModel<T>> {
    private static final RenderType SPIDER_EYES = RenderType.eyes(ResourceLocation.fromNamespaceAndPath(EarthMobsMod.MODID, "textures/entity/bone_spider/stray_bone_spider_eye.png"));

	public StrayBoneSpiderEyesLayer(RenderLayerParent<T, EntityModel<T>> p_117507_) {
		super(p_117507_);
	}

	public RenderType renderType() {
		return SPIDER_EYES;
	}
}