package baguchan.earthmobsmod.effect;

import baguchan.earthmobsmod.registry.ModDamageSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobType;

public class ZombifiedEffect extends MobEffect {
    public ZombifiedEffect(MobEffectCategory beneficial, int i) {
        super(beneficial, i);
    }

    public void applyEffectTick(LivingEntity p_301282_, int p_300945_) {
        super.applyEffectTick(p_301282_, p_300945_);
        if (p_301282_.getMobType() != MobType.UNDEAD) {
            p_301282_.hurt(p_301282_.damageSources().source(ModDamageSource.ZOMBIFIED), 2);
        }

    }

    public boolean shouldApplyEffectTickThisTick(int p_300189_, int p_298417_) {
        int i = 50 >> p_298417_;
        if (i > 0) {
            return p_300189_ % i == 0;
        } else {
            return true;
        }
    }
}
