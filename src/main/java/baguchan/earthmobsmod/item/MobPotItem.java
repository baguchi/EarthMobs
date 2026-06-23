package baguchan.earthmobsmod.item;

import baguchan.earthmobsmod.entity.TeaCupPig;
import net.minecraft.advancements.triggers.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.component.DataComponents;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUtils;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.MobBucketItem;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LiquidBlockContainer;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;

import javax.annotation.Nullable;

public class MobPotItem extends MobBucketItem {
    private final EntityType<? extends Mob> type;
    public MobPotItem(EntityType<? extends Mob> entitySupplier, Fluid fluidSupplier, SoundEvent soundSupplier, Properties properties) {
        super(entitySupplier, fluidSupplier, soundSupplier, properties);
        this.type = entitySupplier;

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

    public void checkExtraContent(@Nullable LivingEntity p_394402_, Level p_151147_, ItemStack p_151148_, BlockPos p_151149_) {
        if (p_151147_ instanceof ServerLevel) {
            this.spawn(p_394402_, (ServerLevel) p_151147_, p_151148_, p_151149_);
            p_151147_.gameEvent(p_394402_, GameEvent.ENTITY_PLACE, p_151149_);
        }

    }

    private void spawn(@Nullable LivingEntity livingEntity, ServerLevel p_151142_, ItemStack p_151143_, BlockPos p_151144_) {
        boolean flag = p_151142_.getBlockState(p_151144_).is(Blocks.FLOWER_POT);

        Mob mob = (Mob) this.type.create(p_151142_, EntityType.createDefaultStackConfig(p_151142_, p_151143_, (LivingEntity) null), flag ? p_151144_.below() : p_151144_, EntitySpawnReason.BUCKET, true, false);
        if (mob instanceof Bucketable bucketable) {
            CustomData customdata = (CustomData) p_151143_.getOrDefault(DataComponents.BUCKET_ENTITY_DATA, CustomData.EMPTY);
            bucketable.loadFromBucketTag(customdata.copyTag());
            bucketable.setFromBucket(true);
            if (flag && mob instanceof TeaCupPig teaCupPig) {
                teaCupPig.setOnPot(true);
                if (livingEntity != null) {
                    teaCupPig.setYRot(livingEntity.getNearestViewDirection().getOpposite().toYRot());
                    teaCupPig.yRotO = livingEntity.getNearestViewDirection().getOpposite().toYRot();
                    teaCupPig.setOldPosAndRot();
                }

            }
        }

        if (mob != null) {
            p_151142_.addFreshEntityWithPassengers(mob);
            mob.playAmbientSound();
        }

    }

    public static ItemStack getEmptySuccessItem(ItemStack p_40700_, Player p_40701_) {
        return !p_40701_.hasInfiniteMaterials() ? new ItemStack(Items.FLOWER_POT) : p_40700_;
    }

    @Override
    protected boolean canBlockContainFluid(@javax.annotation.Nullable Player player, Level worldIn, BlockPos posIn, BlockState blockstate) {
        return blockstate.getBlock() instanceof LiquidBlockContainer && ((LiquidBlockContainer) blockstate.getBlock()).canPlaceLiquid(player, worldIn, posIn, blockstate, this.content);
    }

}
