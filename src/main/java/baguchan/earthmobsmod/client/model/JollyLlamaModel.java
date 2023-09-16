package baguchan.earthmobsmod.client.model;// Made with Blockbench 4.7.4
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


import baguchan.earthmobsmod.client.animation.JollyLlamaAnimation;
import baguchan.earthmobsmod.entity.JollyLlama;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;

public class JollyLlamaModel<T extends JollyLlama> extends HierarchicalModel<T> {
    private final ModelPart root;
    private final ModelPart head;
    private final ModelPart body;
    private final ModelPart leg1;
    private final ModelPart leg2;
    private final ModelPart leg3;
    private final ModelPart leg4;
    private final ModelPart chest_left;
    private final ModelPart chest_right;

    public JollyLlamaModel(ModelPart root) {
        this.root = root;
        this.head = root.getChild("head");
        this.body = root.getChild("body");
        this.leg1 = root.getChild("leg1");
        this.leg2 = root.getChild("leg2");
        this.leg3 = root.getChild("leg3");
        this.leg4 = root.getChild("leg4");
        this.chest_left = this.body.getChild("chest_left");
        this.chest_right = this.body.getChild("chest_right");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(36, 24).addBox(-2.0F, -14.0F, -10.0F, 4.0F, 4.0F, 9.0F, new CubeDeformation(0.0F))
                .texOffs(44, 0).addBox(-4.0F, -16.0F, -6.0F, 8.0F, 18.0F, 6.0F, new CubeDeformation(0.0F))
                .texOffs(39, 44).addBox(-4.5F, -16.0F, -6.5F, 9.0F, 12.0F, 7.0F, new CubeDeformation(0.0F))
                .texOffs(16, 51).addBox(4.0F, -31.0F, -3.5F, 7.0F, 16.0F, 0.0F, new CubeDeformation(0.0F))
                .texOffs(16, 51).mirror().addBox(-11.0F, -31.0F, -3.5F, 7.0F, 16.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(0, 5).addBox(1.0F, -19.0F, -4.0F, 3.0F, 3.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(0, 0).addBox(-4.0F, -19.0F, -4.0F, 3.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 7.0F, -6.0F));

        PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-6.0F, -10.0F, -7.0F, 12.0F, 18.0F, 10.0F, new CubeDeformation(0.0F))
                .texOffs(0, 28).addBox(-6.5F, -6.0F, -6.5F, 13.0F, 13.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 5.0F, 2.0F, 1.5708F, 0.0F, 0.0F));

        PartDefinition chest_left = body.addOrReplaceChild("chest_left", CubeListBuilder.create().texOffs(72, 0).addBox(-4.0F, -2.5F, -3.5F, 8.0F, 8.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.0F, 3.0F, 0.0F, -1.5708F, 0.0F, 1.5708F));

        PartDefinition chest_right = body.addOrReplaceChild("chest_right", CubeListBuilder.create().texOffs(72, 13).addBox(-4.0F, -2.5F, -3.5F, 8.0F, 8.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-9.0F, 3.0F, 0.0F, -1.5708F, 0.0F, 1.5708F));

        PartDefinition leg1 = partdefinition.addOrReplaceChild("leg1", CubeListBuilder.create().texOffs(0, 51).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 14.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-3.5F, 10.0F, 6.0F));

        PartDefinition leg2 = partdefinition.addOrReplaceChild("leg2", CubeListBuilder.create().texOffs(0, 51).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 14.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(3.5F, 10.0F, 6.0F));

        PartDefinition leg3 = partdefinition.addOrReplaceChild("leg3", CubeListBuilder.create().texOffs(0, 51).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 14.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-3.5F, 10.0F, -5.0F));

        PartDefinition leg4 = partdefinition.addOrReplaceChild("leg4", CubeListBuilder.create().texOffs(0, 51).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 14.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(3.5F, 10.0F, -5.0F));

        return LayerDefinition.create(meshdefinition, 128, 128);
    }

    @Override
    public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.root().getAllParts().forEach(ModelPart::resetPose);
        this.head.xRot = headPitch * ((float) Math.PI / 180F);
        this.head.yRot = netHeadYaw * ((float) Math.PI / 180F);
        this.chest_right.visible = entity.hasChest();
        this.chest_left.visible = entity.hasChest();
        if (this.young) {
            this.applyStatic(JollyLlamaAnimation.BABY);
        }
        this.animateWalk(JollyLlamaAnimation.WALK, limbSwing, limbSwingAmount, 1.0F, 1.0F);
    }

    @Override
    public ModelPart root() {
        return this.root;
    }
}