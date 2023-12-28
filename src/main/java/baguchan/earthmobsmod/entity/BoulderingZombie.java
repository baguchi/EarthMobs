package baguchan.earthmobsmod.entity;

import baguchan.earthmobsmod.registry.ModEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.tags.BiomeTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Difficulty;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.ai.navigation.WallClimberNavigation;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.biome.Biome;

public class BoulderingZombie extends Zombie {
	private static final EntityDataAccessor<Byte> DATA_FLAGS_ID = SynchedEntityData.defineId(BoulderingZombie.class, EntityDataSerializers.BYTE);

	public BoulderingZombie(EntityType<? extends BoulderingZombie> p_34271_, Level p_34272_) {
		super(p_34271_, p_34272_);
		this.xpReward = 5;
	}

	public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes().add(Attributes.FOLLOW_RANGE, 35.0D).add(Attributes.MOVEMENT_SPEED, (double) 0.22F).add(Attributes.ATTACK_DAMAGE, 4.0D).add(Attributes.ARMOR, 4.0D).add(Attributes.ARMOR_TOUGHNESS, 2D).add(Attributes.KNOCKBACK_RESISTANCE, 0.25D).add(Attributes.SPAWN_REINFORCEMENTS_CHANCE);
	}

	protected void defineSynchedData() {
		super.defineSynchedData();
		this.entityData.define(DATA_FLAGS_ID, (byte) 0);
	}

	public void tick() {
		super.tick();
        if (!this.level.isClientSide) {
			this.setClimbing(this.horizontalCollision);
		}
	}

	@Override
	protected void doUnderWaterConversion() {
		this.convertToZombieType(ModEntities.BOULDERING_DROWNED.get());
		if (!this.isSilent()) {
            this.level.levelEvent((Player) null, 1040, this.blockPosition(), 0);
		}
	}

	public boolean onClimbable() {
		return this.isClimbing();
	}

	public boolean isClimbing() {
		return (this.entityData.get(DATA_FLAGS_ID) & 1) != 0;
	}

	public void setClimbing(boolean p_33820_) {
		byte b0 = this.entityData.get(DATA_FLAGS_ID);
		if (p_33820_) {
			b0 = (byte) (b0 | 1);
		} else {
			b0 = (byte) (b0 & -2);
		}

		this.entityData.set(DATA_FLAGS_ID, b0);
	}

    public static boolean checkBoulderingSpawnRules(EntityType<? extends BoulderingZombie> p_32350_, ServerLevelAccessor p_32351_, MobSpawnType p_32352_, BlockPos p_32353_, RandomSource p_32354_) {
        Holder<Biome> holder = p_32351_.getBiome(p_32353_);
        boolean flag = p_32351_.getDifficulty() != Difficulty.PEACEFUL && isDarkEnoughToSpawn(p_32351_, p_32353_, p_32354_) && (p_32352_ == MobSpawnType.SPAWNER || p_32353_.getY() < 0 || p_32351_.getBiome(p_32353_).is(BiomeTags.IS_MOUNTAIN));
        return checkMobSpawnRules(p_32350_, p_32351_, p_32352_, p_32353_, p_32354_) && flag;
    }

	protected PathNavigation createNavigation(Level p_33802_) {
		return new WallClimberNavigation(this, p_33802_);
	}
}
