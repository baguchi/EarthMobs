package baguchan.earthmobsmod.client.model;

import net.minecraft.client.model.HeadedModel;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class VilerWitchModel<T extends LivingEntity> extends HierarchicalModel<T> implements HeadedModel {
	private final ModelPart root;
	private final ModelPart head;
	private final ModelPart hat;
	private final ModelPart rightLeg;
	private final ModelPart leftLeg;
	protected final ModelPart nose;

	private boolean holdingItem;

	public VilerWitchModel(ModelPart p_170688_) {
		this.root = p_170688_;
		this.head = p_170688_.getChild("head");
		this.hat = this.head.getChild("hat");
		this.nose = this.head.getChild("nose");
		this.rightLeg = p_170688_.getChild("right_leg");
		this.leftLeg = p_170688_.getChild("left_leg");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();
		PartDefinition partdefinition1 = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -10.0F, -4.0F, 8.0F, 10.0F, 8.0F).texOffs(28, 46).addBox(-4.5F, -4.5F, -4.5F, 9.0F, 5.0F, 9.0F), PartPose.ZERO);
		PartDefinition partdefinition2 = partdefinition1.addOrReplaceChild("hat", CubeListBuilder.create().texOffs(14, 106).addBox(-15.0F, -0.1F, -5.0F, 20.0F, 2.0F, 20.0F), PartPose.offset(5.0F, -10.0F, -5.0F));
		PartDefinition partdefinition3 = partdefinition2.addOrReplaceChild("hat2", CubeListBuilder.create().texOffs(0, 76).addBox(0.0F, -4.0F, 0.0F, 7.0F, 4.0F, 7.0F), PartPose.offsetAndRotation(-8.5F, -0.1F, 0.0F, -0.052F, 0.0F, 0.0F));
		PartDefinition partdefinition4 = partdefinition3.addOrReplaceChild("hat3", CubeListBuilder.create().texOffs(0, 87).addBox(0.0F, -4.0F, 0.0F, 4.0F, 4.0F, 4.0F), PartPose.offsetAndRotation(1.5F, -4.0F, 1.5F, -0.1F, 0.0F, 0.0F));
		partdefinition4.addOrReplaceChild("hat4", CubeListBuilder.create().texOffs(0, 95).addBox(0.0F, -2.0F, 0.0F, 1.0F, 2.0F, 1.0F), PartPose.offsetAndRotation(1.5F, -4.0F, 1.5F, -0.209F, 0.0F, 0.0F));


		PartDefinition partdefinition7 = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(16, 20).addBox(-4.0F, 0.0F, -3.0F, 8.0F, 12.0F, 6.0F), PartPose.ZERO);
		partdefinition7.addOrReplaceChild("jacket", CubeListBuilder.create().texOffs(0, 95).addBox(-5.0F, 0.0F, -3.5F, 10.0F, 19.0F, 7.0F).texOffs(30, 60).addBox(-5.5F, 9.0F, -4.0F, 11.0F, 2.0F, 8.0F), PartPose.ZERO);


		PartDefinition partdefinition8 = partdefinition1.addOrReplaceChild("nose", CubeListBuilder.create().texOffs(24, 0).addBox(-1.0F, -1.0F, -6.0F, 2.0F, 4.0F, 2.0F), PartPose.offset(0.0F, -2.0F, 0.0F));
		partdefinition8.addOrReplaceChild("mole", CubeListBuilder.create().texOffs(0, 0).addBox(0.0F, -1.0F, -6.75F, 1.0F, 1.0F, 1.0F, new CubeDeformation(-0.25F)), PartPose.offset(0.0F, 2.0F, 0.0F));

		PartDefinition partdefinition9 = partdefinition.addOrReplaceChild("arms", CubeListBuilder.create().texOffs(44, 22).addBox(-8.0F, -2.0F, -2.0F, 4.0F, 8.0F, 4.0F).texOffs(44, 22).addBox(4.0F, -2.0F, -2.0F, 4.0F, 8.0F, 4.0F, true).texOffs(40, 38).addBox(-4.0F, 2.0F, -2.0F, 8.0F, 4.0F, 4.0F), PartPose.offsetAndRotation(0.0F, 3.0F, -1.0F, -0.75F, 0.0F, 0.0F));
		partdefinition9.addOrReplaceChild("shoulder_arms", CubeListBuilder.create().texOffs(32, 0).mirror().addBox(4.0F, -25.0F, -3.0F, 5.0F, 5.0F, 6.0F).texOffs(32, 0).mirror().addBox(-9.0F, -25.0F, -3.0F, 5.0F, 5.0F, 6.0F), PartPose.offset(0.0F, 22.0F, 0.0F));


		partdefinition.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(0, 22).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F).texOffs(52, 11).addBox(-2.5F, 7.0F, -2.5F, 5.0F, 3.0F, 5.0F), PartPose.offset(-2.0F, 12.0F, 0.0F));
		partdefinition.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(0, 22).mirror().addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F).texOffs(32, 11).mirror().addBox(-2.5F, 7.0F, -2.5F, 5.0F, 3.0F, 5.0F), PartPose.offset(2.0F, 12.0F, 0.0F));

		/*this.cape = new ModelRenderer(this, 64, 0);
		this.cape.setPos(0.0F, 1.0F, 3.4F);
		this.cape.addBox(-4.5F, 0.0F, 0.0F, 9.0F, 15.0F, 1.0F, 0.6F, 0.7F, 0.0F);*/
		return LayerDefinition.create(meshdefinition, 128, 128);
	}

	public ModelPart root() {
		return this.root;
	}

	public void setupAnim(T p_102928_, float p_102929_, float p_102930_, float p_102931_, float p_102932_, float p_102933_) {
		this.head.yRot = p_102932_ * ((float) Math.PI / 180F);
		this.head.xRot = p_102933_ * ((float) Math.PI / 180F);
		this.nose.setPos(0.0F, -2.0F, 0.0F);
		float f = 0.01F * (float) (p_102928_.getId() % 10);
		this.nose.xRot = Mth.sin((float) p_102928_.tickCount * f) * 4.5F * ((float) Math.PI / 180F);
		this.nose.yRot = 0.0F;
		this.nose.zRot = Mth.cos((float) p_102928_.tickCount * f) * 2.5F * ((float) Math.PI / 180F);
		if (this.riding) {
			this.rightLeg.xRot = -1.4137167F;
			this.rightLeg.yRot = ((float) Math.PI / 10F);
			this.rightLeg.zRot = 0.07853982F;
			this.leftLeg.xRot = -1.4137167F;
			this.leftLeg.yRot = (-(float) Math.PI / 10F);
			this.leftLeg.zRot = -0.07853982F;
		} else {
			this.rightLeg.xRot = Mth.cos(p_102929_ * 0.6662F) * 1.4F * p_102930_ * 0.5F;
			this.rightLeg.yRot = 0.0F;
			this.rightLeg.zRot = 0.0F;
			this.leftLeg.xRot = Mth.cos(p_102929_ * 0.6662F + (float) Math.PI) * 1.4F * p_102930_ * 0.5F;
			this.leftLeg.yRot = 0.0F;
			this.leftLeg.zRot = 0.0F;
		}

		if (this.holdingItem) {
			this.nose.setPos(0.0F, 1.0F, -1.5F);
			this.nose.xRot = -0.9F;
		}

	}

	public ModelPart getHat() {
		return this.hat;
	}

	public ModelPart getHead() {
		return this.head;
	}

	public ModelPart getNose() {
		return nose;
	}

	public void setHoldingItem(boolean p_104075_) {
		this.holdingItem = p_104075_;
	}
}