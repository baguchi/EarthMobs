package baguchan.earthmobsmod.entity.goal;

import baguchan.earthmobsmod.entity.BabyGhast;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;

import java.util.EnumSet;

public class SitEvenFlying extends Goal {
	private final BabyGhast mob;

	public SitEvenFlying(BabyGhast p_25898_) {
		this.mob = p_25898_;
		this.setFlags(EnumSet.of(Goal.Flag.JUMP, Goal.Flag.MOVE));
	}

	public boolean canContinueToUse() {
		return this.mob.isInSittingPose();
	}

	public boolean canUse() {
		if (this.mob.isInWaterOrBubble()) {
			return false;
		} else if (!this.mob.onGround()) {
			return false;
		} else {
			LivingEntity livingentity = this.mob.getOwner();
			if (livingentity == null) {
				return false;
			} else {
				return this.mob.distanceToSqr(livingentity) < 144.0D && livingentity.getLastHurtByMob() != null ? false : this.mob.isInSittingPose();
			}
		}
	}

	public void start() {
		this.mob.getNavigation().stop();
		this.mob.setInSittingPose(true);
	}

	public void stop() {
		this.mob.setInSittingPose(false);
	}
}