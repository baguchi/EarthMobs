package baguchan.earthmobsmod.client.render;

import baguchan.earthmobsmod.EarthMobsMod;
import baguchan.earthmobsmod.client.EarthRenderType;
import baguchan.earthmobsmod.client.ModModelLayers;
import baguchan.earthmobsmod.client.render.layer.TropicalSlimeOuterLayer;
import baguchan.earthmobsmod.client.render.state.TropicalSlimeRenderState;
import baguchan.earthmobsmod.entity.TropicalSlime;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.model.monster.slime.SlimeModel;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.rendertype.RenderType;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.client.renderer.state.CameraRenderState;
import net.minecraft.resources.Identifier;
import net.minecraft.util.Mth;
import org.jetbrains.annotations.Nullable;


public class TropicalSlimeRenderer extends MobRenderer<TropicalSlime, TropicalSlimeRenderState, SlimeModel> {
    public static final Identifier SLIME_LOCATION = Identifier.fromNamespaceAndPath(EarthMobsMod.MODID, "textures/entity/tropical_slime/tropical_slime.png");
    public static final Identifier NO_FISH_LOCATION = Identifier.fromNamespaceAndPath(EarthMobsMod.MODID, "textures/entity/tropical_slime/tropical_slime_no_fish.png");
    public static final Identifier SLIME_OUTER_LOCATION = Identifier.fromNamespaceAndPath(EarthMobsMod.MODID, "textures/entity/tropical_slime/tropical_slime_outer.png");

    public final SlimeModel noFishSlimeModel;
    public final SlimeModel fishSlimeModel;

    public TropicalSlimeRenderer(EntityRendererProvider.Context p_174391_) {
		super(p_174391_, new SlimeModel(p_174391_.bakeLayer(ModModelLayers.TROPICAL_SLIME)), 0.25F);
		this.addLayer(new TropicalSlimeOuterLayer(this, p_174391_.getModelSet()));
        this.noFishSlimeModel = new SlimeModel(p_174391_.bakeLayer(ModModelLayers.TROPICAL_SLIME_STABLE));
        this.fishSlimeModel = new SlimeModel(p_174391_.bakeLayer(ModModelLayers.TROPICAL_SLIME));
    }

    @Override
    public void submit(TropicalSlimeRenderState p_433493_, PoseStack p_434615_, SubmitNodeCollector p_433768_, CameraRenderState p_450931_) {
        SlimeModel var10001;
        if (p_433493_.fish) {
            var10001 = this.fishSlimeModel;
        } else {
            var10001 = this.noFishSlimeModel;
        }

        this.model = var10001;

        super.submit(p_433493_, p_434615_, p_433768_, p_450931_);
    }

    public static LayerDefinition createInnerBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();
		partdefinition.addOrReplaceChild("cube", CubeListBuilder.create().texOffs(0, 40).addBox(-6.0F, 14.0F, -6.0F, 12.0F, 12.0F, 12.0F, new CubeDeformation(-2.8F)), PartPose.ZERO);
		partdefinition.addOrReplaceChild("right_eye", CubeListBuilder.create().texOffs(50, 34).addBox(-3.25F, 18.0F, -3.5F, 2.0F, 2.0F, 2.0F), PartPose.ZERO);
		partdefinition.addOrReplaceChild("left_eye", CubeListBuilder.create().texOffs(50, 42).addBox(1.25F, 18.0F, -3.5F, 2.0F, 2.0F, 2.0F), PartPose.ZERO);
		partdefinition.addOrReplaceChild("mouth", CubeListBuilder.create().texOffs(49, 49).addBox(0.0F, 21.0F, -3.5F, 1.0F, 1.0F, 1.0F), PartPose.ZERO);
		return LayerDefinition.create(meshdefinition, 64, 3072);
	}

    public static LayerDefinition createStableInnerBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();
        partdefinition.addOrReplaceChild("cube", CubeListBuilder.create().texOffs(0, 40).addBox(-6.0F, 14.0F, -6.0F, 12.0F, 12.0F, 12.0F, new CubeDeformation(-2.8F)), PartPose.ZERO);
        partdefinition.addOrReplaceChild("right_eye", CubeListBuilder.create().texOffs(50, 34).addBox(-3.25F, 18.0F, -3.5F, 2.0F, 2.0F, 2.0F), PartPose.ZERO);
        partdefinition.addOrReplaceChild("left_eye", CubeListBuilder.create().texOffs(50, 42).addBox(1.25F, 18.0F, -3.5F, 2.0F, 2.0F, 2.0F), PartPose.ZERO);
        partdefinition.addOrReplaceChild("mouth", CubeListBuilder.create().texOffs(49, 49).addBox(0.0F, 21.0F, -3.5F, 1.0F, 1.0F, 1.0F), PartPose.ZERO);
        return LayerDefinition.create(meshdefinition, 64, 64);
    }

	@Override
    protected @Nullable RenderType getRenderType(TropicalSlimeRenderState p_360858_, boolean p_115323_, boolean p_115324_, boolean p_115325_) {

		if (!p_115324_ && p_115323_) {
            if (!p_360858_.fish) {
                return RenderTypes.entityCutoutNoCull(NO_FISH_LOCATION);
            }
			return EarthRenderType.entityAnimationWithAllTexture(TropicalSlimeRenderer.SLIME_LOCATION, 100, 48, (int) (p_360858_.ageInTicks - p_360858_.partialTick));
		}

		return super.getRenderType(p_360858_, p_115323_, p_115324_, p_115325_);
	}

	@Override
    protected float getShadowRadius(TropicalSlimeRenderState p_383137_) {
		return (float) p_383137_.size * 0.25F;
	}

	@Override
    protected void scale(TropicalSlimeRenderState p_364158_, PoseStack p_115964_) {
		float f = 0.999F;
		p_115964_.scale(0.999F, 0.999F, 0.999F);
		p_115964_.translate(0.0F, 0.001F, 0.0F);
		float f1 = (float) p_364158_.size;
		float f2 = p_364158_.squish / (f1 * 0.5F + 1.0F);
		float f3 = 1.0F / (f2 + 1.0F);
		p_115964_.scale(f3 * f1, 1.0F / f3 * f1, f3 * f1);
	}

	@Override
    public TropicalSlimeRenderState createRenderState() {
        return new TropicalSlimeRenderState();
	}

	@Override
    public void extractRenderState(TropicalSlime p_362664_, TropicalSlimeRenderState p_365237_, float p_361099_) {
		super.extractRenderState(p_362664_, p_365237_, p_361099_);
		p_365237_.squish = Mth.lerp(p_361099_, p_362664_.oSquish, p_362664_.squish);
		p_365237_.size = p_362664_.getSize();
        p_365237_.fish = p_362664_.hasFish();
	}

    public Identifier getTextureLocation(TropicalSlimeRenderState p_115974_) {
		return SLIME_LOCATION;
	}
}