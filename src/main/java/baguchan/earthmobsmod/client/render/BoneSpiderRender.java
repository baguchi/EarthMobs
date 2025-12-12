package baguchan.earthmobsmod.client.render;

import baguchan.earthmobsmod.EarthMobsMod;
import baguchan.earthmobsmod.client.ModModelLayers;
import baguchan.earthmobsmod.client.model.BoneSpiderModel;
import baguchan.earthmobsmod.client.render.layer.BoneSpiderEyesLayer;
import baguchan.earthmobsmod.client.render.state.BoneSpiderRenderState;
import baguchan.earthmobsmod.entity.BoneSpider;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.Identifier;
import net.minecraft.util.ARGB;


public class BoneSpiderRender<T extends BoneSpider> extends MobRenderer<T, BoneSpiderRenderState, BoneSpiderModel<BoneSpiderRenderState>> {
    private static final Identifier TEXTURE = Identifier.fromNamespaceAndPath(EarthMobsMod.MODID, "textures/entity/bone_spider/bone_spider.png");

	public BoneSpiderRender(EntityRendererProvider.Context p_173952_) {
		super(p_173952_, new BoneSpiderModel<>(p_173952_.bakeLayer(ModModelLayers.BONE_SPIDER)), 0.65F);
		this.addLayer(new BoneSpiderEyesLayer(this));
	}

	@Override
    public BoneSpiderRenderState createRenderState() {
        return new BoneSpiderRenderState();
	}

    @Override
    public void extractRenderState(T p_362733_, BoneSpiderRenderState p_360515_, float p_361157_) {
        super.extractRenderState(p_362733_, p_360515_, p_361157_);
        if (!p_362733_.getActiveEffects().isEmpty()) {
            int i = ARGB.opaque(p_362733_.getPotionContents().getColor());

            p_360515_.potionColor = i;
        } else {
            p_360515_.potionColor = -1;
        }
    }

    @Override
    public Identifier getTextureLocation(BoneSpiderRenderState p_110775_1_) {
		return TEXTURE;
	}

	@Override
    protected void scale(BoneSpiderRenderState p_115314_, PoseStack p_115315_) {
		super.scale(p_115314_, p_115315_);
		p_115315_.scale(p_115314_.scale, p_115314_.scale, p_115314_.scale);
	}
}