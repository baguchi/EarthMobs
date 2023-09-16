package baguchan.earthmobsmod.client.model;// Made with Blockbench 4.7.1
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


import baguchan.earthmobsmod.entity.MagmaCow;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;

public class MagmaCowModel<T extends MagmaCow> extends HierarchicalModel<T> {
    private final ModelPart root;
    private final ModelPart head;
    private final ModelPart leg1;
    private final ModelPart leg2;
    private final ModelPart leg3;
    private final ModelPart leg4;
    private final ModelPart body;

    private float headXRot;

    public MagmaCowModel(ModelPart root) {
        this.root = root;
        this.head = root.getChild("head");
        this.leg1 = root.getChild("leg1");
        this.leg2 = root.getChild("leg2");
        this.leg3 = root.getChild("leg3");
        this.leg4 = root.getChild("leg4");
        this.body = root.getChild("body");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(35, 23).addBox(-4.0F, -4.0F, -6.0F, 8.0F, 8.0F, 6.0F, new CubeDeformation(0.0F))
                .texOffs(0, 51).addBox(4.0F, -3.0F, -3.0F, 5.0F, 3.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(0, 51).addBox(-9.0F, -3.0F, -3.0F, 5.0F, 3.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(41, 50).addBox(7.0F, -6.0F, -3.0F, 2.0F, 3.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(41, 50).mirror().addBox(-9.0F, -6.0F, -3.0F, 2.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, 4.0F, -9.0F));

        PartDefinition leg1 = partdefinition.addOrReplaceChild("leg1", CubeListBuilder.create().texOffs(45, 38).addBox(-2.0F, 2.75F, -2.0F, 4.0F, 7.0F, 4.0F, new CubeDeformation(0.25F))
                .texOffs(41, 12).addBox(-2.0F, 3.5F, -2.0F, 4.0F, 6.0F, 4.0F, new CubeDeformation(0.5F))
                .texOffs(41, 0).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 7.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-3.0F, 14.0F, 7.0F));

        PartDefinition leg2 = partdefinition.addOrReplaceChild("leg2", CubeListBuilder.create().texOffs(45, 38).addBox(-2.0F, 2.75F, -2.0F, 4.0F, 7.0F, 4.0F, new CubeDeformation(0.25F))
                .texOffs(41, 12).addBox(-2.0F, 3.5F, -2.0F, 4.0F, 6.0F, 4.0F, new CubeDeformation(0.5F))
                .texOffs(41, 0).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 7.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(3.0F, 14.0F, 7.0F));

        PartDefinition leg3 = partdefinition.addOrReplaceChild("leg3", CubeListBuilder.create().texOffs(45, 38).addBox(-2.0F, 2.75F, -1.0F, 4.0F, 7.0F, 4.0F, new CubeDeformation(0.25F))
                .texOffs(41, 0).addBox(-2.0F, 0.0F, -1.0F, 4.0F, 7.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(41, 12).addBox(-2.0F, 3.5F, -1.0F, 4.0F, 6.0F, 4.0F, new CubeDeformation(0.5F)), PartPose.offset(-3.0F, 14.0F, -6.0F));

        PartDefinition leg4 = partdefinition.addOrReplaceChild("leg4", CubeListBuilder.create().texOffs(45, 38).addBox(-2.0F, 2.75F, -1.0F, 4.0F, 7.0F, 4.0F, new CubeDeformation(0.25F))
                .texOffs(41, 12).addBox(-2.0F, 3.5F, -1.0F, 4.0F, 6.0F, 4.0F, new CubeDeformation(0.5F))
                .texOffs(41, 0).addBox(-2.0F, 0.0F, -1.0F, 4.0F, 7.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(3.0F, 14.0F, -6.0F));

        PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-5.0F, -10.0F, -8.0F, 10.0F, 18.0F, 10.0F, new CubeDeformation(0.0F))
                .texOffs(0, 29).addBox(-6.0F, -11.0F, -5.0F, 12.0F, 11.0F, 10.0F, new CubeDeformation(0.0F))
                .texOffs(15, 51).addBox(-2.0F, 2.0F, -9.0F, 4.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 6.0F, 2.0F, 1.5708F, 0.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 64, 64);
    }

    @Override
    public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.head.xRot = headPitch * ((float) Math.PI / 180F);
        this.head.yRot = netHeadYaw * ((float) Math.PI / 180F);
        this.leg1.xRot = Mth.cos(limbSwing * 0.68F) * 1.4F * limbSwingAmount;
        this.leg2.xRot = Mth.cos(limbSwing * 0.68F + (float) Math.PI) * 1.4F * limbSwingAmount;
        this.leg3.xRot = Mth.cos(limbSwing * 0.68F + (float) Math.PI) * 1.4F * limbSwingAmount;
        this.leg4.xRot = Mth.cos(limbSwing * 0.68F) * 1.4F * limbSwingAmount;
        this.head.xRot = this.headXRot;
    }

    public void prepareMobModel(T p_103687_, float p_103688_, float p_103689_, float p_103690_) {
        super.prepareMobModel(p_103687_, p_103688_, p_103689_, p_103690_);
        this.headXRot = p_103687_.getHeadEatAngleScale(p_103690_);

        if (this.attackTime > 0) {
            this.headXRot = -0.95F * Mth.sin(this.attackTime * (float) Math.PI);
        }
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        if (this.young) {

            poseStack.pushPose();
            float f = 1.5F / 2.0F;
            poseStack.scale(f, f, f);
            poseStack.translate(0, 15.0F / 16.0F, 4.0F / 16F);
            head.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
            poseStack.popPose();
            poseStack.pushPose();

            poseStack.scale(0.5F, 0.5F, 0.5F);
            poseStack.translate(0, 24F / 16.0F, 0);
            leg1.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
            leg2.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
            leg3.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
            leg4.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
            body.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);

            poseStack.popPose();
        } else {
            poseStack.pushPose();
            leg1.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
            leg2.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
            leg3.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
            leg4.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
            body.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
            head.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
            poseStack.popPose();
        }
    }

    @Override
    public ModelPart root() {
        return this.root;
    }
}