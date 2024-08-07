package baguchan.earthmobsmod.client.render.zombie;

import bagu_chan.bagus_lib.client.layer.CustomArmorLayer;
import baguchan.earthmobsmod.EarthMobsMod;
import baguchan.earthmobsmod.client.ModModelLayers;
import baguchan.earthmobsmod.client.model.LobberDrownedModel;
import baguchan.earthmobsmod.client.render.layer.OuterLayer;
import baguchan.earthmobsmod.entity.LobberDrowned;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class LobberDrownedRenderer<T extends LobberDrowned> extends MobRenderer<T, LobberDrownedModel<T>> {
    private static final ResourceLocation LOCATION = ResourceLocation.fromNamespaceAndPath(EarthMobsMod.MODID, "textures/entity/lobber_zombie/lobber_drowned.png");
    private static final ResourceLocation OUTER_LOCATION = ResourceLocation.fromNamespaceAndPath(EarthMobsMod.MODID, "textures/entity/lobber_zombie/lobber_drowned_outer.png");


    public LobberDrownedRenderer(EntityRendererProvider.Context p_173964_) {
        super(p_173964_, new LobberDrownedModel<>(p_173964_.bakeLayer(ModModelLayers.LOBBER_DROWNED)), 0.5F);
        this.addLayer(new OuterLayer(this, new LobberDrownedModel<>(p_173964_.bakeLayer(ModModelLayers.LOBBER_DROWNED_OUTER)), OUTER_LOCATION));
        this.addLayer(new CustomArmorLayer<>(this, p_173964_));
    }

    @Override
    protected void setupRotations(T p_115317_, PoseStack p_114110_, float p_114111_, float p_114112_, float p_114113_, float p_319953_) {
        super.setupRotations(p_115317_, p_114110_, p_114111_, p_114112_, p_114113_, p_319953_);
        float f = p_115317_.getSwimAmount(p_114113_);
        if (f > 0.0F) {
            float f1 = -10.0F - p_115317_.getXRot();
            float f2 = Mth.lerp(f, 0.0F, f1);
            p_114110_.rotateAround(Axis.XP.rotationDegrees(f2), 0.0F, p_115317_.getBbHeight() / 2.0F / p_319953_, 0.0F);
        }
    }

    public ResourceLocation getTextureLocation(T p_114115_) {
        return LOCATION;
    }
}
