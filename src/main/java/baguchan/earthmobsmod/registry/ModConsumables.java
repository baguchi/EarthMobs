package baguchan.earthmobsmod.registry;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.ItemUseAnimation;
import net.minecraft.world.item.component.Consumable;
import net.minecraft.world.item.consume_effects.ApplyStatusEffectsConsumeEffect;

public class ModConsumables {
    public static final Consumable HARDEN_FOOD = defaultHarderFood()
            .onConsume(new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(MobEffects.HUNGER, 600, 0), 0.8F))
            .onConsume(new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 200, 0), 0.8F))
            .build();
    public static final Consumable JELLY = defaultJelly()
            .build();

    public static Consumable.Builder defaultJelly() {
        return Consumable.builder().consumeSeconds(0.8F).animation(ItemUseAnimation.DRINK).sound(SoundEvents.HONEY_DRINK).hasConsumeParticles(false);
    }

    public static Consumable.Builder defaultFood() {
        return Consumable.builder().consumeSeconds(1.6F).animation(ItemUseAnimation.EAT).sound(SoundEvents.GENERIC_EAT).hasConsumeParticles(true);
    }

    public static Consumable.Builder defaultHarderFood() {
        return Consumable.builder().consumeSeconds(2.4F).animation(ItemUseAnimation.EAT).sound(SoundEvents.GENERIC_EAT).hasConsumeParticles(true);
    }
}
