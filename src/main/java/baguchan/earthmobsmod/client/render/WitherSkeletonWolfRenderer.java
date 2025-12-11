package baguchan.earthmobsmod.client.render;

import baguchan.earthmobsmod.EarthMobsMod;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.state.WolfRenderState;
import net.minecraft.resources.Identifier;

public class WitherSkeletonWolfRenderer extends SkeletonWolfRenderer {
    private static final Identifier WOLF_LOCATION = Identifier.fromNamespaceAndPath(EarthMobsMod.MODID, "textures/entity/wither_skeleton_wolf/wither_skeleton_wolf.png");
    private static final Identifier WOLF_ANGRY_LOCATION = Identifier.fromNamespaceAndPath(EarthMobsMod.MODID, "textures/entity/wither_skeleton_wolf/wither_skeleton_wolf_angry.png");


	public WitherSkeletonWolfRenderer(EntityRendererProvider.Context p_174452_) {
		super(p_174452_);
	}

	@Override
    public Identifier getTextureLocation(WolfRenderState p_116526_) {
		return p_116526_.isAngry ? WOLF_ANGRY_LOCATION : WOLF_LOCATION;
	}
}
