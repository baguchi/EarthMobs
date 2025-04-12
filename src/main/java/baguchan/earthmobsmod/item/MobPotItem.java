package baguchan.earthmobsmod.item;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUtils;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.MobBucketItem;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.LiquidBlockContainer;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;

import javax.annotation.Nullable;

public class MobPotItem extends MobBucketItem {
    public MobPotItem(EntityType<? extends Mob> entitySupplier, Fluid fluidSupplier, SoundEvent soundSupplier, Properties properties) {
        super(entitySupplier, fluidSupplier, soundSupplier, properties);

    }
    @Override
    public boolean emptyContents(@Nullable LivingEntity p_394627_, Level p_150717_, BlockPos p_150718_, @Nullable BlockHitResult p_150719_, @Nullable ItemStack container) {
        //only play the sound because low capacity
        this.playEmptySound(p_394627_, p_150717_, p_150718_);
        return true;
    }

    @Override
    public InteractionResult use(Level p_40703_, Player p_40704_, InteractionHand p_40705_) {
        ItemStack itemstack = p_40704_.getItemInHand(p_40705_);
        BlockHitResult blockhitresult = getPlayerPOVHitResult(
                p_40703_, p_40704_, this.content == Fluids.EMPTY ? ClipContext.Fluid.SOURCE_ONLY : ClipContext.Fluid.NONE
        );
        if (blockhitresult.getType() == HitResult.Type.MISS) {
            return InteractionResult.PASS;
        } else if (blockhitresult.getType() != HitResult.Type.BLOCK) {
            return InteractionResult.PASS;
        } else {
            BlockPos blockpos = blockhitresult.getBlockPos();
            Direction direction = blockhitresult.getDirection();
            BlockPos blockpos1 = blockpos.relative(direction);
            if (this.emptyContents(p_40704_, p_40703_, blockpos1, blockhitresult, itemstack)) {
                this.checkExtraContent(p_40704_, p_40703_, itemstack, blockpos1);
                if (p_40704_ instanceof ServerPlayer) {
                    CriteriaTriggers.PLACED_BLOCK.trigger((ServerPlayer) p_40704_, blockpos1, itemstack);
                }

                p_40704_.awardStat(Stats.ITEM_USED.get(this));
                ItemStack itemstack1 = ItemUtils.createFilledResult(itemstack, p_40704_, getEmptySuccessItem(itemstack, p_40704_));
                return InteractionResult.SUCCESS.heldItemTransformedTo(itemstack1);

            }
        }
        return InteractionResult.PASS;
    }

    public static ItemStack getEmptySuccessItem(ItemStack p_40700_, Player p_40701_) {
        return !p_40701_.hasInfiniteMaterials() ? new ItemStack(Items.FLOWER_POT) : p_40700_;
    }

    @Override
    protected boolean canBlockContainFluid(@javax.annotation.Nullable Player player, Level worldIn, BlockPos posIn, BlockState blockstate) {
        return blockstate.getBlock() instanceof LiquidBlockContainer && ((LiquidBlockContainer) blockstate.getBlock()).canPlaceLiquid(player, worldIn, posIn, blockstate, this.content);
    }

}
