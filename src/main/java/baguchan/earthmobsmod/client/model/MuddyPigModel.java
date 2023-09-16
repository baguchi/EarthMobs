package baguchan.earthmobsmod.client.model;// Made with Blockbench 4.1.5
// Exported for Minecraft version 1.17 with Mojang mappings
// Paste this class into your mod and generate all required imports


import baguchan.earthmobsmod.api.ISheared;
import net.minecraft.client.model.PigModel;
import net.minecraft.client.model.QuadrupedModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.world.entity.Entity;

public class MuddyPigModel<T extends Entity> extends PigModel<T> {
	private final ModelPart body;
	private final ModelPart head;
	private final ModelPart mud;
	private final ModelPart flower;

	public MuddyPigModel(ModelPart root) {
		super(root);
		this.body = root.getChild("body");
		this.head = root.getChild("head");
		this.mud = this.head.getChild("mud");
		this.flower = mud.getChild("flower");
	}

	public static LayerDefinition createBodyLayer(CubeDeformation p_170801_) {
		MeshDefinition meshdefinition = QuadrupedModel.createBodyMesh(6, p_170801_);
		PartDefinition partdefinition = meshdefinition.getRoot();
		PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -4.0F, -8.0F, 8.0F, 8.0F, 8.0F, p_170801_).texOffs(16, 16).addBox(-2.0F, 0.0F, -9.0F, 4.0F, 3.0F, 1.0F, p_170801_), PartPose.offset(0.0F, 12.0F, -6.0F));

		PartDefinition mud = head.addOrReplaceChild("mud", CubeListBuilder.create().texOffs(48, 0).addBox(-1.0F, -1.0F, 0.0F, 4.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-2.0F, -4.0F, -7.0F));

		PartDefinition flower = mud.addOrReplaceChild("flower", CubeListBuilder.create().texOffs(20, 0).addBox(-3.0F, 0.0F, 0.0F, 4.0F, 0.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.0F, -1.0F, 2.0F, 1.5708F, 0.0F, 0.0F));


		return LayerDefinition.create(meshdefinition, 64, 32);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		super.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);

		if (entity instanceof ISheared) {
			this.flower.visible = !((ISheared) entity).isSheared();
		}
	}
}