package baguchan.earthmobsmod.client.render;

import net.minecraft.client.model.CowModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.animal.Cow;

public class RevampedCowRenderer<T extends Cow> extends MobRenderer<T, CowModel<T>> {
    private final ResourceLocation TEXTURE;

    public RevampedCowRenderer(EntityRendererProvider.Context p_173952_, ResourceLocation resourceLocation) {
        super(p_173952_, new CowModel<>(p_173952_.bakeLayer(ModelLayers.COW)), 0.5F);
        TEXTURE = resourceLocation;
    }


    @Override
    public ResourceLocation getTextureLocation(T p_110775_1_) {
        return TEXTURE;
    }
}