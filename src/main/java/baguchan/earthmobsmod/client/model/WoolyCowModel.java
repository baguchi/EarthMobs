package baguchan.earthmobsmod.client.model;

import baguchan.earthmobsmod.entity.WoolyCow;
import net.minecraft.client.model.CowModel;
import net.minecraft.client.model.geom.ModelPart;

public class WoolyCowModel<T extends WoolyCow> extends CowModel<T> {
	private float headXRot;

	public WoolyCowModel(ModelPart modelPart) {
		super(modelPart);
	}

	public void prepareMobModel(T p_103687_, float p_103688_, float p_103689_, float p_103690_) {
		super.prepareMobModel(p_103687_, p_103688_, p_103689_, p_103690_);
		this.head.y = 4.0F + p_103687_.getHeadEatPositionScale(p_103690_) * 4.0F;
		this.headXRot = p_103687_.getHeadEatAngleScale(p_103690_);
	}

	public void setupAnim(T p_103692_, float p_103693_, float p_103694_, float p_103695_, float p_103696_, float p_103697_) {
		super.setupAnim(p_103692_, p_103693_, p_103694_, p_103695_, p_103696_, p_103697_);
		this.head.xRot = this.headXRot;
	}
}
