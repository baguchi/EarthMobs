package baguchan.earthmobsmod.client.render;

import baguchan.earthmobsmod.EarthMobsMod;
import baguchan.earthmobsmod.client.ModModelLayers;
import baguchan.earthmobsmod.client.model.HornedSheepModel;
import baguchan.earthmobsmod.client.render.layer.HornedSheepFurLayer;
import baguchan.earthmobsmod.client.render.layer.MossSheepLayer;
import baguchan.earthmobsmod.client.render.state.HornedSheepRenderState;
import baguchan.earthmobsmod.entity.HornedSheep;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class HornedSheepRenderer extends MobRenderer<HornedSheep, HornedSheepRenderState, HornedSheepModel<HornedSheepRenderState>> {
    private static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(EarthMobsMod.MODID, "textures/entity/horned_sheep.png");
    private static final ResourceLocation TEXTURE_HORNLESS = ResourceLocation.fromNamespaceAndPath(EarthMobsMod.MODID, "textures/entity/horned_sheep_hornless.png");



	public HornedSheepRenderer(EntityRendererProvider.Context p_173952_) {
		super(p_173952_, new HornedSheepModel<>(p_173952_.bakeLayer(ModModelLayers.HORNED_SHEEP)), 0.5F);
		this.addLayer(new HornedSheepFurLayer(this, p_173952_.getModelSet()));
        this.addLayer(new MossSheepLayer(this, p_173952_.getModelSet()));
	}

	@Override
	public HornedSheepRenderState createRenderState() {
		return new HornedSheepRenderState();
	}

	@Override
	public void extractRenderState(HornedSheep p_362733_, HornedSheepRenderState p_360515_, float p_361157_) {
		super.extractRenderState(p_362733_, p_360515_, p_361157_);
		p_360515_.horn = p_362733_.hasHorn();
		p_360515_.agressiveScale = p_362733_.getAggressiveAnimationScale(p_361157_);
		p_360515_.headEatAngleScale = p_362733_.getHeadEatAngleScale(p_361157_);
		p_360515_.headEatPositionScale = p_362733_.getHeadEatPositionScale(p_361157_);
		p_360515_.isSheared = p_362733_.isSheared();
		p_360515_.woolColor = p_362733_.getColor();
		p_360515_.id = p_362733_.getId();
	}

	@Override
	public ResourceLocation getTextureLocation(HornedSheepRenderState p_110775_1_) {
		if (!p_110775_1_.horn) {
            return TEXTURE_HORNLESS;
        }
		return TEXTURE;
	}
}