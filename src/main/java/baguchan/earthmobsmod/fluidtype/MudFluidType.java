package baguchan.earthmobsmod.fluidtype;

import baguchan.earthmobsmod.EarthMobsMod;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.*;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.CoreShaders;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.client.extensions.common.IClientFluidTypeExtensions;
import net.neoforged.neoforge.fluids.FluidType;
import org.joml.Matrix4f;

public class MudFluidType extends FluidType {
	public MudFluidType(FluidType.Properties properties) {
		super(properties);
	}

	@Override
	public boolean move(FluidState state, LivingEntity entity, Vec3 movementVector, double gravity) {
		boolean flag = entity.getDeltaMovement().y <= 0.0D;
		double d8 = entity.getY();

		entity.moveRelative(0.02F, movementVector);
		entity.move(MoverType.SELF, entity.getDeltaMovement());

		if (entity.getFluidTypeHeight(this) <= entity.getFluidJumpThreshold()) {
			entity.setDeltaMovement(entity.getDeltaMovement().multiply(0.75D, (double) 0.8F, 0.75D));
			Vec3 vec33 = entity.getFluidFallingAdjustedMovement(gravity, flag, entity.getDeltaMovement());
			entity.setDeltaMovement(vec33);
		} else {
			entity.setDeltaMovement(entity.getDeltaMovement().scale(0.75D));
		}

		if (!entity.isNoGravity()) {
			entity.setDeltaMovement(entity.getDeltaMovement().add(0.0D, -gravity / 4.0D, 0.0D));
		}

		Vec3 vec34 = entity.getDeltaMovement();
		if (entity.horizontalCollision && entity.isFree(vec34.x, vec34.y + (double) 0.6F - entity.getY() + d8, vec34.z)) {
			entity.setDeltaMovement(vec34.x, (double) 0.3F, vec34.z);
		}

		return true;
	}

	public static class MudRender implements IClientFluidTypeExtensions {
			private static final ResourceLocation TEXTURE_STILL = ResourceLocation.fromNamespaceAndPath(EarthMobsMod.MODID, "block/mud");
			private static final ResourceLocation TEXTURE_FLOW = ResourceLocation.fromNamespaceAndPath(EarthMobsMod.MODID, "block/flowing_mud");
			private static final ResourceLocation TEXTURE_OVERLAY = ResourceLocation.fromNamespaceAndPath(EarthMobsMod.MODID, "textures/block/mud.png");

			@Override
			public ResourceLocation getStillTexture() {
				return TEXTURE_STILL;
			}

			@Override
			public ResourceLocation getFlowingTexture() {
				return TEXTURE_FLOW;
			}

			@Override
			public ResourceLocation getRenderOverlayTexture(Minecraft mc) {
				return TEXTURE_OVERLAY;
			}

		@Override
		public void renderOverlay(Minecraft mc, PoseStack stack, MultiBufferSource buffers) {
				ResourceLocation texture = this.getRenderOverlayTexture(mc);
				if (texture == null) return;
			RenderSystem.setShader(CoreShaders.POSITION_TEX_COLOR);
				RenderSystem.setShaderTexture(0, texture);
				BufferBuilder buffer = Tesselator.getInstance().begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_TEX);
				BlockPos playerEyePos = BlockPos.containing(mc.player.getX(), mc.player.getEyeY(), mc.player.getZ());
				float brightness = LightTexture.getBrightness(mc.player.level().dimensionType(), mc.player.level().getMaxLocalRawBrightness(playerEyePos));
				RenderSystem.enableBlend();
				RenderSystem.defaultBlendFunc();
				RenderSystem.setShaderColor(brightness, brightness, brightness, 0.65F);
				float uOffset = -mc.player.getYRot() / 64.0F;
				float vOffset = mc.player.getXRot() / 64.0F;
				Matrix4f pose = stack.last().pose();
				buffer.addVertex(pose, -1.0F, -1.0F, -0.5F).setUv(4.0F + uOffset, 4.0F + vOffset);
				buffer.addVertex(pose, 1.0F, -1.0F, -0.5F).setUv(uOffset, 4.0F + vOffset);
				buffer.addVertex(pose, 1.0F, 1.0F, -0.5F).setUv(uOffset, vOffset);
				buffer.addVertex(pose, -1.0F, 1.0F, -0.5F).setUv(4.0F + uOffset, vOffset);
				BufferUploader.drawWithShader(buffer.buildOrThrow());
			RenderSystem.setShaderColor(1F, 1F, 1F, 1F);

				RenderSystem.disableBlend();
			}
	}
}
