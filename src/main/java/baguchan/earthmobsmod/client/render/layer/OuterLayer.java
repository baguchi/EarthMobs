package baguchan.earthmobsmod.client.render.layer;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.resources.Identifier;


public class OuterLayer<T extends LivingEntityRenderState> extends RenderLayer<T, EntityModel<T>> {
    private final Identifier location;
	private final EntityModel<T> model;
    private final EntityModel<T> babyModel;

    public OuterLayer(RenderLayerParent<T, EntityModel<T>> p_174490_, Identifier location, EntityModel<T> entityModel, EntityModel<T> babyModel) {
		super(p_174490_);
        this.location = location;
        this.model = entityModel;
        this.babyModel = babyModel;
	}

    @Override
    public void submit(PoseStack poseStack, SubmitNodeCollector submitNodeCollector, int i, T entityRenderState, float v, float v1) {
        EntityModel<T> drownedmodel = entityRenderState.isBaby ? this.babyModel : this.model;

        coloredCutoutModelCopyLayerRender(drownedmodel, this.location, poseStack, submitNodeCollector, i, entityRenderState, -1, 1);
    }
}