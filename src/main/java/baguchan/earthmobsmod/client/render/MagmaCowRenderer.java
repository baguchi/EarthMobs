package baguchan.earthmobsmod.client.render;

import baguchan.earthmobsmod.EarthMobsMod;
import baguchan.earthmobsmod.client.ModModelLayers;
import baguchan.earthmobsmod.client.model.MagmaCowModel;
import baguchan.earthmobsmod.entity.MagmaCow;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.EyesLayer;
import net.minecraft.resources.ResourceLocation;

public class MagmaCowRenderer<T extends MagmaCow> extends MobRenderer<T, MagmaCowModel<T>> {
    private static final ResourceLocation TEXTURE = new ResourceLocation(EarthMobsMod.MODID, "textures/entity/magma_cow/magma_cow.png");
    private static final ResourceLocation TEXTURE_GLOW = new ResourceLocation(EarthMobsMod.MODID, "textures/entity/magma_cow/magma_cow_glow.png");

    public MagmaCowRenderer(EntityRendererProvider.Context p_173952_) {
        super(p_173952_, new MagmaCowModel<>(p_173952_.bakeLayer(ModModelLayers.MAGMA_COW)), 0.3F);
        this.addLayer(new EyesLayer<T, MagmaCowModel<T>>(this) {
            @Override
            public RenderType renderType() {
                return RenderType.eyes(TEXTURE_GLOW);
            }
        });
    }


    @Override
    public ResourceLocation getTextureLocation(T p_110775_1_) {
        return TEXTURE;
    }
}