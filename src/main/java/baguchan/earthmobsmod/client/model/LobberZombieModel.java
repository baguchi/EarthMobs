package baguchan.earthmobsmod.client.model;

import baguchan.earthmobsmod.client.animation.LobberZombieAnimation;
import baguchan.earthmobsmod.client.render.state.LobberZombieRenderState;
import baguchi.bagus_lib.client.layer.IArmor;
import net.minecraft.client.model.geom.ModelPart;

public class LobberZombieModel<T extends LobberZombieRenderState> extends AbstractLobberZombieModel<T> implements IArmor {

	public LobberZombieModel(ModelPart root) {
		super(root);
	}

	@Override
	public void setupAnim(T entity) {
		super.setupAnim(entity);
		this.animate(entity.shootAnimationState, LobberZombieAnimation.shoot, entity.ageInTicks);

	}
}