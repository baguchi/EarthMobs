package baguchan.earthmobsmod.client.render;

import baguchan.earthmobsmod.client.ModModelLayers;
import baguchan.earthmobsmod.client.model.RevampedCowModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.animal.Cow;

public class RevampedCowRenderer<T extends Cow> extends MobRenderer<T, RevampedCowModel<T>> {
    private final ResourceLocation TEXTURE;

    public RevampedCowRenderer(EntityRendererProvider.Context p_173952_, ResourceLocation resourceLocation) {
        super(p_173952_, new RevampedCowModel<>(p_173952_.bakeLayer(ModModelLayers.COW)), 0.5F);
        TEXTURE = resourceLocation;
    }


    @Override
    public ResourceLocation getTextureLocation(T p_110775_1_) {
        return TEXTURE;
    }
}