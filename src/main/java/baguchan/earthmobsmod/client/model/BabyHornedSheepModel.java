package baguchan.earthmobsmod.client.model;// Made with Blockbench 5.0.7
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


import baguchan.earthmobsmod.client.render.state.HornedSheepRenderState;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;

public class BabyHornedSheepModel<T extends HornedSheepRenderState> extends HornedSheepModel<T> {
    private final ModelPart head;
    private final ModelPart right_hind_leg;
    private final ModelPart left_hind_leg;
    private final ModelPart right_front_leg;
    private final ModelPart left_front_leg;
    private final ModelPart body;

    public BabyHornedSheepModel(ModelPart root) {
        super(root);
        this.head = root.getChild("head");
        this.right_hind_leg = root.getChild("right_hind_leg");
        this.left_hind_leg = root.getChild("left_hind_leg");
        this.right_front_leg = root.getChild("right_front_leg");
        this.left_front_leg = root.getChild("left_front_leg");
        this.body = root.getChild("body");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-2.5F, -4.5F, -3.5F, 5.0F, 5.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 15.5F, -2.5F));

        PartDefinition right_horn = head.addOrReplaceChild("right_horn", CubeListBuilder.create().texOffs(15, 0).addBox(2.5F, -4.5F, -1.5F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition left_horn = head.addOrReplaceChild("left_horn", CubeListBuilder.create().texOffs(15, 0).mirror().addBox(2.5F, -4.5F, -1.5F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-6.0F, 0.0F, 0.0F));

        PartDefinition right_hind_leg = partdefinition.addOrReplaceChild("right_hind_leg", CubeListBuilder.create().texOffs(0, 23).addBox(-0.975F, 0.0F, -1.0F, 2.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-2.0F, 19.0F, 3.0F));

        PartDefinition left_hind_leg = partdefinition.addOrReplaceChild("left_hind_leg", CubeListBuilder.create().texOffs(24, 12).addBox(-1.025F, 0.0F, -1.0F, 2.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(2.0F, 19.0F, 3.0F));

        PartDefinition right_front_leg = partdefinition.addOrReplaceChild("right_front_leg", CubeListBuilder.create().texOffs(8, 23).addBox(-0.975F, 0.0F, -1.0F, 2.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-2.0F, 19.0F, -2.0F));

        PartDefinition left_front_leg = partdefinition.addOrReplaceChild("left_front_leg", CubeListBuilder.create().texOffs(24, 5).addBox(-1.025F, 0.0F, -1.0F, 2.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(2.0F, 19.0F, -2.0F));

        PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 10).addBox(-3.0F, -1.0F, -6.5F, 6.0F, 4.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 16.0F, 2.5F));

        return LayerDefinition.create(meshdefinition, 64, 32);
    }

    @Override
    public void setupAnim(T state) {
        super.setupAnim(state);
        this.head.xRot = state.xRot * ((float) Math.PI / 180F);
        this.head.yRot = state.yRot * ((float) Math.PI / 180F);
        float animationPos = state.walkAnimationPos;
        float animationSpeed = state.walkAnimationSpeed;
        this.right_hind_leg.xRot = Mth.cos((double) (animationPos * 0.6662F)) * 1.4F * animationSpeed;
        this.left_hind_leg.xRot = Mth.cos((double) (animationPos * 0.6662F + (float) Math.PI)) * 1.4F * animationSpeed;
        this.right_front_leg.xRot = Mth.cos((double) (animationPos * 0.6662F + (float) Math.PI)) * 1.4F * animationSpeed;
        this.left_front_leg.xRot = Mth.cos((double) (animationPos * 0.6662F)) * 1.4F * animationSpeed;

        this.head.y = this.head.y + state.headEatPositionScale * 9.0F * state.ageScale;
        this.head.xRot = state.headEatAngleScale + (state.agressiveScale * 25F) * ((float) Math.PI / 180F);

        boolean horn = state.horn;
    }
}