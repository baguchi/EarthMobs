package baguchan.earthmobsmod.mixin;

import baguchan.earthmobsmod.api.IOnMud;
import baguchan.earthmobsmod.registry.ModFluidTypes;
import net.minecraft.world.entity.Entity;
import net.minecraftforge.common.extensions.IForgeEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Entity.class)
public abstract class EntityMixin implements IOnMud, IForgeEntity {

	private boolean wasTouchingMud;

	@Inject(method = "tick", at = @At("TAIL"))
	void tick(CallbackInfo callbackInfo) {
		if (this.isInFluidType(ModFluidTypes.MUD.get())) {
			this.wasTouchingMud = true;
		} else {
			this.wasTouchingMud = false;
		}
	}

	@Override
	public boolean isOnMud() {
		return wasTouchingMud;
	}
}
