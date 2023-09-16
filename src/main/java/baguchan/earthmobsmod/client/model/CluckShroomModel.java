package baguchan.earthmobsmod.client.model;

import baguchan.earthmobsmod.entity.CluckShroom;
import com.google.common.collect.ImmutableList;
import net.minecraft.client.model.ChickenModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;

public class CluckShroomModel<T extends CluckShroom> extends ChickenModel<T> {
	private final ModelPart head;
	private final ModelPart body;
	private final ModelPart rightLeg;
	private final ModelPart leftLeg;
	private final ModelPart rightWing;
	private final ModelPart leftWing;
	private final ModelPart beak;
	private final ModelPart redThing;

	public CluckShroomModel(ModelPart p_170490_) {
		super(p_170490_);
		this.head = p_170490_.getChild("head");
		this.beak = p_170490_.getChild("beak");
		this.redThing = p_170490_.getChild("red_thing");
		this.body = p_170490_.getChild("body");
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

	protected Iterable<ModelPart> headParts() {
		return ImmutableList.of(this.head, this.beak, this.redThing);
	}

	protected Iterable<ModelPart> bodyParts() {
		return ImmutableList.of(this.body, this.rightLeg, this.leftLeg, this.rightWing, this.leftWing);
	}
}
