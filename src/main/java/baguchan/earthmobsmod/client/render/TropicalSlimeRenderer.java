package baguchan.earthmobsmod.client.render;

import baguchan.earthmobsmod.EarthMobsMod;
import baguchan.earthmobsmod.entity.TropicalSlime;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.ColorableHierarchicalModel;
import net.minecraft.client.model.SlimeModel;
import net.minecraft.client.model.TropicalFishModelA;
import net.minecraft.client.model.TropicalFishModelB;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.SlimeOuterLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.monster.Slime;
import net.minecraft.world.item.DyeColor;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import static baguchan.earthmobsmod.entity.TropicalSlime.TAG_FISH_LIST;
import static baguchan.earthmobsmod.entity.TropicalSlime.TAG_FISH_VARIANT;

@OnlyIn(Dist.CLIENT)
public class TropicalSlimeRenderer extends MobRenderer<Slime, SlimeModel<Slime>> {
	private static final ResourceLocation SLIME_LOCATION = new ResourceLocation(EarthMobsMod.MODID, "textures/entity/tropical_slime/tropical_slime.png");

	private static final ResourceLocation[] BASE_TEXTURE_LOCATIONS = new ResourceLocation[]{new ResourceLocation("textures/entity/fish/tropical_a.png"), new ResourceLocation("textures/entity/fish/tropical_b.png")};
	private static final ResourceLocation[] PATTERN_A_TEXTURE_LOCATIONS = new ResourceLocation[]{new ResourceLocation("textures/entity/fish/tropical_a_pattern_1.png"), new ResourceLocation("textures/entity/fish/tropical_a_pattern_2.png"), new ResourceLocation("textures/entity/fish/tropical_a_pattern_3.png"), new ResourceLocation("textures/entity/fish/tropical_a_pattern_4.png"), new ResourceLocation("textures/entity/fish/tropical_a_pattern_5.png"), new ResourceLocation("textures/entity/fish/tropical_a_pattern_6.png")};
	private static final ResourceLocation[] PATTERN_B_TEXTURE_LOCATIONS = new ResourceLocation[]{new ResourceLocation("textures/entity/fish/tropical_b_pattern_1.png"), new ResourceLocation("textures/entity/fish/tropical_b_pattern_2.png"), new ResourceLocation("textures/entity/fish/tropical_b_pattern_3.png"), new ResourceLocation("textures/entity/fish/tropical_b_pattern_4.png"), new ResourceLocation("textures/entity/fish/tropical_b_pattern_5.png"), new ResourceLocation("textures/entity/fish/tropical_b_pattern_6.png")};


	private final ColorableHierarchicalModel modelA;
	private final ColorableHierarchicalModel modelB;
	private final ColorableHierarchicalModel patternModelA;
	private final ColorableHierarchicalModel patternModelB;

	public TropicalSlimeRenderer(EntityRendererProvider.Context p_174391_) {
		super(p_174391_, new SlimeModel<>(p_174391_.bakeLayer(ModelLayers.SLIME)), 0.25F);
		this.addLayer(new SlimeOuterLayer<>(this, p_174391_.getModelSet()));

		this.modelA = new TropicalFishModelA<>(p_174391_.bakeLayer(ModelLayers.TROPICAL_FISH_SMALL));
		this.modelB = new TropicalFishModelB<>(p_174391_.bakeLayer(ModelLayers.TROPICAL_FISH_LARGE));
		this.patternModelA = new TropicalFishModelA<>(p_174391_.bakeLayer(ModelLayers.TROPICAL_FISH_SMALL_PATTERN));
		this.patternModelB = new TropicalFishModelB<>(p_174391_.bakeLayer(ModelLayers.TROPICAL_FISH_LARGE_PATTERN));
	}

	public void render(Slime p_115976_, float p_115977_, float p_115978_, PoseStack p_115979_, MultiBufferSource p_115980_, int p_115981_) {
		this.shadowRadius = 0.25F * (float) p_115976_.getSize();
		if (p_115976_ instanceof TropicalSlime) {
			renderFish((TropicalSlime) p_115976_, p_115978_, p_115979_, p_115980_, p_115981_);
		}
        super.render(p_115976_, p_115977_, p_115978_, p_115979_, p_115980_, p_115981_);
	}

	/*
	 * copy form vanilla code
	 */
	public static int getBaseVariant(int p_30059_) {
		return Math.min(p_30059_ & 255, 1);
	}

	private static int getBaseColorIdx(int p_30061_) {
		return (p_30061_ & 16711680) >> 16;
	}

	public float[] getBaseColor(int variant) {
		return DyeColor.byId(getBaseColorIdx(variant)).getTextureDiffuseColors();
	}

	private static int getPatternColorIdx(int p_30063_) {
		return (p_30063_ & -16777216) >> 24;
	}

