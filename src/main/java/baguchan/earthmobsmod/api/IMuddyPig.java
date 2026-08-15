package baguchan.earthmobsmod.api;

import baguchan.earthmobsmod.registry.ModTags;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.DyeColor;

public interface IMuddyPig {
	boolean isMuddy();

	void setMuddy(boolean playing);

	float getBodyRollScale(float p_30433_);

    void setSheared(boolean sheared);

    boolean isSheared();


    DyeColor getColor();

    void setColor(DyeColor color);

	default boolean canMuddy(LivingEntity livingEntity) {
		return livingEntity.is(ModTags.Entities.CAN_MUDDY);
	}
}
