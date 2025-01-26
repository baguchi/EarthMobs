package baguchan.earthmobsmod.client.render.zombie;

import baguchan.earthmobsmod.EarthMobsMod;
import baguchan.earthmobsmod.client.render.state.ZombifiedRabbitRenderState;
import baguchan.earthmobsmod.entity.ZombifiedRabbit;
import net.minecraft.ChatFormatting;
import net.minecraft.client.model.RabbitModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.AgeableMobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ZombifiedRabbitRenderer<T extends ZombifiedRabbit> extends AgeableMobRenderer<T, ZombifiedRabbitRenderState, RabbitModel> {
    private static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(EarthMobsMod.MODID, "textures/entity/zombified_rabbit/zombified_rabbit.png");
    private static final ResourceLocation EVIL_TEXTURE = ResourceLocation.fromNamespaceAndPath(EarthMobsMod.MODID, "textures/entity/zombified_rabbit/zombified_rabbit_evil.png");

    public ZombifiedRabbitRenderer(EntityRendererProvider.Context p_173952_) {
        super(p_173952_, new RabbitModel(p_173952_.bakeLayer(ModelLayers.RABBIT)), new RabbitModel(p_173952_.bakeLayer(ModelLayers.RABBIT_BABY)), 0.3F);
    }

    @Override
    public ResourceLocation getTextureLocation(ZombifiedRabbitRenderState p_115803_) {
        switch (p_115803_.variant) {
            case EVIL:
                return EVIL_TEXTURE;
            default:
                return TEXTURE;
        }
    }

    @Override
    public ZombifiedRabbitRenderState createRenderState() {
        return new ZombifiedRabbitRenderState();
    }

    public void extractRenderState(T p_363386_, ZombifiedRabbitRenderState p_362192_, float p_365470_) {
        super.extractRenderState(p_363386_, p_362192_, p_365470_);
        p_362192_.jumpCompletion = p_363386_.getJumpCompletion(p_365470_);
        p_362192_.isToast = "Toast".equals(ChatFormatting.stripFormatting(p_363386_.getName().getString()));
        p_362192_.variant = p_363386_.getVariant();
        p_362192_.isConverting = p_363386_.isConverting();
    }

    @Override
    protected boolean isShaking(ZombifiedRabbitRenderState p_115304_) {
        return super.isShaking(p_115304_) || p_115304_.isConverting;
    }
}