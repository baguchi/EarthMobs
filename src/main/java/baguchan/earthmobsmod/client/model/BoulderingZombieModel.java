package baguchan.earthmobsmod.client.model;

import baguchan.earthmobsmod.client.render.state.BoulderingZombieRenderState;
import baguchi.bagus_lib.client.layer.IArmor;
import net.minecraft.client.model.geom.ModelPart;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class BoulderingZombieModel<T extends BoulderingZombieRenderState> extends AbstractBoulderingZombieModel<T> implements IArmor {

	public BoulderingZombieModel(ModelPart root) {
		super(root);
	}

	protected float rotlerpRad(float angle, float maxAngle, float mul) {
		float f = (mul - maxAngle) % (float) (Math.PI * 2);
		if (f < (float) -Math.PI) {
			f += (float) (Math.PI * 2);
		}

		if (f >= (float) Math.PI) {
			f -= (float) (Math.PI * 2);
		}

		return maxAngle + angle * f;
	}
}
