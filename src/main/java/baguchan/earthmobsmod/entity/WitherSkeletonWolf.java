package baguchan.earthmobsmod.entity;

import baguchan.earthmobsmod.registry.ModEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.pathfinder.PathType;

public class WitherSkeletonWolf extends SkeletonWolf {
	public WitherSkeletonWolf(EntityType<? extends WitherSkeletonWolf> p_30369_, Level p_30370_) {
		super(p_30369_, p_30370_);
		this.setPathfindingMalus(PathType.LAVA, 8.0F);
		this.setPathfindingMalus(PathType.DANGER_FIRE, 0.0F);
		this.setPathfindingMalus(PathType.DAMAGE_FIRE, 0.0F);
	}

	@Override
	public WitherSkeletonWolf getBreedOffspring(ServerLevel p_149088_, AgeableMob p_149089_) {
		WitherSkeletonWolf skeletonWolf = ModEntities.WITHER_SKELETON_WOLF.get().create(p_149088_, EntitySpawnReason.BREEDING);

		if (this.isTame()) {
			skeletonWolf.setOwnerUUID(this.getOwnerUUID());
			skeletonWolf.setTame(true, true);
		}
		return skeletonWolf;
	}

	@Override
	public boolean canBeAffected(MobEffectInstance p_31495_) {
		return p_31495_.getEffect() == MobEffects.WITHER ? false : super.canBeAffected(p_31495_);
	}

	@Override
	public boolean doHurtTarget(ServerLevel serverLevel, Entity p_34169_) {
		if (!super.doHurtTarget(serverLevel, p_34169_)) {
			return false;
		} else {
			if (p_34169_ instanceof LivingEntity) {
				((LivingEntity) p_34169_).addEffect(new MobEffectInstance(MobEffects.WITHER, 200), this);
			}

			return true;
		}
	}

	public float getWalkTargetValue(BlockPos p_27573_, LevelReader p_27574_) {
		return p_27574_.getBlockState(p_27573_.below()).is(Blocks.SOUL_SAND) || p_27574_.getBlockState(p_27573_.below()).is(Blocks.SOUL_SOIL) ? 10.0F : super.getWalkTargetValue(p_27573_, p_27574_);
	}
}
