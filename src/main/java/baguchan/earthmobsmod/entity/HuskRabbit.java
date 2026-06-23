package baguchan.earthmobsmod.entity;

import baguchan.earthmobsmod.registry.ModEntities;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.animal.rabbit.Rabbit;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

public class HuskRabbit extends ZombifiedRabbit {
    public HuskRabbit(EntityType<? extends Rabbit> p_29656_, Level p_29657_) {
        super(p_29656_, p_29657_);
    }

    @Override
    public boolean doHurtTarget(ServerLevel p_478036_, Entity p_480930_) {
        boolean flag = super.doHurtTarget(p_478036_, p_480930_);
        if (flag && this.getMainHandItem().isEmpty() && p_480930_ instanceof LivingEntity) {
            float f = p_478036_.getCurrentDifficultyAt(this.blockPosition()).getEffectiveDifficulty();
            ((LivingEntity) p_480930_).addEffect(new MobEffectInstance(MobEffects.HUNGER, 100 * (int) f), this);
        }

        return flag;
    }

    @Nullable
    @Override
    public Rabbit getBreedOffspring(ServerLevel p_149035_, AgeableMob p_149036_) {
        Rabbit rabbit = ModEntities.HUSK_RABBIT.get().create(p_149035_, EntitySpawnReason.BREEDING);
        if (rabbit != null) {

            //rabbit.setVariant(rabbit$variant);
        }

        return rabbit;
    }
}
