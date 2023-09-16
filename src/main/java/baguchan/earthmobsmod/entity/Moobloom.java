package baguchan.earthmobsmod.entity;

import baguchan.earthmobsmod.registry.ModBlocks;
import baguchan.earthmobsmod.registry.ModEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.Cow;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUtils;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.SuspiciousStewItem;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import org.apache.commons.lang3.tuple.Pair;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class Moobloom extends Cow implements net.minecraftforge.common.IForgeShearable, IFlowerCow, IHasFlower {
	public Moobloom(EntityType<? extends Cow> p_28285_, Level p_28286_) {
		super(p_28285_, p_28286_);
	}

	public InteractionResult mobInteract(Player p_28941_, InteractionHand p_28942_) {
		ItemStack itemstack = p_28941_.getItemInHand(p_28942_);
		if (itemstack.is(Items.BONE_MEAL) && !this.isBaby()) {
			ItemStack itemstack1 = new ItemStack(this.getFlower());
			ItemStack itemstack2 = ItemUtils.createFilledResult(itemstack, p_28941_, itemstack1, false);
			p_28941_.setItemInHand(p_28942_, itemstack2);
			SoundEvent soundevent = SoundEvents.BONE_MEAL_USE;
			this.playSound(soundevent, 1.0F, 1.0F);
			return InteractionResult.sidedSuccess(this.level().isClientSide);
		} else if (itemstack.is(Items.BOWL) && !this.isBaby()) {
			Pair<MobEffect, Integer> pair = this.getEffectForCow();
			ItemStack itemstack1 = new ItemStack(Items.SUSPICIOUS_STEW);
			SuspiciousStewItem.saveMobEffect(itemstack1, pair.getLeft(), pair.getRight());

			ItemStack itemstack2 = ItemUtils.createFilledResult(itemstack, p_28941_, itemstack1, false);
			p_28941_.setItemInHand(p_28942_, itemstack2);
			SoundEvent soundevent = SoundEvents.MOOSHROOM_MILK_SUSPICIOUSLY;
			this.playSound(soundevent, 1.0F, 1.0F);
			return InteractionResult.sidedSuccess(this.level().isClientSide);
		} else {
			return super.mobInteract(p_28941_, p_28942_);
		}
	}

	@Override
	public Block getFlower() {
		return ModBlocks.BUTTERCUP.get();
	}

	@Override
	public boolean isShearable(@NotNull ItemStack item, Level level, BlockPos pos) {
		return true;
	}

	@NotNull
	@Override
	public List<ItemStack> onSheared(@Nullable Player player, @NotNull ItemStack item, Level level, BlockPos pos, int fortune) {
		return shearInternal(player == null ? SoundSource.BLOCKS : SoundSource.PLAYERS);
	}

	private java.util.List<ItemStack> shearInternal(SoundSource p_28924_) {
		this.level().playSound((Player) null, this, SoundEvents.MOOSHROOM_SHEAR, p_28924_, 1.0F, 1.0F);
		if (!this.level().isClientSide()) {
			((ServerLevel) this.level()).sendParticles(ParticleTypes.EXPLOSION, this.getX(), this.getY(0.5D), this.getZ(), 1, 0.0D, 0.0D, 0.0D, 0.0D);
			this.discard();
			Cow cow = EntityType.COW.create(this.level());
			cow.moveTo(this.getX(), this.getY(), this.getZ(), this.getYRot(), this.getXRot());
			cow.setHealth(this.getHealth());
			cow.yBodyRot = this.yBodyRot;
			if (this.hasCustomName()) {
				cow.setCustomName(this.getCustomName());
				cow.setCustomNameVisible(this.isCustomNameVisible());
			}

			if (this.isPersistenceRequired()) {
				cow.setPersistenceRequired();
			}

			cow.setInvulnerable(this.isInvulnerable());
			this.level().addFreshEntity(cow);

			java.util.List<ItemStack> items = new java.util.ArrayList<>();
			for (int i = 0; i < 4; ++i) {
				items.add(new ItemStack(this.getFlower()));
			}
			return items;
		}
		return java.util.Collections.emptyList();
	}

	@Override
	public Cow getBreedOffspring(ServerLevel p_148890_, AgeableMob p_148891_) {
		return ModEntities.MOOBLOOM.get().create(p_148890_);
	}

	@Override
	public boolean hasFlower() {
		return !this.isBaby();
	}
}
