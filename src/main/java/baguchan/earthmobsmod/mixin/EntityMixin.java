package baguchan.earthmobsmod.mixin;

import baguchan.earthmobsmod.api.IOnMud;
import baguchan.earthmobsmod.registry.ModBlocks;
import baguchan.earthmobsmod.registry.ModFluidTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.common.extensions.IEntityExtension;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Entity.class)
public abstract class EntityMixin implements IOnMud, IEntityExtension {

	@Shadow
	protected boolean firstTick;
	@Unique
	protected boolean wasTouchingMud;
	@Shadow
	@Final
	protected RandomSource random;
	@Shadow
	private EntityDimensions dimensions;

	@Shadow
	public abstract BlockPos blockPosition();

	@Shadow
	public abstract Level level();

	@Inject(method = "updateInWaterStateAndDoFluidPushing",
			at = @At(value = "RETURN"))
	protected void updateInWaterStateAndDoFluidPushing(CallbackInfoReturnable<Boolean> callbackInfoReturnable) {
		updateInMud();
	}

	@Unique
	void updateInMud() {
		if (this.isInFluidType(ModFluidTypes.MUD.get())) {
			if (!this.wasTouchingMud && !this.firstTick) {
				this.doMudSplash();
			}
			this.wasTouchingMud = true;
		} else {
			this.wasTouchingMud = false;
		}

	}

	@Unique
	protected void doMudSplash() {
		Entity realEntity = (Entity) ((Object) this);
		Entity entity = (Entity) (realEntity.isVehicle() && realEntity.getControllingPassenger() != null ? realEntity.getControllingPassenger() : realEntity);
		float f = entity == realEntity ? 0.2F : 0.9F;
		Vec3 vec3 = entity.getDeltaMovement();
		float f1 = Math.min(1.0F, (float) Math.sqrt(vec3.x * vec3.x * (double) 0.2F + vec3.y * vec3.y + vec3.z * vec3.z * (double) 0.2F) * f);
		realEntity.playSound(SoundEvents.MUD_FALL, f1, 1.0F + (this.random.nextFloat() - this.random.nextFloat()) * 0.4F);


		float f2 = (float) Mth.floor(realEntity.getY());


		for (int j = 0; (float) j < 1.0F + this.dimensions.width * 20.0F; ++j) {
			double d2 = (this.random.nextDouble() * 2.0D - 1.0D) * (double) this.dimensions.width;
			double d3 = (this.random.nextDouble() * 2.0D - 1.0D) * (double) this.dimensions.width;
			this.level().addParticle(new BlockParticleOption(ParticleTypes.BLOCK, ModBlocks.MUD.get().defaultBlockState()).setPos(this.blockPosition()), realEntity.getX() + d2, (double) (f2 + 1.0F), realEntity.getZ() + d3, vec3.x, vec3.y, vec3.z);
		}

		realEntity.gameEvent(GameEvent.SPLASH);
	}


	@Override
	public boolean isOnMud() {
		return wasTouchingMud;
	}
}
