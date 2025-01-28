package baguchan.earthmobsmod.client.render;

import baguchan.earthmobsmod.EarthMobsMod;
import baguchan.earthmobsmod.client.render.layer.TropicalSlimeOuterLayer;
import baguchan.earthmobsmod.entity.TropicalSlime;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.SlimeModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.state.SlimeRenderState;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class TropicalSlimeRenderer extends MobRenderer<TropicalSlime, SlimeRenderState, SlimeModel> {
	public static final ResourceLocation SLIME_LOCATION = ResourceLocation.fromNamespaceAndPath(EarthMobsMod.MODID, "textures/entity/tropical_slime/tropical_slime.png");

	private static final ResourceLocation[] BASE_TEXTURE_LOCATIONS = new ResourceLocation[]{ResourceLocation.withDefaultNamespace("textures/entity/fish/tropical_a.png"), ResourceLocation.withDefaultNamespace("textures/entity/fish/tropical_b.png")};
	private static final ResourceLocation[] PATTERN_A_TEXTURE_LOCATIONS = new ResourceLocation[]{ResourceLocation.withDefaultNamespace("textures/entity/fish/tropical_a_pattern_1.png"), ResourceLocation.withDefaultNamespace("textures/entity/fish/tropical_a_pattern_2.png"), ResourceLocation.withDefaultNamespace("textures/entity/fish/tropical_a_pattern_3.png"), ResourceLocation.withDefaultNamespace("textures/entity/fish/tropical_a_pattern_4.png"), ResourceLocation.withDefaultNamespace("textures/entity/fish/tropical_a_pattern_5.png"), ResourceLocation.withDefaultNamespace("textures/entity/fish/tropical_a_pattern_6.png")};
	private static final ResourceLocation[] PATTERN_B_TEXTURE_LOCATIONS = new ResourceLocation[]{ResourceLocation.withDefaultNamespace("textures/entity/fish/tropical_b_pattern_1.png"), ResourceLocation.withDefaultNamespace("textures/entity/fish/tropical_b_pattern_2.png"), ResourceLocation.withDefaultNamespace("textures/entity/fish/tropical_b_pattern_3.png"), ResourceLocation.withDefaultNamespace("textures/entity/fish/tropical_b_pattern_4.png"), ResourceLocation.withDefaultNamespace("textures/entity/fish/tropical_b_pattern_5.png"), ResourceLocation.withDefaultNamespace("textures/entity/fish/tropical_b_pattern_6.png")};

	public TropicalSlimeRenderer(EntityRendererProvider.Context p_174391_) {
		super(p_174391_, new SlimeModel(p_174391_.bakeLayer(ModelLayers.SLIME)), 0.25F);
		this.addLayer(new TropicalSlimeOuterLayer(this, p_174391_.getModelSet()));
	}

	@Override
	protected float getShadowRadius(SlimeRenderState p_383137_) {
		return (float) p_383137_.size * 0.25F;
	}

	@Override
	protected void scale(SlimeRenderState p_364158_, PoseStack p_115964_) {
		float f = 0.999F;
		p_115964_.scale(0.999F, 0.999F, 0.999F);
		p_115964_.translate(0.0F, 0.001F, 0.0F);
		float f1 = (float) p_364158_.size;
		float f2 = p_364158_.squish / (f1 * 0.5F + 1.0F);
		float f3 = 1.0F / (f2 + 1.0F);
		p_115964_.scale(f3 * f1, 1.0F / f3 * f1, f3 * f1);
	}

	@Override
	public SlimeRenderState createRenderState() {
		return new SlimeRenderState();
	}

	@Override
	public void extractRenderState(TropicalSlime p_362664_, SlimeRenderState p_365237_, float p_361099_) {
		super.extractRenderState(p_362664_, p_365237_, p_361099_);
		p_365237_.squish = Mth.lerp(p_361099_, p_362664_.oSquish, p_362664_.squish);
		p_365237_.size = p_362664_.getSize();
	}

	public ResourceLocation getTextureLocation(SlimeRenderState p_115974_) {
		return SLIME_LOCATION;
	}
}