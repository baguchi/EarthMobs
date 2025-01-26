package baguchan.earthmobsmod.client.render;

import baguchan.earthmobsmod.EarthMobsMod;
import baguchan.earthmobsmod.client.ModModelLayers;
import baguchan.earthmobsmod.client.model.VilerWitchModel;
import baguchan.earthmobsmod.client.render.layer.VilerWitchItemLayer;
import baguchan.earthmobsmod.entity.VilerWitch;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.state.HoldingEntityRenderState;
import net.minecraft.client.renderer.entity.state.WitchRenderState;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class VilerWitchRenderer<T extends VilerWitch> extends MobRenderer<T, WitchRenderState, VilerWitchModel<WitchRenderState>> {
    private static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(EarthMobsMod.MODID, "textures/entity/viler_witch.png");

	public VilerWitchRenderer(EntityRendererProvider.Context p_173952_) {
		super(p_173952_, new VilerWitchModel<>(p_173952_.bakeLayer(ModModelLayers.VILER_WITCH)), 0.5F);
        this.addLayer(new VilerWitchItemLayer(this));
	}


    @Override
    public WitchRenderState createRenderState() {
        return new WitchRenderState();
    }
	@Override
    public void extractRenderState(T p_360566_, WitchRenderState p_364734_, float p_364448_) {
        super.extractRenderState(p_360566_, p_364734_, p_364448_);
        HoldingEntityRenderState.extractHoldingEntityRenderState(p_360566_, p_364734_, this.itemModelResolver);
        p_364734_.entityId = p_360566_.getId();
        ItemStack itemstack = p_360566_.getMainHandItem();
        p_364734_.isHoldingItem = !itemstack.isEmpty();
        p_364734_.isHoldingPotion = itemstack.is(Items.POTION);
    }

    @Override
    public ResourceLocation getTextureLocation(WitchRenderState p_110775_1_) {
		return TEXTURE;
	}
}