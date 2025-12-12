package baguchan.earthmobsmod.client.render.layer;

import baguchan.earthmobsmod.client.render.state.MelonGolemRenderState;
import baguchan.earthmobsmod.registry.ModBlocks;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.model.animal.golem.SnowGolemModel;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.block.BlockRenderDispatcher;
import net.minecraft.client.renderer.block.model.BlockStateModel;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.rendertype.RenderType;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.world.level.block.state.BlockState;


public class MelonGolemHeadLayer extends RenderLayer<MelonGolemRenderState, SnowGolemModel> {
    private final BlockRenderDispatcher blockRenderer;

    public MelonGolemHeadLayer(RenderLayerParent<MelonGolemRenderState, SnowGolemModel> p_234871_, BlockRenderDispatcher p_234872_) {
        super(p_234871_);
        this.blockRenderer = p_234872_;
    }

    @Override
    public void submit(PoseStack poseStack, SubmitNodeCollector submitNodeCollector, int i, MelonGolemRenderState melonGolemRenderState, float v, float v1) {
        if (melonGolemRenderState.hasPumpkin && (!melonGolemRenderState.isInvisible || melonGolemRenderState.appearsGlowing())) {
            poseStack.pushPose();
            ((SnowGolemModel) this.getParentModel()).getHead().translateAndRotate(poseStack);
            float f = 0.625F;
            poseStack.translate(0.0F, -0.34375F, 0.0F);
            poseStack.mulPose(Axis.YP.rotationDegrees(180.0F));
            poseStack.scale(0.625F, -0.625F, -0.625F);
            BlockState blockstate = melonGolemRenderState.aggressive ? ModBlocks.CARVED_MELON_SHOOT.get().defaultBlockState() : ModBlocks.CARVED_MELON.get().defaultBlockState();
            BlockStateModel blockstatemodel = this.blockRenderer.getBlockModel(blockstate);
            int i2 = LivingEntityRenderer.getOverlayCoords(melonGolemRenderState, 0.0F);
            poseStack.translate(-0.5F, -0.5F, -0.5F);
            RenderType rendertype = melonGolemRenderState.appearsGlowing() && melonGolemRenderState.isInvisible ? RenderTypes.outline(TextureAtlas.LOCATION_BLOCKS) : ItemBlockRenderTypes.getRenderType(blockstate);
            submitNodeCollector.submitBlockModel(poseStack, rendertype, blockstatemodel, 0.0F, 0.0F, 0.0F, i, i2, melonGolemRenderState.outlineColor);
            poseStack.popPose();
        }
    }
}
