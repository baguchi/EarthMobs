package baguchan.earthmobsmod.mixin;


import baguchan.earthmobsmod.api.IMuddy;
import baguchan.earthmobsmod.api.IOnMud;
import baguchan.earthmobsmod.api.ISheared;
import baguchan.earthmobsmod.entity.IHasFlower;
import baguchan.earthmobsmod.util.DyeUtil;
import com.google.common.collect.Maps;
import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.goal.EatBlockGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.Pig;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import javax.annotation.Nullable;
import java.util.Map;

@Mixin(Pig.class)
public abstract class PigMixin extends Animal implements IMuddy, net.minecraftforge.common.IForgeShearable, ISheared, IHasFlower {
	private static final EntityDataAccessor<Boolean> DATA_MUDDY_ID = SynchedEntityData.defineId(Pig.class, EntityDataSerializers.BOOLEAN);
	private static final EntityDataAccessor<Byte> DATA_DYE_ID = SynchedEntityData.defineId(Pig.class, EntityDataSerializers.BYTE);
	private static final Map<DyeColor, ItemLike> ITEM_BY_DYE = Util.make(Maps.newEnumMap(DyeColor.class), (p_29841_) -> {
		p_29841_.put(DyeColor.WHITE, Items.WHITE_DYE);
		p_29841_.put(DyeColor.ORANGE, Items.ORANGE_DYE);
		p_29841_.put(DyeColor.MAGENTA, Items.MAGENTA_DYE);
		p_29841_.put(DyeColor.LIGHT_BLUE, Items.LIGHT_BLUE_DYE);
		p_29841_.put(DyeColor.YELLOW, Items.YELLOW_DYE);
		p_29841_.put(DyeColor.LIME, Items.LIME_DYE);
		p_29841_.put(DyeColor.PINK, Items.PINK_DYE);
		p_29841_.put(DyeColor.GRAY, Items.GRAY_DYE);
		p_29841_.put(DyeColor.LIGHT_GRAY, Items.LIGHT_GRAY_DYE);
		p_29841_.put(DyeColor.CYAN, Items.CYAN_DYE);
		p_29841_.put(DyeColor.PURPLE, Items.PURPLE_DYE);
		p_29841_.put(DyeColor.BLUE, Items.BLUE_DYE);
		p_29841_.put(DyeColor.BROWN, Items.BROWN_DYE);
		p_29841_.put(DyeColor.GREEN, Items.GREEN_DYE);
		p_29841_.put(DyeColor.RED, Items.RED_DYE);
		p_29841_.put(DyeColor.BLACK, Items.BLACK_DYE);
	});

	private int eatAnimationTick;
	private EatBlockGoal eatBlockGoal;

	private boolean inMud;
	private boolean isShaking;
	private float shakeAnim;
	private float shakeAnimO;


	protected PigMixin(EntityType<? extends Animal> p_27557_, Level p_27558_) {
		super(p_27557_, p_27558_);
	}

	@Inject(method = "defineSynchedData", at = @At("TAIL"))
	protected void defineSynchedData(CallbackInfo callbackInfo) {
		this.entityData.define(DATA_MUDDY_ID, false);
		this.entityData.define(DATA_DYE_ID, (byte) 0);
	}

	@Override
	public boolean isMuddy() {
		return this.entityData.get(DATA_MUDDY_ID);
	}

	@Override
	public void setMuddy(boolean playing) {
		this.entityData.set(DATA_MUDDY_ID, playing);
	}

	@Override
	public float getBodyRollAngle(float p_30433_, float p_30434_) {
		float f = (Mth.lerp(p_30433_, this.shakeAnimO, this.shakeAnim) + p_30434_) / 1.8F;
		if (f < 0.0F) {
			f = 0.0F;
		} else if (f > 1.0F) {
			f = 1.0F;
		}

		return Mth.sin(f * (float) Math.PI) * Mth.sin(f * (float) Math.PI * 11.0F) * 0.15F * (float) Math.PI;
	}

	public boolean isSheared() {
		return (this.entityData.get(DATA_DYE_ID) & 16) != 0;
	}

	public void setSheared(boolean p_29879_) {
		byte b0 = this.entityData.get(DATA_DYE_ID);
		if (p_29879_) {
			this.entityData.set(DATA_DYE_ID, (byte) (b0 | 16));
		} else {
			this.entityData.set(DATA_DYE_ID, (byte) (b0 & -17));
		}

	}

	public DyeColor getColor() {
		return DyeColor.byId(this.entityData.get(DATA_DYE_ID) & 15);
	}

	public void setColor(DyeColor p_29856_) {
		byte b0 = this.entityData.get(DATA_DYE_ID);
		this.entityData.set(DATA_DYE_ID, (byte) (b0 & 240 | p_29856_.getId() & 15));
	}

