package baguchan.earthmobsmod.entity;

import baguchan.earthmobsmod.api.IMuddyPig;
import baguchan.earthmobsmod.registry.ModEntities;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.animal.Pig;
import net.minecraft.world.level.Level;

public class ZombifiedPig extends Pig implements IMuddyPig {
	public ZombifiedPig(EntityType<? extends Pig> p_29462_, Level p_29463_) {
		super(p_29462_, p_29463_);
	}

	public static AttributeSupplier.Builder createAttributes() {
		return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 10.0D).add(Attributes.MOVEMENT_SPEED, 0.25D);
	}

	@Override
	public boolean isMuddy() {
		return false;
	}

	@Override
	public void setMuddy(boolean playing) {

	}

	@Override
	public float getBodyRollAngle(float p_30433_, float p_30434_) {
		return 0;
	}

    @Override
    public void setColorData(byte colorData) {

    }

    @Override
    public byte getColorData() {
        return 0;
    }

	@Override
	public ZombifiedPig getBreedOffspring(ServerLevel p_149001_, AgeableMob p_149002_) {
		return ModEntities.ZOMBIFIED_PIG.get().create(p_149001_, EntitySpawnReason.BREEDING);
	}
}
