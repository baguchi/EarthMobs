package baguchan.earthmobsmod.client.render;

import baguchan.earthmobsmod.EarthMobsMod;
import baguchan.earthmobsmod.client.model.WoolyCowModel;
import baguchan.earthmobsmod.client.render.state.WoolyCowRenderState;
import baguchan.earthmobsmod.entity.UmbraCow;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class UmbraCowRenderer<T extends UmbraCow> extends MobRenderer<T, WoolyCowRenderState, WoolyCowModel<WoolyCowRenderState>> {
    private static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(EarthMobsMod.MODID, "textures/entity/umbra_cow/umbra_cow.png");
    private static final ResourceLocation SHEARED_TEXTURE = ResourceLocation.fromNamespaceAndPath(EarthMobsMod.MODID, "textures/entity/umbra_cow/umbra_cow_sheared.png");


	public UmbraCowRenderer(EntityRendererProvider.Context p_173952_) {
		super(p_173952_, new WoolyCowModel<>(p_173952_.bakeLayer(ModelLayers.COW)), 0.5F);
	}

    @Override
    public WoolyCowRenderState createRenderState() {
        return new WoolyCowRenderState();
    }

	@Override
    public void extractRenderState(T p_362733_, WoolyCowRenderState p_360515_, float p_361157_) {
        super.extractRenderState(p_362733_, p_360515_, p_361157_);
        p_360515_.wool = !p_362733_.isSheared();
        p_360515_.headEatAngleScale = p_362733_.getHeadEatAngleScale(p_361157_);
        p_360515_.headEatPositionScale = p_362733_.getHeadEatPositionScale(p_361157_);
    }

    @Override
    public ResourceLocation getTextureLocation(WoolyCowRenderState p_110775_1_) {
        return p_110775_1_.wool ? SHEARED_TEXTURE : TEXTURE;
	}
}