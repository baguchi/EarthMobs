package baguchan.earthmobsmod.client.render;

import baguchan.earthmobsmod.EarthMobsMod;
import baguchan.earthmobsmod.client.ModModelLayers;
import baguchan.earthmobsmod.client.model.FurnaceGolemModel;
import baguchan.earthmobsmod.client.render.layer.FurnaceGolemCrackinessLayer;
import baguchan.earthmobsmod.entity.FurnaceGolem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.EyesLayer;
import net.minecraft.resources.ResourceLocation;

public class FurnaceGolemRenderer<T extends FurnaceGolem> extends MobRenderer<T, FurnaceGolemModel<T>> {
    private static final ResourceLocation GOLEM_LOCATION = new ResourceLocation(EarthMobsMod.MODID, "textures/entity/furnace_golem/furnace_golem.png");
    private static final ResourceLocation TEXTURE_GLOW = new ResourceLocation(EarthMobsMod.MODID, "textures/entity/furnace_golem/furnace_golem_glow.png");

    public FurnaceGolemRenderer(EntityRendererProvider.Context p_174304_) {
        super(p_174304_, new FurnaceGolemModel<>(p_174304_.bakeLayer(ModModelLayers.FURNACE_GOLEM)), 0.7F);
        this.addLayer(new EyesLayer<T, FurnaceGolemModel<T>>(this) {
            @Override
            public RenderType renderType() {
                return RenderType.eyes(TEXTURE_GLOW);
            }
        });
        this.addLayer(new FurnaceGolemCrackinessLayer(this));
    }

    protected void setupRotations(T p_115014_, PoseStack p_115015_, float p_115016_, float p_115017_, float p_115018_) {
        super.setupRotations(p_115014_, p_115015_, p_115016_, p_115017_, p_115018_);
        if (!((double) p_115014_.walkAnimation.speed() < 0.01D)) {
            float f = 13.0F;
            float f1 = p_115014_.walkAnimation.position(p_115018_) + 6.0F;
            float f2 = (Math.abs(f1 % 13.0F - 6.5F) - 3.25F) / 3.25F;
            p_115015_.mulPose(Axis.ZP.rotationDegrees(6.5F * f2));
        }
    }

    @Override
    public ResourceLocation getTextureLocation(T p_114482_) {
        return GOLEM_LOCATION;
    }
}
