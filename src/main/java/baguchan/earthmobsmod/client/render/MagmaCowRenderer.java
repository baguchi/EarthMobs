package baguchan.earthmobsmod.client.render;

import baguchan.earthmobsmod.EarthMobsMod;
import baguchan.earthmobsmod.client.EarthRenderType;
import baguchan.earthmobsmod.client.ModModelLayers;
import baguchan.earthmobsmod.client.model.MagmaCowModel;
import baguchan.earthmobsmod.client.render.state.MagmaCowRenderState;
import baguchan.earthmobsmod.entity.MagmaCow;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.AgeableMobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.layers.EyesLayer;
import net.minecraft.client.renderer.feature.ModelFeatureRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.resources.ResourceLocation;

public class MagmaCowRenderer<T extends MagmaCow> extends AgeableMobRenderer<T, MagmaCowRenderState, MagmaCowModel<MagmaCowRenderState>> {
    private static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(EarthMobsMod.MODID, "textures/entity/magma_cow/magma_cow.png");
    private static final ResourceLocation TEXTURE_WEAK = ResourceLocation.fromNamespaceAndPath(EarthMobsMod.MODID, "textures/entity/magma_cow/magma_cow_no_lava.png");
    private static final ResourceLocation TEXTURE_GLOW = ResourceLocation.fromNamespaceAndPath(EarthMobsMod.MODID, "textures/entity/magma_cow/magma_cow_glow");

    public MagmaCowRenderer(EntityRendererProvider.Context p_173952_) {
        super(p_173952_, new MagmaCowModel<>(p_173952_.bakeLayer(ModModelLayers.MAGMA_COW)), new MagmaCowModel<>(p_173952_.bakeLayer(ModModelLayers.MAGMA_COW_BABY)), 0.3F);
        this.addLayer(new EyesLayer<>(this) {
            @Override
            public void submit(PoseStack p_433452_, SubmitNodeCollector p_433171_, int p_434650_, MagmaCowRenderState p_435883_, float p_433542_, float p_435619_) {
                if (p_435883_.magma) {
                    p_433171_.order(1).submitModel(this.getParentModel(), p_435883_, p_433452_, EarthRenderType.animationEye(TEXTURE_GLOW, 18, 3, (int) (p_435883_.ageInTicks - p_435883_.partialTick)), p_434650_, OverlayTexture.NO_OVERLAY, -1, (TextureAtlasSprite) null, p_435883_.outlineColor, (ModelFeatureRenderer.CrumblingOverlay) null);
                }
            }

            @Override
            public RenderType renderType() {
                return RenderType.eyes(TEXTURE_GLOW);
            }
        });
    }

    @Override
    public MagmaCowRenderState createRenderState() {
        return new MagmaCowRenderState();
    }
    @Override
    public void extractRenderState(T p_362733_, MagmaCowRenderState p_360515_, float p_361157_) {
        super.extractRenderState(p_362733_, p_360515_, p_361157_);
        p_360515_.magma = !p_362733_.isWeaking();
        p_360515_.headEatAngleScale = p_362733_.getHeadEatAngleScale(p_361157_);
    }


    @Override
    public ResourceLocation getTextureLocation(MagmaCowRenderState p_110775_1_) {
        if (!p_110775_1_.magma) {
            return TEXTURE_WEAK;
        }
        return TEXTURE;
    }

    @Override
    protected boolean isShaking(MagmaCowRenderState p_115304_) {
        return super.isShaking(p_115304_) || !p_115304_.magma;
    }
}