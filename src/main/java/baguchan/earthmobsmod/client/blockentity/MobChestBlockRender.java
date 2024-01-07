package baguchan.earthmobsmod.client.blockentity;

import baguchan.earthmobsmod.block.MobChestBlock;
import baguchan.earthmobsmod.client.ModModelLayers;
import baguchan.earthmobsmod.client.model.MobChestModel;
import baguchan.earthmobsmod.registry.ModBlocks;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import it.unimi.dsi.fastutil.floats.Float2FloatFunction;
import it.unimi.dsi.fastutil.ints.Int2IntFunction;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.blockentity.BrightnessCombiner;
import net.minecraft.core.Direction;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.ChestBlock;
import net.minecraft.world.level.block.DoubleBlockCombiner;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.ChestBlockEntity;
import net.minecraft.world.level.block.entity.LidBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.ChestType;

public class MobChestBlockRender<T extends BlockEntity & LidBlockEntity> implements BlockEntityRenderer<T> {

    public final MobChestModel model;

    public MobChestBlockRender(BlockEntityRendererProvider.Context context) {
        this.model = new MobChestModel(context.getModelSet().bakeLayer(ModModelLayers.MOB_CHEST));
    }

    @Override
    public void render(T p_112363_, float p_112364_, PoseStack p_112365_, MultiBufferSource p_112366_, int p_112367_, int p_112368_) {
        Level level = p_112363_.getLevel();
        boolean flag = level != null;
        BlockState blockstate = flag ? p_112363_.getBlockState() : ModBlocks.COMMON_MOB_CHEST.get().defaultBlockState().setValue(ChestBlock.FACING, Direction.SOUTH);
        ChestType chesttype = ChestType.SINGLE;
        Block block = blockstate.getBlock();
        if (block instanceof MobChestBlock chestBlock) {
            p_112365_.pushPose();
            float f = blockstate.getValue(MobChestBlock.FACING).toYRot();
            p_112365_.translate(0.5F, 0.5F, 0.5F);
            p_112365_.translate(0.0F, 1.0F, 0.0F);
            p_112365_.mulPose(Axis.YP.rotationDegrees(-f));
            p_112365_.mulPose(Axis.XP.rotationDegrees(-180F));
            DoubleBlockCombiner.NeighborCombineResult<? extends ChestBlockEntity> neighborcombineresult = DoubleBlockCombiner.Combiner::acceptNone;


            float f1 = neighborcombineresult.<Float2FloatFunction>apply(ChestBlock.opennessCombiner(p_112363_)).get(p_112364_);
            f1 = 1.0F - f1;
            f1 = 1.0F - f1 * f1 * f1;
            int i = neighborcombineresult.<Int2IntFunction>apply(new BrightnessCombiner<>()).applyAsInt(p_112367_);
            VertexConsumer vertexconsumer = p_112366_.getBuffer(RenderType.entityCutoutNoCull(chestBlock.getTexture()));

            this.render(p_112365_, vertexconsumer, model.lid, f1, i, p_112368_);


            p_112365_.popPose();
        }
    }

    private void render(
            PoseStack p_112370_,
            VertexConsumer p_112371_,
            ModelPart p_112372_,
            float p_112375_,
            int p_112376_,
            int p_112377_
    ) {
        p_112372_.xRot = -(p_112375_ * (float) (Math.PI / 2));
        this.model.bone.render(p_112370_, p_112371_, p_112376_, p_112377_);
    }

    @Override
    public net.minecraft.world.phys.AABB getRenderBoundingBox(T blockEntity) {
        net.minecraft.core.BlockPos pos = blockEntity.getBlockPos();
        return net.minecraft.world.phys.AABB.encapsulatingFullBlocks(pos.offset(-1, 0, -1), pos.offset(1, 1, 1));
    }
}