package baguchan.earthmobsmod.mixin;

import baguchan.earthmobsmod.registry.ModEffects;
import net.minecraft.core.Holder;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.common.extensions.ILivingEntityExtension;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity implements ILivingEntityExtension {
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
}
