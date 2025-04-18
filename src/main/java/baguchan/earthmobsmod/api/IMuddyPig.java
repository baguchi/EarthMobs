package baguchan.earthmobsmod.api;

import baguchan.earthmobsmod.registry.ModTags;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.DyeColor;

public interface IMuddyPig {
	boolean isMuddy();

	void setMuddy(boolean playing);

	float getBodyRollScale(float p_30433_);

	void setSheared(DyeColor sheared);

	DyeColor getSheared();

	default boolean canMuddy(LivingEntity livingEntity) {
		return livingEntity.getType().is(ModTags.Entities.CAN_MUDDY);
	}
}
