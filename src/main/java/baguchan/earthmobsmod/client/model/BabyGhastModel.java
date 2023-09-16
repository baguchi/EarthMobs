package baguchan.earthmobsmod.client.model;// Made with Blockbench 4.5.2
// Exported for Minecraft version 1.17 - 1.18 with Mojang mappings
// Paste this class into your mod and generate all required imports


import baguchan.earthmobsmod.client.animation.BabyGhastAnimation;
import baguchan.earthmobsmod.entity.BabyGhast;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.client.renderer.RenderType;

public class BabyGhastModel<T extends BabyGhast> extends HierarchicalModel<T> {

	private final ModelPart root;
	private final ModelPart body;
	public final ModelPart legRoot;
	public final ModelPart core;

	public BabyGhastModel(ModelPart root) {
		super(RenderType::entityTranslucent);
		this.root = root;
		this.body = root.getChild("body");
		this.legRoot = this.body.getChild("legRoot");
		this.core = this.body.getChild("core");
	}

	public static LayerDefinition createCoreLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-3.0F, -4.0F, -3.0F, 6.0F, 6.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 21.0F, 0.0F));

		PartDefinition legRoot = body.addOrReplaceChild("legRoot", CubeListBuilder.create(), PartPose.offset(0.0F, 2.0F, 0.0F));


		PartDefinition core = body.addOrReplaceChild("core", CubeListBuilder.create().texOffs(0, 12).addBox(-2.0F, -2.0F, -2.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -1.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 32, 32);
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-3.0F, -4.0F, -3.0F, 6.0F, 6.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 21.0F, 0.0F));

		PartDefinition legRoot = body.addOrReplaceChild("legRoot", CubeListBuilder.create(), PartPose.offset(0.0F, 2.0F, 0.0F));

		PartDefinition bone3 = legRoot.addOrReplaceChild("bone3", CubeListBuilder.create().texOffs(4, 22).addBox(-0.5F, 0.0F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.5F, 0.0F, -2.5F, -0.5672F, 0.0F, 0.0F));

		PartDefinition bone6 = legRoot.addOrReplaceChild("bone6", CubeListBuilder.create().texOffs(0, 20).addBox(-0.5F, 0.0F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.5F, 0.0F, 2.5F, 0.5672F, 0.0F, 0.0F));

		PartDefinition bone5 = legRoot.addOrReplaceChild("bone5", CubeListBuilder.create().texOffs(4, 20).addBox(-0.5F, 0.0F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 2.5F, 0.5672F, 0.0F, 0.0F));

		PartDefinition bone2 = legRoot.addOrReplaceChild("bone2", CubeListBuilder.create().texOffs(0, 22).addBox(-0.5F, 0.0F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.5F, 0.0F, -2.5F, -0.5672F, 0.0F, 0.0F));

		PartDefinition bone4 = legRoot.addOrReplaceChild("bone4", CubeListBuilder.create().texOffs(8, 20).addBox(-0.5F, 0.0F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.5F, 0.0F, 2.5F, 0.5672F, 0.0F, 0.0F));

		PartDefinition bone = legRoot.addOrReplaceChild("bone", CubeListBuilder.create().texOffs(0, 24).addBox(-0.5F, 0.0F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, -2.5F, -0.5672F, 0.0F, 0.0F));

		PartDefinition bone7 = legRoot.addOrReplaceChild("bone7", CubeListBuilder.create(), PartPose.offset(-2.5F, 0.0F, 1.0F));

		PartDefinition cube_r1 = bone7.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(12, 20).addBox(-0.5F, 0.0F, -2.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 1.5F, 0.0F, 0.0F, 0.5672F));

		PartDefinition bone8 = legRoot.addOrReplaceChild("bone8", CubeListBuilder.create().texOffs(8, 22).addBox(-0.5F, 0.0F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.5F, 0.0F, -1.0F, 0.0F, 0.0F, -0.5672F));

		PartDefinition bone9 = legRoot.addOrReplaceChild("bone9", CubeListBuilder.create().texOffs(12, 20).addBox(-0.5F, 0.0F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.5F, 0.0F, -1.0F, 0.0F, 0.0F, 0.5672F));

		PartDefinition bone10 = legRoot.addOrReplaceChild("bone10", CubeListBuilder.create().texOffs(8, 22).addBox(-0.5F, 0.0F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.5F, 0.0F, 1.0F, 0.0F, 0.0F, -0.5672F));

		PartDefinition core = body.addOrReplaceChild("core", CubeListBuilder.create().texOffs(0, 12).addBox(-2.0F, -2.0F, -2.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -1.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 32, 32);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.root().getAllParts().forEach(ModelPart::resetPose);
		float f = Math.min((float) entity.getDeltaMovement().lengthSqr() * 100.0F, 8.0F);
		this.animate(entity.idleAnimationState, BabyGhastAnimation.IDLE, ageInTicks, f);
		this.animate(entity.shootAnimationState, BabyGhastAnimation.SHOOT, ageInTicks);
	}

	@Override
	public ModelPart root() {
		return this.root;
	}
}