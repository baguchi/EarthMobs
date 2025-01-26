package baguchan.earthmobsmod.client.render.layer;

import baguchan.earthmobsmod.registry.ModBlocks;
import net.minecraft.client.model.CowModel;
import net.minecraft.client.renderer.block.BlockRenderDispatcher;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;

public class MoobloomLayer extends CowPlantLayer {
    public MoobloomLayer(RenderLayerParent<LivingEntityRenderState, CowModel> p_117243_, BlockRenderDispatcher p_234851_) {
        super(p_117243_, p_234851_, ModBlocks.BUTTERCUP.get().defaultBlockState());
    }
}
