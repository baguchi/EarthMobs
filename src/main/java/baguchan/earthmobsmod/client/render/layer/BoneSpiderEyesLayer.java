package baguchan.earthmobsmod.client.render.layer;

import baguchan.earthmobsmod.EarthMobsMod;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.EyesLayer;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.client.renderer.rendertype.RenderType;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.resources.Identifier;

public class BoneSpiderEyesLayer<T extends LivingEntityRenderState> extends EyesLayer<T, EntityModel<T>> {
    private static final RenderType SPIDER_EYES = RenderTypes.eyes(Identifier.fromNamespaceAndPath(EarthMobsMod.MODID, "textures/entity/bone_spider/bone_spider_eye.png"));

	public BoneSpiderEyesLayer(RenderLayerParent<T, EntityModel<T>> p_117507_) {
		super(p_117507_);
	}

	public RenderType renderType() {
		return SPIDER_EYES;
	}
}