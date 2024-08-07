package baguchan.earthmobsmod.client.render.zombie;

import baguchan.earthmobsmod.EarthMobsMod;
import baguchan.earthmobsmod.entity.ZombifiedRabbit;
import net.minecraft.ChatFormatting;
import net.minecraft.client.model.RabbitModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ZombifiedRabbitRenderer<T extends ZombifiedRabbit> extends MobRenderer<T, RabbitModel<T>> {
    private static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(EarthMobsMod.MODID, "textures/entity/zombified_rabbit/zombified_rabbit.png");
    private static final ResourceLocation EVIL_TEXTURE = ResourceLocation.fromNamespaceAndPath(EarthMobsMod.MODID, "textures/entity/zombified_rabbit/zombified_rabbit_evil.png");

    public ZombifiedRabbitRenderer(EntityRendererProvider.Context p_173952_) {
        super(p_173952_, new RabbitModel<>(p_173952_.bakeLayer(ModelLayers.RABBIT)), 0.3F);
    }

    @Override
    public ResourceLocation getTextureLocation(T p_115803_) {
        String s = ChatFormatting.stripFormatting(p_115803_.getName().getString());

        switch (p_115803_.getVariant()) {
            case EVIL:
                return EVIL_TEXTURE;
            default:
                return TEXTURE;
        }
    }

    @Override
    protected boolean isShaking(T p_115304_) {
        return super.isShaking(p_115304_) || p_115304_.isConverting();
    }
}