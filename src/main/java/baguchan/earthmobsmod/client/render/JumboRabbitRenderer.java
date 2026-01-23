package baguchan.earthmobsmod.client.render;

import baguchan.earthmobsmod.EarthMobsMod;
import baguchan.earthmobsmod.client.ModModelLayers;
import baguchan.earthmobsmod.client.model.JumboRabbitModel;
import baguchan.earthmobsmod.entity.JumboRabbit;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.ChatFormatting;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.state.RabbitRenderState;
import net.minecraft.resources.Identifier;


public class JumboRabbitRenderer<T extends JumboRabbit> extends MobRenderer<T, RabbitRenderState, JumboRabbitModel<RabbitRenderState>> {
    private static final Identifier RABBIT_BROWN_LOCATION = Identifier.fromNamespaceAndPath(EarthMobsMod.MODID, "textures/entity/jumbo_rabbit/brown.png");
    private static final Identifier RABBIT_WHITE_LOCATION = Identifier.fromNamespaceAndPath(EarthMobsMod.MODID, "textures/entity/jumbo_rabbit/white.png");
    private static final Identifier RABBIT_BLACK_LOCATION = Identifier.fromNamespaceAndPath(EarthMobsMod.MODID, "textures/entity/jumbo_rabbit/black.png");
    private static final Identifier RABBIT_EVIL_LOCATION = Identifier.fromNamespaceAndPath(EarthMobsMod.MODID, "textures/entity/jumbo_rabbit/evil.png");


	public JumboRabbitRenderer(EntityRendererProvider.Context p_173952_) {
        super(p_173952_, new JumboRabbitModel<>(p_173952_.bakeLayer(ModModelLayers.JUMBO_RABBIT)), 0.45F);
	}

	@Override
    public RabbitRenderState createRenderState() {
        return new RabbitRenderState();
    }

    @Override
    public void extractRenderState(T entity, RabbitRenderState state, float p_365470_) {
        super.extractRenderState(entity, state, p_365470_);
        state.jumpCompletion = entity.getJumpCompletion(p_365470_);
        state.isToast = "Toast".equals(ChatFormatting.stripFormatting(entity.getName().getString()));
        state.variant = entity.getVariant();
        state.hopAnimationState.copyFrom(entity.hopAnimationState);
        state.idleHeadTiltAnimationState.copyFrom(entity.idleHeadTiltAnimationState);
    }

    @Override
    protected void scale(RabbitRenderState p_115314_, PoseStack p_115315_) {
        float scale = p_115314_.isBaby ? 0.6F : 1.0F;
		p_115315_.scale(scale, scale, scale);
        super.scale(p_115314_, p_115315_);
	}

	@Override
    public Identifier getTextureLocation(RabbitRenderState p_115803_) {
        switch (p_115803_.variant) {
			case BROWN:
			default:
				return RABBIT_BROWN_LOCATION;
			case WHITE:
				return RABBIT_WHITE_LOCATION;
			case BLACK:
				return RABBIT_BLACK_LOCATION;
			case WHITE_SPLOTCHED:
				return RABBIT_WHITE_LOCATION;
			case EVIL:
				return RABBIT_EVIL_LOCATION;
		}
	}
}