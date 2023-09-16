package baguchan.earthmobsmod.entity;

import baguchan.earthmobsmod.registry.ModBlocks;
import baguchan.earthmobsmod.registry.ModEntities;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.Cow;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;

public class Moolip extends Moobloom {
	public Moolip(EntityType<? extends Cow> p_28285_, Level p_28286_) {
		super(p_28285_, p_28286_);
	}

	@Override
	public Block getFlower() {
		return ModBlocks.PINK_DAISY.get();
	}

	@Override
	public Cow getBreedOffspring(ServerLevel p_148890_, AgeableMob p_148891_) {
		return ModEntities.MOOLIP.get().create(p_148890_);
	}
}
