package baguchan.earthmobsmod.client.model;

import baguchan.earthmobsmod.client.render.state.CluckShroomRenderState;
import net.minecraft.client.model.BabyModelTransform;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;

import java.util.Set;

public class CluckShroomModel<T extends CluckShroomRenderState> extends EntityModel<T> {
	public static final String RED_THING = "red_thing";
	public static final MeshTransformer BABY_TRANSFORMER = new BabyModelTransform(Set.of("head", "beak", "red_thing"));
	private final ModelPart head;
	private final ModelPart rightLeg;
	private final ModelPart leftLeg;
	private final ModelPart rightWing;
	private final ModelPart leftWing;

	public CluckShroomModel(ModelPart p_170490_) {
		super(p_170490_);
		this.head = p_170490_.getChild("head");
		this.rightLeg = p_170490_.getChild("right_leg");
		this.leftLeg = p_170490_.getChild("left_leg");
		this.rightWing = p_170490_.getChild("right_wing");
		this.leftWing = p_170490_.getChild("left_wing");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();
		int i = 16;
		PartDefinition partdefinition2 = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-2.0F, -6.0F, -2.0F, 4.0F, 6.0F, 3.0F), PartPose.offset(0.0F, 15.0F, -4.0F));
		partdefinition.addOrReplaceChild("beak", CubeListBuilder.create().texOffs(14, 0).addBox(-2.0F, -4.0F, -4.0F, 4.0F, 2.0F, 2.0F), PartPose.offset(0.0F, 15.0F, -4.0F));
		partdefinition.addOrReplaceChild("red_thing", CubeListBuilder.create().texOffs(14, 4).addBox(-1.0F, -2.0F, -3.0F, 2.0F, 2.0F, 2.0F), PartPose.offset(0.0F, 15.0F, -4.0F));
		PartDefinition partdefinition3 = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 10).addBox(-3.0F, -4.0F, -3.0F, 6.0F, 8.0F, 6.0F), PartPose.offsetAndRotation(0.0F, 16.0F, 0.0F, ((float) Math.PI / 2F), 0.0F, 0.0F));
		CubeListBuilder cubelistbuilder = CubeListBuilder.create().texOffs(26, 0).addBox(-1.0F, 0.0F, -3.0F, 3.0F, 5.0F, 3.0F);
		partdefinition.addOrReplaceChild("right_leg", cubelistbuilder, PartPose.offset(-2.0F, 19.0F, 1.0F));
		partdefinition.addOrReplaceChild("left_leg", cubelistbuilder, PartPose.offset(1.0F, 19.0F, 1.0F));
		partdefinition.addOrReplaceChild("right_wing", CubeListBuilder.create().texOffs(24, 14).addBox(0.0F, 0.0F, -3.0F, 1.0F, 4.0F, 6.0F), PartPose.offset(-4.0F, 13.0F, 0.0F));
		partdefinition.addOrReplaceChild("left_wing", CubeListBuilder.create().texOffs(24, 14).addBox(-1.0F, 0.0F, -3.0F, 1.0F, 4.0F, 6.0F), PartPose.offset(4.0F, 13.0F, 0.0F));

		partdefinition2.addOrReplaceChild("mushroom", CubeListBuilder.create().texOffs(0, 24).addBox(-2.5F, -4.0F, 0.0F, 5.0F, 5.0F, 0.0F), PartPose.offsetAndRotation(1.0F, -6.0F, 0.0F, 0.0F, 1.5707963267948966F, 0.0F));
		partdefinition2.addOrReplaceChild("mushroom2", CubeListBuilder.create().texOffs(0, 24).addBox(-2.5F, -4.0F, 0.0F, 5.0F, 5.0F, 0.0F), PartPose.offset(1.0F, -6.0F, 0.0F));

		partdefinition3.addOrReplaceChild("mushroom3", CubeListBuilder.create().texOffs(0, 24).addBox(0.0F, -5.0F, -2.5F, 0.0F, 5.0F, 5.0F), PartPose.offsetAndRotation(1.0F, 1.5F, 3.0F, -1.5707963267948966F, 0.0F, 3.141592653589793F));
		partdefinition3.addOrReplaceChild("mushroom4", CubeListBuilder.create().texOffs(0, 24).addBox(0.0F, -5.0F, -2.5F, 0.0F, 5.0F, 5.0F), PartPose.offset(1.0F, 1.5F, 3.0F));

		return LayerDefinition.create(meshdefinition, 64, 32);
	}

	@Override
	public void setupAnim(T p_364616_) {
		super.setupAnim(p_364616_);
		float f = (Mth.sin(p_364616_.flap) + 1.0F) * p_364616_.flapSpeed;
		this.head.xRot = p_364616_.xRot * (float) (Math.PI / 180.0);
		this.head.yRot = p_364616_.yRot * (float) (Math.PI / 180.0);
		float f1 = p_364616_.walkAnimationSpeed;
		float f2 = p_364616_.walkAnimationPos;
		this.rightLeg.xRot = Mth.cos(f2 * 0.6662F) * 1.4F * f1;
		this.leftLeg.xRot = Mth.cos(f2 * 0.6662F + (float) Math.PI) * 1.4F * f1;
		this.rightWing.zRot = f;
		this.leftWing.zRot = -f;
	}
}
