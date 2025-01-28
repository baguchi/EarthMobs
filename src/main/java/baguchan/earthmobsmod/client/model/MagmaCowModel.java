package baguchan.earthmobsmod.client.model;// Made with Blockbench 4.7.1
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


import baguchan.earthmobsmod.client.render.state.MagmaCowRenderState;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;

public class MagmaCowModel<T extends MagmaCowRenderState> extends EntityModel<T> {
    private final ModelPart root;
    private final ModelPart head;
    private final ModelPart leg1;
    private final ModelPart leg2;
    private final ModelPart leg3;
    private final ModelPart leg4;
    private final ModelPart body;

    private float headXRot;

    public MagmaCowModel(ModelPart root) {
        super(root);
        this.root = root;
        this.head = root.getChild("head");
        this.leg1 = root.getChild("leg1");
        this.leg2 = root.getChild("leg2");
        this.leg3 = root.getChild("leg3");
        this.leg4 = root.getChild("leg4");
        this.body = root.getChild("body");
    }

    public static LayerDefinition createAnimateBodyLayer() {
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
    public void setupAnim(T entity) {
        super.setupAnim(entity);
        this.head.yRot = entity.yRot * ((float) Math.PI / 180F);
        this.leg1.xRot = Mth.cos(entity.walkAnimationPos * 0.68F) * 1.4F * entity.walkAnimationSpeed;
        this.leg2.xRot = Mth.cos(entity.walkAnimationPos * 0.68F + (float) Math.PI) * 1.4F * entity.walkAnimationSpeed;
        this.leg3.xRot = Mth.cos(entity.walkAnimationPos * 0.68F + (float) Math.PI) * 1.4F * entity.walkAnimationSpeed;
        this.leg4.xRot = Mth.cos(entity.walkAnimationPos * 0.68F) * 1.4F * entity.walkAnimationSpeed;
        this.head.xRot = entity.headEatAngleScale;

    }

}