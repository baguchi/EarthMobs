package baguchan.earthmobsmod.entity;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SuspiciousEffectHolder;

import java.util.List;
import java.util.Optional;

public interface IFlowerCow extends IPlantMob {

	default Optional<List<SuspiciousEffectHolder.EffectEntry>> getEffectsFromItemStack(ItemStack p_298141_) {
		SuspiciousEffectHolder suspiciouseffectholder = SuspiciousEffectHolder.tryGet(p_298141_.getItem());
		return suspiciouseffectholder != null ? Optional.of(suspiciouseffectholder.getSuspiciousEffects()) : Optional.empty();
	}

	Block getFlower();

	default Block getPlant() {
		return getFlower();
	}
}