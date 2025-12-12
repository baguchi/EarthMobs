package baguchan.earthmobsmod.entity;

import baguchan.earthmobsmod.entity.projectile.StrayBoneShard;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;

import java.util.Collection;

public class StrayBoneSpider extends BoneSpider {
	public StrayBoneSpider(EntityType<? extends StrayBoneSpider> p_33786_, Level p_33787_) {
		super(p_33786_, p_33787_);
	}

	public void startFreezeConversion(int p_149831_) {

	}

	public void performRangedAttack(LivingEntity p_29912_, float p_29913_) {
		StrayBoneShard bone = new StrayBoneShard(this.level(), this);
        double x = p_29912_.getX() - this.getX();
        double y = p_29912_.getEyeY() - this.getEyeY();
        double z = p_29912_.getZ() - this.getZ();
        double length = Math.sqrt(x * x + z * z);
        bone.shoot(x, y + (length * 0.275F), z, 0.75F, 2.0F);
        Collection<MobEffectInstance> collection = this.getActiveEffects();
        if (!collection.isEmpty()) {
            for (MobEffectInstance mobEffectInstance : this.getActiveEffects()) {
                if (!mobEffectInstance.getEffect().value().isBeneficial()) {
                    if (mobEffectInstance.getEffect().value().isInstantenous()) {
                        bone.addEffect(new MobEffectInstance(mobEffectInstance.getEffect(), 1, 0));
                    } else if (mobEffectInstance.isInfiniteDuration()) {
                        bone.addEffect(new MobEffectInstance(mobEffectInstance.getEffect(), 100, 0));
                    } else {
                        bone.addEffect(new MobEffectInstance(mobEffectInstance.getEffect(), mobEffectInstance.getDuration() / 4, 0));
                    }
                }
            }
        }
		this.level().addFreshEntity(bone);
	}

	@Override
	public boolean canFreeze() {
		return false;
	}
}
