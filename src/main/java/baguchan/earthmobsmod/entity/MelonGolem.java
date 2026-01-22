package baguchan.earthmobsmod.entity;

import baguchan.earthmobsmod.entity.projectile.MelonSeed;
import baguchan.earthmobsmod.registry.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.attribute.EnvironmentAttributes;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.golem.AbstractGolem;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.monster.RangedAttackMob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.common.IShearable;

import javax.annotation.Nullable;
import java.util.EnumSet;
import java.util.List;

public class MelonGolem extends AbstractGolem implements RangedAttackMob, IShearable {
	private static final EntityDataAccessor<Byte> DATA_MELON_ID = SynchedEntityData.defineId(MelonGolem.class, EntityDataSerializers.BYTE);
	private static final byte MELON_FLAG = 16;
	private static final float EYE_HEIGHT = 1.7F;

	public MelonGolem(EntityType<? extends MelonGolem> p_29902_, Level p_29903_) {
		super(p_29902_, p_29903_);
	}

	protected void registerGoals() {
		this.goalSelector.addGoal(1, new MelonGolem.ShootSeedGoal(this));
		this.goalSelector.addGoal(2, new WaterAvoidingRandomStrollGoal(this, 1.0D, 1.0000001E-5F));
		this.goalSelector.addGoal(3, new LookAtPlayerGoal(this, Player.class, 6.0F));
		this.goalSelector.addGoal(4, new RandomLookAroundGoal(this));
		this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Mob.class, 10, true, false, (p_29932_, serverLevel) -> {
			return p_29932_ instanceof Enemy;
		}));
	}

	public static AttributeSupplier.Builder createAttributes() {
		return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 4.0D).add(Attributes.MOVEMENT_SPEED, (double) 0.2F);
	}

	@Override
	protected void defineSynchedData(SynchedEntityData.Builder builder) {
		super.defineSynchedData(builder);
		builder.define(DATA_MELON_ID, (byte) 16);
	}

	@Override
	public void addAdditionalSaveData(ValueOutput p_29923_) {
		super.addAdditionalSaveData(p_29923_);
		p_29923_.putBoolean("Melon", this.hasMelon());
	}

	@Override
	public void readAdditionalSaveData(ValueInput p_29915_) {
		super.readAdditionalSaveData(p_29915_);
		this.setMelon(p_29915_.getBooleanOr("Melon", true));
	}

	@Override
	public boolean isSensitiveToWater() {
		return true;
	}

	@Override
	public boolean canAttack(LivingEntity target) {
		return !target.is(EntityType.CREEPER) && super.canAttack(target);
	}

	@Override
    public void aiStep() {
        super.aiStep();
        if (this.level() instanceof ServerLevel serverlevel) {
            if (serverlevel.environmentAttributes().getValue(EnvironmentAttributes.SNOW_GOLEM_MELTS, this.position())) {
                this.hurtServer(serverlevel, this.damageSources().onFire(), 1.0F);
            }

            if (!net.neoforged.neoforge.event.EventHooks.canEntityGrief(serverlevel, this)) {
                return;
            }

            BlockState blockstate = Blocks.SNOW.defaultBlockState();

            for (int i = 0; i < 4; i++) {
                int j = Mth.floor(this.getX() + (i % 2 * 2 - 1) * 0.25F);
                int k = Mth.floor(this.getY());
                int l = Mth.floor(this.getZ() + (i / 2 % 2 * 2 - 1) * 0.25F);
                BlockPos blockpos = new BlockPos(j, k, l);
                if (this.level().getBlockState(blockpos).isAir() && blockstate.canSurvive(this.level(), blockpos)) {
                    this.level().setBlockAndUpdate(blockpos, blockstate);
                    this.level().gameEvent(GameEvent.BLOCK_PLACE, blockpos, GameEvent.Context.of(this, blockstate));
                }
            }
        }
    }


    @Override
	public void performRangedAttack(LivingEntity target, float p_29913_) {
		MelonSeed melonSeed = new MelonSeed(this.level(), this, Items.MELON_SEEDS.getDefaultInstance());
		double d0 = target.getEyeY() - (double) 1.1F;
		double d1 = target.getX() - this.getX();
		double d2 = d0 - melonSeed.getY();
		double d3 = target.getZ() - this.getZ();
		double d4 = Math.sqrt(d1 * d1 + d3 * d3) * (double) 0.2F;
		melonSeed.shoot(d1, d2 + d4, d3, 1.6F, 12.0F);
		this.playSound(SoundEvents.SNOW_GOLEM_SHOOT, 1.0F, 0.4F / (this.getRandom().nextFloat() * 0.4F + 0.8F));
		this.level().addFreshEntity(melonSeed);
	}

	public boolean readyForShearing() {
		return this.isAlive() && this.hasMelon();
	}

	public boolean hasMelon() {
		return (this.entityData.get(DATA_MELON_ID) & 16) != 0;
	}

	public void setMelon(boolean p_29937_) {
		byte b0 = this.entityData.get(DATA_MELON_ID);
		if (p_29937_) {
			this.entityData.set(DATA_MELON_ID, (byte) (b0 | 16));
		} else {
			this.entityData.set(DATA_MELON_ID, (byte) (b0 & -17));
		}

	}

	@Nullable
	protected SoundEvent getAmbientSound() {
		return SoundEvents.SNOW_GOLEM_AMBIENT;
	}

	@Nullable
	protected SoundEvent getHurtSound(DamageSource p_29929_) {
		return SoundEvents.SNOW_GOLEM_HURT;
	}

	@Nullable
	protected SoundEvent getDeathSound() {
		return SoundEvents.SNOW_GOLEM_DEATH;
	}

	@Override
	public Vec3 getLeashOffset() {
		return new Vec3(0.0D, (double) (0.75F * this.getEyeHeight()), (double) (this.getBbWidth() * 0.4F));
	}

	@Override
	public boolean isShearable(@org.jetbrains.annotations.Nullable Player player, ItemStack item, Level level, BlockPos pos) {
		return readyForShearing();
	}

	@Override
	public List<ItemStack> onSheared(@org.jetbrains.annotations.Nullable Player player, ItemStack item, Level level, BlockPos pos) {
		level.playSound(null, this, SoundEvents.SNOW_GOLEM_SHEAR, player == null ? SoundSource.BLOCKS : SoundSource.PLAYERS, 1.0F, 1.0F);
		if (!level.isClientSide()) {
            Block block = this.isAggressive() ? ModBlocks.CARVED_MELON_SHOOT.get() : ModBlocks.CARVED_MELON.get();

			setMelon(false);
            return java.util.Collections.singletonList(new ItemStack(block));
		}
		return java.util.Collections.emptyList();
	}

	static class ShootSeedGoal extends Goal {
		private final MelonGolem golem;
		private int attackStep;
		private int attackTime;
		private int lastSeen;

		public ShootSeedGoal(MelonGolem melonGolem) {
			this.golem = melonGolem;
			this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
		}

		public boolean canUse() {
			LivingEntity livingentity = this.golem.getTarget();
			return livingentity != null && livingentity.isAlive() && this.golem.canAttack(livingentity) && this.lastSeen < 200;
		}

		public void start() {
			this.attackStep = 0;
			this.golem.setAggressive(true);
		}

		public void stop() {
			this.lastSeen = 0;
			this.golem.setAggressive(false);
		}

		public void tick() {
			--this.attackTime;
			LivingEntity livingentity = this.golem.getTarget();
			if (livingentity != null) {
				boolean flag = this.golem.getSensing().hasLineOfSight(livingentity);
				if (flag) {
					this.lastSeen = 0;
				} else {
					++this.lastSeen;
				}

				double d0 = this.golem.distanceToSqr(livingentity);
				if (d0 < this.getFollowDistance() * this.getFollowDistance() && flag) {
					if (this.attackTime <= 0) {
						++this.attackStep;
						if (this.attackStep == 1) {
							this.attackTime = 10;
						} else if (this.attackStep <= 5) {
							this.attackTime = 5;
						} else {
							this.attackTime = 10;
							this.attackStep = 0;
						}

						if (this.attackStep > 1) {
							double d4 = Math.sqrt(Math.sqrt(d0)) * 0.5D;

							this.golem.performRangedAttack(livingentity, attackTime);
						}
					}

					this.golem.getLookControl().setLookAt(livingentity, 10.0F, 10.0F);
				}
				if (d0 >= 10F * 10F && d0 < this.getFollowDistance() * this.getFollowDistance() && flag || this.lastSeen >= 5) {
					this.golem.getNavigation().moveTo(livingentity.getX(), livingentity.getY(), livingentity.getZ(), 1.0F);
				} else {
					this.golem.getNavigation().stop();
				}

				super.tick();
			}
		}

		private double getFollowDistance() {
			return this.golem.getAttributeValue(Attributes.FOLLOW_RANGE);
		}
	}
}
