package baguchan.earthmobsmod.client.render.zombie;

import baguchan.earthmobsmod.EarthMobsMod;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.PigRenderer;
import net.minecraft.client.renderer.entity.state.PigRenderState;
import net.minecraft.resources.ResourceLocation;

public class ZombifiedPigRenderer extends PigRenderer {
    private static final ResourceLocation PIG_LOCATION = ResourceLocation.fromNamespaceAndPath(EarthMobsMod.MODID, "textures/entity/zombified_pig/zombified_pig.png");

	public ZombifiedPigRenderer(EntityRendererProvider.Context p_174340_) {
		super(p_174340_);
	}

	@Override
	public ResourceLocation getTextureLocation(PigRenderState p_115697_) {
		return PIG_LOCATION;
	}
}
