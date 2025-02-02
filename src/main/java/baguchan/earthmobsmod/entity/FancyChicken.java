package baguchan.earthmobsmod.entity;

import baguchan.earthmobsmod.registry.ModBuiltInLootTables;
import baguchan.earthmobsmod.registry.ModEntities;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.Chicken;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;

public class FancyChicken extends Chicken {


	public int eggSpecialTime = this.random.nextInt(6000) + 6000;

	public FancyChicken(EntityType<? extends FancyChicken> p_28236_, Level p_28237_) {
		super(p_28236_, p_28237_);
	}

	@Override
	public void aiStep() {
		super.aiStep();
		Level var3 = this.level();
		if (var3 instanceof ServerLevel serverlevel) {
			if (this.isAlive() && !this.isBaby() && !this.isChickenJockey() && --this.eggSpecialTime <= 0) {
				if (this.dropFromGiftLootTable(serverlevel, ModBuiltInLootTables.FANCY_CHICKEN_LAY, this::spawnAtLocation)) {
					this.playSound(SoundEvents.CHICKEN_EGG, 1.0F, (this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 1.0F);
					this.gameEvent(GameEvent.ENTITY_PLACE);
				}
				this.eggSpecialTime = this.random.nextInt(6000) + 6000;
			}
			//do not trigger the normal egg spawn
			this.eggTime = 10;
		}
	}

	@Override
	public void readAdditionalSaveData(CompoundTag compound) {
		super.readAdditionalSaveData(compound);
		if (compound.contains("SpecialEggLayTime")) {
			this.eggSpecialTime = compound.getInt("SpecialEggLayTime");
		}

	}

	@Override
	public void addAdditionalSaveData(CompoundTag compound) {
		super.addAdditionalSaveData(compound);
		compound.putInt("SpecialEggLayTime", this.eggSpecialTime);
	}

	@Override
	public Chicken getBreedOffspring(ServerLevel p_148884_, AgeableMob p_148885_) {
		return ModEntities.FANCY_CHICKEN.get().create(p_148884_, EntitySpawnReason.BREEDING);
	}
}
