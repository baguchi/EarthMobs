package baguchan.earthmobsmod.client.render;

import baguchan.earthmobsmod.EarthMobsMod;
import baguchan.earthmobsmod.client.model.WoolyCowModel;
import baguchan.earthmobsmod.entity.WoolyCow;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class WoolyCowRenderer<T extends WoolyCow> extends MobRenderer<T, WoolyCowModel<T>> {
	private static final ResourceLocation TEXTURE = new ResourceLocation(EarthMobsMod.MODID, "textures/entity/wooly_cow/wooly_cow.png");
	private static final ResourceLocation SHEARED_TEXTURE = new ResourceLocation(EarthMobsMod.MODID, "textures/entity/wooly_cow/wooly_cow_sheared.png");


	public WoolyCowRenderer(EntityRendererProvider.Context p_173952_) {
		super(p_173952_, new WoolyCowModel<>(p_173952_.bakeLayer(ModelLayers.COW)), 0.5F);
	}


	@Override
	public ResourceLocation getTextureLocation(T p_110775_1_) {
		return p_110775_1_.isSheared() ? SHEARED_TEXTURE : TEXTURE;
	}
}