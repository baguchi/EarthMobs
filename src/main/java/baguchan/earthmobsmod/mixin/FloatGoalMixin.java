package baguchan.earthmobsmod.mixin;

import baguchan.earthmobsmod.registry.ModTags;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(FloatGoal.class)
public class FloatGoalMixin {
    @Shadow
    @Final
    private Mob mob;

    @Inject(method = "canUse", at = @At("RETURN"), cancellable = true)
    public void canUse(CallbackInfoReturnable<Boolean> cir) {
        if (!cir.getReturnValue() && this.mob.fluidInteraction.isInFluid(ModTags.Fluids.MUD) && this.mob.getFluidHeight(ModTags.Fluids.MUD) > this.mob.getFluidJumpThreshold()) {
            cir.setReturnValue(true);
        }
    }
}
