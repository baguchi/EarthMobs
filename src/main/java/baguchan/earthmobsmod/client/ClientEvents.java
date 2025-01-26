package baguchan.earthmobsmod.client;

import baguchan.earthmobsmod.EarthMobsMod;
import net.minecraft.world.entity.LivingEntity;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.fml.common.EventBusSubscriber;

@EventBusSubscriber(modid = EarthMobsMod.MODID, value = Dist.CLIENT)
public class ClientEvents {


/*	@SubscribeEvent
	public static void renderEvent(RenderLivingEvent.Post<LivingEntity, LivingEntityRenderState, EntityModel<LivingEntityRenderState>> event) {
		MultiBufferSource buffer = event.getMultiBufferSource();
		LivingEntityRenderState entity = event.getRenderState();
		LivingEntityRenderer<LivingEntity, LivingEntityRenderState, EntityModel<LivingEntityRenderState>> renderer = event.getRenderer();
		PoseStack posestack = event.getPoseStack();
		int light = event.getPackedLight();
		float partialtick = event.getPartialTick();

		ShadowCapability shadowCapability = event.getEntity().getData(ModCapability.SHADOW_ATTACH);
		if (shadowCapability != null) {
			if (entity instanceof HyperRabbit && ((HyperRabbit) entity).isSpark() || entity.hasEffect(ModEffects.HYPER_SPARK)) {
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

				float yRotShadow = Mth.lerp(partialtick, shadowCapability.prevYRot, shadowCapability.yRot);
				float yRotShadow2 = Mth.lerp(partialtick, shadowCapability.prevYRot2, shadowCapability.yRot2);

				float ownerYRot = entity.yRotO + (entity.getYRot() - entity.yRotO) * partialtick;

				float yRot = yRotShadow - ownerYRot;
				float yRot2 = yRotShadow2 - yRotShadow;
				Pose pose = entity.getPose();

				posestack.translate(deltaX, deltaY, deltaZ);

				if (!entity.hasPose(Pose.SLEEPING)) {
					posestack.mulPose(Axis.YP.rotationDegrees(yRot));
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
				renderer.getModel().renderToBuffer(posestack, vertexconsumer, light, i, FastColor.ARGB32.color(255, 255, 255, (int) (0.45F * 255)));
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
					posestack.mulPose(Axis.YP.rotationDegrees(yRot2));
					posestack.mulPose(Axis.YP.rotationDegrees(180.0F - f));
				}

				//renderer.setupRotations(entity, posestack, f7, f, partialtick);

				posestack.scale(-1.0F, -1.0F, 1.0F);
				//renderer.scale(entity, posestack, partialtick);
				posestack.translate(0.0F, (double) -1.501F, 0.0F);


				renderer.getModel().setupAnim(entity);
				renderer.getModel().renderToBuffer(posestack, vertexconsumer, light, i, FastColor.ARGB32.color(255, 255, 255, (int) (0.15F * 255)));

				posestack.popPose();
			}
		}
	}*/

    protected static float getBob(LivingEntity livingEntity, float partialTick) {
        return (float) livingEntity.tickCount + partialTick;
	}
}
