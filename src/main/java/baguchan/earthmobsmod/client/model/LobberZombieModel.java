package baguchan.earthmobsmod.client.model;

import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.ZombieModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.world.entity.monster.Zombie;

public class LobberZombieModel<T extends Zombie> extends ZombieModel<T> {
	public LobberZombieModel(ModelPart p_170534_) {
		super(p_170534_);
	}

	public static LayerDefinition createBodyLayer(CubeDeformation p_170536_) {
		MeshDefinition meshdefinition = HumanoidModel.createMesh(p_170536_, 0.0F);
		PartDefinition partdefinition = meshdefinition.getRoot();
		partdefinition.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(40, 16).addBox(-4.0F, -2.0F, -2.0F, 5.0F, 14.0F, 4.0F, p_170536_), PartPose.offset(-5.0F, 2.0F + 0.0F, 0.0F));
		partdefinition.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(40, 34).mirror().addBox(-1.0F, -2.0F, -2.0F, 4.0F, 11.0F, 4.0F, p_170536_), PartPose.offset(5.0F, 2.0F + 0.0F, 0.0F));
		return LayerDefinition.create(meshdefinition, 64, 64);
	}
}