	public void tick() {
		super.tick();
		if (this.isAlive() && this instanceof IOnMud && this.canMuddy()) {
			if (((IOnMud) this).isOnMud() && (!this.isMuddy() || this.isSheared()) && !this.isShaking) {
				this.isShaking = true;
				this.inMud = true;
			} else if (this.isInWaterRainOrBubble() && this.isMuddy() && !this.isShaking) {
				this.isShaking = true;
				this.inMud = false;
			} else if (!this.inMud && this.isShaking) {
				if (this.shakeAnim == 0.0F) {
					this.playSound(SoundEvents.WOLF_SHAKE, this.getSoundVolume(), (this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 1.0F);
					this.gameEvent(GameEvent.ITEM_INTERACT_START);
				}

				this.shakeAnimO = this.shakeAnim;
				this.shakeAnim += 0.05F;
				if (this.shakeAnimO >= 2.0F) {
					this.inMud = false;
					this.isShaking = false;
					this.shakeAnimO = 0.0F;
					this.shakeAnim = 0.0F;
					this.setMuddy(false);
				}

				if (this.shakeAnim > 0.4F) {
					float f = (float) this.getY();
					int i = (int) (Mth.sin((this.shakeAnim - 0.4F) * (float) Math.PI) * 7.0F);
					Vec3 vec3 = this.getDeltaMovement();

					for (int j = 0; j < i; ++j) {
						float f1 = (this.random.nextFloat() * 2.0F - 1.0F) * this.getBbWidth() * 0.5F;
						float f2 = (this.random.nextFloat() * 2.0F - 1.0F) * this.getBbWidth() * 0.5F;
						this.level().addParticle(ParticleTypes.SPLASH, this.getX() + (double) f1, (double) (f + 0.8F), this.getZ() + (double) f2, vec3.x, vec3.y, vec3.z);
					}
				}
			} else if (this.inMud && this.isShaking) {
				if (this.shakeAnim == 0.0F) {
					this.playSound(SoundEvents.WOLF_SHAKE, this.getSoundVolume(), (this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 1.0F);
					this.gameEvent(GameEvent.ENTITY_INTERACT);
				}

				this.shakeAnimO = this.shakeAnim;
				this.shakeAnim += 0.05F;
				if (this.shakeAnimO >= 2.0F) {
					this.inMud = false;
					this.isShaking = false;
					this.shakeAnimO = 0.0F;
					this.shakeAnim = 0.0F;
					this.setMuddy(true);
					this.setSheared(false);
					this.setColor(DyeUtil.getRandomColor(this.random));
				}

				if (this.shakeAnim > 0.4F) {
					float f = (float) this.getY();
					int i = (int) (Mth.sin((this.shakeAnim - 0.4F) * (float) Math.PI) * 7.0F);
					Vec3 vec3 = this.getDeltaMovement();

					for (int j = 0; j < i; ++j) {
						float f1 = (this.random.nextFloat() * 2.0F - 1.0F) * this.getBbWidth() * 0.5F;
						float f2 = (this.random.nextFloat() * 2.0F - 1.0F) * this.getBbWidth() * 0.5F;
						this.level().addParticle(ParticleTypes.SPLASH, this.getX() + (double) f1, (double) (f + 0.8F), this.getZ() + (double) f2, vec3.x, vec3.y, vec3.z);
					}
				}
			}

		}
	}

	@Inject(method = "addAdditionalSaveData", at = @At("TAIL"))
	public void addAdditionalSaveData(CompoundTag p_27587_, CallbackInfo callbackInfo) {
		p_27587_.putBoolean("Muddy", this.isMuddy());
		p_27587_.putBoolean("Sheared", this.isSheared());
		p_27587_.putByte("Color", (byte) this.getColor().getId());
	}

	@Inject(method = "readAdditionalSaveData", at = @At("TAIL"))
	public void readAdditionalSaveData(CompoundTag p_27576_, CallbackInfo callbackInfo) {
		this.setMuddy(p_27576_.getBoolean("Muddy"));
		this.setSheared(p_27576_.getBoolean("Sheared"));
		this.setColor(DyeColor.byId(p_27576_.getByte("Color")));
	}

	@Override
	public boolean isShearable(@javax.annotation.Nonnull ItemStack item, Level world, BlockPos pos) {
		return this.isAlive() && !this.isSheared() && !this.isBaby() && this.isMuddy();
	}

	@javax.annotation.Nonnull
	@Override
	public java.util.List<ItemStack> onSheared(@Nullable Player player, @javax.annotation.Nonnull ItemStack item, Level world, BlockPos pos, int fortune) {
		world.playSound(null, this, SoundEvents.SHEEP_SHEAR, player == null ? SoundSource.BLOCKS : SoundSource.PLAYERS, 1.0F, 1.0F);
		this.gameEvent(GameEvent.SHEAR, player);
		if (!world.isClientSide) {
			this.setSheared(true);
			int i = 1 + this.random.nextInt(3);

			java.util.List<ItemStack> items = new java.util.ArrayList<>();
			for (int j = 0; j < i; ++j) {
				items.add(new ItemStack(ITEM_BY_DYE.get(this.getColor())));
			}
			return items;
		}
		return java.util.Collections.emptyList();
	}

	@Override
	public boolean hasFlower() {
		return !isSheared() && this.isMuddy();
	}
}
