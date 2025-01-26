package baguchan.earthmobsmod.client.model;

import baguchan.earthmobsmod.client.animation.LobberZombieAnimation;
import baguchan.earthmobsmod.client.render.state.LobberZombieRenderState;
import baguchi.bagus_lib.client.layer.IArmor;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.HumanoidArm;

public class LobberDrownedModel<T extends LobberZombieRenderState> extends AbstractLobberZombieModel<T> implements IArmor {
	public float swimAmount;

	public LobberDrownedModel(ModelPart root) {
		super(root);
	}
	public static LayerDefinition createBodyLayer(CubeDeformation cubeDeformation) {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition bone = partdefinition.addOrReplaceChild("bone", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition head = bone.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 16).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, cubeDeformation)
				.texOffs(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, cubeDeformation.extend(0.5F)), PartPose.offset(0.0F, -24.0F, 0.0F));

		PartDefinition body = bone.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 32).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, cubeDeformation)
				.texOffs(28, 28).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, cubeDeformation.extend(0.25F)), PartPose.offset(0.0F, -24.0F, 0.0F));

		PartDefinition left_arm = bone.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(20, 44).addBox(-1.0F, -2.0F, -2.0F, 5.0F, 14.0F, 4.0F, cubeDeformation)
				.texOffs(32, 0).addBox(-1.0F, -2.0F, -2.0F, 5.0F, 14.0F, 4.0F, cubeDeformation.extend(0.25F)), PartPose.offset(5.0F, -22.0F, 0.0F));

		PartDefinition right_arm = bone.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(60, 0).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 11.0F, 4.0F, cubeDeformation)
				.texOffs(54, 46).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 11.0F, 4.0F, cubeDeformation.extend(0.25F)), PartPose.offset(-5.0F, -22.0F, 0.0F));

		PartDefinition left_leg = bone.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(0, 48).addBox(-1.9F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, cubeDeformation)
				.texOffs(38, 44).addBox(-1.9F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, cubeDeformation.extend(0.25F)), PartPose.offset(1.9F, -12.0F, 0.0F));

		PartDefinition right_leg = bone.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(52, 30).addBox(-2.1F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, cubeDeformation)
				.texOffs(48, 14).addBox(-2.1F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, cubeDeformation.extend(0.25F)), PartPose.offset(-1.9F, -12.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 128, 128);
	}

	@Override
	public void setupAnim(T entity) {
		super.setupAnim(entity);
		if (entity.rightArmPose == HumanoidModel.ArmPose.THROW_SPEAR) {
			if (entity.mainArm == HumanoidArm.RIGHT) {
				this.right_arm.xRot = this.right_arm.xRot * 0.5F - (float) Math.PI;
				this.right_arm.yRot = 0.0F;
			} else {
				this.left_arm.xRot = this.left_arm.xRot * 0.5F - (float) Math.PI;
				this.left_arm.yRot = 0.0F;
			}
		}

		if (swimAmount > 0.0F) {
			this.right_arm.xRot = this.rotlerpRad(this.swimAmount, this.right_arm.xRot, -2.5132742F) + this.swimAmount * 0.35F * Mth.sin(0.1F * entity.ageInTicks);
			this.left_arm.xRot = this.rotlerpRad(this.swimAmount, this.left_arm.xRot, -2.5132742F) - this.swimAmount * 0.35F * Mth.sin(0.1F * entity.ageInTicks);
			this.right_arm.zRot = this.rotlerpRad(this.swimAmount, this.right_arm.zRot, -0.15F);
			this.left_arm.zRot = this.rotlerpRad(this.swimAmount, this.left_arm.zRot, 0.15F);
			this.left_leg.xRot -= this.swimAmount * 0.55F * Mth.sin(0.1F * entity.ageInTicks);
			this.right_leg.xRot += this.swimAmount * 0.55F * Mth.sin(0.1F * entity.ageInTicks);
			this.head.xRot = 0.0F;
		}
		this.animate(entity.shootAnimationState, LobberZombieAnimation.shoot, entity.ageInTicks);
	}

	protected float rotlerpRad(float angle, float maxAngle, float mul) {
		float f = (mul - maxAngle) % (float) (Math.PI * 2);
		if (f < (float) -Math.PI) {
			f += (float) (Math.PI * 2);
		}

		if (f >= (float) Math.PI) {
			f -= (float) (Math.PI * 2);
		}

		return maxAngle + angle * f;
	}
}
