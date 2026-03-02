package baguchan.earthmobsmod.client.render.layer;

import baguchan.earthmobsmod.client.render.state.MoobloomRenderState;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.model.animal.cow.CowModel;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;


public class CowPlantFlowerLayer extends RenderLayer<MoobloomRenderState, CowModel> {

    public CowPlantFlowerLayer(RenderLayerParent<MoobloomRenderState, CowModel> p_117243_) {
		super(p_117243_);
    }

    @Override
    public void submit(PoseStack p_117256_, SubmitNodeCollector p_432964_, int p_117258_, MoobloomRenderState state, float p_117260_, float p_117261_) {
        if (!state.isBaby) {
            p_117256_.pushPose();
            p_117256_.translate(0.2F, -0.35F, 0.5F);
            p_117256_.mulPose(Axis.YP.rotationDegrees(-48.0F));
            p_117256_.scale(-1.0F, -1.0F, 1.0F);
            p_117256_.translate(-0.5F, -0.5F, -0.5F);
            this.submitMushroomBlock(p_117256_, p_432964_, p_117258_, state.outlineColor, state);
            p_117256_.popPose();
            p_117256_.pushPose();
            p_117256_.translate(0.2F, -0.35F, 0.5F);
            p_117256_.mulPose(Axis.YP.rotationDegrees(42.0F));
            p_117256_.translate(0.1F, 0.0F, -0.6F);
            p_117256_.mulPose(Axis.YP.rotationDegrees(-48.0F));
            p_117256_.scale(-1.0F, -1.0F, 1.0F);
            p_117256_.translate(-0.5F, -0.5F, -0.5F);
            this.submitMushroomBlock(p_117256_, p_432964_, p_117258_, state.outlineColor, state);
            p_117256_.popPose();
            p_117256_.pushPose();
            (this.getParentModel()).getHead().translateAndRotate(p_117256_);
            p_117256_.translate(0.0F, -0.7F, -0.2F);
            p_117256_.mulPose(Axis.YP.rotationDegrees(-78.0F));
            p_117256_.scale(-1.0F, -1.0F, 1.0F);
            p_117256_.translate(-0.5F, -0.5F, -0.5F);
            this.submitMushroomBlock(p_117256_, p_432964_, p_117258_, state.outlineColor, state);
            p_117256_.popPose();
        }

    }

    private void submitMushroomBlock(
            PoseStack poseStack,
            SubmitNodeCollector submitNodeCollector,
            int lightCoords,
            int overlayCoords,
            MoobloomRenderState state
    ) {
        if (state.appearsGlowing() && state.isInvisible) {
            state.plantBlock.submitOnlyOutline(poseStack, submitNodeCollector, lightCoords, overlayCoords, state.outlineColor);
        } else {
            state.plantBlock.submit(poseStack, submitNodeCollector, lightCoords, overlayCoords, state.outlineColor);
        }
    }
}