package baguchan.earthmobsmod.client.render;

import baguchan.earthmobsmod.EarthMobsMod;
import baguchan.earthmobsmod.client.ModModelLayers;
import baguchan.earthmobsmod.client.model.JollyLlamaModel;
import baguchan.earthmobsmod.entity.JollyLlama;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.state.LlamaRenderState;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class JollyLlamaRenderer<T extends JollyLlama> extends MobRenderer<T, LlamaRenderState, JollyLlamaModel<LlamaRenderState>> {
    private static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(EarthMobsMod.MODID, "textures/entity/jolly_llama/jolly_llama.png");
    private static final ResourceLocation TAMED_TEXTURE = ResourceLocation.fromNamespaceAndPath(EarthMobsMod.MODID, "textures/entity/jolly_llama/jolly_llama_tamed.png");


    public JollyLlamaRenderer(EntityRendererProvider.Context p_174304_) {
        super(p_174304_, new JollyLlamaModel<>(p_174304_.bakeLayer(ModModelLayers.JOLLY_LLAMA)), 0.5F);
    }

    @Override
    public LlamaRenderState createRenderState() {
        return new LlamaRenderState();
    }

    @Override
    public void extractRenderState(T p_363790_, LlamaRenderState p_363082_, float p_361575_) {
        super.extractRenderState(p_363790_, p_363082_, p_361575_);
        p_363082_.variant = p_363790_.getVariant();
        p_363082_.hasChest = !p_363790_.isBaby() && p_363790_.hasChest();
        p_363082_.bodyItem = p_363790_.getBodyArmorItem();
        p_363082_.isTraderLlama = p_363790_.isTraderLlama();
    }

    @Override
    public ResourceLocation getTextureLocation(LlamaRenderState p_114482_) {
        if (!p_114482_.bodyItem.isEmpty()) {
            return TAMED_TEXTURE;
        }
        return TEXTURE;
    }
}
