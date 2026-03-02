package baguchan.earthmobsmod.client.model;

import baguchi.bagus_lib.client.layer.CustomArmorRender;
import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.AnimationUtils;
import net.minecraft.client.model.ArmedModel;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.renderer.entity.state.ZombieRenderState;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.HumanoidArm;

public class AbstractLobberZombieModel<T extends ZombieRenderState> extends EntityModel<T> implements CustomArmorRender<T>, ArmedModel<T> {
    public final ModelPart head;
    public final ModelPart body;
    public final ModelPart left_arm;
    public final ModelPart right_arm;
    public final ModelPart left_leg;
    public final ModelPart right_leg;

    public AbstractLobberZombieModel(ModelPart root) {
        super(root);
        this.head = root.getChild("head");
        this.body = root.getChild("body");
        this.left_arm = root.getChild("left_arm");
        this.right_arm = root.getChild("right_arm");
        this.left_leg = root.getChild("left_leg");
        this.right_leg = root.getChild("right_leg");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 16).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F))
                .texOffs(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.5F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 32).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(28, 28).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.25F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition left_arm = partdefinition.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(20, 44).addBox(-1.0F, -2.0F, -2.0F, 5.0F, 14.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(32, 0).addBox(-1.0F, -2.0F, -2.0F, 5.0F, 14.0F, 4.0F, new CubeDeformation(0.25F)), PartPose.offsetAndRotation(5.0F, 2.0F, 0.0F, -1.0908F, 0.0F, 0.0F));

        PartDefinition right_arm = partdefinition.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(60, 0).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 11.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(54, 46).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 11.0F, 4.0F, new CubeDeformation(0.25F)), PartPose.offsetAndRotation(-5.0F, 2.0F, 0.0F, -1.1345F, 0.0F, 0.0F));

        PartDefinition left_leg = partdefinition.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(0, 48).addBox(-1.9F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(38, 44).addBox(-1.9F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.25F)), PartPose.offset(1.9F, 12.0F, 0.0F));

        PartDefinition right_leg = partdefinition.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(52, 30).addBox(-2.1F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(48, 14).addBox(-2.1F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.25F)), PartPose.offset(-1.9F, 12.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 128, 128);
    }

    @Override
    public void setupAnim(T entity) {
        super.setupAnim(entity);
        this.head.yRot = entity.yRot * ((float) Math.PI / 180F);
        this.head.xRot = entity.xRot * ((float) Math.PI / 180F);

        AnimationUtils.animateZombieArms(this.left_arm, this.right_arm, entity.isAggressive, entity);

        this.right_leg.xRot = Mth.cos(entity.walkAnimationPos * 0.6662F) * 1.4F * entity.walkAnimationSpeed * 0.5F;
        this.right_leg.yRot = 0.0F;
        this.right_leg.zRot = 0.0F;
        this.left_leg.xRot = Mth.cos(entity.walkAnimationPos * 0.6662F + (float) Math.PI) * 1.4F * entity.walkAnimationSpeed * 0.5F;
        this.left_leg.yRot = 0.0F;
        this.left_leg.zRot = 0.0F;

        if (entity.isPassenger) {
            this.right_leg.xRot = -1.4137167F;
            this.right_leg.yRot = (float) (Math.PI / 10);
            this.right_leg.zRot = 0.07853982F;
            this.left_leg.xRot = -1.4137167F;
            this.left_leg.yRot = (float) (-Math.PI / 10);
            this.left_leg.zRot = -0.07853982F;
        }
    }

    @Override
    public void translateToHead(T entity, ModelPart part, PoseStack poseStack) {
        part.translateAndRotate(poseStack);
    }

    @Override
    public void translateToChest(T entity, ModelPart part, PoseStack poseStack) {
        part.translateAndRotate(poseStack);
    }

    @Override
    public void translateToLeg(T entity, ModelPart part, PoseStack poseStack) {
        part.translateAndRotate(poseStack);
    }

    @Override
    public void translateToChestPat(T entity, ModelPart part, PoseStack poseStack) {
        part.translateAndRotate(poseStack);
        poseStack.scale(1.05F, 1.05F, 1.05F);
    }

    public Iterable<ModelPart> rightHandArmors() {
        return ImmutableList.of(this.right_arm);
    }

    public Iterable<ModelPart> leftHandArmors() {
        return ImmutableList.of(this.left_arm);
    }

    public Iterable<ModelPart> rightLegPartArmors() {
        return ImmutableList.of(this.right_leg);
    }

    public Iterable<ModelPart> leftLegPartArmors() {
        return ImmutableList.of(this.left_leg);
    }

    public Iterable<ModelPart> bodyPartArmors() {
        return ImmutableList.of(this.body);
    }

    public Iterable<ModelPart> headPartArmors() {
        return ImmutableList.of(this.head);
    }

    @Override
    public void translateToHand(T p_434082_, HumanoidArm p_102854_, PoseStack p_102855_) {
        this.getArm(p_102854_).translateAndRotate(p_102855_);
    }

    public ModelPart getArm(HumanoidArm p_102852_) {
        return p_102852_ == HumanoidArm.LEFT ? this.left_arm : this.right_arm;
    }
}