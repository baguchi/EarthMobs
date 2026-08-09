package baguchan.earthmobsmod.fluidtype;

import baguchan.earthmobsmod.EarthMobsMod;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.client.extensions.common.IClientFluidTypeExtensions;
import net.neoforged.neoforge.common.NeoForgeMod;
import net.neoforged.neoforge.fluids.FluidType;

public class MudFluidType extends FluidType {
	public MudFluidType(FluidType.Properties properties) {
		super(properties);
	}

	@Override
	public boolean move(LivingEntity entity, Vec3 movementVector, double gravity) {
		boolean isFalling = entity.getDeltaMovement().y <= 0.0;
		double oldY = entity.getY();
		double baseGravity = gravity;
		float slowDown = entity.isSprinting() ? 0.85F : 0.8F;
		float speed = 0.02F;
		float waterWalker = (float) entity.getAttributeValue(Attributes.WATER_MOVEMENT_EFFICIENCY);
		if (!entity.onGround()) {
			waterWalker *= 0.5F;
		}

		if (waterWalker > 0.0F) {
			slowDown += (0.54600006F - slowDown) * waterWalker;
			speed += (entity.getSpeed() - speed) * waterWalker;
		}

		speed *= (float) entity.getAttributeValue(NeoForgeMod.SWIM_SPEED);
		entity.moveRelative(speed, movementVector);
		entity.move(MoverType.SELF, entity.getDeltaMovement());
		Vec3 movement = entity.getDeltaMovement();
		if (entity.horizontalCollision && entity.onClimbable()) {
			movement = new Vec3(movement.x, 0.2, movement.z);
		}

		movement = movement.multiply((double) slowDown, (double) 0.8F, (double) slowDown);
		entity.setDeltaMovement(entity.getFluidFallingAdjustedMovement(baseGravity, isFalling, movement));
		jumpOutOfFluid(entity, oldY);
		return false;
	}


	private void jumpOutOfFluid(LivingEntity entity, double oldY) {
		Vec3 movement = entity.getDeltaMovement();
		if (entity.horizontalCollision && entity.isFree(movement.x, movement.y + (double) 0.6F - entity.getY() + oldY, movement.z)) {
			entity.setDeltaMovement(movement.x, (double) 0.3F, movement.z);
		}

	}


	public static class MudRender implements IClientFluidTypeExtensions {
        private static final Identifier TEXTURE_STILL = Identifier.fromNamespaceAndPath(EarthMobsMod.MODID, "block/mud");
        private static final Identifier TEXTURE_FLOW = Identifier.fromNamespaceAndPath(EarthMobsMod.MODID, "block/flowing_mud");
        private static final Identifier TEXTURE_OVERLAY = Identifier.fromNamespaceAndPath(EarthMobsMod.MODID, "textures/block/mud.png");

		@Override
        public Identifier getRenderOverlayTexture(Minecraft mc) {
			return TEXTURE_OVERLAY;
		}
	}
}
