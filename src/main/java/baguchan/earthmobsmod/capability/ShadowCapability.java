package baguchan.earthmobsmod.capability;

import baguchan.earthmobsmod.EarthMobsMod;
import baguchan.earthmobsmod.entity.HyperRabbit;
import baguchan.earthmobsmod.registry.ModEffects;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.EntitySelector;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.level.entity.EntityTypeTest;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.attachment.IAttachmentHolder;
import net.neoforged.neoforge.attachment.IAttachmentSerializer;

import java.util.List;

public class ShadowCapability implements IAttachmentSerializer<ShadowCapability> {
	private static final ResourceLocation SPEED_MODIFIER_BOOST_UUID = ResourceLocation.fromNamespaceAndPath(EarthMobsMod.MODID, "shadow_speed");
	public Vec3 prevShadow = Vec3.ZERO;

	public Vec3 shadow = Vec3.ZERO;

	public Vec3 prevShadow2 = Vec3.ZERO;

	public Vec3 shadow2 = Vec3.ZERO;

	public Vec2 shadowRot = Vec2.ZERO;
	public Vec2 shadowRot2 = Vec2.ZERO;
	public Vec2 prevShadowRot = Vec2.ZERO;
	public Vec2 prevShadowRot2 = Vec2.ZERO;

	public float percentBoost = 0.0F;

	public void tick(LivingEntity mob) {
		double elasticity = 0.25D;
		this.prevShadow = this.shadow;
		this.prevShadow2 = this.shadow2;
		this.prevShadowRot = this.shadowRot;
		this.prevShadowRot2 = this.shadowRot2;
		this.shadowRot = new Vec2((float) (mob.getXRot() + (this.shadowRot.x - mob.getXRot()) * elasticity * 0.75D), (float) (mob.yBodyRot + (this.shadowRot.y - mob.yBodyRot) * elasticity * 0.75D));
		this.shadowRot2 = new Vec2((float) (this.shadowRot.x + (this.shadowRot2.x - this.shadowRot.x) * elasticity * 0.3499999940395355D), (float) (this.shadowRot.y + (this.shadowRot2.y - this.shadowRot.y) * elasticity * 0.3499999940395355D));
		float shadowX = (float) (this.shadow.x + (mob.getX() - this.shadow.x) * elasticity);
		float shadowY = (float) (this.shadow.y + (mob.getY() - this.shadow.y) * elasticity);
		float shadowZ = (float) (this.shadow.z + (mob.getZ() - this.shadow.z) * elasticity);
		float shadowX2 = (float) (this.shadow2.x + (this.shadow.x - this.shadow2.x) * elasticity * 0.375D);
		float shadowY2 = (float) (this.shadow2.y + (this.shadow.y - this.shadow2.y) * elasticity * 0.375D);
		float shadowZ2 = (float) (this.shadow2.z + (this.shadow.z - this.shadow2.z) * elasticity * 0.375D);
		this.shadow = new Vec3(shadowX, shadowY, shadowZ);
		this.shadow2 = new Vec3(shadowX2, shadowY2, shadowZ2);

        if (!mob.level().isClientSide()) {
			removeBoost(mob);
		}

		if (mob instanceof HyperRabbit hyperRabbit && hyperRabbit.isSpark()) {
			this.percentBoost = 1F;
		} else {
			if (mob.hasEffect(ModEffects.HYPER_SPARK)) {
				if (percentBoost >= 0.65F) {
					pushEntities(mob);
				}
				tryAddBooster(mob);
			} else {
				percentBoost = 0F;
			}
		}
	}

	public void setPercentBoost(float percentBoost) {
		this.percentBoost = percentBoost;
	}

	public Vec3 getShadow() {
		return shadow;
	}

	public Vec3 getShadow2() {
		return shadow2;
	}

	public Vec3 getPrevShadow() {
		return prevShadow;
	}

	public Vec3 getPrevShadow2() {
		return prevShadow2;
	}

	public Vec2 getShadowRot() {
		return shadowRot;
	}

	public Vec2 getShadowRot2() {
		return shadowRot2;
	}

	public Vec2 getPrevShadowRot() {
		return prevShadowRot;
	}

	public Vec2 getPrevShadowRot2() {
		return prevShadowRot2;
	}

	protected void pushEntities(LivingEntity entity) {
		if (!entity.level().isClientSide()) {
			List<LivingEntity> list = entity.level().getEntities(EntityTypeTest.forClass(LivingEntity.class), entity.getBoundingBox().expandTowards(0.05F, 0.0F, 0.05F), EntitySelector.pushableBy(entity));
			if (!list.isEmpty()) {
				for (int l = 0; l < list.size(); ++l) {
					LivingEntity entity2 = list.get(l);
					if (entity != entity2 && !entity.isAlliedTo(entity2)) {
						entity2.knockback(5.0D * percentBoost, entity.getDeltaMovement().x, entity.getDeltaMovement().z);
						entity2.hurt(entity.damageSources().mobAttack(entity), Mth.floor(8.0F * percentBoost));
					}
				}
			}
		}
	}

	protected void removeBoost(LivingEntity entity) {
		AttributeInstance attributeinstance = entity.getAttribute(Attributes.MOVEMENT_SPEED);
		if (attributeinstance != null) {
			if (attributeinstance.hasModifier(SPEED_MODIFIER_BOOST_UUID)) {
				attributeinstance.removeModifier(SPEED_MODIFIER_BOOST_UUID);
			}

		}
	}

	protected void tryAddBooster(LivingEntity entity) {
		if (entity.isSprinting() && !entity.isInWater() && entity.getPose() == Pose.STANDING) {
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
            if (!entity.level().isClientSide()) {
				AttributeInstance attributeinstance = entity.getAttribute(Attributes.MOVEMENT_SPEED);
				if (attributeinstance == null) {
					return;
				}

				float f = 0.2F * percentBoost;
				attributeinstance.addTransientModifier(new AttributeModifier(SPEED_MODIFIER_BOOST_UUID, (double) f, AttributeModifier.Operation.ADD_VALUE));
			}
		}
	}
	public float getPercentBoost() {
		return percentBoost;
	}

	@Override
	public ShadowCapability read(IAttachmentHolder iAttachmentHolder, ValueInput valueInput) {
		return null;
	}

	@Override
	public boolean write(ShadowCapability shadowCapability, ValueOutput valueOutput) {
		return false;
	}
}