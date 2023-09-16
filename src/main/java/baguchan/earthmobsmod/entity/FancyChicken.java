package baguchan.earthmobsmod.entity;

import baguchan.earthmobsmod.registry.ModEntities;
import baguchan.earthmobsmod.registry.ModItems;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.Chicken;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;

public class FancyChicken extends Chicken {

	public FancyChicken(EntityType<? extends FancyChicken> p_28236_, Level p_28237_) {
		super(p_28236_, p_28237_);
	}

	@Nullable
	@Override
	public ItemEntity spawnAtLocation(ItemLike p_19999_) {
		//override to smelly egg
		if (p_19999_.asItem() == Items.EGG) {
			p_19999_ = ModItems.FANCY_EGG.get();
		}

		return super.spawnAtLocation(p_19999_);
	}

	@Override
	public Chicken getBreedOffspring(ServerLevel p_148884_, AgeableMob p_148885_) {
		return ModEntities.FANCY_CHICKEN.get().create(p_148884_);
	}
}
