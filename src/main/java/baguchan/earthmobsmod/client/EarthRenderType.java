package baguchan.earthmobsmod.client;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.client.renderer.RenderStateShard;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import org.joml.Matrix4f;

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
                        .setTextureState(new RenderStateShard.TextureStateShard(location.withSuffix("_" + String.valueOf(age % frameCount) + ".png"), false))
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
                RenderPipelines.ENTITY_CUTOUT_NO_CULL,
                RenderType.CompositeState.builder()
                        .setTextureState(new RenderStateShard.TextureStateShard(location.withSuffix("_" + String.valueOf(age % frameCount) + ".png"), false))
                        .setLightmapState(LIGHTMAP)
                        .setOverlayState(OVERLAY)
                        .createCompositeState(false)
        );
    }

    public static RenderType entityAnimationWithAllTexture(ResourceLocation location, int maxAge, int frameCount, int tick) {
        int age = tick * (frameCount - 1) / maxAge;

        return create(
                "earthmobsmod:entity_animation_all_texture",
                1536,
                true,
                true,
                ClientRegistrar.ANIMATION_ENTITY,
                RenderType.CompositeState.builder()
                        .setTextureState(new RenderStateShard.TextureStateShard(location, false))
                        .setTexturingState(new OffsetScaleTexturingStateShard(0, (float) ((float) (age % frameCount) / frameCount), 0, (float) frameCount))
                        .setLightmapState(LIGHTMAP)
                        .setOverlayState(OVERLAY)
                        .createCompositeState(true)
        );
    }

    public static final class OffsetScaleTexturingStateShard extends TexturingStateShard {
        public OffsetScaleTexturingStateShard(float p_110290_, float p_110291_, float scaleX, float scaleY) {
            super("offset_scale_texturing", () -> RenderSystem.setTextureMatrix((new Matrix4f()).scale(scaleX, scaleY, 0).translation(p_110290_, p_110291_, 0.0F)), () -> RenderSystem.resetTextureMatrix());
        }
    }
}
