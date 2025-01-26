package baguchan.earthmobsmod.client.render;

import baguchan.earthmobsmod.EarthMobsMod;
import net.minecraft.client.renderer.entity.ChickenRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.state.ChickenRenderState;
import net.minecraft.resources.ResourceLocation;

public class DuckRenderer extends ChickenRenderer {

	private static final ResourceLocation DUCK_LOCATION = ResourceLocation.fromNamespaceAndPath(EarthMobsMod.MODID, "textures/entity/duck.png");

	public DuckRenderer(EntityRendererProvider.Context p_173952_) {
		super(p_173952_);
	}

	@Override
	public ResourceLocation getTextureLocation(ChickenRenderState p_113998_) {
		return DUCK_LOCATION;
	}

}
