package baguchan.earthmobsmod.client.render;

import baguchan.earthmobsmod.EarthMobsMod;
import baguchan.earthmobsmod.client.ModModelLayers;
import baguchan.earthmobsmod.client.model.BoneSpiderModel;
import baguchan.earthmobsmod.client.render.layer.BoneSpiderEyesLayer;
import baguchan.earthmobsmod.entity.BoneSpider;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class BoneSpiderRender<T extends BoneSpider> extends MobRenderer<T, LivingEntityRenderState, BoneSpiderModel<LivingEntityRenderState>> {
	private static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(EarthMobsMod.MODID, "textures/entity/bone_spider/bone_spider.png");

	public BoneSpiderRender(EntityRendererProvider.Context p_173952_) {
		super(p_173952_, new BoneSpiderModel<>(p_173952_.bakeLayer(ModModelLayers.BONE_SPIDER)), 0.65F);
		this.addLayer(new BoneSpiderEyesLayer(this));
	}

	@Override
	public LivingEntityRenderState createRenderState() {
		return new LivingEntityRenderState();
	}


	@Override
	public ResourceLocation getTextureLocation(LivingEntityRenderState p_110775_1_) {
		return TEXTURE;
	}

	@Override
	protected void scale(LivingEntityRenderState p_115314_, PoseStack p_115315_) {
		super.scale(p_115314_, p_115315_);
		p_115315_.scale(p_115314_.scale, p_115314_.scale, p_115314_.scale);
	}
}