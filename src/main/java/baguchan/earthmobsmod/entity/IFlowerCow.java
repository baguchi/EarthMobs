package baguchan.earthmobsmod.entity;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.FlowerBlock;
import org.apache.commons.lang3.tuple.Pair;

public interface IFlowerCow extends IPlantMob {

	default Pair<MobEffect, Integer> getEffectForCow() {
		FlowerBlock flowerblock = (FlowerBlock) getFlower();
		return Pair.of(flowerblock.getSuspiciousEffect(), flowerblock.getEffectDuration());
	}

	Block getFlower();

	default Block getPlant() {
		return getFlower();
	}
}