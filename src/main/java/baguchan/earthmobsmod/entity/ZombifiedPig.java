package baguchan.earthmobsmod.entity;

import baguchan.earthmobsmod.registry.ModEntities;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.Pig;
import net.minecraft.world.level.Level;

public class ZombifiedPig extends Pig {
	public ZombifiedPig(EntityType<? extends Pig> p_29462_, Level p_29463_) {
		super(p_29462_, p_29463_);
	}

	public static AttributeSupplier.Builder createAttributes() {
		return Animal.createAnimalAttributes().add(Attributes.MAX_HEALTH, 10.0D).add(Attributes.MOVEMENT_SPEED, 0.25D);
	}

	@Override
	public ZombifiedPig getBreedOffspring(ServerLevel p_149001_, AgeableMob p_149002_) {
		return ModEntities.ZOMBIFIED_PIG.get().create(p_149001_, EntitySpawnReason.BREEDING);
	}
}
