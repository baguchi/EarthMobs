package baguchan.earthmobsmod.client.render;

import baguchan.earthmobsmod.EarthMobsMod;
import baguchan.earthmobsmod.client.ModModelLayers;
import baguchan.earthmobsmod.client.model.HyperRabbitModel;
import baguchan.earthmobsmod.entity.HyperRabbit;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.ChatFormatting;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.state.RabbitRenderState;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class HyperRabbitRenderer<T extends HyperRabbit> extends MobRenderer<T, RabbitRenderState, HyperRabbitModel<RabbitRenderState>> {
    private static final ResourceLocation RABBIT_BROWN_LOCATION = ResourceLocation.fromNamespaceAndPath(EarthMobsMod.MODID, "textures/entity/hyper_rabbit/brown.png");
    private static final ResourceLocation RABBIT_WHITE_LOCATION = ResourceLocation.fromNamespaceAndPath(EarthMobsMod.MODID, "textures/entity/hyper_rabbit/white.png");
    private static final ResourceLocation RABBIT_GOLD_LOCATION = ResourceLocation.fromNamespaceAndPath(EarthMobsMod.MODID, "textures/entity/hyper_rabbit/gold.png");
    private static final ResourceLocation RABBIT_SA_X_LOCATION = ResourceLocation.fromNamespaceAndPath(EarthMobsMod.MODID, "textures/entity/hyper_rabbit/hr_x.png");

	public HyperRabbitRenderer(EntityRendererProvider.Context p_173952_) {
		super(p_173952_, new HyperRabbitModel<>(p_173952_.bakeLayer(ModModelLayers.HYPER_RABBIT)), 0.3F);
	}

	@Override
    public RabbitRenderState createRenderState() {
        return new RabbitRenderState();
    }

    @Override
    public void extractRenderState(T p_363386_, RabbitRenderState p_362192_, float p_365470_) {
        super.extractRenderState(p_363386_, p_362192_, p_365470_);
        p_362192_.jumpCompletion = p_363386_.getJumpCompletion(p_365470_);
        p_362192_.isToast = "Toast".equals(ChatFormatting.stripFormatting(p_363386_.getName().getString()));
        p_362192_.variant = p_363386_.getVariant();
    }

    @Override
    protected void scale(RabbitRenderState p_115314_, PoseStack p_115315_) {
        float scale = p_115314_.isBaby ? 0.4F : 0.6F;
		p_115315_.scale(scale, scale, scale);
        super.scale(p_115314_, p_115315_);
	}

	@Override
    public ResourceLocation getTextureLocation(RabbitRenderState p_115803_) {

        switch (p_115803_.variant) {
			case WHITE, SALT:
				return RABBIT_WHITE_LOCATION;
			case GOLD:
				return RABBIT_GOLD_LOCATION;
			case EVIL:
				return RABBIT_SA_X_LOCATION;
            case BROWN:
            default:
                return RABBIT_BROWN_LOCATION;
		}
	}

	@Override
    protected boolean isShaking(RabbitRenderState p_115304_) {
        return super.isShaking(p_115304_);
	}
}