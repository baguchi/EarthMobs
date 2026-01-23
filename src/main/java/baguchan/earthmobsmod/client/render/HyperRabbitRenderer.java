package baguchan.earthmobsmod.client.render;

import baguchan.earthmobsmod.EarthMobsMod;
import baguchan.earthmobsmod.client.ModModelLayers;
import baguchan.earthmobsmod.client.model.HyperRabbitModel;
import baguchan.earthmobsmod.client.render.state.HyperRabbitRenderState;
import baguchan.earthmobsmod.entity.HyperRabbit;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.ChatFormatting;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.Identifier;


public class HyperRabbitRenderer extends MobRenderer<HyperRabbit, HyperRabbitRenderState, HyperRabbitModel<HyperRabbitRenderState>> {
    private static final Identifier RABBIT_BROWN_LOCATION = Identifier.fromNamespaceAndPath(EarthMobsMod.MODID, "textures/entity/hyper_rabbit/brown.png");
    private static final Identifier RABBIT_WHITE_LOCATION = Identifier.fromNamespaceAndPath(EarthMobsMod.MODID, "textures/entity/hyper_rabbit/white.png");
    private static final Identifier RABBIT_GOLD_LOCATION = Identifier.fromNamespaceAndPath(EarthMobsMod.MODID, "textures/entity/hyper_rabbit/gold.png");
    private static final Identifier RABBIT_SA_X_LOCATION = Identifier.fromNamespaceAndPath(EarthMobsMod.MODID, "textures/entity/hyper_rabbit/hr_x.png");

	public HyperRabbitRenderer(EntityRendererProvider.Context p_173952_) {
		super(p_173952_, new HyperRabbitModel<>(p_173952_.bakeLayer(ModModelLayers.HYPER_RABBIT)), 0.3F);
	}

	@Override
    public HyperRabbitRenderState createRenderState() {
        return new HyperRabbitRenderState();
    }

    @Override
    public void extractRenderState(HyperRabbit entity, HyperRabbitRenderState state, float p_365470_) {
        super.extractRenderState(entity, state, p_365470_);
        state.jumpCompletion = entity.getJumpCompletion(p_365470_);
        state.isToast = "Toast".equals(ChatFormatting.stripFormatting(entity.getName().getString()));
        state.variant = entity.getVariant();
        state.hopAnimationState.copyFrom(entity.hopAnimationState);
        state.idleHeadTiltAnimationState.copyFrom(entity.idleHeadTiltAnimationState);

    }

    @Override
    protected void scale(HyperRabbitRenderState p_115314_, PoseStack p_115315_) {
        float scale = p_115314_.isBaby ? 0.4F : 0.6F;
		p_115315_.scale(scale, scale, scale);
        super.scale(p_115314_, p_115315_);
	}

	@Override
    public Identifier getTextureLocation(HyperRabbitRenderState p_115803_) {

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
    protected boolean isShaking(HyperRabbitRenderState p_115304_) {
        return super.isShaking(p_115304_);
	}
}