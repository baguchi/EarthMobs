package baguchan.earthmobsmod.client.render.zombie;

import bagu_chan.bagus_lib.client.layer.CustomArmorLayer;
import baguchan.earthmobsmod.EarthMobsMod;
import baguchan.earthmobsmod.client.ModModelLayers;
import baguchan.earthmobsmod.client.model.LobberZombieModel;
import baguchan.earthmobsmod.entity.LobberHusk;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class LobberHuskRenderer<T extends LobberHusk> extends MobRenderer<T, LobberZombieModel<T>> {
    private static final ResourceLocation LOCATION = ResourceLocation.fromNamespaceAndPath(EarthMobsMod.MODID, "textures/entity/lobber_zombie/lobber_husk.png");

    public LobberHuskRenderer(EntityRendererProvider.Context p_173964_) {
        super(p_173964_, new LobberZombieModel<>(p_173964_.bakeLayer(ModModelLayers.LOBBER_ZOMBIE)), 0.5F);
        this.addLayer(new CustomArmorLayer<>(this, p_173964_));
    }

    @Override
    protected void scale(T livingEntity, PoseStack poseStack, float partialTickTime) {
        float f = 1.0625F;
        poseStack.scale(1.0625F, 1.0625F, 1.0625F);
        super.scale(livingEntity, poseStack, partialTickTime);
    }

    public ResourceLocation getTextureLocation(T p_114115_) {
        return LOCATION;
    }
}
