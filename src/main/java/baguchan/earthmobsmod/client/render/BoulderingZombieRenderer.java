package baguchan.earthmobsmod.client.render;

import baguchan.earthmobsmod.EarthMobsMod;
import baguchan.earthmobsmod.client.ModModelLayers;
import baguchan.earthmobsmod.client.model.BoulderingZombieModel;
import baguchan.earthmobsmod.entity.BoulderingZombie;
import net.minecraft.client.model.ZombieModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.AbstractZombieRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class BoulderingZombieRenderer extends AbstractZombieRenderer<BoulderingZombie, ZombieModel<BoulderingZombie>> {
	private static final ResourceLocation LOCATION = new ResourceLocation(EarthMobsMod.MODID, "textures/entity/bouldering_zombie/bouldering_zombie.png");

	public BoulderingZombieRenderer(EntityRendererProvider.Context p_173964_) {
		super(p_173964_, new BoulderingZombieModel<>(p_173964_.bakeLayer(ModModelLayers.BOULDERING_ZOMBIE)), new BoulderingZombieModel<>(p_173964_.bakeLayer(ModelLayers.ZOMBIE_INNER_ARMOR)), new BoulderingZombieModel<>(p_173964_.bakeLayer(ModelLayers.ZOMBIE_OUTER_ARMOR)));
	}

	public ResourceLocation getTextureLocation(Zombie p_114115_) {
		return LOCATION;
	}
}
