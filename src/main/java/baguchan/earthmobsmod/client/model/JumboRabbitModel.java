package baguchan.earthmobsmod.client.model;// Made with Blockbench 4.2.4
// Exported for Minecraft version 1.17 - 1.18 with Mojang mappings
// Paste this class into your mod and generate all required imports


import baguchan.earthmobsmod.entity.JumboRabbit;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;

public class JumboRabbitModel<T extends JumboRabbit> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	private final ModelPart rearFootRight;
	private final ModelPart rearFootLeft;
	private final ModelPart haunchRight;
	private final ModelPart haunchLeft;
	private final ModelPart body;
	private final ModelPart frontLegRight;
	private final ModelPart frontLegLeft;
	private final ModelPart head;
	private final ModelPart earLeft;
	private final ModelPart earRight;
	private final ModelPart tail;
	private final ModelPart nose;

	private float jumpRotation;

	public JumboRabbitModel(ModelPart root) {
		this.rearFootRight = root.getChild("rearFootRight");
		this.rearFootLeft = root.getChild("rearFootLeft");
		this.haunchRight = root.getChild("haunchRight");
		this.haunchLeft = root.getChild("haunchLeft");
		this.body = root.getChild("body");
		this.frontLegRight = root.getChild("frontLegRight");
		this.frontLegLeft = root.getChild("frontLegLeft");
		this.head = root.getChild("head");
		this.earLeft = root.getChild("earLeft");
		this.earRight = root.getChild("earRight");
		this.tail = root.getChild("tail");
		this.nose = root.getChild("nose");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition rearFootRight = partdefinition.addOrReplaceChild("rearFootRight", CubeListBuilder.create().texOffs(20, 34).addBox(-3.0F, 5.5F, -3.7F, 3.0F, 1.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(-3.0F, 17.5F, 3.7F));

		PartDefinition rearFootLeft = partdefinition.addOrReplaceChild("rearFootLeft", CubeListBuilder.create().texOffs(0, 34).addBox(0.0F, 5.5F, -3.7F, 3.0F, 1.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(3.0F, 17.5F, 3.7F));

		PartDefinition haunchRight = partdefinition.addOrReplaceChild("haunchRight", CubeListBuilder.create().texOffs(38, 22).addBox(-3.0F, -3.0F, 0.0F, 3.0F, 7.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, 17.5F, 3.7F, -0.3491F, 0.0F, 0.0F));

		PartDefinition haunchLeft = partdefinition.addOrReplaceChild("haunchLeft", CubeListBuilder.create().texOffs(22, 22).addBox(0.0F, -3.0F, 0.0F, 3.0F, 7.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.0F, 17.5F, 3.7F, -0.3491F, 0.0F, 0.0F));

		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -5.0F, -14.0F, 8.0F, 8.0F, 14.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 19.0F, 8.0F, -0.2182F, 0.0F, 0.0F));

		PartDefinition frontLegRight = partdefinition.addOrReplaceChild("frontLegRight", CubeListBuilder.create().texOffs(0, 47).addBox(-1.0F, -4.0F, -1.0F, 2.0F, 11.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.0F, 17.0F, -5.0F, -0.1745F, 0.0F, 0.0F));

		PartDefinition frontLegLeft = partdefinition.addOrReplaceChild("frontLegLeft", CubeListBuilder.create().texOffs(40, 34).addBox(-1.0F, -4.0F, -1.0F, 2.0F, 11.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.0F, 17.0F, -5.0F, -0.1745F, 0.0F, 0.0F));

		PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 22).addBox(-2.5F, -5.0F, -6.0F, 5.0F, 5.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 14.0F, -4.0F));

		PartDefinition earLeft = partdefinition.addOrReplaceChild("earLeft", CubeListBuilder.create().texOffs(6, 60).addBox(0.5F, -7.0F, -1.0F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 14.0F, -5.0F, 0.0F, 0.2618F, 0.0F));

		PartDefinition earLeft2 = earLeft.addOrReplaceChild("earLeft2", CubeListBuilder.create().texOffs(16, 47).addBox(0.0F, -10.0F, -0.1F, 3.0F, 10.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.5F, -7.0F, -1.0F));

		PartDefinition earRight = partdefinition.addOrReplaceChild("earRight", CubeListBuilder.create().texOffs(0, 60).addBox(-2.5F, -6.0F, -1.0F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 13.0F, -5.0F, 0.0F, -0.2618F, 0.0F));

		PartDefinition earRight2 = earRight.addOrReplaceChild("earRight2", CubeListBuilder.create().texOffs(8, 47).addBox(-3.5F, -13.0F, -1.0F, 3.0F, 10.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -3.0F, 0.0F));

		PartDefinition tail = partdefinition.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(24, 47).addBox(-1.5F, -1.5F, 0.0F, 3.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 20.0F, 7.0F, -0.3491F, 0.0F, 0.0F));

		PartDefinition nose = partdefinition.addOrReplaceChild("nose", CubeListBuilder.create().texOffs(12, 60).addBox(-0.5F, -2.5F, -6.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 14.0F, -4.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		float f = ageInTicks - (float) entity.tickCount;
		this.nose.xRot = headPitch * ((float) Math.PI / 180F);
		this.head.xRot = headPitch * ((float) Math.PI / 180F);
		this.earLeft.xRot = headPitch * ((float) Math.PI / 180F);
		this.earRight.xRot = headPitch * ((float) Math.PI / 180F);
		this.head.yRot = netHeadYaw * ((float) Math.PI / 180F);
		this.nose.yRot = netHeadYaw * ((float) Math.PI / 180F);
		this.earLeft.yRot = this.nose.yRot + 0.2617994F;
		this.earRight.yRot = this.nose.yRot - 0.2617994F;
		this.jumpRotation = Mth.sin(entity.getJumpCompletion(f) * (float) Math.PI);
		this.haunchRight.xRot = -0.1745F + (this.jumpRotation * 50.0F - 21.0F) * ((float) Math.PI / 180F);
		this.haunchLeft.xRot = -0.1745F + (this.jumpRotation * 50.0F - 21.0F) * ((float) Math.PI / 180F);
		this.rearFootRight.xRot = this.jumpRotation * 50.0F * ((float) Math.PI / 180F);
		this.rearFootLeft.xRot = this.jumpRotation * 50.0F * ((float) Math.PI / 180F);
		this.frontLegRight.xRot = -0.1745F + (this.jumpRotation * -29.0F) * ((float) Math.PI / 180F);
		this.frontLegLeft.xRot = -0.1745F + (this.jumpRotation * -29.0F) * ((float) Math.PI / 180F);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		rearFootRight.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		rearFootLeft.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		haunchRight.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		haunchLeft.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		body.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		frontLegRight.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		frontLegLeft.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		head.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		earLeft.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		earRight.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		tail.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		nose.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}