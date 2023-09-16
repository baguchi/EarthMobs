package baguchan.earthmobsmod.entity;

import baguchan.earthmobsmod.registry.ModEntities;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.Cow;
import net.minecraft.world.level.Level;

public class AlbinoCow extends Cow {
	public AlbinoCow(EntityType<? extends Cow> p_28285_, Level p_28286_) {
		super(p_28285_, p_28286_);
	}

	public Cow getBreedOffspring(ServerLevel p_148884_, AgeableMob p_148885_) {
		return ModEntities.ALBINO_COW.get().create(p_148884_);
	}
}
