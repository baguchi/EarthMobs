package baguchan.earthmobsmod.entity;

import baguchan.earthmobsmod.entity.projectile.StrayBoneShard;
import baguchan.earthmobsmod.registry.ModItems;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.animal.golem.IronGolem;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

import java.util.Collection;

public class StrayBoneSpider extends BoneSpider {
	public StrayBoneSpider(EntityType<? extends StrayBoneSpider> p_33786_, Level p_33787_) {
		super(p_33786_, p_33787_);
	}

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(1, new FloatGoal(this));
        this.goalSelector.addGoal(4, new BoneSpiderAttackGoal(this, 20));
        this.goalSelector.addGoal(5, new WaterAvoidingRandomStrollGoal(this, 0.8D));
        this.goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(6, new RandomLookAroundGoal(this));
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
        this.targetSelector.addGoal(2, new SpiderTargetGoal<>(this, Player.class));
        this.targetSelector.addGoal(3, new SpiderTargetGoal<>(this, IronGolem.class));
    }

	public void startFreezeConversion(int p_149831_) {

	}

	public void performRangedAttack(LivingEntity p_29912_, float p_29913_) {
		StrayBoneShard bone = new StrayBoneShard(this.level(), this, ModItems.BONE_SHARD.toStack());
        double x = p_29912_.getX() - this.getX();
        double y = p_29912_.getEyeY() - this.getEyeY();
        double z = p_29912_.getZ() - this.getZ();
        double length = Math.sqrt(x * x + z * z);
        bone.shoot(x, y + (length * 0.25F), z, 0.75F, 2.0F);
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
