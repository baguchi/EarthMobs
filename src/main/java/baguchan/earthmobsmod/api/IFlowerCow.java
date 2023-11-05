package baguchan.earthmobsmod.api;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SuspiciousEffectHolder;

import javax.annotation.Nullable;
import java.util.List;

public interface IFlowerCow extends IPlantMob {


    @Nullable
    default List<SuspiciousEffectHolder.EffectEntry> getEffectFromItemStack(ItemStack p_28957_) {
		SuspiciousEffectHolder suspiciouseffectholder = SuspiciousEffectHolder.tryGet(p_28957_.getItem());
        return suspiciouseffectholder != null ? suspiciouseffectholder.getSuspiciousEffects() : null;
	}

	Block getFlower();

	default Block getPlant() {
		return getFlower();
	}
}