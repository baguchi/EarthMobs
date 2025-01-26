package baguchan.earthmobsmod.client.render.layer;

import baguchan.earthmobsmod.client.render.state.MelonGolemRenderState;
import baguchan.earthmobsmod.registry.ModBlocks;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.model.SnowGolemModel;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.block.BlockRenderDispatcher;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class MelonGolemHeadLayer extends RenderLayer<MelonGolemRenderState, SnowGolemModel> {
    private final BlockRenderDispatcher blockRenderer;

    public MelonGolemHeadLayer(RenderLayerParent<MelonGolemRenderState, SnowGolemModel> p_234871_, BlockRenderDispatcher p_234872_) {
        super(p_234871_);
        this.blockRenderer = p_234872_;
    }

    public void render(PoseStack p_117483_, MultiBufferSource p_117484_, int p_117485_, MelonGolemRenderState p_388156_, float p_117487_, float p_117488_) {
        if (p_388156_.hasPumpkin) {
            if (!p_388156_.isInvisible || p_388156_.appearsGlowing) {
                p_117483_.pushPose();
                this.getParentModel().getHead().translateAndRotate(p_117483_);
				float f = 0.625F;
                p_117483_.translate(0.0F, -0.34375F, 0.0F);
                p_117483_.mulPose(Axis.YP.rotationDegrees(180.0F));
                p_117483_.scale(0.625F, -0.625F, -0.625F);
                Block block = p_388156_.aggressive ? ModBlocks.CARVED_MELON_SHOOT.get() : ModBlocks.CARVED_MELON.get();

                BlockState blockstate = block.defaultBlockState();
                BakedModel bakedmodel = this.blockRenderer.getBlockModel(blockstate);
                int i = LivingEntityRenderer.getOverlayCoords(p_388156_, 0.0F);
                p_117483_.translate(-0.5F, -0.5F, -0.5F);
                VertexConsumer vertexconsumer = p_388156_.appearsGlowing && p_388156_.isInvisible
                        ? p_117484_.getBuffer(RenderType.outline(TextureAtlas.LOCATION_BLOCKS))
                        : p_117484_.getBuffer(ItemBlockRenderTypes.getRenderType(blockstate));
                this.blockRenderer.getModelRenderer().renderModel(p_117483_.last(), vertexconsumer, blockstate, bakedmodel, 0.0F, 0.0F, 0.0F, p_117485_, i);
                p_117483_.popPose();
            }
        }
    }
}
