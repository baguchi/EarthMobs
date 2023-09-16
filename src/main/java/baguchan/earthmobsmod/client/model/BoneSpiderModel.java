package baguchan.earthmobsmod.client.model;

import baguchan.earthmobsmod.entity.BoneSpider;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;

public class BoneSpiderModel<T extends BoneSpider> extends HierarchicalModel<T> {
	private final ModelPart bone;
	private final ModelPart head;
	private final ModelPart rightHindLeg;
	private final ModelPart leftHindLeg;
	private final ModelPart rightMiddleHindLeg;
	private final ModelPart leftMiddleHindLeg;
	private final ModelPart rightMiddleFrontLeg;
	private final ModelPart leftMiddleFrontLeg;
	private final ModelPart rightFrontLeg;
	private final ModelPart leftFrontLeg;

	public BoneSpiderModel(ModelPart p_170984_) {
		super();
		this.bone = p_170984_.getChild("bone");
		this.head = this.bone.getChild("head");
		this.rightHindLeg = this.bone.getChild("right_hind_leg");
		this.leftHindLeg = this.bone.getChild("left_hind_leg");
		this.rightMiddleHindLeg = this.bone.getChild("right_middle_hind_leg");
		this.leftMiddleHindLeg = this.bone.getChild("left_middle_hind_leg");
		this.rightMiddleFrontLeg = this.bone.getChild("right_middle_front_leg");
		this.leftMiddleFrontLeg = this.bone.getChild("left_middle_front_leg");
		this.rightFrontLeg = this.bone.getChild("right_front_leg");
		this.leftFrontLeg = this.bone.getChild("left_front_leg");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition bone = partdefinition.addOrReplaceChild("bone", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition head = bone.addOrReplaceChild("head", CubeListBuilder.create().texOffs(24, 28).addBox(-4.0F, -4.0F, -8.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F))
				.texOffs(0, 20).addBox(-4.0F, -4.0F, -8.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.25F)), PartPose.offset(0.0F, -9.0F, -3.0F));

		PartDefinition neck = bone.addOrReplaceChild("neck", CubeListBuilder.create().texOffs(32, 0).addBox(-3.0F, -3.0F, -3.0F, 6.0F, 6.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -9.0F, 0.0F));

