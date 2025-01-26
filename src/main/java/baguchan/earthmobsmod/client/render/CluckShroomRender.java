package baguchan.earthmobsmod.client.render;

import baguchan.earthmobsmod.EarthMobsMod;
import baguchan.earthmobsmod.client.ModModelLayers;
import baguchan.earthmobsmod.client.model.CluckShroomModel;
import baguchan.earthmobsmod.client.render.state.AbstractChickenRender;
import baguchan.earthmobsmod.client.render.state.CluckShroomRenderState;
import baguchan.earthmobsmod.entity.CluckShroom;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class CluckShroomRender<T extends CluckShroom> extends AbstractChickenRender<T, CluckShroomRenderState, CluckShroomModel<CluckShroomRenderState>> {
	private static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(EarthMobsMod.MODID, "textures/entity/cluck_shroom/cluck_shroom.png");
	private static final ResourceLocation TEXTURE_BROWN = ResourceLocation.fromNamespaceAndPath(EarthMobsMod.MODID, "textures/entity/cluck_shroom/brown_cluck_shroom.png");

	public CluckShroomRender(EntityRendererProvider.Context p_173952_) {
        super(p_173952_, new CluckShroomModel<>(p_173952_.bakeLayer(ModModelLayers.CLUCK_SHROOM)), new CluckShroomModel<>(p_173952_.bakeLayer(ModModelLayers.CLUCK_SHROOM_BABY)), 0.3F);
	}

    @Override
    public CluckShroomRenderState createRenderState() {
        return new CluckShroomRenderState();
	}

	@Override
    public void extractRenderState(T p_362733_, CluckShroomRenderState p_360515_, float p_361157_) {
        super.extractRenderState(p_362733_, p_360515_, p_361157_);
        p_360515_.type = p_362733_.getCluckShroomType();
    }

    @Override
    public ResourceLocation getTextureLocation(CluckShroomRenderState p_110775_1_) {
        return p_110775_1_.type == CluckShroom.CluckShroomType.BROWN ? TEXTURE_BROWN : TEXTURE;
	}
}