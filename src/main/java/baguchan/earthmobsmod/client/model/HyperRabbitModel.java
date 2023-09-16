package baguchan.earthmobsmod.client.model;// Made with Blockbench 4.1.1
// Exported for Minecraft version 1.17 with Mojang mappings
// Paste this class into your mod and generate all required imports


import baguchan.earthmobsmod.entity.HyperRabbit;
import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;

public class HyperRabbitModel<T extends HyperRabbit> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	private final ModelPart rearFootLeft;
	private final ModelPart rearFootRight;
	private final ModelPart haunchLeft;
	private final ModelPart haunchRight;
	private final ModelPart body;
	private final ModelPart frontLegLeft;
	private final ModelPart frontLegRight;
	private final ModelPart head;
	private final ModelPart earRight;
	private final ModelPart earLeft;
	private final ModelPart torso;
	private final ModelPart tail;
	private final ModelPart nose;
	private float jumpRotation;
	private static final float NEW_SCALE = 0.6F;

	public HyperRabbitModel(ModelPart root) {
		this.body = root.getChild("body");
		this.rearFootLeft = this.body.getChild("rearFootLeft");
		this.rearFootRight = this.body.getChild("rearFootRight");
		this.haunchLeft = this.body.getChild("haunchLeft");
		this.haunchRight = this.body.getChild("haunchRight");
		this.frontLegLeft = this.body.getChild("frontLegLeft");
		this.frontLegRight = this.body.getChild("frontLegRight");
		this.head = this.body.getChild("head");
		this.earRight = this.head.getChild("earRight");
		this.earLeft = this.head.getChild("earLeft");
		this.torso = this.body.getChild("torso");
		this.tail = this.torso.getChild("tail");
		this.nose = this.head.getChild("nose");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create(), PartPose.offset(0.0F, 19.0F, 8.0F));

		PartDefinition torso = body.addOrReplaceChild("torso", CubeListBuilder.create().texOffs(16, 3).addBox(3.0F, -6.5F, 3.0F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F))
				.texOffs(16, 3).addBox(-4.0F, -4.5F, 2.0F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F))
				.texOffs(16, 3).addBox(-4.0F, -6.5F, 4.0F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F))
				.texOffs(0, 10).addBox(-3.0F, -7.0F, -2.0F, 6.0F, 5.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 1.0F, -12.0F, -0.6109F, 0.0F, 0.0F));

		PartDefinition tail = torso.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(22, 10).addBox(-1.5F, 0.0F, 1.0F, 3.0F, 2.0F, 3.0F, new CubeDeformation(0.0F))
				.texOffs(35, 10).addBox(-1.5F, -1.0F, 1.0F, 3.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -9.0F, 3.0F));

		PartDefinition rearFootLeft = body.addOrReplaceChild("rearFootLeft", CubeListBuilder.create().texOffs(18, 49).addBox(-1.0F, 5.5F, -3.7F, 2.0F, 1.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(4.0F, -1.5F, -4.3F));

		PartDefinition rearFootRight = body.addOrReplaceChild("rearFootRight", CubeListBuilder.create().texOffs(0, 49).addBox(-1.0F, 5.5F, -3.7F, 2.0F, 1.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(-4.0F, -1.5F, -4.3F));

		PartDefinition haunchLeft = body.addOrReplaceChild("haunchLeft", CubeListBuilder.create().texOffs(0, 25).addBox(-1.0F, 0.0F, -1.7F, 2.0F, 3.0F, 7.0F, new CubeDeformation(0.0F))
				.texOffs(61, 0).addBox(1.0F, -1.0F, -1.7F, 0.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(60, 1).addBox(0.0F, 1.5F, -2.7F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(30, 24).addBox(1.0F, -2.0F, 2.3F, 0.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(4.0F, -1.5F, -4.3F));

		PartDefinition haunchRight = body.addOrReplaceChild("haunchRight", CubeListBuilder.create().texOffs(0, 25).addBox(-1.0F, 0.0F, -1.7F, 2.0F, 3.0F, 7.0F, new CubeDeformation(0.0F))
				.texOffs(61, 0).addBox(0.0F, -1.0F, -0.7F, 0.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(30, 24).addBox(-1.0F, -2.0F, 3.0F, 0.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-4.0F, -1.5F, -4.3F));

		PartDefinition frontLegLeft = body.addOrReplaceChild("frontLegLeft", CubeListBuilder.create().texOffs(8, 35).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 12.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(61, 0).addBox(1.0F, 0.0F, 1.0F, 0.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.0F, -5.0F, -9.0F, -0.6109F, 0.0F, 0.0F));

		PartDefinition frontLegRight = body.addOrReplaceChild("frontLegRight", CubeListBuilder.create().texOffs(0, 35).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 12.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(61, 0).addBox(-1.0F, 0.0F, 1.0F, 0.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, -5.0F, -9.0F, -0.6109F, 0.0F, 0.0F));

		PartDefinition head = body.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-2.5F, -5.0F, -5.0F, 5.0F, 5.0F, 5.0F, new CubeDeformation(0.0F))
				.texOffs(16, 1).addBox(2.5F, -2.0F, -5.0F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F))
				.texOffs(16, 3).addBox(-3.5F, -3.5F, -3.0F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F))
				.texOffs(16, 3).addBox(-3.5F, -4.5F, -1.0F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F))
				.texOffs(16, 3).addBox(2.5F, -4.5F, -3.0F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F))
				.texOffs(16, 3).addBox(0.5F, -6.0F, -2.0F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -3.0F, -10.0F));

		PartDefinition head_r1 = head.addOrReplaceChild("head_r1", CubeListBuilder.create().texOffs(39, 5).addBox(-1.0F, -2.0F, 0.0F, 2.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.5F, -5.0F, -4.0F, 0.2618F, 0.0F, 0.0F));

		PartDefinition head_r2 = head.addOrReplaceChild("head_r2", CubeListBuilder.create().texOffs(45, 1).addBox(0.0F, -1.0F, 0.0F, 2.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.5F, -2.5F, 0.0F, 0.0F, -0.4363F, 0.0F));

		PartDefinition head_r3 = head.addOrReplaceChild("head_r3", CubeListBuilder.create().texOffs(33, 5).addBox(-1.0F, -1.0F, 0.0F, 2.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.5F, -2.0F, -4.0F, 0.0F, -0.3491F, 0.0F));

		PartDefinition head_r4 = head.addOrReplaceChild("head_r4", CubeListBuilder.create().texOffs(16, 1).addBox(-1.0F, -0.5F, 0.0F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.5F, -1.5F, -5.0F, 0.0F, -0.6545F, 0.0F));

		PartDefinition earRight = head.addOrReplaceChild("earRight", CubeListBuilder.create().texOffs(33, 1).addBox(-1.0F, -10.5F, -0.5F, 2.0F, 2.0F, 0.0F, new CubeDeformation(0.0F))
				.texOffs(20, 0).addBox(-1.0F, -8.5F, -0.5F, 2.0F, 9.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.5F, -4.5F, 0.5F));

		PartDefinition earLeft = head.addOrReplaceChild("earLeft", CubeListBuilder.create().texOffs(39, 1).addBox(-1.0F, -10.5F, -0.5F, 2.0F, 2.0F, 0.0F, new CubeDeformation(0.0F))
				.texOffs(26, 0).addBox(-1.0F, -8.5F, -0.5F, 2.0F, 9.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(1.5F, -4.5F, 0.5F));

		PartDefinition nose = head.addOrReplaceChild("nose", CubeListBuilder.create().texOffs(0, 0).addBox(-0.5F, -2.5F, -5.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		float f = ageInTicks - (float) entity.tickCount;
		this.head.xRot = headPitch * ((float) Math.PI / 180F);
		this.head.yRot = netHeadYaw * ((float) Math.PI / 180F);

		this.jumpRotation = Mth.sin(entity.getJumpCompletion(f) * (float) Math.PI);
		this.haunchLeft.xRot = -0.6109F + (this.jumpRotation * 50.0F - 21.0F) * ((float) Math.PI / 180F);
		this.haunchRight.xRot = -0.6109F + (this.jumpRotation * 50.0F - 21.0F) * ((float) Math.PI / 180F);
		this.rearFootLeft.xRot = this.jumpRotation * 50.0F * ((float) Math.PI / 180F);
		this.rearFootRight.xRot = this.jumpRotation * 50.0F * ((float) Math.PI / 180F);
		this.frontLegLeft.xRot = -0.6109F + (this.jumpRotation * -29.0F) * ((float) Math.PI / 180F);
		this.frontLegRight.xRot = -0.6109F + (this.jumpRotation * -29.0F) * ((float) Math.PI / 180F);
	}

	@Override
	public void renderToBuffer(PoseStack p_103555_, VertexConsumer p_103556_, int p_103557_, int p_103558_, float p_103559_, float p_103560_, float p_103561_, float p_103562_) {
		ImmutableList.of(this.body).forEach((p_103597_) -> {
			p_103597_.render(p_103555_, p_103556_, p_103557_, p_103558_, p_103559_, p_103560_, p_103561_, p_103562_);
		});

	}
}