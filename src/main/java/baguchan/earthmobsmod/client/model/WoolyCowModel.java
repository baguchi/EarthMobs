package baguchan.earthmobsmod.client.model;// Made with Blockbench 4.12.6
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


import baguchan.earthmobsmod.client.render.state.WoolyCowRenderState;
import net.minecraft.client.model.BabyModelTransform;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;

import java.util.Set;

public class WoolyCowModel<T extends WoolyCowRenderState> extends EntityModel<T> {
    public static final MeshTransformer BABY_TRANSFORMER = new BabyModelTransform(Set.of("head"));

    private final ModelPart body;
    private final ModelPart wool_front;
    private final ModelPart wool_back;
    private final ModelPart wool_sideL;
    private final ModelPart wool_sideR;
    private final ModelPart head;
    private final ModelPart left_hind_leg;
    private final ModelPart right_hind_leg;
    private final ModelPart left_front_leg;
    private final ModelPart right_front_leg;

    public WoolyCowModel(ModelPart root) {
        super(root);
        this.body = root.getChild("body");
        this.wool_front = this.body.getChild("wool_front");
        this.wool_back = this.body.getChild("wool_back");
        this.wool_sideL = this.body.getChild("wool_sideL");
        this.wool_sideR = this.body.getChild("wool_sideR");
        this.head = root.getChild("head");
        this.left_hind_leg = root.getChild("left_hind_leg");
        this.right_hind_leg = root.getChild("right_hind_leg");
        this.left_front_leg = root.getChild("left_front_leg");
        this.right_front_leg = root.getChild("right_front_leg");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(18, 4).addBox(-6.0F, -10.0F, -7.0F, 12.0F, 18.0F, 10.0F, new CubeDeformation(0.0F))
                .texOffs(52, 33).addBox(-2.0F, 1.75F, -8.0F, 4.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 5.0F, 2.0F, 1.5708F, 0.0F, 0.0F));

        PartDefinition wool_front = body.addOrReplaceChild("wool_front", CubeListBuilder.create().texOffs(52, 3).addBox(0.0F, -4.0F, -3.0F, 0.0F, 8.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -10.0F, -7.0F, 0.0F, 0.0F, -1.5708F));

        PartDefinition wool_back = body.addOrReplaceChild("wool_back", CubeListBuilder.create().texOffs(52, 3).addBox(0.0F, -4.0F, -3.0F, 0.0F, 8.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 8.0F, -7.0F, 0.0F, 0.0F, -1.5708F));

        PartDefinition wool_sideL = body.addOrReplaceChild("wool_sideL", CubeListBuilder.create(), PartPose.offset(6.0F, -1.0F, -7.0F));

        PartDefinition cube_r1 = wool_sideL.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(29, -11).addBox(0.0F, 0.0F, -5.5F, 0.0F, 3.0F, 11.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -1.5708F, 0.0F, 0.0F));

        PartDefinition wool_sideR = body.addOrReplaceChild("wool_sideR", CubeListBuilder.create(), PartPose.offsetAndRotation(-6.0F, -1.0F, -7.0F, 0.0F, 0.0F, -3.1416F));

        PartDefinition cube_r2 = wool_sideR.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(29, -11).addBox(0.0F, 0.0F, -5.5F, 0.0F, 3.0F, 11.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -1.5708F, 0.0F, 0.0F));

        PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -4.0F, -6.0F, 8.0F, 8.0F, 6.0F, new CubeDeformation(0.0F))
                .texOffs(22, 0).addBox(4.0F, -5.0F, -4.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(22, 0).addBox(-5.0F, -5.0F, -4.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(1, 33).addBox(-3.0F, 1.0F, -7.0F, 6.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 4.0F, -8.0F));

        PartDefinition left_hind_leg = partdefinition.addOrReplaceChild("left_hind_leg", CubeListBuilder.create().texOffs(0, 16).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(4.0F, 12.0F, 7.0F));

        PartDefinition right_hind_leg = partdefinition.addOrReplaceChild("right_hind_leg", CubeListBuilder.create().texOffs(0, 16).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-4.0F, 12.0F, 7.0F));

        PartDefinition left_front_leg = partdefinition.addOrReplaceChild("left_front_leg", CubeListBuilder.create().texOffs(0, 16).addBox(-2.0F, 0.0F, -1.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(4.0F, 12.0F, -6.0F));

        PartDefinition right_front_leg = partdefinition.addOrReplaceChild("right_front_leg", CubeListBuilder.create().texOffs(0, 16).addBox(-2.0F, 0.0F, -1.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-4.0F, 12.0F, -6.0F));

        return LayerDefinition.create(meshdefinition, 64, 64);
    }

    @Override
    public void setupAnim(T entity) {
        super.setupAnim(entity);
        this.head.xRot = entity.xRot * (float) (Math.PI / 180.0);
        this.head.yRot = entity.yRot * (float) (Math.PI / 180.0);
        float f = entity.walkAnimationPos;
        float f1 = entity.walkAnimationSpeed;
        this.right_hind_leg.xRot = Mth.cos(f * 0.6662F) * 1.4F * f1;
        this.left_hind_leg.xRot = Mth.cos(f * 0.6662F + (float) Math.PI) * 1.4F * f1;
        this.right_front_leg.xRot = Mth.cos(f * 0.6662F + (float) Math.PI) * 1.4F * f1;
        this.left_front_leg.xRot = Mth.cos(f * 0.6662F) * 1.4F * f1;
        this.head.y = this.head.y + entity.headEatPositionScale * 9.0F * entity.ageScale;
        this.head.xRot = entity.headEatAngleScale;

    }
}