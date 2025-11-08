package baguchan.earthmobsmod.client;

import baguchan.earthmobsmod.EarthMobsMod;
import baguchan.earthmobsmod.capability.ShadowCapability;
import baguchi.bagus_lib.animation.client.BaguKeyFrameController;
import baguchi.bagus_lib.client.event.BagusModelEvent;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.animation.KeyframeAnimation;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.core.Direction;
import net.minecraft.util.ARGB;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Pose;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RenderLivingEvent;
import org.jetbrains.annotations.Nullable;

import static net.minecraft.client.renderer.entity.LivingEntityRenderer.getOverlayCoords;

@EventBusSubscriber(modid = EarthMobsMod.MODID, value = Dist.CLIENT)
public class ClientEvents {

    @SubscribeEvent
    public static void renderAnimationEvent(BagusModelEvent.PostAnimate event) {
        @Nullable BaguKeyFrameController keyFrames = event.getBaguKeyframeController();
        if (keyFrames != null) {
            KeyframeAnimation shake = event.getBaguKeyframeController().getKeyframe(ClientRegistrar.SHAKE_ANIMATION);
            if (shake != null) {
                shake.applyWalk(event.getEntityRenderState().ageInTicks, 1F, 1F, event.getEntityRenderState().getRenderDataOrDefault(ClientRegistrar.SHAKE, 0F));
            }
        }
    }
    @SubscribeEvent
    public static void renderEvent(RenderLivingEvent.Post<LivingEntity, LivingEntityRenderState, EntityModel<LivingEntityRenderState>> event) {
        SubmitNodeCollector buffer = event.getSubmitNodeCollector();
        LivingEntityRenderState entity = event.getRenderState();
        LivingEntityRenderer<LivingEntity, LivingEntityRenderState, EntityModel<LivingEntityRenderState>> renderer = event.getRenderer();
        PoseStack posestack = event.getPoseStack();
        int light = event.getRenderState().lightCoords;
        float partialtick = event.getPartialTick();


        ShadowCapability shadow = entity.getRenderData(ClientRegistrar.SHADOW);

        if (shadow != null && shadow.getPercentBoost() >= 0.65F) {

            double shadowX = (shadow.getPrevShadow().x + (shadow.getShadow().x - shadow.getPrevShadow().x) * partialtick);
            double shadowY = (shadow.getPrevShadow().y + (shadow.getShadow().y - shadow.getPrevShadow().y) * partialtick);
            double shadowZ = (shadow.getPrevShadow().z + (shadow.getShadow().z - shadow.getPrevShadow().z) * partialtick);
            double shadowX2 = (shadow.getPrevShadow2().x + (shadow.getShadow2().x - shadow.getPrevShadow2().x) * partialtick);
            double shadowY2 = (shadow.getPrevShadow2().y + (shadow.getShadow2().y - shadow.getPrevShadow2().y) * partialtick);
            double shadowZ2 = (shadow.getPrevShadow2().z + (shadow.getShadow2().z - shadow.getPrevShadow2().z) * partialtick);
            double ownerInX = entity.x;
            double ownerInY = entity.y;
            double ownerInZ = entity.z;
            double deltaX = shadowX - ownerInX;
            double deltaY = shadowY - ownerInY;
            double deltaZ = shadowZ - ownerInZ;
            double deltaX2 = shadowX2 - shadowX;
            double deltaY2 = shadowY2 - shadowY;
            double deltaZ2 = shadowZ2 - shadowZ;
            posestack.pushPose();

            posestack.translate(deltaX, deltaY, deltaZ);
            setupRender(entity, renderer, posestack, buffer, light);
            posestack.popPose();
            posestack.pushPose();

            posestack.translate(deltaX2, deltaY2, deltaZ2);
            setupRender(entity, renderer, posestack, buffer, light);

            posestack.popPose();
        }
    }

    private static void setupRender(LivingEntityRenderState entity, LivingEntityRenderer<LivingEntity, LivingEntityRenderState, EntityModel<LivingEntityRenderState>> renderer, PoseStack posestack, SubmitNodeCollector buffer, int light) {
        if (entity.hasPose(Pose.SLEEPING)) {
            Direction direction = entity.bedOrientation;
            if (direction != null) {
                float f = entity.eyeHeight - 0.1F;
                posestack.translate((float) (-direction.getStepX()) * f, 0.0F, (float) (-direction.getStepZ()) * f);
            }
        }

        float f1 = entity.scale;
        posestack.scale(f1, f1, f1);
        setupRotations(renderer, entity, posestack, entity.bodyRot, f1);
        posestack.scale(-1.0F, -1.0F, 1.0F);
        //renderer.scale(entity, posestack);
        posestack.translate(0.0F, -1.501F, 0.0F);
        RenderType rendertype = RenderType.entityTranslucent(renderer.getTextureLocation(entity));
        if (rendertype != null && renderer.getModel() instanceof EntityModel<LivingEntityRenderState> entityModel) {
            int i = getOverlayCoords(entity, 0.0F);
            int j = 654311423;
            int k = ARGB.multiply(j, -1);
            buffer.submitModel(entityModel, entity, posestack, rendertype, light, i, k, null, entity.outlineColor, null);
        }
    }

    private static void setupRotations(LivingEntityRenderer<LivingEntity, LivingEntityRenderState, EntityModel<LivingEntityRenderState>> renderer, LivingEntityRenderState entity, PoseStack poseStack, float bodyRot, float scale) {

        if (!entity.hasPose(Pose.SLEEPING)) {
            poseStack.mulPose(Axis.YP.rotationDegrees(180.0F - bodyRot));
        }

        /*if (entity.deathTime > 0.0F) {
            float f = (entity.deathTime - 1.0F) / 20.0F * 1.6F;
            f = Mth.sqrt(f);
            if (f > 1.0F) {
                f = 1.0F;
            }

            poseStack.mulPose(Axis.ZP.rotationDegrees(f * this.getFlipDegrees()));
        } else*/
        if (entity.isAutoSpinAttack) {
            poseStack.mulPose(Axis.XP.rotationDegrees(-90.0F - entity.xRot));
            poseStack.mulPose(Axis.YP.rotationDegrees(entity.ageInTicks * -75.0F));
        } else /*if (entity.hasPose(Pose.SLEEPING)) {
            Direction direction = entity.bedOrientation;
            float f1 = direction != null ? sleepDirectionToRotation(direction) : bodyRot;
            poseStack.mulPose(Axis.YP.rotationDegrees(f1));
            poseStack.mulPose(Axis.ZP.rotationDegrees(this.getFlipDegrees()));
            poseStack.mulPose(Axis.YP.rotationDegrees(270.0F));
        } else*/ if (entity.isUpsideDown) {
            poseStack.translate(0.0F, (entity.boundingBoxHeight + 0.1F) / scale, 0.0F);
            poseStack.mulPose(Axis.ZP.rotationDegrees(180.0F));
        }
    }

    protected static float getBob(LivingEntityRenderState p_115305_, float p_115306_) {
        return (float) p_115305_.ageInTicks;
    }

}
