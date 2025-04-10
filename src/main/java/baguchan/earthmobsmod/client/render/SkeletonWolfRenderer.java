package baguchan.earthmobsmod.client.render;

import baguchan.earthmobsmod.EarthMobsMod;
import baguchan.earthmobsmod.client.ModModelLayers;
import net.minecraft.client.model.WolfModel;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.renderer.entity.AgeableMobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.layers.WolfArmorLayer;
import net.minecraft.client.renderer.entity.layers.WolfCollarLayer;
import net.minecraft.client.renderer.entity.state.WolfRenderState;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.ARGB;
import net.minecraft.world.entity.animal.wolf.Wolf;

public class SkeletonWolfRenderer extends AgeableMobRenderer<Wolf, WolfRenderState, WolfModel> {
    private static final ResourceLocation WOLF_LOCATION = ResourceLocation.fromNamespaceAndPath(EarthMobsMod.MODID, "textures/entity/skeleton_wolf/skeleton_wolf.png");
    private static final ResourceLocation WOLF_ANGRY_LOCATION = ResourceLocation.fromNamespaceAndPath(EarthMobsMod.MODID, "textures/entity/skeleton_wolf/skeleton_wolf_angry.png");

	public SkeletonWolfRenderer(EntityRendererProvider.Context p_174452_) {
		super(p_174452_, new WolfModel(p_174452_.bakeLayer(ModModelLayers.SKELETON_WOLF)), new WolfModel(p_174452_.bakeLayer(ModModelLayers.SKELETON_WOLF_BABY)), 0.5F);
		this.addLayer(new WolfArmorLayer(this, p_174452_.getModelSet(), p_174452_.getEquipmentRenderer()));
		this.addLayer(new WolfCollarLayer(this));
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();


		PartDefinition partdefinition1 = partdefinition.addOrReplaceChild("head", CubeListBuilder.create(), PartPose.offset(-1.0F, 13.5F, -7.0F));
		partdefinition1.addOrReplaceChild("real_head", CubeListBuilder.create().texOffs(0, 0).addBox(-2.0F, -3.0F, -2.0F, 6.0F, 6.0F, 4.0F, new CubeDeformation(0.0F)).texOffs(16, 14).addBox(-2.0F, -5.0F, 0.0F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(16, 14).addBox(2.0F, -5.0F, 0.0F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(0, 10).addBox(-0.5F, -0.001F, -5.0F, 3.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.ZERO);

		PartDefinition mane = partdefinition.addOrReplaceChild("mane", CubeListBuilder.create().texOffs(21, 0).addBox(-3.0F, -3.0F, -3.0F, 8.0F, 6.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, 14.0F, -3.0F, 1.5708F, 0.0F, 0.0F));

		partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(18, 14).addBox(-3.0F, -2.0F, -3.0F, 6.0F, 9.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 14.0F, 2.0F, ((float) Math.PI / 2F), 0.0F, 0.0F));
		partdefinition.addOrReplaceChild("upper_body", CubeListBuilder.create().texOffs(21, 0).addBox(-3.0F, -3.0F, -3.0F, 8.0F, 6.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, 14.0F, -3.0F, ((float) Math.PI / 2F), 0.0F, 0.0F));

		PartDefinition right_hind_leg = partdefinition.addOrReplaceChild("right_hind_leg", CubeListBuilder.create().texOffs(0, 18).addBox(0.0F, 0.0F, -1.0F, 2.0F, 8.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-2.5F, 16.0F, 7.0F));

		PartDefinition left_hind_leg = partdefinition.addOrReplaceChild("left_hind_leg", CubeListBuilder.create().texOffs(0, 18).mirror().addBox(0.0F, 0.0F, -1.0F, 2.0F, 8.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.5F, 16.0F, 7.0F));

		PartDefinition right_front_leg = partdefinition.addOrReplaceChild("right_front_leg", CubeListBuilder.create().texOffs(0, 18).addBox(0.0F, 0.0F, -1.0F, 2.0F, 8.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-2.5F, 16.0F, -4.0F));

		PartDefinition left_front_leg = partdefinition.addOrReplaceChild("left_front_leg", CubeListBuilder.create().texOffs(0, 18).mirror().addBox(0.0F, 0.0F, -1.0F, 2.0F, 8.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.5F, 16.0F, -4.0F));


		PartDefinition partdefinition2 = partdefinition.addOrReplaceChild("tail", CubeListBuilder.create(), PartPose.offsetAndRotation(-1.0F, 12.0F, 9.0F, ((float) Math.PI / 5F), 0.0F, 0.0F));
		partdefinition2.addOrReplaceChild("real_tail", CubeListBuilder.create().texOffs(9, 18).addBox(0.0F, 0.0F, -1.0F, 2.0F, 8.0F, 2.0F, new CubeDeformation(0)), PartPose.ZERO);

		return LayerDefinition.create(meshdefinition, 64, 32);
	}

	protected int getModelTint(WolfRenderState p_365181_) {
		float f = p_365181_.wetShade;
		return f == 1.0F ? -1 : ARGB.colorFromFloat(1.0F, f, f, f);
	}

	@Override
	public ResourceLocation getTextureLocation(WolfRenderState p_116526_) {
		return p_116526_.isAngry ? WOLF_ANGRY_LOCATION : WOLF_LOCATION;
	}

	public WolfRenderState createRenderState() {
		return new WolfRenderState();
	}

	public void extractRenderState(Wolf p_406305_, WolfRenderState p_363549_, float p_362105_) {
		super.extractRenderState(p_406305_, p_363549_, p_362105_);
		p_363549_.isAngry = p_406305_.isAngry();
		p_363549_.isSitting = p_406305_.isInSittingPose();
		p_363549_.tailAngle = p_406305_.getTailAngle();
		p_363549_.headRollAngle = p_406305_.getHeadRollAngle(p_362105_);
		p_363549_.shakeAnim = p_406305_.getShakeAnim(p_362105_);
		p_363549_.texture = p_406305_.getTexture();
		p_363549_.wetShade = p_406305_.getWetShade(p_362105_);
		p_363549_.collarColor = p_406305_.isTame() ? p_406305_.getCollarColor() : null;
		p_363549_.bodyArmorItem = p_406305_.getBodyArmorItem().copy();
	}
}