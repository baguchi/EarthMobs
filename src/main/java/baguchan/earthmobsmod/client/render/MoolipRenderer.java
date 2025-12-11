package baguchan.earthmobsmod.client.render;

import baguchan.earthmobsmod.EarthMobsMod;
import baguchan.earthmobsmod.client.ModModelLayers;
import baguchan.earthmobsmod.client.model.MoobloomModel;
import baguchan.earthmobsmod.client.render.layer.MoolipLayer;
import baguchan.earthmobsmod.client.render.state.MoobloomRenderState;
import baguchan.earthmobsmod.entity.Moolip;
import net.minecraft.client.renderer.entity.AgeableMobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.Identifier;

public class MoolipRenderer extends AgeableMobRenderer<Moolip, MoobloomRenderState, MoobloomModel<MoobloomRenderState>> {
    private static final Identifier TEXTURE = Identifier.fromNamespaceAndPath(EarthMobsMod.MODID, "textures/entity/moobloom/moolip.png");

	public MoolipRenderer(EntityRendererProvider.Context p_173952_) {
        super(p_173952_, new MoobloomModel<>(p_173952_.bakeLayer(ModModelLayers.MOOBLOOM)), new MoobloomModel<>(p_173952_.bakeLayer(ModModelLayers.MOOBLOOM_BABY)), 0.5F);
        this.addLayer(new MoolipLayer(this, p_173952_.getBlockRenderDispatcher()));

    }

	@Override
    public MoobloomRenderState createRenderState() {
        return new MoobloomRenderState();
    }


    @Override
    public Identifier getTextureLocation(MoobloomRenderState p_110775_1_) {
		return TEXTURE;
	}

}