		PartDefinition body = bone.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-5.0F, -4.0F, -6.0F, 10.0F, 8.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -9.0F, 9.0F));

		PartDefinition leg2 = bone.addOrReplaceChild("left_hind_leg", CubeListBuilder.create().texOffs(24, 20).addBox(-1.0F, -1.0F, -1.0F, 16.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(4.0F, -9.0F, 4.0F));

		PartDefinition leg3 = bone.addOrReplaceChild("right_middle_hind_leg", CubeListBuilder.create().texOffs(24, 20).addBox(-15.0F, -1.0F, -1.0F, 16.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-4.0F, -9.0F, 1.0F));

		PartDefinition leg4 = bone.addOrReplaceChild("left_middle_hind_leg", CubeListBuilder.create().texOffs(24, 20).addBox(-1.0F, -1.0F, -1.0F, 16.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(4.0F, -9.0F, 1.0F));

		PartDefinition leg5 = bone.addOrReplaceChild("right_middle_front_leg", CubeListBuilder.create().texOffs(24, 20).addBox(-15.0F, -1.0F, -1.0F, 16.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-4.0F, -9.0F, -2.0F));

		PartDefinition leg6 = bone.addOrReplaceChild("left_middle_front_leg", CubeListBuilder.create().texOffs(24, 20).addBox(-1.0F, -1.0F, -1.0F, 16.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(4.0F, -9.0F, -2.0F));

		PartDefinition leg7 = bone.addOrReplaceChild("right_front_leg", CubeListBuilder.create().texOffs(24, 20).addBox(-15.0F, -1.0F, -1.0F, 16.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-4.0F, -9.0F, -5.0F));

		PartDefinition leg8 = bone.addOrReplaceChild("left_front_leg", CubeListBuilder.create().texOffs(24, 20).addBox(-1.0F, -1.0F, -1.0F, 16.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(4.0F, -9.0F, -5.0F));

		PartDefinition leg1 = bone.addOrReplaceChild("right_hind_leg", CubeListBuilder.create().texOffs(24, 20).addBox(-15.0F, -1.0F, -1.0F, 16.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-4.0F, -9.0F, 4.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public ModelPart root() {
		return this.bone;
	}

	@Override
	public void setupAnim(T p_103866_, float p_103867_, float p_103868_, float p_103869_, float p_103870_, float p_103871_) {
		this.head.yRot = p_103870_ * ((float) Math.PI / 180F);
		this.head.xRot = p_103871_ * ((float) Math.PI / 180F);
		float f = ((float) Math.PI / 4F);
		this.rightHindLeg.zRot = (-(float) Math.PI / 4F);
		this.leftHindLeg.zRot = ((float) Math.PI / 4F);
		this.rightMiddleHindLeg.zRot = -0.58119464F;
		this.leftMiddleHindLeg.zRot = 0.58119464F;
		this.rightMiddleFrontLeg.zRot = -0.58119464F;
		this.leftMiddleFrontLeg.zRot = 0.58119464F;
		this.rightFrontLeg.zRot = (-(float) Math.PI / 4F);
		this.leftFrontLeg.zRot = ((float) Math.PI / 4F);
		float f1 = -0.0F;
		float f2 = ((float) Math.PI / 8F);
		this.rightHindLeg.yRot = ((float) Math.PI / 4F);
		this.leftHindLeg.yRot = (-(float) Math.PI / 4F);
		this.rightMiddleHindLeg.yRot = ((float) Math.PI / 8F);
		this.leftMiddleHindLeg.yRot = (-(float) Math.PI / 8F);
		this.rightMiddleFrontLeg.yRot = (-(float) Math.PI / 8F);
		this.leftMiddleFrontLeg.yRot = ((float) Math.PI / 8F);
		this.rightFrontLeg.yRot = (-(float) Math.PI / 4F);
		this.leftFrontLeg.yRot = ((float) Math.PI / 4F);
		float f3 = -(Mth.cos(p_103867_ * 0.6662F * 2.0F + 0.0F) * 0.4F) * p_103868_;
		float f4 = -(Mth.cos(p_103867_ * 0.6662F * 2.0F + (float) Math.PI) * 0.4F) * p_103868_;
		float f5 = -(Mth.cos(p_103867_ * 0.6662F * 2.0F + ((float) Math.PI / 2F)) * 0.4F) * p_103868_;
		float f6 = -(Mth.cos(p_103867_ * 0.6662F * 2.0F + ((float) Math.PI * 1.5F)) * 0.4F) * p_103868_;
		float f7 = Math.abs(Mth.sin(p_103867_ * 0.6662F + 0.0F) * 0.4F) * p_103868_;
		float f8 = Math.abs(Mth.sin(p_103867_ * 0.6662F + (float) Math.PI) * 0.4F) * p_103868_;
		float f9 = Math.abs(Mth.sin(p_103867_ * 0.6662F + ((float) Math.PI / 2F)) * 0.4F) * p_103868_;
		float f10 = Math.abs(Mth.sin(p_103867_ * 0.6662F + ((float) Math.PI * 1.5F)) * 0.4F) * p_103868_;
		this.rightHindLeg.yRot += f3;
		this.leftHindLeg.yRot += -f3;
		this.rightMiddleHindLeg.yRot += f4;
		this.leftMiddleHindLeg.yRot += -f4;
		this.rightMiddleFrontLeg.yRot += f5;
		this.leftMiddleFrontLeg.yRot += -f5;
		this.rightFrontLeg.yRot += f6;
		this.leftFrontLeg.yRot += -f6;
		this.rightHindLeg.zRot += f7;
		this.leftHindLeg.zRot += -f7;
		this.rightMiddleHindLeg.zRot += f8;
		this.leftMiddleHindLeg.zRot += -f8;
		this.rightMiddleFrontLeg.zRot += f9;
		this.leftMiddleFrontLeg.zRot += -f9;
		this.rightFrontLeg.zRot += f10;
		this.leftFrontLeg.zRot += -f10;
	}
}
