package baguchan.earthmobsmod.client.render;

import baguchan.earthmobsmod.EarthMobsMod;
import baguchan.earthmobsmod.client.ModModelLayers;
import baguchan.earthmobsmod.client.model.AdultJumboRabbitModel;
import baguchan.earthmobsmod.client.model.BabyJumboRabbitModel;
import baguchan.earthmobsmod.entity.JumboRabbit;
import net.minecraft.ChatFormatting;
import net.minecraft.client.model.animal.rabbit.RabbitModel;
import net.minecraft.client.renderer.entity.AgeableMobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.state.RabbitRenderState;
import net.minecraft.resources.Identifier;


public class JumboRabbitRenderer extends AgeableMobRenderer<JumboRabbit, RabbitRenderState, RabbitModel> {
    private static final Identifier RABBIT_LOCATION = Identifier.fromNamespaceAndPath(EarthMobsMod.MODID, "textures/entity/jumbo_rabbit/jumbo_rabbit.png");
    private static final Identifier RABBIT_BABY_LOCATION = Identifier.fromNamespaceAndPath(EarthMobsMod.MODID, "textures/entity/jumbo_rabbit/jumbo_rabbit_baby.png");

    public JumboRabbitRenderer(EntityRendererProvider.Context context) {
        super(context, new AdultJumboRabbitModel(context.bakeLayer(ModModelLayers.JUMBO_RABBIT)), new BabyJumboRabbitModel(context.bakeLayer(ModModelLayers.JUMBO_RABBIT_BABY)), 0.45F);
	}

	@Override
    public RabbitRenderState createRenderState() {
        return new RabbitRenderState();
    }

    @Override
    public void extractRenderState(JumboRabbit entity, RabbitRenderState state, float p_365470_) {
        super.extractRenderState(entity, state, p_365470_);
        state.jumpCompletion = entity.getJumpCompletion(p_365470_);
        state.isToast = "Toast".equals(ChatFormatting.stripFormatting(entity.getName().getString()));
        state.variant = entity.getVariant();
        state.hopAnimationState.copyFrom(entity.hopAnimationState);
        state.idleHeadTiltAnimationState.copyFrom(entity.idleHeadTiltAnimationState);
    }

    @Override
    public Identifier getTextureLocation(RabbitRenderState renderState) {
        if (renderState.isBaby) {
            return RABBIT_BABY_LOCATION;
        }
        return RABBIT_LOCATION;
	}
}