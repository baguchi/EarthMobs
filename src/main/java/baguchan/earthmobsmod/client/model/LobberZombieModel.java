package baguchan.earthmobsmod.client.model;

import baguchan.earthmobsmod.client.animation.LobberZombieAnimation;
import baguchan.earthmobsmod.client.render.state.LobberZombieRenderState;
import baguchi.bagus_lib.client.layer.IArmor;
import net.minecraft.client.animation.KeyframeAnimation;
import net.minecraft.client.model.geom.ModelPart;

public class LobberZombieModel<T extends LobberZombieRenderState> extends AbstractLobberZombieModel<T> implements IArmor {
	private final KeyframeAnimation shootAnimation;

	public LobberZombieModel(ModelPart root) {
		super(root);
		shootAnimation = LobberZombieAnimation.shoot.bake(root);

	}

	@Override
	public void setupAnim(T entity) {
		super.setupAnim(entity);
		this.shootAnimation.apply(entity.shootAnimationState, entity.ageInTicks);
	}
}