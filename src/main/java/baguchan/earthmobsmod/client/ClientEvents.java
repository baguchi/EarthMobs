package baguchan.earthmobsmod.client;

import baguchan.earthmobsmod.EarthMobsMod;
import baguchan.earthmobsmod.capability.ShadowCapability;
import baguchan.earthmobsmod.entity.HyperRabbit;
import baguchan.earthmobsmod.registry.ModCapability;
import baguchan.earthmobsmod.registry.ModEffects;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.core.Direction;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Pose;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.event.RenderLivingEvent;

import static net.minecraft.client.renderer.entity.LivingEntityRenderer.getOverlayCoords;

@Mod.EventBusSubscriber(modid = EarthMobsMod.MODID, value = Dist.CLIENT)
public class ClientEvents {


	@SubscribeEvent
	public static void renderEvent(RenderLivingEvent.Post<LivingEntity, EntityModel<LivingEntity>> event) {
		MultiBufferSource buffer = event.getMultiBufferSource();
		LivingEntity entity = event.getEntity();
		LivingEntityRenderer<LivingEntity, EntityModel<LivingEntity>> renderer = event.getRenderer();
		PoseStack posestack = event.getPoseStack();
		int light = event.getPackedLight();
		float partialtick = event.getPartialTick();

		ShadowCapability shadowCapability = event.getEntity().getData(ModCapability.SHADOW_ATTACH);
		if (shadowCapability != null) {
			if (entity instanceof HyperRabbit && ((HyperRabbit) entity).isSpark() || entity.hasEffect(ModEffects.HYPER_SPARK.get())) {
				posestack.pushPose();
				boolean shouldSit = entity.isPassenger() && (entity.getVehicle() != null && entity.getVehicle().shouldRiderSit());
				float f = Mth.rotLerp(partialtick, entity.yBodyRotO, entity.yBodyRot);
				float f1 = Mth.rotLerp(partialtick, entity.yHeadRotO, entity.yHeadRot);
				float f2 = f1 - f;
				if (shouldSit && entity.getVehicle() instanceof LivingEntity) {
					LivingEntity livingentity = (LivingEntity) entity.getVehicle();
					f = Mth.rotLerp(partialtick, shadowCapability.yBodyRot, livingentity.yBodyRot);
					f2 = f1 - f;
					float f3 = Mth.wrapDegrees(f2);
					if (f3 < -85.0F) {
						f3 = -85.0F;
					}

					if (f3 >= 85.0F) {
						f3 = 85.0F;
					}

					f = f1 - f3;
					if (f3 * f3 > 2500.0F) {
						f += f3 * 0.2F;
					}

					f2 = f1 - f;
				}

				float f6 = Mth.lerp(partialtick, entity.xRotO, entity.getXRot());

				if (entity.getPose() == Pose.SLEEPING) {
					Direction direction = entity.getBedOrientation();
					if (direction != null) {
						float f4 = entity.getEyeHeight(Pose.STANDING) - 0.1F;
						posestack.translate((double) ((float) (-direction.getStepX()) * f4), 0.0D, (double) ((float) (-direction.getStepZ()) * f4));
					}
				}

				float f7 = getBob(entity, partialtick);

				double shadowX = (shadowCapability.prevShadowX + (shadowCapability.shadowX - shadowCapability.prevShadowX) * partialtick);
				double shadowY = (shadowCapability.prevShadowY + (shadowCapability.shadowY - shadowCapability.prevShadowY) * partialtick);
				double shadowZ = (shadowCapability.prevShadowZ + (shadowCapability.shadowZ - shadowCapability.prevShadowZ) * partialtick);
				double shadowX2 = (shadowCapability.prevShadowX2 + (shadowCapability.shadowX2 - shadowCapability.prevShadowX2) * partialtick);
				double shadowY2 = (shadowCapability.prevShadowY2 + (shadowCapability.shadowY2 - shadowCapability.prevShadowY2) * partialtick);
				double shadowZ2 = (shadowCapability.prevShadowZ2 + (shadowCapability.shadowZ2 - shadowCapability.prevShadowZ2) * partialtick);
				double ownerInX = entity.xo + (entity.getX() - entity.xo) * partialtick;
				double ownerInY = entity.yo + (entity.getY() - entity.yo) * partialtick;
				double ownerInZ = entity.zo + (entity.getZ() - entity.zo) * partialtick;
				double deltaX = shadowX - ownerInX;
				double deltaY = shadowY - ownerInY;
				double deltaZ = shadowZ - ownerInZ;
				double deltaX2 = shadowX2 - shadowX;
				double deltaY2 = shadowY2 - shadowY;
				double deltaZ2 = shadowZ2 - shadowZ;

				Pose pose = entity.getPose();

				posestack.translate(deltaX, deltaY, deltaZ);

				if (!entity.hasPose(Pose.SLEEPING)) {
					posestack.mulPose(Axis.YP.rotationDegrees(180.0F - f));
				}

				//renderer.setupRotations(entity, posestack, f7, f, partialtick);

				posestack.scale(-1.0F, -1.0F, 1.0F);
				//renderer.scale(entity, posestack, partialtick);
				posestack.translate(0.0F, (double) -1.501F, 0.0F);


				float f8 = 0.0F;
				float f5 = 0.0F;
				if (!shouldSit && entity.isAlive()) {
					f8 = entity.walkAnimation.speed(partialtick);
					f5 = entity.walkAnimation.position(partialtick);
					if (entity.isBaby()) {
						f5 *= 3.0F;
					}

					if (f8 > 1.0F) {
						f8 = 1.0F;
					}
				}

				renderer.getModel().prepareMobModel(entity, f5, f8, partialtick);
				renderer.getModel().setupAnim(entity, f5, f8, f7, f2, f6);
				VertexConsumer vertexconsumer = buffer.getBuffer(RenderType.entityTranslucent(renderer.getTextureLocation(entity)));
				int i = getOverlayCoords(entity, 0.0F);
				renderer.getModel().renderToBuffer(posestack, vertexconsumer, light, i, 1.0F, 1.0F, 1.0F, (0.45F));
				posestack.popPose();
				posestack.pushPose();
				if (shouldSit && entity.getVehicle() instanceof LivingEntity) {
					f = Mth.rotLerp(partialtick, shadowCapability.yBodyRot2, shadowCapability.yBodyRot);
					f2 = f1 - f;
					float f3 = Mth.wrapDegrees(f2);
					if (f3 < -85.0F) {
						f3 = -85.0F;
					}

					if (f3 >= 85.0F) {
						f3 = 85.0F;
					}

					f = f1 - f3;
					if (f3 * f3 > 2500.0F) {
						f += f3 * 0.2F;
					}

					f2 = f1 - f;
				}

				if (entity.getPose() == Pose.SLEEPING) {
					Direction direction = entity.getBedOrientation();
					if (direction != null) {
						float f4 = entity.getEyeHeight(Pose.STANDING) - 0.1F;
						posestack.translate((double) ((float) (-direction.getStepX()) * f4), 0.0D, (double) ((float) (-direction.getStepZ()) * f4));
					}
				}

				posestack.translate(deltaX2, deltaY2, deltaZ2);

				if (!entity.hasPose(Pose.SLEEPING)) {
					posestack.mulPose(Axis.YP.rotationDegrees(180.0F - f));
				}

				//renderer.setupRotations(entity, posestack, f7, f, partialtick);

				posestack.scale(-1.0F, -1.0F, 1.0F);
				//renderer.scale(entity, posestack, partialtick);
				posestack.translate(0.0F, (double) -1.501F, 0.0F);


				renderer.getModel().prepareMobModel(entity, f5, f8, partialtick);
				renderer.getModel().setupAnim(entity, f5, f8, f7, f2, f6);
				renderer.getModel().renderToBuffer(posestack, vertexconsumer, light, i, 1.0F, 1.0F, 1.0F, 0.15F);

				posestack.popPose();
			}
		}
	}

	protected static float getBob(LivingEntity p_115305_, float p_115306_) {
		return (float) p_115305_.tickCount + p_115306_;
	}
}
