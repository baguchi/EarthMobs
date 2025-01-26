package baguchan.earthmobsmod.client.render;

import baguchan.earthmobsmod.client.render.layer.MelonGolemHeadLayer;
import baguchan.earthmobsmod.client.render.state.MelonGolemRenderState;
import baguchan.earthmobsmod.entity.MelonGolem;
import net.minecraft.client.model.SnowGolemModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class MelonGolemRenderer extends MobRenderer<MelonGolem, MelonGolemRenderState, SnowGolemModel> {
	private static final ResourceLocation SNOW_GOLEM_LOCATION = ResourceLocation.withDefaultNamespace("textures/entity/snow_golem.png");

	public MelonGolemRenderer(EntityRendererProvider.Context p_174393_) {
        super(p_174393_, new SnowGolemModel(p_174393_.bakeLayer(ModelLayers.SNOW_GOLEM)), 0.5F);
        this.addLayer(new MelonGolemHeadLayer(this, p_174393_.getBlockRenderDispatcher()));
    }

    @Override
    public MelonGolemRenderState createRenderState() {
        return new MelonGolemRenderState();
    }

    public void extractRenderState(MelonGolem p_364437_, MelonGolemRenderState p_388288_, float p_364064_) {
        super.extractRenderState(p_364437_, p_388288_, p_364064_);
        p_388288_.hasPumpkin = p_364437_.hasMelon();
	}

    @Override
    public ResourceLocation getTextureLocation(MelonGolemRenderState p_368654_) {
		return SNOW_GOLEM_LOCATION;
	}
}