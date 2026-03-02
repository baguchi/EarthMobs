package baguchan.earthmobsmod.client;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.client.renderer.rendertype.LayeringTransform;
import net.minecraft.client.renderer.rendertype.RenderSetup;
import net.minecraft.client.renderer.rendertype.RenderType;
import net.minecraft.client.renderer.rendertype.TextureTransform;
import net.minecraft.resources.Identifier;
import org.joml.Matrix4f;

public class EarthRenderType {


    public static RenderType animationEye(Identifier location, int maxAge, int frameCount, int tick) {
        int age = tick * (frameCount - 1) / maxAge;

        return RenderType.create(
                "earthmobsmod:animation_eyes",
                RenderSetup.builder(RenderPipelines.EYES)
                        .withTexture("Sampler0", location.withSuffix("_" + age % frameCount + ".png"))
                        .createRenderSetup()
        );
    }

    public static RenderType entityAnimation(Identifier location, int maxAge, int frameCount, int tick) {
        int age = tick * (frameCount - 1) / maxAge;

        return RenderType.create(
                "earthmobsmod:entity_animation",
                RenderSetup.builder(RenderPipelines.ENTITY_CUTOUT)
                        .withTexture("Sampler0", location.withSuffix("_" + age % frameCount + ".png"))
                        .useLightmap()
                        .useOverlay()
                        .affectsCrumbling()
                        .setOutline(RenderSetup.OutlineProperty.AFFECTS_OUTLINE)
                        .createRenderSetup());
    }

    public static RenderType entityAnimationWithAllTexture(Identifier location, int maxAge, int frameCount, int tick) {
        int age = tick * (frameCount - 1) / maxAge;

        return RenderType.create(
                "earthmobsmod:entity_animation_all_texture",
                RenderSetup.builder(ClientRegistrar.ANIMATION_ENTITY)
                        .withTexture("Sampler0", location)
                        .setTextureTransform(new OffsetScaleTexturingStateShard(0, (float) (age % frameCount) / frameCount, 0, (float) frameCount))
                        .useLightmap()
                        .useOverlay()
                        .affectsCrumbling()
                        .setOutline(RenderSetup.OutlineProperty.AFFECTS_OUTLINE)
                        .createRenderSetup()
        );
    }

    public static final LayeringTransform OFFSET_SCALE = new LayeringTransform("view_offset_z_layering_forward", (p_458961_) -> RenderSystem.getProjectionType().applyLayeringTransform(p_458961_, -1.0F));


    public static final class OffsetScaleTexturingStateShard extends TextureTransform {
        public OffsetScaleTexturingStateShard(float p_110290_, float p_110291_, float scaleX, float scaleY) {
            super("offset_scale_texturing", () -> (new Matrix4f().scale(scaleX, scaleY, 0).translation(p_110290_, p_110291_, 0.0F)));
        }
    }
}
