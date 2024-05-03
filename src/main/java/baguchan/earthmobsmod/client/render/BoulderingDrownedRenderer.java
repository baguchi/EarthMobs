package baguchan.earthmobsmod.client.render;

import baguchan.earthmobsmod.EarthMobsMod;
import baguchan.earthmobsmod.client.ModModelLayers;
import baguchan.earthmobsmod.client.model.BoulderingDrownedModel;
import baguchan.earthmobsmod.client.render.layer.SpinAttackEffectLayer;
import baguchan.earthmobsmod.client.render.layer.ZombieOuterLayer;
import baguchan.earthmobsmod.entity.BoulderingDrowned;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.model.ZombieModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.AbstractZombieRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.monster.Zombie;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class BoulderingDrownedRenderer extends AbstractZombieRenderer<BoulderingDrowned, ZombieModel<BoulderingDrowned>> {
	private static final ResourceLocation LOCATION = new ResourceLocation(EarthMobsMod.MODID, "textures/entity/bouldering_zombie/bouldering_drowned.png");
	private static final ResourceLocation OUTER_LOCATION = new ResourceLocation(EarthMobsMod.MODID, "textures/entity/bouldering_zombie/bouldering_drowned_outer_layer.png");

	public BoulderingDrownedRenderer(EntityRendererProvider.Context p_173964_) {
		super(p_173964_, new BoulderingDrownedModel<>(p_173964_.bakeLayer(ModModelLayers.BOULDERING_DROWNED)), new BoulderingDrownedModel<>(p_173964_.bakeLayer(ModelLayers.ZOMBIE_INNER_ARMOR)), new BoulderingDrownedModel<>(p_173964_.bakeLayer(ModelLayers.ZOMBIE_OUTER_ARMOR)));
		this.addLayer(new ZombieOuterLayer<>(this, new BoulderingDrownedModel<>(p_173964_.bakeLayer(ModModelLayers.BOULDERING_DROWNED_OUTER)), OUTER_LOCATION));
		this.addLayer(new SpinAttackEffectLayer<>(this, p_173964_.getModelSet()))
		;
	}

	@Override
	protected void setupRotations(BoulderingDrowned p_115317_, PoseStack p_114110_, float p_114111_, float p_114112_, float p_114113_, float p_319953_) {
		super.setupRotations(p_115317_, p_114110_, p_114111_, p_114112_, p_114113_, p_319953_);
		float f = p_115317_.getSwimAmount(p_114113_);
		if (f > 0.0F) {
			float f1 = -10.0F - p_115317_.getXRot();
			float f2 = Mth.lerp(f, 0.0F, f1);
			p_114110_.rotateAround(Axis.XP.rotationDegrees(f2), 0.0F, p_115317_.getBbHeight() / 2.0F / p_319953_, 0.0F);
		}
	}

	public ResourceLocation getTextureLocation(Zombie p_114115_) {
		return LOCATION;
	}
}
