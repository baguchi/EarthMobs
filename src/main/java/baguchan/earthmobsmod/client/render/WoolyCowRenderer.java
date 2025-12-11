package baguchan.earthmobsmod.client.render;

import baguchan.earthmobsmod.EarthMobsMod;
import baguchan.earthmobsmod.client.ModModelLayers;
import baguchan.earthmobsmod.client.model.WoolyCowModel;
import baguchan.earthmobsmod.client.render.state.WoolyCowRenderState;
import baguchan.earthmobsmod.entity.WoolyCow;
import net.minecraft.client.renderer.entity.AgeableMobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.Identifier;

public class WoolyCowRenderer<T extends WoolyCow> extends AgeableMobRenderer<T, WoolyCowRenderState, WoolyCowModel<WoolyCowRenderState>> {
    private static final Identifier TEXTURE = Identifier.fromNamespaceAndPath(EarthMobsMod.MODID, "textures/entity/wooly_cow/wooly_cow.png");
    private static final Identifier SHEARED_TEXTURE = Identifier.fromNamespaceAndPath(EarthMobsMod.MODID, "textures/entity/wooly_cow/wooly_cow_sheared.png");


	public WoolyCowRenderer(EntityRendererProvider.Context p_173952_) {
        super(p_173952_, new WoolyCowModel<>(p_173952_.bakeLayer(ModModelLayers.WOOLY_COW)), new WoolyCowModel<>(p_173952_.bakeLayer(ModModelLayers.WOOLY_COW_BABY)), 0.5F);
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
    public Identifier getTextureLocation(WoolyCowRenderState p_110775_1_) {
        return !p_110775_1_.wool ? SHEARED_TEXTURE : TEXTURE;
	}
}