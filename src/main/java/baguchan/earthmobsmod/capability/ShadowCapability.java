package baguchan.earthmobsmod.capability;

import baguchan.earthmobsmod.EarthMobsMod;
import baguchan.earthmobsmod.registry.ModEffects;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.EntitySelector;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.level.entity.EntityTypeTest;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;
import java.util.UUID;

public class ShadowCapability implements ICapabilityProvider, INBTSerializable<CompoundTag> {
	private static final UUID SPEED_MODIFIER_BOOST_UUID = UUID.fromString("a4be9598-fd19-8c8b-7e3d-142defd78b7c");
	public float prevShadowX;
	public float prevShadowY;
	public float prevShadowZ;

	public float shadowX;
	public float shadowY;
	public float shadowZ;

	public float prevShadowX2;
	public float prevShadowY2;
	public float prevShadowZ2;

	public float shadowX2;
	public float shadowY2;
	public float shadowZ2;

	public float prevYBodyRot;
	public float yBodyRot;
	public float prevYBodyRot2;
	public float yBodyRot2;
	public float prevRotationPitch;
	public float xRot;
	public float prevRotationPitch2;
	public float xRot2;

	private float percentBoost;

	public void tick(LivingEntity livingEntity) {
		if (!livingEntity.level().isClientSide) {
			removeBoost(livingEntity);
		}

			double elasticity = 0.25D;
			this.prevShadowX = this.shadowX;
			this.prevShadowY = this.shadowY;
			this.prevShadowZ = this.shadowZ;
			this.prevShadowX2 = this.shadowX2;
			this.prevShadowY2 = this.shadowY2;
			this.prevShadowZ2 = this.shadowZ2;
			this.prevYBodyRot = this.yBodyRot;
			this.prevYBodyRot2 = this.yBodyRot2;
			this.yBodyRot = (float) (this.yBodyRot + (livingEntity.yBodyRot - this.yBodyRot) * elasticity * 0.75D);
			this.yBodyRot2 = (float) (this.yBodyRot2 + (this.yBodyRot - this.yBodyRot2) * elasticity * 0.3499999940395355D);
			this.xRot = (float) (this.xRot + (livingEntity.getXRot() - this.xRot) * elasticity * 0.75D);
			this.xRot2 = (float) (this.xRot2 + (this.xRot - this.xRot2) * elasticity * 0.3499999940395355D);
			this.shadowX = (float) (this.shadowX + (livingEntity.getX() - this.shadowX) * elasticity);
			this.shadowY = (float) (this.shadowY + (livingEntity.getY() - this.shadowY) * elasticity);
			this.shadowZ = (float) (this.shadowZ + (livingEntity.getZ() - this.shadowZ) * elasticity);
			this.shadowX2 = (float) (this.shadowX2 + (this.shadowX - this.shadowX2) * elasticity * 0.375D);
			this.shadowY2 = (float) (this.shadowY2 + (this.shadowY - this.shadowY2) * elasticity * 0.375D);
			this.shadowZ2 = (float) (this.shadowZ2 + (this.shadowZ - this.shadowZ2) * elasticity * 0.375D);

		if (livingEntity.hasEffect(ModEffects.HYPER_SPARK.get())) {
			if (percentBoost >= 0.65F) {
				pushEntities(livingEntity);
			}
			tryAddBooster(livingEntity);
		}
	}

	protected void pushEntities(LivingEntity entity) {
		if (!entity.level().isClientSide()) {
			List<LivingEntity> list = entity.level().getEntities(EntityTypeTest.forClass(LivingEntity.class), entity.getBoundingBox().expandTowards(0.05F, 0.0F, 0.05F), EntitySelector.pushableBy(entity));
			if (!list.isEmpty()) {
				for (int l = 0; l < list.size(); ++l) {
					LivingEntity entity2 = list.get(l);
					if (entity != entity2 && !entity.isAlliedTo(entity2)) {
						entity2.knockback(2.0D * percentBoost, entity2.getX() - entity.getX(), entity2.getZ() - entity.getZ());
						entity2.hurt(entity.damageSources().mobAttack(entity), Mth.floor(8.0F * percentBoost));
					}
				}
			}
		}
	}

	protected void removeBoost(LivingEntity entity) {
		AttributeInstance attributeinstance = entity.getAttribute(Attributes.MOVEMENT_SPEED);
		if (attributeinstance != null) {
			if (attributeinstance.getModifier(SPEED_MODIFIER_BOOST_UUID) != null) {
				attributeinstance.removeModifier(SPEED_MODIFIER_BOOST_UUID);
			}

		}
	}

	protected void tryAddBooster(LivingEntity entity) {
        if (entity.isSprinting() && entity.getPose() == Pose.STANDING) {
            if (percentBoost <= 1) {
                percentBoost += 0.01F;
            } else {
                percentBoost = 1;
            }

        } else {
            if (percentBoost >= 0) {
                percentBoost -= 0.1F;
            } else {
				percentBoost = 0;
			}
		}
		if (percentBoost > 0) {
			if (!entity.level().isClientSide) {
				AttributeInstance attributeinstance = entity.getAttribute(Attributes.MOVEMENT_SPEED);
				if (attributeinstance == null) {
					return;
				}

				float f = 0.15F * percentBoost;
				attributeinstance.addTransientModifier(new AttributeModifier(SPEED_MODIFIER_BOOST_UUID, "Spark Boost", (double) f, AttributeModifier.Operation.ADDITION));
			}
		}
	}

	public CompoundTag serializeNBT() {
		CompoundTag nbt = new CompoundTag();

		nbt.putFloat("PercentBoost", percentBoost);


		return nbt;
	}

	public void deserializeNBT(CompoundTag nbt) {
		percentBoost = nbt.getFloat("PercentBoost");
	}

	public float getPercentBoost() {
		return percentBoost;
	}

	@Override
	@Nonnull
	public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> capability, @Nullable Direction facing) {
		return capability == EarthMobsMod.SHADOW_CAP ? LazyOptional.of(() -> this).cast() : LazyOptional.empty();
	}
}