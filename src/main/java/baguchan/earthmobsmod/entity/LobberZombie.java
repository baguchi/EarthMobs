package baguchan.earthmobsmod.entity;

import baguchan.earthmobsmod.entity.goal.RangedAndMeleeAttack;
import baguchan.earthmobsmod.entity.projectile.ZombieFlesh;
import baguchan.earthmobsmod.registry.ModEntities;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.MoveThroughVillageGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.animal.Turtle;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.RangedAttackMob;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.entity.monster.ZombifiedPiglin;
import net.minecraft.world.entity.npc.AbstractVillager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public class LobberZombie extends Zombie implements RangedAttackMob {
	public LobberZombie(EntityType<? extends LobberZombie> p_34271_, Level p_34272_) {
		super(p_34271_, p_34272_);
		this.xpReward = 5;
	}

	protected void addBehaviourGoals() {
		this.goalSelector.addGoal(2, new RangedAndMeleeAttack(this, 1.0D, 40, 10.0F));
		this.goalSelector.addGoal(6, new MoveThroughVillageGoal(this, 1.0D, true, 4, this::canBreakDoors));
		this.goalSelector.addGoal(7, new WaterAvoidingRandomStrollGoal(this, 1.0D));
		this.targetSelector.addGoal(1, (new HurtByTargetGoal(this)).setAlertOthers(ZombifiedPiglin.class));
		this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true));
		this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, AbstractVillager.class, false));
		this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, IronGolem.class, true));
		this.targetSelector.addGoal(5, new NearestAttackableTargetGoal<>(this, Turtle.class, 10, true, false, Turtle.BABY_ON_LAND_SELECTOR));
	}

	public static AttributeSupplier.Builder createAttributes() {
		return Monster.createMonsterAttributes().add(Attributes.FOLLOW_RANGE, 35.0D).add(Attributes.MAX_HEALTH, 24.0D).add(Attributes.MOVEMENT_SPEED, (double) 0.23F).add(Attributes.ATTACK_DAMAGE, 3.0D).add(Attributes.ARMOR, 2.0D).add(Attributes.SPAWN_REINFORCEMENTS_CHANCE);
	}

	@Override
	protected void doUnderWaterConversion() {
		this.convertToZombieType(ModEntities.LOBBER_DROWNED.get());
		if (!this.isSilent()) {
			this.level().levelEvent((Player) null, 1040, this.blockPosition(), 0);
		}
	}

	@Override
	public void performRangedAttack(LivingEntity p_29912_, float p_29913_) {
		ZombieFlesh zombieFlesh = new ZombieFlesh(this.level(), this);
		double d0 = p_29912_.getEyeY() - this.getEyeY();
		double d1 = p_29912_.getX() - this.getX();
		double d3 = p_29912_.getZ() - this.getZ();
		double d4 = Math.sqrt(d1 * d1 + d3 * d3) * (double) 0.1F;
		zombieFlesh.shoot(d1, d0 + d4, d3, 0.8F, 0.1F);
		this.playSound(SoundEvents.SNOW_GOLEM_SHOOT, 1.0F, 0.4F / (this.getRandom().nextFloat() * 0.4F + 0.8F));
		this.level().addFreshEntity(zombieFlesh);
		this.swing(InteractionHand.MAIN_HAND);
	}
}
