package baguchan.earthmobsmod.api;

import baguchan.earthmobsmod.registry.ModTags;
import net.minecraft.world.entity.LivingEntity;

public interface IMoss {
    boolean isMoss();

    void setMoss(boolean moss);

    default boolean canMoss(LivingEntity livingEntity) {
        return livingEntity.getType().is(ModTags.Entities.CAN_MOSS);
    }
}
