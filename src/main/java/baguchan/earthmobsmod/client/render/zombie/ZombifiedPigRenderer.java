package baguchan.earthmobsmod.client.render.zombie;

import baguchan.earthmobsmod.EarthMobsMod;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.PigRenderer;
import net.minecraft.client.renderer.entity.state.PigRenderState;
import net.minecraft.resources.Identifier;

public class ZombifiedPigRenderer extends PigRenderer {
    private static final Identifier PIG_LOCATION = Identifier.fromNamespaceAndPath(EarthMobsMod.MODID, "textures/entity/zombified_pig/zombified_pig.png");
	private static final Identifier PIG_BABY_LOCATION = Identifier.fromNamespaceAndPath(EarthMobsMod.MODID, "textures/entity/zombified_pig/zombified_pig_baby.png");

	public ZombifiedPigRenderer(EntityRendererProvider.Context p_174340_) {
		super(p_174340_);
	}

	@Override
    public Identifier getTextureLocation(PigRenderState p_115697_) {
		if (p_115697_.isBaby) {
			return PIG_BABY_LOCATION;
		}
		return PIG_LOCATION;
	}
}
