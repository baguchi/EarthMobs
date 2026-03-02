package baguchan.earthmobsmod.client.render;

import baguchan.earthmobsmod.client.render.layer.MelonGolemHeadLayer;
import baguchan.earthmobsmod.client.render.state.MelonGolemRenderState;
import baguchan.earthmobsmod.entity.MelonGolem;
import net.minecraft.client.model.animal.golem.SnowGolemModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.block.BlockModelResolver;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.block.Blocks;


public class MelonGolemRenderer extends MobRenderer<MelonGolem, MelonGolemRenderState, SnowGolemModel> {
    private static final Identifier SNOW_GOLEM_LOCATION = Identifier.withDefaultNamespace("textures/entity/snow_golem.png");
    private final BlockModelResolver blockModelResolver;

    public MelonGolemRenderer(EntityRendererProvider.Context context) {
        super(context, new SnowGolemModel(context.bakeLayer(ModelLayers.SNOW_GOLEM)), 0.5F);
        this.blockModelResolver = context.getBlockModelResolver();
        this.addLayer(new MelonGolemHeadLayer(this));
    }

    @Override
    public MelonGolemRenderState createRenderState() {
        return new MelonGolemRenderState();
    }

    @Override
    public void extractRenderState(MelonGolem entity, MelonGolemRenderState state, float p_364064_) {
        super.extractRenderState(entity, state, p_364064_);
        if (entity.hasMelon()) {
            this.blockModelResolver.update(state.headBlock, Blocks.CARVED_PUMPKIN.defaultBlockState());
        } else {
            state.headBlock.clear();
        }
        state.aggressive = entity.isAggressive();
	}

    @Override
    public Identifier getTextureLocation(MelonGolemRenderState p_368654_) {
		return SNOW_GOLEM_LOCATION;
	}
}