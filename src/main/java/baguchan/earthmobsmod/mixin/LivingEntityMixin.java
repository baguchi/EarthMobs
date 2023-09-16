package baguchan.earthmobsmod.mixin;

import baguchan.earthmobsmod.registry.ModEffects;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Objects;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity {
	protected LivingEntityMixin(EntityType<? extends Entity> p_20966_, Level p_20967_) {
		super(p_20966_, p_20967_);
	}

	@Inject(method = "isInvertedHealAndHarm", at = @At("HEAD"), cancellable = true)
	public void isInvertedHealAndHarm(CallbackInfoReturnable<Boolean> callbackInfoReturnable) {
		if (hasEffect(Objects.requireNonNull(ModEffects.UNDEAD_BODY.get()))) {
			callbackInfoReturnable.setReturnValue(true);
		}
	}

	@Shadow
	public boolean hasEffect(MobEffect p_21024_) {
		return false;
	}
}
