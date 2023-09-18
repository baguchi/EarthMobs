package baguchan.earthmobsmod.entity.goal;

import baguchan.earthmobsmod.entity.IHasFlower;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.animal.Bee;
import net.minecraft.world.phys.Vec3;

import java.util.EnumSet;
import java.util.Optional;
import java.util.function.Predicate;

public class BeePollinateFlowerMobGoal extends Goal {
	private static final int MIN_POLLINATION_TICKS = 400;
	private static final int MIN_FIND_FLOWER_RETRY_COOLDOWN = 20;
	private static final int MAX_FIND_FLOWER_RETRY_COOLDOWN = 60;
	public static final Predicate<LivingEntity> FLOWER_MOB_SELECTOR = (p_30437_) -> {
		return p_30437_ instanceof IHasFlower && ((IHasFlower) p_30437_).hasFlower();
	};
	private static final double ARRIVAL_THRESHOLD = 0.1D;
	private static final int POSITION_CHANGE_CHANCE = 25;
	private static final float SPEED_MODIFIER = 0.35F;
	private static final float HOVER_HEIGHT_WITHIN_FLOWER = 0.6F;
	private static final float HOVER_POS_OFFSET = 0.33333334F;
	private int successfulPollinatingTicks;
	private int lastSoundPlayedTick;
	private boolean pollinating;
	private int pollinatingTicks;
	private static final int MAX_POLLINATING_TICKS = 600;
	private final Bee bee;
	private LivingEntity flowerMob;

	public BeePollinateFlowerMobGoal(Bee bee) {
		this.bee = bee;
		this.setFlags(EnumSet.of(Flag.MOVE));
	}


	public boolean canBeeUse() {
		if (this.bee.remainingCooldownBeforeLocatingNewFlower > 0) {
			return false;
		} else if (this.bee.hasNectar()) {
			return false;
		} else if (this.bee.level.isRaining()) {
			return false;
		} else {
			Optional<LivingEntity> optional = this.findNearbyFlowerMob();
			if (optional.isPresent()) {
				this.flowerMob = optional.get();
				this.bee.getNavigation().moveTo((double) this.flowerMob.getX() + 0.5D, (double) this.flowerMob.getY() + 0.5D, (double) this.flowerMob.getZ() + 0.5D, (double) 1.2F);
				return true;
			} else {
				this.bee.remainingCooldownBeforeLocatingNewFlower =
						Mth.nextInt(this.bee.getRandom(), 20, 60);
				return false;
			}
		}
	}

	public boolean canBeeContinueToUse() {
		if (!this.pollinating) {
			return false;
		} else if (this.bee.level.isRaining()) {
			return false;
		} else if (this.hasPollinatedLongEnough()) {
			return this.bee.getRandom().nextFloat() < 0.2F;
		} else if (this.flowerMob == null || !this.flowerMob.isAlive()) {
			this.flowerMob = null;
			return false;
		} else {
			return true;
		}
	}

	private boolean hasPollinatedLongEnough() {
		return this.successfulPollinatingTicks > 200;
	}

	boolean isPollinating() {
		return this.pollinating;
	}

	void stopPollinating() {
		this.pollinating = false;
	}

	@Override
	public boolean canUse() {
		return this.canBeeUse();
	}

	@Override
	public boolean canContinueToUse() {
		return this.canBeeContinueToUse();
	}

	public void start() {
		this.successfulPollinatingTicks = 0;
		this.pollinatingTicks = 0;
		this.lastSoundPlayedTick = 0;
		this.pollinating = true;
		this.bee.resetTicksWithoutNectarSinceExitingHive();
	}

	public void stop() {
		if (this.hasPollinatedLongEnough()) {
			this.bee.setHasNectar(true);
		}

		this.pollinating = false;
		this.flowerMob = null;
		this.bee.getNavigation().stop();
		this.bee.remainingCooldownBeforeLocatingNewFlower = 200;
	}

	public void tick() {
		++this.pollinatingTicks;

		if (this.pollinatingTicks > 600 || this.flowerMob == null || !this.flowerMob.isAlive()) {
			this.stopPollinating();
			return;
		}

		Vec3 pos = this.flowerMob.getEyePosition();
		double dinstanceToFlower = this.bee.distanceToSqr(pos);

		if (dinstanceToFlower >= 0.5) {
			this.bee.getNavigation().moveTo(pos.x, pos.y, pos.z, 0.8F);
			this.bee.getLookControl().setLookAt(
					pos
			);
		}

		if (dinstanceToFlower <= 4.0D) {
			++this.successfulPollinatingTicks;
			if (this.bee.getRandom().nextFloat() < 0.05F && this.successfulPollinatingTicks > this.lastSoundPlayedTick + 60) {
				this.lastSoundPlayedTick = this.successfulPollinatingTicks;
				this.bee.playSound(SoundEvents.BEE_POLLINATE, 1.0F, 1.0F);
			}
		}
	}

	private Optional<LivingEntity> findNearbyFlowerMob() {
		return this.findNearestFlowerMob(FLOWER_MOB_SELECTOR, 10.0D);
	}

	private Optional<LivingEntity> findNearestFlowerMob(Predicate<LivingEntity> p_28076_, double p_28077_) {
		TargetingConditions targetConditions = TargetingConditions.forNonCombat().range(p_28077_).selector(p_28076_);

		LivingEntity livingEntity = this.bee.level.getNearestEntity(this.bee.level.getEntitiesOfClass(LivingEntity.class, this.bee.getBoundingBox().inflate(p_28077_), (p_148152_) -> {
			return true;
		}), targetConditions, this.bee, this.bee.getX(), this.bee.getEyeY(), this.bee.getZ());

		if (livingEntity != null) {
			return Optional.of(livingEntity);
		}

		return Optional.empty();
	}
}