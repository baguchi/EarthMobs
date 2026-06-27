package baguchan.earthmobsmod.mixin;

import baguchan.earthmobsmod.registry.ModTags;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.behavior.Swim;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Swim.class)
public class SwimMixin {
    @Inject(method = "shouldSwim", at = @At("RETURN"), cancellable = true)
    private static <T extends Mob> void canUse(T mob, CallbackInfoReturnable<Boolean> cir) {
        if (!cir.getReturnValue() && mob.fluidInteraction.isInFluid(ModTags.Fluids.MUD) && mob.getFluidHeight(ModTags.Fluids.MUD) > mob.getFluidJumpThreshold()) {
            cir.setReturnValue(true);
        }
    }
}
