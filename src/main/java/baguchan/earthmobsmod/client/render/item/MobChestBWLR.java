package baguchan.earthmobsmod.client.render.item;

import baguchan.earthmobsmod.blockentity.MobChestBlockEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;

public class MobChestBWLR extends BlockEntityWithoutLevelRenderer {

    public MobChestBWLR() {
        super(Minecraft.getInstance().getBlockEntityRenderDispatcher(), Minecraft.getInstance().getEntityModels());
    }

    @Override
    public void renderByItem(ItemStack pStack, ItemDisplayContext pTransformType, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight, int pOverlay) {
        if (pStack.getItem() instanceof BlockItem blockItem) {
            pPoseStack.translate(1F, 0.0F, 1F);
            pPoseStack.mulPose(Axis.YP.rotationDegrees(-180F));
            Minecraft.getInstance().getBlockEntityRenderDispatcher().renderItem(new MobChestBlockEntity(BlockPos.ZERO, blockItem.getBlock().defaultBlockState()), pPoseStack, pBuffer, pPackedLight, pOverlay);
        }
    }
}