package baguchan.earthmobsmod.client.render;

import baguchan.earthmobsmod.EarthMobsMod;
import baguchan.earthmobsmod.client.render.layer.MoolipLayer;
import net.minecraft.client.model.CowModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.AgeableMobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.animal.Cow;

public class MoolipRenderer extends AgeableMobRenderer<Cow, LivingEntityRenderState, CowModel> {
    private static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(EarthMobsMod.MODID, "textures/entity/moobloom/moolip.png");

	public MoolipRenderer(EntityRendererProvider.Context p_173952_) {
        super(p_173952_, new CowModel(p_173952_.bakeLayer(ModelLayers.COW)), new CowModel(p_173952_.bakeLayer(ModelLayers.COW_BABY)), 0.5F);
        this.addLayer(new MoolipLayer(this, p_173952_.getBlockRenderDispatcher()));

    }

	@Override
    public LivingEntityRenderState createRenderState() {
        return new LivingEntityRenderState();
    }


    @Override
    public ResourceLocation getTextureLocation(LivingEntityRenderState p_110775_1_) {
		return TEXTURE;
	}

}