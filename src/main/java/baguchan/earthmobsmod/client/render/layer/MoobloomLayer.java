package baguchan.earthmobsmod.client.render.layer;

import baguchan.earthmobsmod.client.render.state.MoobloomRenderState;
import baguchan.earthmobsmod.registry.ModBlocks;
import net.minecraft.client.model.animal.cow.CowModel;
import net.minecraft.client.renderer.block.BlockRenderDispatcher;
import net.minecraft.client.renderer.entity.RenderLayerParent;

public class MoobloomLayer extends CowPlantLayer {
    public MoobloomLayer(RenderLayerParent<MoobloomRenderState, CowModel> p_117243_, BlockRenderDispatcher p_234851_) {
        super(p_117243_, p_234851_, ModBlocks.BUTTERCUP.get().defaultBlockState());
    }
}
