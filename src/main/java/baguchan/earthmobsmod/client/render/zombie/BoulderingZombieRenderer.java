package baguchan.earthmobsmod.client.render.zombie;

import bagu_chan.bagus_lib.client.layer.CustomArmorLayer;
import baguchan.earthmobsmod.EarthMobsMod;
import baguchan.earthmobsmod.client.ModModelLayers;
import baguchan.earthmobsmod.client.model.BoulderingZombieModel;
import baguchan.earthmobsmod.entity.BoulderingZombie;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class BoulderingZombieRenderer<T extends BoulderingZombie> extends MobRenderer<T, BoulderingZombieModel<T>> {
	private static final ResourceLocation LOCATION = ResourceLocation.fromNamespaceAndPath(EarthMobsMod.MODID, "textures/entity/bouldering_zombie/bouldering_zombie.png");

	public BoulderingZombieRenderer(EntityRendererProvider.Context p_173964_) {
		super(p_173964_, new BoulderingZombieModel<>(p_173964_.bakeLayer(ModModelLayers.BOULDERING_ZOMBIE)), 0.5F);
		this.addLayer(new CustomArmorLayer<>(this, p_173964_));
	}

	public ResourceLocation getTextureLocation(T p_114115_) {
		return LOCATION;
	}
}
