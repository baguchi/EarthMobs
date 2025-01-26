package baguchan.earthmobsmod.client.render;

import baguchan.earthmobsmod.EarthMobsMod;
import baguchan.earthmobsmod.client.render.layer.MoobloomLayer;
import net.minecraft.client.model.CowModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.animal.Cow;

public class MoobloomRenderer extends MobRenderer<Cow, LivingEntityRenderState, CowModel> {
    private static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(EarthMobsMod.MODID, "textures/entity/moobloom/moobloom.png");

	public MoobloomRenderer(EntityRendererProvider.Context p_173952_) {
        super(p_173952_, new CowModel(p_173952_.bakeLayer(ModelLayers.COW)), 0.5F);
        this.addLayer(new MoobloomLayer(this, p_173952_.getBlockRenderDispatcher()));
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