package baguchan.earthmobsmod.client.render.layer;

import baguchan.earthmobsmod.client.render.state.MoobloomRenderState;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.model.animal.cow.CowModel;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.block.BlockRenderDispatcher;
import net.minecraft.client.renderer.block.model.BlockStateModel;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.world.level.block.state.BlockState;


public abstract class CowPlantLayer extends RenderLayer<MoobloomRenderState, CowModel> {
    private final BlockRenderDispatcher blockRenderer;
    private final BlockState blockState;

    public CowPlantLayer(RenderLayerParent<MoobloomRenderState, CowModel> p_117243_, BlockRenderDispatcher p_234851_, BlockState blockState) {
		super(p_117243_);

        this.blockRenderer = p_234851_;
        this.blockState = blockState;
    }

    @Override
    public void submit(PoseStack p_117256_, SubmitNodeCollector p_432964_, int p_117258_, MoobloomRenderState p_361786_, float p_117260_, float p_117261_) {
        if (!p_361786_.isBaby) {
            boolean flag = p_361786_.appearsGlowing() && p_361786_.isInvisible;
            if (!p_361786_.isInvisible || flag) {
                BlockState blockstate = this.blockState;
                int i = LivingEntityRenderer.getOverlayCoords(p_361786_, 0.0F);
                BlockStateModel blockstatemodel = this.blockRenderer.getBlockModel(blockstate);
                p_117256_.pushPose();
                p_117256_.translate(0.2F, -0.35F, 0.5F);
                p_117256_.mulPose(Axis.YP.rotationDegrees(-48.0F));
                p_117256_.scale(-1.0F, -1.0F, 1.0F);
                p_117256_.translate(-0.5F, -0.5F, -0.5F);
                this.submitMushroomBlock(p_117256_, p_432964_, p_117258_, flag, p_361786_.outlineColor, blockstate, i, blockstatemodel);
                p_117256_.popPose();
                p_117256_.pushPose();
                p_117256_.translate(0.2F, -0.35F, 0.5F);
                p_117256_.mulPose(Axis.YP.rotationDegrees(42.0F));
                p_117256_.translate(0.1F, 0.0F, -0.6F);
                p_117256_.mulPose(Axis.YP.rotationDegrees(-48.0F));
                p_117256_.scale(-1.0F, -1.0F, 1.0F);
                p_117256_.translate(-0.5F, -0.5F, -0.5F);
                this.submitMushroomBlock(p_117256_, p_432964_, p_117258_, flag, p_361786_.outlineColor, blockstate, i, blockstatemodel);
                p_117256_.popPose();
                p_117256_.pushPose();
                (this.getParentModel()).getHead().translateAndRotate(p_117256_);
                p_117256_.translate(0.0F, -0.7F, -0.2F);
                p_117256_.mulPose(Axis.YP.rotationDegrees(-78.0F));
                p_117256_.scale(-1.0F, -1.0F, 1.0F);
                p_117256_.translate(-0.5F, -0.5F, -0.5F);
                this.submitMushroomBlock(p_117256_, p_432964_, p_117258_, flag, p_361786_.outlineColor, blockstate, i, blockstatemodel);
                p_117256_.popPose();
            }
        }

    }

    private void submitMushroomBlock(
            PoseStack poseStack,
            SubmitNodeCollector submitNodeCollector,
            int lightCoords,
            boolean appearsGlowingWithInvisibility,
            int outlineColor,
            BlockState mushroomBlockState,
            int overlayCoords,
            BlockStateModel model
    ) {
        if (appearsGlowingWithInvisibility) {
            submitNodeCollector.submitBlockModel(
                    poseStack, RenderTypes.outline(TextureAtlas.LOCATION_BLOCKS), model, -16777216, lightCoords, overlayCoords, outlineColor
            );
        } else {
            submitNodeCollector.submitBlock(poseStack, mushroomBlockState, lightCoords, overlayCoords, outlineColor);
        }
    }
}