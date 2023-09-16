package baguchan.earthmobsmod.entity;

import baguchan.earthmobsmod.registry.ModEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.world.entity.ai.goal.PanicGoal;
import net.minecraft.world.entity.animal.Rabbit;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import org.jetbrains.annotations.Nullable;

public class HyperRabbit extends Rabbit {
	private static final EntityDataAccessor<Boolean> DATA_SPARK = SynchedEntityData.defineId(HyperRabbit.class, EntityDataSerializers.BOOLEAN);

	public HyperRabbit(EntityType<? extends Rabbit> p_29656_, Level p_29657_) {
		super(p_29656_, p_29657_);
	}

	protected void registerGoals() {
		super.registerGoals();
		this.goalSelector.getAvailableGoals().stream().map(it -> it.getGoal()).filter(it -> it instanceof PanicGoal).findFirst().ifPresent(goal -> {
			this.goalSelector.removeGoal(goal);
			this.goalSelector.addGoal(1, new RabbitPanicGoal(this, 1.25D));
		});
	}

	@Override
	public void setTarget(@Nullable LivingEntity p_21544_) {
		super.setTarget(p_21544_);
		if (p_21544_ != null) {
			this.setSpark(true);
		} else {
			this.setSpark(false);
		}
	}

	@Override
	public Rabbit getBreedOffspring(ServerLevel p_149035_, AgeableMob p_149036_) {
		return ModEntities.HYPER_RABBIT.get().create(p_149035_);
	}

	@Override
	public void setVariant(Rabbit.Variant p_29734_) {
		if (p_29734_ == Rabbit.Variant.EVIL) {
			if (!this.hasCustomName()) {
				this.setCustomName(Component.literal("HR_X"));
			}
		}
		super.setVariant(p_29734_);
	}

	@Override
	protected void defineSynchedData() {
		super.defineSynchedData();
		this.entityData.define(DATA_SPARK, false);
	}

	public static AttributeSupplier.Builder createAttributes() {
		return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 4.0D).add(Attributes.MOVEMENT_SPEED, (double) 0.32F);
	}

	public static boolean checkHyperSpawnRules(EntityType<HyperRabbit> p_29699_, LevelAccessor p_29700_, MobSpawnType p_29701_, BlockPos p_29702_, RandomSource p_29703_) {
		return p_29700_.getBlockState(p_29702_.below()).is(BlockTags.RABBITS_SPAWNABLE_ON) && isBrightEnoughToSpawn(p_29700_, p_29702_);
	}

	public boolean isSpark() {
		return this.entityData.get(DATA_SPARK);
	}

	public void setSpark(boolean spark) {
		this.entityData.set(DATA_SPARK, spark);
	}

	public void push(Entity p_33636_) {
		if (p_33636_ instanceof LivingEntity && !(p_33636_ instanceof HyperRabbit) && !(p_33636_ instanceof Player)) {
			this.dealDamage((LivingEntity) p_33636_);
		}
		super.push(p_33636_);
	}

	protected void dealDamage(LivingEntity livingentity) {
		if (this.isAlive() && isSpark() && !this.isAlliedTo(livingentity)) {
			boolean flag = livingentity.isDamageSourceBlocked(this.damageSources().indirectMagic(this, this));
			float f1 = (float) Mth.clamp(livingentity.getDeltaMovement().horizontalDistanceSqr() * 1.15F, 0.2F, 3.0F);
			float f2 = flag ? 0.25F : 1.0F;
			float i = getVariant() == Variant.EVIL ? 1.5F : 1F;
			double d1 = this.getX() - livingentity.getX();
			double d2 = this.getZ() - livingentity.getZ();
			if (livingentity.hurt(this.damageSources().indirectMagic(this, this), 2.0F * i)) {
				this.playSound(SoundEvents.PLAYER_ATTACK_KNOCKBACK, 1.0F, (this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 1.0F);
				this.doEnchantDamageEffects(this, livingentity);
				livingentity.knockback(f2 * f1, d1, d2);
			}
		}
	}

	@Override
	public void playerTouch(Player p_20081_) {
		super.playerTouch(p_20081_);
		this.dealDamage(p_20081_);
	}

	static class RabbitPanicGoal extends PanicGoal {
		private final HyperRabbit rabbit;

		public RabbitPanicGoal(HyperRabbit p_29775_, double p_29776_) {
			super(p_29775_, p_29776_);
			this.rabbit = p_29775_;
		}

		@Override
		public void start() {
			super.start();
			this.rabbit.setSpark(true);
		}

		@Override
		public void stop() {
			super.stop();
			if (this.rabbit.getVariant() != Variant.EVIL) {
				this.rabbit.setSpark(false);
			}
		}

		public void tick() {
			super.tick();
			this.rabbit.setSpeedModifier(this.speedModifier);
		}
	}

	static class RabbitAvoidEntityGoal<T extends LivingEntity> extends AvoidEntityGoal<T> {
		private final HyperRabbit rabbit;

		public RabbitAvoidEntityGoal(HyperRabbit p_29743_, Class<T> p_29744_, float p_29745_, double p_29746_, double p_29747_) {
			super(p_29743_, p_29744_, p_29745_, p_29746_, p_29747_);
			this.rabbit = p_29743_;
		}

		public boolean canUse() {
			return this.rabbit.getVariant() != Variant.EVIL && super.canUse();
		}

		@Override
		public void start() {
			super.start();
			this.rabbit.setSpark(true);
		}

		@Override
		public void stop() {
			super.stop();
			this.rabbit.setSpark(false);
		}
	}
}
