package baguchan.earthmobsmod.client.render;

import baguchan.earthmobsmod.EarthMobsMod;
import baguchan.earthmobsmod.client.ModModelLayers;
import baguchan.earthmobsmod.client.model.HyperRabbitModel;
import baguchan.earthmobsmod.entity.HyperRabbit;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.ChatFormatting;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class HyperRabbitRenderer<T extends HyperRabbit> extends MobRenderer<T, HyperRabbitModel<T>> {
	private static final ResourceLocation RABBIT_BROWN_LOCATION = new ResourceLocation(EarthMobsMod.MODID, "textures/entity/hyper_rabbit/brown.png");
	private static final ResourceLocation RABBIT_WHITE_LOCATION = new ResourceLocation(EarthMobsMod.MODID, "textures/entity/hyper_rabbit/white.png");
	private static final ResourceLocation RABBIT_GOLD_LOCATION = new ResourceLocation(EarthMobsMod.MODID, "textures/entity/hyper_rabbit/gold.png");
	private static final ResourceLocation RABBIT_SA_X_LOCATION = new ResourceLocation(EarthMobsMod.MODID, "textures/entity/hyper_rabbit/hr_x.png");

	public HyperRabbitRenderer(EntityRendererProvider.Context p_173952_) {
		super(p_173952_, new HyperRabbitModel<>(p_173952_.bakeLayer(ModModelLayers.HYPER_RABBIT)), 0.3F);
	}

	@Override
	protected void scale(T p_115314_, PoseStack p_115315_, float p_115316_) {
		float scale = p_115314_.isBaby() ? 0.4F : 0.6F;
		p_115315_.scale(scale, scale, scale);
		super.scale(p_115314_, p_115315_, p_115316_);
	}

	@Override
	public ResourceLocation getTextureLocation(T p_115803_) {
		String s = ChatFormatting.stripFormatting(p_115803_.getName().getString());

		switch (p_115803_.getVariant()) {
			case BROWN:
			default:
				return RABBIT_BROWN_LOCATION;
			case WHITE, SALT:
				return RABBIT_WHITE_LOCATION;
			case GOLD:
				return RABBIT_GOLD_LOCATION;
			case EVIL:
				return RABBIT_SA_X_LOCATION;
		}
	}

	@Override
	protected boolean isShaking(T p_115304_) {
		return super.isShaking(p_115304_) || p_115304_.isSpark();
	}
}