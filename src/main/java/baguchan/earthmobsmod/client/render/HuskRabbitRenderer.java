package baguchan.earthmobsmod.client.render;

import baguchan.earthmobsmod.EarthMobsMod;
import baguchan.earthmobsmod.client.render.state.ZombifiedRabbitRenderState;
import baguchan.earthmobsmod.client.render.zombie.ZombifiedRabbitRenderer;
import baguchan.earthmobsmod.entity.HuskRabbit;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.Identifier;

public class HuskRabbitRenderer<T extends HuskRabbit> extends ZombifiedRabbitRenderer<T> {
    private static final Identifier TEXTURE = Identifier.fromNamespaceAndPath(EarthMobsMod.MODID, "textures/entity/zombified_rabbit/husk_rabbit.png");
    private static final Identifier BABY_TEXTURE = Identifier.fromNamespaceAndPath(EarthMobsMod.MODID, "textures/entity/zombified_rabbit/husk_rabbit_baby.png");

    public HuskRabbitRenderer(EntityRendererProvider.Context p_173952_) {
        super(p_173952_);
    }

    @Override
    public Identifier getTextureLocation(ZombifiedRabbitRenderState renderState) {
        if (renderState.isBaby) {
            return BABY_TEXTURE;
        }
        return TEXTURE;
    }

}
