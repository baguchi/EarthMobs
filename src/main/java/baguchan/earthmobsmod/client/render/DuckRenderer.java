package baguchan.earthmobsmod.client.render;

import baguchan.earthmobsmod.EarthMobsMod;
import net.minecraft.client.renderer.entity.ChickenRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.animal.Chicken;

public class DuckRenderer extends ChickenRenderer {

	private static final ResourceLocation DUCK_LOCATION = new ResourceLocation(EarthMobsMod.MODID, "textures/entity/duck.png");

	public DuckRenderer(EntityRendererProvider.Context p_173952_) {
		super(p_173952_);
	}

	public ResourceLocation getTextureLocation(Chicken p_113998_) {
		return DUCK_LOCATION;
	}

}
