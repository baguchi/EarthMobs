package baguchan.earthmobsmod.mixin;

import baguchan.earthmobsmod.entity.goal.BeeGlowFlowerMobGoal;
import baguchan.earthmobsmod.entity.goal.BeePollinateFlowerMobGoal;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.Bee;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Bee.class)
public abstract class BeeMixin extends Animal {

	BeePollinateFlowerMobGoal beePollinateGoal;

	protected BeeMixin(EntityType<? extends Animal> p_27557_, Level p_27558_) {
		super(p_27557_, p_27558_);
	}

	@Inject(
			at = @At(
					value = "INVOKE",
					target = "Lnet/minecraft/world/entity/ai/goal/GoalSelector;addGoal(ILnet/minecraft/world/entity/ai/goal/Goal;)V",
					ordinal = 4,
					shift = At.Shift.AFTER
			),
			method = "registerGoals"
	)
	protected void registerGoals(CallbackInfo callbackInfo) {
        this.goalSelector.addGoal(0, new BeeGlowFlowerMobGoal((Bee) (Object) this));
        beePollinateGoal = new BeePollinateFlowerMobGoal((Bee) (Object) this);

		this.goalSelector.addGoal(3, beePollinateGoal);
	}
}
