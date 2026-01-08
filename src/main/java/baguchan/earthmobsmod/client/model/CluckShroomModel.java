package baguchan.earthmobsmod.client.model;

import net.minecraft.client.model.animal.chicken.ChickenModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;

public class CluckShroomModel extends ChickenModel {
	public CluckShroomModel(ModelPart p_170490_) {
		super(p_170490_);
	}

	public static LayerDefinition createBodyLayer() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition root = mesh.getRoot();
        PartDefinition head = root.addOrReplaceChild(
                "head", CubeListBuilder.create().texOffs(0, 0).addBox(-2.0F, -6.0F, -2.0F, 4.0F, 6.0F, 3.0F), PartPose.offset(0.0F, 15.0F, -4.0F)
        );
        head.addOrReplaceChild("beak", CubeListBuilder.create().texOffs(14, 0).addBox(-2.0F, -4.0F, -4.0F, 4.0F, 2.0F, 2.0F), PartPose.ZERO);
        head.addOrReplaceChild("red_thing", CubeListBuilder.create().texOffs(14, 4).addBox(-1.0F, -2.0F, -3.0F, 2.0F, 2.0F, 2.0F), PartPose.ZERO);
        PartDefinition body = root.addOrReplaceChild(
                "body",
                CubeListBuilder.create().texOffs(0, 9).addBox(-3.0F, -4.0F, -3.0F, 6.0F, 8.0F, 6.0F),
                PartPose.offsetAndRotation(0.0F, 16.0F, 0.0F, (float) (Math.PI / 2), 0.0F, 0.0F)
        );
        CubeListBuilder leg = CubeListBuilder.create().texOffs(26, 0).addBox(-1.0F, 0.0F, -3.0F, 3.0F, 5.0F, 3.0F);
        root.addOrReplaceChild("right_leg", leg, PartPose.offset(-2.0F, 19.0F, 1.0F));
        root.addOrReplaceChild("left_leg", leg, PartPose.offset(1.0F, 19.0F, 1.0F));
        root.addOrReplaceChild(
                "right_wing", CubeListBuilder.create().texOffs(24, 13).addBox(0.0F, 0.0F, -3.0F, 1.0F, 4.0F, 6.0F), PartPose.offset(-4.0F, 13.0F, 0.0F)
        );
        root.addOrReplaceChild(
                "left_wing", CubeListBuilder.create().texOffs(24, 13).addBox(-1.0F, 0.0F, -3.0F, 1.0F, 4.0F, 6.0F), PartPose.offset(4.0F, 13.0F, 0.0F)
        );
        head.addOrReplaceChild("mushroom", CubeListBuilder.create().texOffs(0, 24).addBox(-2.5F, -4.0F, 0.0F, 5.0F, 5.0F, 0.0F), PartPose.offsetAndRotation(1.0F, -6.0F, 0.0F, 0.0F, 1.5707963267948966F, 0.0F));
        head.addOrReplaceChild("mushroom2", CubeListBuilder.create().texOffs(0, 24).addBox(-2.5F, -4.0F, 0.0F, 5.0F, 5.0F, 0.0F), PartPose.offset(1.0F, -6.0F, 0.0F));

        body.addOrReplaceChild("mushroom3", CubeListBuilder.create().texOffs(0, 24).addBox(0.0F, -5.0F, -2.5F, 0.0F, 5.0F, 5.0F), PartPose.offsetAndRotation(1.0F, 1.5F, 3.0F, -1.5707963267948966F, 0.0F, 3.141592653589793F));
        body.addOrReplaceChild("mushroom4", CubeListBuilder.create().texOffs(0, 24).addBox(0.0F, -5.0F, -2.5F, 0.0F, 5.0F, 5.0F), PartPose.offset(1.0F, 1.5F, 3.0F));

        return LayerDefinition.create(mesh, 64, 32);
	}
}
