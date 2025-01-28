package baguchan.earthmobsmod.client.model;

import baguchan.earthmobsmod.client.render.state.WoolyCowRenderState;
import net.minecraft.client.model.QuadrupedModel;
import net.minecraft.client.model.geom.ModelPart;

public class WoolyCowModel<T extends WoolyCowRenderState> extends QuadrupedModel<T> {
	public WoolyCowModel(ModelPart modelPart) {
		super(modelPart);
	}


    public void setupAnim(T entity) {
        super.setupAnim(entity);
        this.head.y = this.head.y + entity.headEatPositionScale * 9.0F * entity.ageScale;
        this.head.xRot = entity.headEatAngleScale;

	}
}
