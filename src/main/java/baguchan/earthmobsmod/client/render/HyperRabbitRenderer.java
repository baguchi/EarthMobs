package baguchan.earthmobsmod.client.render;

import baguchan.earthmobsmod.EarthMobsMod;
import baguchan.earthmobsmod.client.ModModelLayers;
import baguchan.earthmobsmod.client.model.HyperRabbitModel;
import baguchan.earthmobsmod.client.render.state.HyperRabbitRenderState;
import baguchan.earthmobsmod.entity.HyperRabbit;
import net.minecraft.ChatFormatting;
import net.minecraft.client.renderer.entity.AgeableMobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.Identifier;


public class HyperRabbitRenderer extends AgeableMobRenderer<HyperRabbit, HyperRabbitRenderState, HyperRabbitModel> {
    private static final Identifier RABBIT_BROWN_LOCATION = Identifier.fromNamespaceAndPath(EarthMobsMod.MODID, "textures/entity/hyper_rabbit/brown.png");
    private static final Identifier RABBIT_WHITE_LOCATION = Identifier.fromNamespaceAndPath(EarthMobsMod.MODID, "textures/entity/hyper_rabbit/white.png");
    private static final Identifier RABBIT_GOLD_LOCATION = Identifier.fromNamespaceAndPath(EarthMobsMod.MODID, "textures/entity/hyper_rabbit/gold.png");
    private static final Identifier RABBIT_SA_X_LOCATION = Identifier.fromNamespaceAndPath(EarthMobsMod.MODID, "textures/entity/hyper_rabbit/hr_x.png");

    public HyperRabbitRenderer(EntityRendererProvider.Context context) {
        super(context, new HyperRabbitModel(context.bakeLayer(ModModelLayers.HYPER_RABBIT)), new HyperRabbitModel(context.bakeLayer(ModModelLayers.HYPER_RABBIT_BABY)), 0.3F);
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