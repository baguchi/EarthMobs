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
		double d1 = p_29912_.getX() - this.getX();
		double d2 = p_29912_.getEyeY() - this.getEyeY();
		double d3 = p_29912_.getZ() - this.getZ();
		bone.shoot(d1, d2, d3, 1.4F, 2.0F + p_29913_);

		Collection<MobEffectInstance> collection = this.getActiveEffects();
		if (!collection.isEmpty()) {
			for (MobEffectInstance mobEffectInstance : this.getActiveEffects()) {
				bone.addEffect(new MobEffectInstance(mobEffectInstance.getEffect(), mobEffectInstance.getDuration() / 4, 0));
			}
		}
		this.level().addFreshEntity(bone);
	}

	@Override
	public boolean canFreeze() {
		return false;
	}
}
