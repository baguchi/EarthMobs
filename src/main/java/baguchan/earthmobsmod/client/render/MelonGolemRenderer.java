package baguchan.earthmobsmod.client.render;

import baguchan.earthmobsmod.client.render.layer.MelonGolemHeadLayer;
import baguchan.earthmobsmod.entity.MelonGolem;
import net.minecraft.client.model.SnowGolemModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class MelonGolemRenderer extends MobRenderer<MelonGolem, SnowGolemModel<MelonGolem>> {
	private static final ResourceLocation SNOW_GOLEM_LOCATION = new ResourceLocation("textures/entity/snow_golem.png");

	public MelonGolemRenderer(EntityRendererProvider.Context p_174393_) {
		super(p_174393_, new SnowGolemModel<>(p_174393_.bakeLayer(ModelLayers.SNOW_GOLEM)), 0.5F);
		this.addLayer(new MelonGolemHeadLayer(this));
	}

	public ResourceLocation getTextureLocation(MelonGolem p_115993_) {
		return SNOW_GOLEM_LOCATION;
	}
}