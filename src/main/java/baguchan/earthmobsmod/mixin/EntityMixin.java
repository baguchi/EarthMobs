package baguchan.earthmobsmod.mixin;

import baguchan.earthmobsmod.registry.ModTags;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityFluidInteraction;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Entity.class)
public abstract class EntityMixin {
    @Shadow
    @Final
    public EntityFluidInteraction fluidInteraction;

    @Shadow
    public abstract void resetFallDistance();

    @Shadow
    @Deprecated
    public abstract boolean isPushedByFluid();

    @Shadow
    private Level level;

    @Inject(method = "updateFluidInteraction", at = @At("RETURN"), cancellable = true)
    public void updateFluidInteraction(CallbackInfoReturnable<Boolean> cir) {
        boolean inMud = this.fluidInteraction.isInFluid(ModTags.Fluids.MUD);
        if (inMud) {
            this.resetFallDistance();
        }

        if (this.isPushedByFluid()) {
            Entity entity = (Entity) (Object) this;
            if (inMud) {
                this.fluidInteraction.applyCurrentTo(ModTags.Fluids.MUD, entity, 0.005F);
            }
        }

        if (inMud) {
            cir.setReturnValue(true);
        }
    }
}