	public float[] getPatternColor(int variant) {
		return DyeColor.byId(getPatternColorIdx(variant)).getTextureDiffuseColors();
	}

	public ResourceLocation getPatternTextureLocation(int variant) {
		return getBaseVariant(variant) == 0 ? PATTERN_A_TEXTURE_LOCATIONS[getPatternVariant(variant)] : PATTERN_B_TEXTURE_LOCATIONS[getPatternVariant(variant)];
	}

	public ResourceLocation getBaseTextureLocation(int variant) {
		return BASE_TEXTURE_LOCATIONS[getBaseVariant(variant)];
	}

	private static int getPatternVariant(int p_30065_) {
		return Math.min((p_30065_ & '\uff00') >> 8, 5);
	}


	public void renderFish(TropicalSlime parent, float partialTicks, PoseStack stack, MultiBufferSource buffer, int light) {
		CompoundTag compoundTag = parent.getFishData();
		int i = parent.getSize();
		ListTag listTag = (ListTag) compoundTag.get(TAG_FISH_LIST);

        if (compoundTag != null && listTag != null) {
			float f = (float) i / 3.0F;
			for (int l = 0; l < listTag.size(); ++l) {
                double f1 = ((CompoundTag) listTag.get(l)).getDouble(TropicalSlime.TAG_FISH_POSX);
                double f2 = ((CompoundTag) listTag.get(l)).getDouble(TropicalSlime.TAG_FISH_POSZ);

				int variant = ((CompoundTag) listTag.get(l)).getInt(TAG_FISH_VARIANT);

				ColorableHierarchicalModel basemodel = getBaseVariant(((CompoundTag) listTag.get(l)).getInt(TAG_FISH_VARIANT)) == 0 ? this.modelA : this.modelB;
				ColorableHierarchicalModel patternModel = getPatternVariant(((CompoundTag) listTag.get(l)).getInt(TAG_FISH_VARIANT)) == 0 ? this.patternModelA : this.patternModelB;


				double fishInX = (Mth.lerp(partialTicks, parent.xOld + (double) f1, parent.getX() + (double) f1) - Mth.lerp(partialTicks, parent.xOld, parent.getX()));
                double fishInY = ((CompoundTag) listTag.get(l)).getDouble(TropicalSlime.TAG_FISH_POSY);
				double fishInZ = (Mth.lerp(partialTicks, parent.zOld + (double) f2, parent.getZ() + (double) f2) - Mth.lerp(partialTicks, parent.zOld, parent.getZ()));

				stack.pushPose();
				VertexConsumer vertexConsumer = buffer.getBuffer(basemodel.renderType(this.getBaseTextureLocation(variant)));

				float[] afloat = this.getBaseColor(variant);
				basemodel.setColor(afloat[0], afloat[1], afloat[2]);

				stack.translate(fishInX, fishInY, fishInZ);
				stack.scale(-1.0F, -1.0F, 1.0F);
                stack.translate(0.0F, -1.501F, 0.0F);
				basemodel.setupAnim(parent, 0.0F, 0.0F, getBob(parent, partialTicks), 0.0F, 0.0F);
				basemodel.renderToBuffer(stack, vertexConsumer, light, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
				basemodel.setColor(1.0F, 1.0F, 1.0F);
				stack.popPose();

				stack.pushPose();
				VertexConsumer vertexConsumer2 = buffer.getBuffer(basemodel.renderType(this.getPatternTextureLocation(variant)));


				float[] afloat2 = this.getPatternColor(variant);
				patternModel.setColor(afloat2[0], afloat2[1], afloat2[2]);

				stack.translate(fishInX, fishInY, fishInZ);
				stack.scale(-1.0F, -1.0F, 1.0F);
                stack.translate(0.0F, -1.501F, 0.0F);
				patternModel.setupAnim(parent, 0.0F, 0.0F, getBob(parent, partialTicks), 0.0F, 0.0F);
				patternModel.renderToBuffer(stack, vertexConsumer2, light, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
				patternModel.setColor(1.0F, 1.0F, 1.0F);
				stack.popPose();
			}
		}
	}

	protected void scale(Slime p_115983_, PoseStack p_115984_, float p_115985_) {
		float f = 0.999F;
		p_115984_.scale(0.999F, 0.999F, 0.999F);
		p_115984_.translate(0.0D, (double) 0.001F, 0.0D);
		float f1 = (float) p_115983_.getSize();
		float f2 = Mth.lerp(p_115985_, p_115983_.oSquish, p_115983_.squish) / (f1 * 0.5F + 1.0F);
		float f3 = 1.0F / (f2 + 1.0F);
		p_115984_.scale(f3 * f1, 1.0F / f3 * f1, f3 * f1);
	}

	public ResourceLocation getTextureLocation(Slime p_115974_) {
		return SLIME_LOCATION;
	}
}