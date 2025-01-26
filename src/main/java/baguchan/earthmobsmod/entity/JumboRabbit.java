package baguchan.earthmobsmod.entity;

import baguchan.earthmobsmod.registry.ModEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.animal.Rabbit;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;

public class JumboRabbit extends Rabbit {
	public JumboRabbit(EntityType<? extends Rabbit> p_29656_, Level p_29657_) {
		super(p_29656_, p_29657_);
	}

	@Override
	public Rabbit getBreedOffspring(ServerLevel p_149035_, AgeableMob p_149036_) {
		return ModEntities.JUMBO_RABBIT.get().create(p_149035_, EntitySpawnReason.BREEDING);
	}

	public static AttributeSupplier.Builder createAttributes() {
		return Rabbit.createAttributes().add(Attributes.MAX_HEALTH, 10.0D).add(Attributes.MOVEMENT_SPEED, (double) 0.325F).add(Attributes.ATTACK_DAMAGE, 5.0);
	}

	public static boolean checkJumboSpawnRules(EntityType<JumboRabbit> p_29699_, LevelAccessor p_29700_, EntitySpawnReason p_29701_, BlockPos p_29702_, RandomSource p_29703_) {
		return p_29700_.getBlockState(p_29702_.below()).is(BlockTags.RABBITS_SPAWNABLE_ON) && isBrightEnoughToSpawn(p_29700_, p_29702_);
	}

	public boolean doHurtTarget(ServerLevel serverLevel, Entity p_29659_) {
		if (this.getVariant() == Variant.EVIL) {
			this.playSound(SoundEvents.RABBIT_ATTACK, 1.0F, (this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 1.0F);
			return p_29659_.hurtServer(serverLevel, this.damageSources().mobAttack(this), 9.0F);
		} else {
			return p_29659_.hurtServer(serverLevel, this.damageSources().mobAttack(this), 5.0F);
		}
	}
}
