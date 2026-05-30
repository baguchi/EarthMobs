package baguchan.earthmobsmod.client.model;// Made with Blockbench 4.1.1
// Exported for Minecraft version 1.17 with Mojang mappings
// Paste this class into your mod and generate all required imports


import net.minecraft.client.animation.definitions.RabbitAnimation;
import net.minecraft.client.model.BabyModelTransform;
import net.minecraft.client.model.animal.rabbit.RabbitModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;

import java.util.Set;

public class HyperRabbitModel extends RabbitModel {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final MeshTransformer BABY_TRANSFORMER = new BabyModelTransform(false, 8.0F, 6.0F, Set.of("head"));


	public HyperRabbitModel(ModelPart root) {
		super(root, RabbitAnimation.HOP, RabbitAnimation.IDLE_HEAD_TILT);
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition backlegs = partdefinition.addOrReplaceChild("backlegs", CubeListBuilder.create(), PartPose.offset(0.0F, 23.0F, 3.0F));

		PartDefinition left_hind_leg = backlegs.addOrReplaceChild("left_hind_leg", CubeListBuilder.create().texOffs(16, 13).addBox(0.0F, 0.0F, -4.0F, 3.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.5F, 0.0F, 0.0F, 0.0F, -0.3491F, 0.0F));

		PartDefinition right_hind_leg = backlegs.addOrReplaceChild("right_hind_leg", CubeListBuilder.create().texOffs(30, 13).addBox(-3.0F, 0.0F, -4.0F, 3.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.5F, 0.0F, 0.0F, 0.0F, 0.3491F, 0.0F));

		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(20, 0).addBox(-2.5F, -5.0F, -8.0F, 5.0F, 5.0F, 8.0F, new CubeDeformation(0.0F))
				.texOffs(46, 8).addBox(-4.5F, -5.0F, -4.0F, 2.0F, 2.0F, 0.0F, new CubeDeformation(0.0F))
				.texOffs(46, 8).mirror().addBox(2.5F, -4.0F, -4.0F, 2.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false)
				.texOffs(46, 8).mirror().addBox(0.5F, -7.0F, -2.0F, 2.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 23.0F, 3.0F, -0.3927F, 0.0F, 0.0F));

		PartDefinition head = body.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-2.5F, -5.0F, -5.0F, 5.0F, 5.0F, 5.0F, new CubeDeformation(-0.1F)), PartPose.offsetAndRotation(0.0F, -3.0F, -6.0F, 0.3927F, 0.0F, 0.0F));

		PartDefinition left_ear = head.addOrReplaceChild("left_ear", CubeListBuilder.create().texOffs(0, 10).addBox(-1.0F, -4.0F, -1.0F, 2.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(1.5F, -4.5F, -1.0F));

		PartDefinition left_ear2 = left_ear.addOrReplaceChild("left_ear2", CubeListBuilder.create(), PartPose.offset(-1.0F, -2.0F, -1.0F));

		PartDefinition right_ear = head.addOrReplaceChild("right_ear", CubeListBuilder.create().texOffs(6, 10).addBox(-2.5F, -4.0F, -1.0F, 2.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -4.5F, -1.0F));

		PartDefinition right_ear2 = right_ear.addOrReplaceChild("right_ear2", CubeListBuilder.create(), PartPose.offset(0.0F, 1.0F, 0.0F));

		PartDefinition frontlegs = body.addOrReplaceChild("frontlegs", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, -7.0F));

		PartDefinition right_front_leg = frontlegs.addOrReplaceChild("right_front_leg", CubeListBuilder.create().texOffs(32, 18).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.0F, -0.6F, 0.5F, 0.3927F, 0.0F, 0.0F));

		PartDefinition left_front_leg = frontlegs.addOrReplaceChild("left_front_leg", CubeListBuilder.create().texOffs(18, 18).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.0F, -0.6F, 0.5F, 0.3927F, 0.0F, 0.0F));

		PartDefinition tail = body.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(38, 0).addBox(-0.9F, -1.4F, 0.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -4.5F, -0.5F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}
}