package baguchan.earthmobsmod.item;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.MobBucketItem;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.LiquidBlockContainer;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.Nullable;

public class MobPotItem extends MobBucketItem {
    public MobPotItem(EntityType<? extends Mob> entitySupplier, Fluid fluidSupplier, SoundEvent soundSupplier, Properties properties) {
        super(entitySupplier, fluidSupplier, soundSupplier, properties);

    }
    @Override
    public boolean emptyContents(@Nullable Player p_150716_, Level p_150717_, BlockPos p_150718_, @Nullable BlockHitResult p_150719_) {
        //only play the sound because low capacity
        this.playEmptySound(p_150716_, p_150717_, p_150718_);
        return true;
    }

    public static ItemStack getEmptySuccessItem(ItemStack p_40700_, Player p_40701_) {
        return !p_40701_.getAbilities().instabuild ? new ItemStack(Items.FLOWER_POT) : p_40700_;
    }

    @Override
    protected boolean canBlockContainFluid(@javax.annotation.Nullable Player player, Level worldIn, BlockPos posIn, BlockState blockstate) {
        return blockstate.getBlock() instanceof LiquidBlockContainer && ((LiquidBlockContainer) blockstate.getBlock()).canPlaceLiquid(player, worldIn, posIn, blockstate, this.content);
    }

}
