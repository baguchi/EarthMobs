package baguchan.earthmobsmod.client.render;

import baguchan.earthmobsmod.EarthMobsMod;
import baguchan.earthmobsmod.client.ModModelLayers;
import baguchan.earthmobsmod.client.model.FancyChickenModel;
import baguchan.earthmobsmod.client.render.state.AbstractChickenRender;
import baguchan.earthmobsmod.entity.FancyChicken;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.state.ChickenRenderState;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class FancyChickenRenderer<T extends FancyChicken> extends AbstractChickenRender<T, ChickenRenderState, FancyChickenModel<ChickenRenderState>> {
    private static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(EarthMobsMod.MODID, "textures/entity/fancy_chicken.png");

	public FancyChickenRenderer(EntityRendererProvider.Context p_173952_) {
        super(p_173952_, new FancyChickenModel<>(p_173952_.bakeLayer(ModModelLayers.FANCY_CHICKEN)), new FancyChickenModel<>(p_173952_.bakeLayer(ModModelLayers.FANCY_CHICKEN_BABY)), 0.3F);
	}

	@Override
    public ChickenRenderState createRenderState() {
        return new ChickenRenderState();
    }

    @Override
    public ResourceLocation getTextureLocation(ChickenRenderState p_110775_1_) {
		return TEXTURE;
	}
}