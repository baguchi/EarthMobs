package baguchan.earthmobsmod.client.model;
// Made with Blockbench 4.0.1
// Exported for Minecraft version 1.17 with Mojang mappings
// Paste this class into your mod and generate all required imports


import baguchan.earthmobsmod.entity.HornedSheep;
import net.minecraft.client.model.SheepModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;

public class HornedSheepModel<T extends HornedSheep> extends SheepModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	private final ModelPart body;
	private final ModelPart head;
	private float headXRot;

	public HornedSheepModel(ModelPart root) {
		super(root);
		this.body = root.getChild("body");
		this.head = root.getChild("head");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create(), PartPose.offset(0.0F, 5.0F, 2.0F));

		PartDefinition rotation = body.addOrReplaceChild("rotation", CubeListBuilder.create().texOffs(22, 10).addBox(-4.0F, -10.0F, -7.0F, 8.0F, 16.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 1.5708F, 0.0F, 0.0F));

		PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-3.0F, -4.0F, -6.0F, 6.0F, 6.0F, 8.0F, new CubeDeformation(0.0F))
				.texOffs(44, 3).addBox(3.0F, -5.0F, -5.0F, 4.0F, 7.0F, 6.0F, new CubeDeformation(0.0F))
				.texOffs(30, 2).addBox(3.0F, -1.0F, -8.0F, 4.0F, 3.0F, 3.0F, new CubeDeformation(0.0F))
				.texOffs(44, 3).mirror().addBox(-7.0F, -5.0F, -5.0F, 4.0F, 7.0F, 6.0F, new CubeDeformation(0.0F)).mirror(false)
				.texOffs(30, 2).mirror().addBox(-7.0F, -1.0F, -8.0F, 4.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, 6.0F, -8.0F));

		PartDefinition leg1 = partdefinition.addOrReplaceChild("right_hind_leg", CubeListBuilder.create().texOffs(0, 16).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-3.0F, 12.0F, 7.0F));

		PartDefinition leg2 = partdefinition.addOrReplaceChild("left_hind_leg", CubeListBuilder.create().texOffs(0, 16).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(3.0F, 12.0F, 7.0F));

		PartDefinition leg3 = partdefinition.addOrReplaceChild("right_front_leg", CubeListBuilder.create().texOffs(0, 16).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-3.0F, 12.0F, -5.0F));

		PartDefinition leg4 = partdefinition.addOrReplaceChild("left_front_leg", CubeListBuilder.create().texOffs(0, 16).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(3.0F, 12.0F, -5.0F));

		return LayerDefinition.create(meshdefinition, 64, 32);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		super.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
        float f = ageInTicks - (float) entity.tickCount;

        this.head.xRot = this.headXRot + (entity.getAggressiveAnimationScale(f) * 25F) * ((float) Math.PI / 180F);
	}

	public void prepareMobModel(T p_103687_, float p_103688_, float p_103689_, float p_103690_) {
		super.prepareMobModel(p_103687_, p_103688_, p_103689_, p_103690_);
		this.headXRot = p_103687_.getHeadEatAngleScale(p_103690_);

		if (this.attackTime > 0) {
			this.headXRot = -0.95F * Mth.sin(this.attackTime * (float) Math.PI);
		}
	}
}