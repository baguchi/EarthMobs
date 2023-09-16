package baguchan.earthmobsmod.entity;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.Witch;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ThrownPotion;
import net.minecraft.world.entity.raid.Raider;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class VilerWitch extends Witch {
	public VilerWitch(EntityType<? extends Witch> p_34134_, Level p_34135_) {
		super(p_34134_, p_34135_);
	}

	public static AttributeSupplier.Builder createAttributes() {
		return Monster.createMonsterAttributes().add(Attributes.MAX_HEALTH, 26.0D).add(Attributes.MOVEMENT_SPEED, 0.25D);
	}

	public void performRangedAttack(LivingEntity p_34143_, float p_34144_) {
		if (!this.isDrinkingPotion()) {
			Vec3 vec3 = p_34143_.getDeltaMovement();
			double d0 = p_34143_.getX() + vec3.x - this.getX();
			double d1 = p_34143_.getEyeY() - (double) 1.1F - this.getY();
			double d2 = p_34143_.getZ() + vec3.z - this.getZ();
			double d3 = Math.sqrt(d0 * d0 + d2 * d2);
			Potion potion = Potions.HARMING;
			if (p_34143_ instanceof Raider) {
				if (p_34143_.getHealth() <= 4.0F) {
					potion = Potions.HEALING;
				} else {
					potion = Potions.REGENERATION;
				}

				this.setTarget((LivingEntity) null);
			} else if (d3 >= 8.0D && !p_34143_.hasEffect(MobEffects.MOVEMENT_SLOWDOWN)) {
				potion = Potions.SLOWNESS;
			} else if (p_34143_.getHealth() >= 8.0F && !p_34143_.hasEffect(MobEffects.POISON)) {
				potion = Potions.POISON;
			} else if (d3 <= 3.0D && !p_34143_.hasEffect(MobEffects.WEAKNESS) && this.random.nextFloat() < 0.25F) {
				potion = Potions.WEAKNESS;
			}

			ThrownPotion thrownpotion = new ThrownPotion(this.level(), this);
			thrownpotion.setItem(PotionUtils.setPotion(new ItemStack(Items.LINGERING_POTION), potion));
			thrownpotion.setXRot(thrownpotion.getXRot() - -20.0F);
			thrownpotion.shoot(d0, d1 + d3 * 0.2D, d2, 0.75F, 8.0F);
			if (!this.isSilent()) {
				this.level().playSound((Player) null, this.getX(), this.getY(), this.getZ(), SoundEvents.WITCH_THROW, this.getSoundSource(), 1.0F, 0.8F + this.random.nextFloat() * 0.4F);
			}

			this.level().addFreshEntity(thrownpotion);
		}
	}
}
