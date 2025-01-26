package baguchan.earthmobsmod.client.model;// Made with Blockbench 4.8.0
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


import net.minecraft.client.model.BabyModelTransform;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.renderer.entity.state.ChickenRenderState;
import net.minecraft.util.Mth;

import java.util.Set;

public class FancyChickenModel<T extends ChickenRenderState> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final MeshTransformer BABY_TRANSFORMER = new BabyModelTransform(Set.of("head"));

	private final ModelPart root;
	private final ModelPart bone;
	private final ModelPart head;
	private final ModelPart body;
	private final ModelPart rightLeg;
	private final ModelPart leftLeg;
	private final ModelPart rightWing;
	private final ModelPart leftWing;
	private final ModelPart bill;
	private final ModelPart chin;

	public FancyChickenModel(ModelPart root) {
		super(root);
		this.root = root;
		this.bone = root.getChild("bone");
		this.head = this.bone.getChild("head");
		this.bill = this.bone.getChild("bill");
		this.chin = this.bone.getChild("chin");
		this.body = this.bone.getChild("body");
		this.rightLeg = this.bone.getChild("right_leg");
		this.leftLeg = this.bone.getChild("left_leg");
		this.rightWing = this.bone.getChild("right_wing");
		this.leftWing = this.bone.getChild("left_wing");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition bone = partdefinition.addOrReplaceChild("bone", CubeListBuilder.create(), PartPose.offset(0.0F, 22.0F, 0.0F));

		PartDefinition head = bone.addOrReplaceChild("head", CubeListBuilder.create().texOffs(22, 11).addBox(-2.0F, -6.0F, -2.0F, 4.0F, 6.0F, 3.0F, new CubeDeformation(0.0F))
				.texOffs(12, 19).addBox(0.0F, -10.0F, -3.0F, 0.0F, 5.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -9.0F, -4.0F));

		PartDefinition bill = bone.addOrReplaceChild("bill", CubeListBuilder.create().texOffs(18, 0).addBox(-2.0F, -4.0F, -4.0F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -9.0F, -4.0F));

		PartDefinition chin = bone.addOrReplaceChild("chin", CubeListBuilder.create().texOffs(24, 4).addBox(-1.0F, -2.0F, -3.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -9.0F, -4.0F));

		PartDefinition body = bone.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-3.0F, -4.0F, -3.0F, 6.0F, 8.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -8.0F, 0.0F, 1.5708F, 0.0F, 0.0F));

		PartDefinition cube_r1 = body.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(0, 7).addBox(0.0F, -15.0F, 5.0F, 0.0F, 10.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -1.0F, -4.0F, -1.5708F, 0.0F, 0.0F));

		PartDefinition right_wing = bone.addOrReplaceChild("right_wing", CubeListBuilder.create().texOffs(14, 14).addBox(0.0F, 0.0F, -3.0F, 1.0F, 4.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(-4.0F, -11.0F, 0.0F));

		PartDefinition right_leg = bone.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(0, 24).addBox(-1.5F, 0.0F, -3.0F, 3.0F, 7.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.5F, -5.0F, 1.0F));

		PartDefinition left_leg = bone.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(0, 24).addBox(-1.5F, 0.0F, -3.0F, 3.0F, 7.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(1.5F, -5.0F, 1.0F));

		PartDefinition left_wing = bone.addOrReplaceChild("left_wing", CubeListBuilder.create().texOffs(14, 14).addBox(-1.0F, 0.0F, -3.0F, 1.0F, 4.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(4.0F, -11.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void setupAnim(ChickenRenderState entity) {
		this.root().getAllParts().forEach(ModelPart::resetPose);

		this.head.xRot = entity.xRot * ((float) Math.PI / 180F);
		this.head.yRot = entity.yRot * ((float) Math.PI / 180F);
		this.bill.xRot = this.head.xRot;
		this.bill.yRot = this.head.yRot;
		this.chin.xRot = this.head.xRot;
		this.chin.yRot = this.head.yRot;
		this.rightLeg.xRot = Mth.cos(entity.walkAnimationPos * 0.6662F) * 1.4F * entity.walkAnimationSpeed;
		this.leftLeg.xRot = Mth.cos(entity.walkAnimationPos * 0.6662F + (float) Math.PI) * 1.4F * entity.walkAnimationSpeed;
		float f = (Mth.sin(entity.flap) + 1.0F) * entity.flapSpeed;
		this.rightWing.zRot = f;
		this.leftWing.zRot = -f;
	}
}