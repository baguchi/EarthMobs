package baguchan.earthmobsmod.client.render;

import baguchan.earthmobsmod.EarthMobsMod;
import baguchan.earthmobsmod.client.ModModelLayers;
import baguchan.earthmobsmod.client.model.JollyLlamaModel;
import baguchan.earthmobsmod.entity.JollyLlama;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class JollyLlamaRenderer<T extends JollyLlama> extends MobRenderer<T, JollyLlamaModel<T>> {
    private static final ResourceLocation TEXTURE = new ResourceLocation(EarthMobsMod.MODID, "textures/entity/jolly_llama/jolly_llama.png");
    private static final ResourceLocation TAMED_TEXTURE = new ResourceLocation(EarthMobsMod.MODID, "textures/entity/jolly_llama/jolly_llama_tamed.png");


    public JollyLlamaRenderer(EntityRendererProvider.Context p_174304_) {
        super(p_174304_, new JollyLlamaModel<>(p_174304_.bakeLayer(ModModelLayers.JOLLY_LLAMA)), 0.5F);
    }

    @Override
    public ResourceLocation getTextureLocation(T p_114482_) {
        if (p_114482_.getSwag() != null) {
            return TAMED_TEXTURE;
        }
        return TEXTURE;
    }
}
