package baguchan.earthmobsmod.client;

import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.client.renderer.RenderStateShard;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.TriState;

public abstract class EarthRenderType extends RenderType {

    public EarthRenderType(String p_173178_, int p_173181_, boolean p_173182_, boolean p_173183_, Runnable p_173184_, Runnable p_173185_) {
        super(p_173178_, p_173181_, p_173182_, p_173183_, p_173184_, p_173185_);
    }

    public static RenderType animationEye(ResourceLocation location, int maxAge, int frameCount, int tick) {
        int age = tick * (frameCount - 1) / maxAge;

        return create(
                "earthmobsmod:animation_eyes",
                1536,
                false,
                true,
                RenderPipelines.EYES,
                RenderType.CompositeState.builder()
                        .setTextureState(new RenderStateShard.TextureStateShard(location.withSuffix("_" + String.valueOf(age % frameCount) + ".png"), TriState.DEFAULT, false))
                        .createCompositeState(false)
        );
    }

    public static RenderType entityAnimation(ResourceLocation location, int maxAge, int frameCount, int tick) {
        int age = tick * (frameCount - 1) / maxAge;

        return create(
                "earthmobsmod:entity_animation",
                1536,
                false,
                true,
                RenderPipelines.EYES,
                RenderType.CompositeState.builder()
                        .setTextureState(new RenderStateShard.TextureStateShard(location.withSuffix("_" + String.valueOf(age % frameCount) + ".png"), TriState.DEFAULT, false))
                        .setLightmapState(LIGHTMAP)
                        .setOverlayState(OVERLAY)
                        .createCompositeState(false)
        );
    }
}
