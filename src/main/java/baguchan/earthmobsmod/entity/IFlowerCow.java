package baguchan.earthmobsmod.entity;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SuspiciousEffectHolder;
import org.apache.commons.lang3.tuple.Pair;

import java.util.Optional;

public interface IFlowerCow extends IPlantMob {

	default Optional<Pair<MobEffect, Integer>> getEffectFromItemStack(ItemStack p_28957_) {
		SuspiciousEffectHolder suspiciouseffectholder = SuspiciousEffectHolder.tryGet(p_28957_.getItem());
		return suspiciouseffectholder != null ? Optional.of(Pair.of(suspiciouseffectholder.getSuspiciousEffect(), suspiciouseffectholder.getEffectDuration())) : Optional.empty();
	}

	Block getFlower();

	default Block getPlant() {
		return getFlower();
	}
}