package baguchan.earthmobsmod.mixin;

import baguchan.earthmobsmod.registry.ModEffects;
import baguchan.earthmobsmod.registry.ModFluids;
import baguchan.earthmobsmod.registry.ModTags;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.core.Holder;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.common.extensions.ILivingEntityExtension;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity implements ILivingEntityExtension {

	@Shadow
	private int noJumpDelay;

	@Shadow
	public abstract void jumpFromGround();

	@Shadow
	protected abstract void travelInWater(Vec3 input, double baseGravity, boolean isFalling, double oldY);

	protected LivingEntityMixin(EntityType<? extends Entity> p_20966_, Level p_20967_) {
		super(p_20966_, p_20967_);
	}

	@Inject(method = "isInvertedHealAndHarm", at = @At("HEAD"), cancellable = true)
	public void isInvertedHealAndHarm(CallbackInfoReturnable<Boolean> callbackInfoReturnable) {
		if (hasEffect(ModEffects.UNDEAD_BODY)) {
			callbackInfoReturnable.setReturnValue(true);
		}
	}

	@Shadow
	public boolean hasEffect(Holder<MobEffect> p_316430_) {
		return false;
	}

	@Inject(method = "aiStep", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/LivingEntity;getFluidJumpThreshold()D", shift = At.Shift.AFTER))
	public void floatInFluid(CallbackInfo ci) {
		double fluidHeight = this.getFluidHeight(ModTags.Fluids.MUD);
		double fluidJumpThreshold = this.getFluidJumpThreshold();
		if (!this.fluidInteraction.isInFluid(ModTags.Fluids.MUD) || this.onGround() && !(fluidHeight > fluidJumpThreshold)) {
			if ((this.onGround() || this.fluidInteraction.isInFluid(ModTags.Fluids.MUD) && fluidHeight <= fluidJumpThreshold) && this.noJumpDelay == 0) {
				this.jumpFromGround();
				this.noJumpDelay = 10;
			}
		} else {
			this.jumpInFluid(ModFluids.MUD.get().getFluidType());
		}

	}

	@WrapOperation(method = "shouldTravelInFluid", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/LivingEntity;isInWater()Z"))
	public boolean travelInFluid(LivingEntity instance, Operation<Boolean> original) {
		return original.call(instance) || this.fluidInteraction.isInFluid(ModTags.Fluids.MUD);
	}
}
