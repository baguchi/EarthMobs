package baguchan.earthmobsmod.client.render;

import baguchan.earthmobsmod.EarthMobsMod;
import baguchan.earthmobsmod.client.ModModelLayers;
import baguchan.earthmobsmod.client.model.HornedSheepModel;
import baguchan.earthmobsmod.client.render.layer.HornedSheepFurLayer;
import baguchan.earthmobsmod.entity.HornedSheep;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class HornedSheepRenderer extends MobRenderer<HornedSheep, HornedSheepModel<HornedSheep>> {
	private static final ResourceLocation TEXTURE = new ResourceLocation(EarthMobsMod.MODID, "textures/entity/horned_sheep.png");
    private static final ResourceLocation TEXTURE_HORNLESS = new ResourceLocation(EarthMobsMod.MODID, "textures/entity/horned_sheep_hornless.png");



	public HornedSheepRenderer(EntityRendererProvider.Context p_173952_) {
		super(p_173952_, new HornedSheepModel<>(p_173952_.bakeLayer(ModModelLayers.HORNED_SHEEP)), 0.5F);
		this.addLayer(new HornedSheepFurLayer(this, p_173952_.getModelSet()));
	}


	@Override
	public ResourceLocation getTextureLocation(HornedSheep p_110775_1_) {
        if (!p_110775_1_.hasHorn()) {
            return TEXTURE_HORNLESS;
        }
		return TEXTURE;
	}
}