package baguchan.earthmobsmod.client.render;

import baguchan.earthmobsmod.EarthMobsMod;
import baguchan.earthmobsmod.client.EarthRenderType;
import baguchan.earthmobsmod.client.ModModelLayers;
import baguchan.earthmobsmod.client.model.MagmaCowModel;
import baguchan.earthmobsmod.entity.MagmaCow;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.EyesLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;

public class MagmaCowRenderer<T extends MagmaCow> extends MobRenderer<T, MagmaCowModel<T>> {
    private static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(EarthMobsMod.MODID, "textures/entity/magma_cow/magma_cow.png");
    private static final ResourceLocation TEXTURE_WEAK = ResourceLocation.fromNamespaceAndPath(EarthMobsMod.MODID, "textures/entity/magma_cow/magma_cow_no_lava.png");
    private static final ResourceLocation TEXTURE_GLOW = ResourceLocation.fromNamespaceAndPath(EarthMobsMod.MODID, "textures/entity/magma_cow/magma_cow_glow");

    public MagmaCowRenderer(EntityRendererProvider.Context p_173952_) {
        super(p_173952_, new MagmaCowModel<>(p_173952_.bakeLayer(ModModelLayers.MAGMA_COW)), 0.3F);
        this.addLayer(new EyesLayer<T, MagmaCowModel<T>>(this) {

            @Override
            public void render(PoseStack poseStack,
                               MultiBufferSource buffer,
                               int packedLight,
                               T livingEntity,
                               float limbSwing,
                               float limbSwingAmount,
                               float partialTicks,
                               float ageInTicks,
                               float netHeadYaw,
                               float headPitch
            ) {
                if (!livingEntity.isWeaking()) {
                    VertexConsumer vertexconsumer = buffer.getBuffer(EarthRenderType.animationEye(TEXTURE_GLOW, 18, 3, livingEntity.tickCount));
                    this.getParentModel().renderToBuffer(poseStack, vertexconsumer, 15728640, OverlayTexture.NO_OVERLAY);
                }
            }

            @Override
            public RenderType renderType() {
                return RenderType.eyes(TEXTURE_GLOW);
            }
        });
    }


    @Override
    public ResourceLocation getTextureLocation(T p_110775_1_) {
        if (p_110775_1_.isWeaking()) {
            return TEXTURE_WEAK;
        }
        return TEXTURE;
    }

    @Override
    protected boolean isShaking(T p_115304_) {
        return super.isShaking(p_115304_) || p_115304_.isWeaking();
    }
}