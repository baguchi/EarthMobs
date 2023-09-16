package baguchan.earthmobsmod.client.render;

import baguchan.earthmobsmod.EarthMobsMod;
import baguchan.earthmobsmod.client.ModModelLayers;
import baguchan.earthmobsmod.client.model.VilerWitchModel;
import baguchan.earthmobsmod.client.render.layer.VilerWitchItemLayer;
import baguchan.earthmobsmod.entity.VilerWitch;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class VilerWitchRenderer<T extends VilerWitch> extends MobRenderer<T, VilerWitchModel<T>> {
	private static final ResourceLocation TEXTURE = new ResourceLocation(EarthMobsMod.MODID, "textures/entity/viler_witch.png");

	public VilerWitchRenderer(EntityRendererProvider.Context p_173952_) {
		super(p_173952_, new VilerWitchModel<>(p_173952_.bakeLayer(ModModelLayers.VILER_WITCH)), 0.5F);
		this.addLayer(new VilerWitchItemLayer<>(this, p_173952_.getItemInHandRenderer()));
	}

	public void render(T p_116412_, float p_116413_, float p_116414_, PoseStack p_116415_, MultiBufferSource p_116416_, int p_116417_) {
		this.model.setHoldingItem(!p_116412_.getMainHandItem().isEmpty());
		super.render(p_116412_, p_116413_, p_116414_, p_116415_, p_116416_, p_116417_);
	}


	@Override
	public ResourceLocation getTextureLocation(T p_110775_1_) {
		return TEXTURE;
	}
}