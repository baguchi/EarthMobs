package baguchan.earthmobsmod.entity;

import baguchan.earthmobsmod.registry.ModEntities;
import baguchan.earthmobsmod.registry.ModItems;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.Chicken;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.Nullable;

public class Duck extends Chicken {

	public Duck(EntityType<? extends Duck> p_28236_, Level p_28237_) {
		super(p_28236_, p_28237_);
	}

	@Nullable
	@Override
	public ItemEntity spawnAtLocation(ItemLike p_19999_) {
		//override to duck egg
		if (p_19999_.asItem() == Items.EGG) {
			p_19999_ = ModItems.DUCK_EGG.get();
		}

		return super.spawnAtLocation(p_19999_);
	}

	@Override
	public void aiStep() {
		super.aiStep();
		if (this.isInWater()) {
			this.flapping = 0;
			this.flap = 0;
			this.flapSpeed = 0;
			this.oFlap = 0;
			this.oFlapSpeed = 0;
		}
	}

	@Override
	public Chicken getBreedOffspring(ServerLevel p_148884_, AgeableMob p_148885_) {
		return ModEntities.DUCK.get().create(p_148884_);
	}

	@Override
	protected float getWaterSlowDown() {
		return 0.85F;
	}
}
