package baguchan.earthmobsmod.entity.goal;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

import java.util.EnumSet;

public class EatLavaGoal extends Goal {
    private static final int EAT_ANIMATION_TICKS = 40;
    private final Mob mob;
    private final Level level;
    private int eatAnimationTick;

    public EatLavaGoal(Mob p_25207_) {
        this.mob = p_25207_;
        this.level = p_25207_.level();
        this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK, Goal.Flag.JUMP));
    }

    public boolean canUse() {
        if (this.mob.getRandom().nextInt(this.mob.isBaby() ? 50 : 1000) != 0) {
            return false;
        } else {
            BlockPos blockpos = this.mob.blockPosition();
            return this.level.getBlockState(blockpos.below()).is(Blocks.MAGMA_BLOCK);
        }
    }

    public void start() {
        this.eatAnimationTick = this.adjustedTickDelay(40);
        this.level.broadcastEntityEvent(this.mob, (byte) 10);
        this.mob.getNavigation().stop();
    }

    public void stop() {
        this.eatAnimationTick = 0;
    }

    public boolean canContinueToUse() {
        return this.eatAnimationTick > 0;
    }

    public int getEatAnimationTick() {
        return this.eatAnimationTick;
    }

    public void tick() {
        this.eatAnimationTick = Math.max(0, this.eatAnimationTick - 1);
        if (this.eatAnimationTick == this.adjustedTickDelay(4)) {
            BlockPos blockpos = this.mob.blockPosition();

            BlockPos blockpos1 = blockpos.below();
            if (this.level.getBlockState(blockpos1).is(Blocks.MAGMA_BLOCK)) {
                this.level.levelEvent(2001, blockpos1, Block.getId(Blocks.LAVA.defaultBlockState()));
                this.mob.ate();
            }

        }
    }
}
