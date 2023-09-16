package baguchan.earthmobsmod.entity.projectile;

import baguchan.earthmobsmod.entity.FancyChicken;
import baguchan.earthmobsmod.registry.ModEntities;
import baguchan.earthmobsmod.registry.ModItems;
import net.minecraft.core.particles.ItemParticleOption;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;

public class FancyEgg extends ThrowableItemProjectile {
	public FancyEgg(EntityType<? extends FancyEgg> p_37391_, Level p_37392_) {
		super(p_37391_, p_37392_);
	}

	public FancyEgg(Level p_37399_, LivingEntity p_37400_) {
		super(ModEntities.FANCY_EGG.get(), p_37400_, p_37399_);
	}

	public FancyEgg(Level p_37394_, double p_37395_, double p_37396_, double p_37397_) {
		super(ModEntities.FANCY_EGG.get(), p_37395_, p_37396_, p_37397_, p_37394_);
	}

	protected Item getDefaultItem() {
		return ModItems.FANCY_EGG.get();
	}

	private ParticleOptions getParticle() {
		return new ItemParticleOption(ParticleTypes.ITEM, ModItems.FANCY_EGG.get().getDefaultInstance());
	}

	public void handleEntityEvent(byte p_37402_) {
		if (p_37402_ == 3) {
			ParticleOptions particleoptions = this.getParticle();

			for (int i = 0; i < 8; ++i) {
				this.level().addParticle(particleoptions, this.getX(), this.getY(), this.getZ(), 0.0D, 0.0D, 0.0D);
			}
		}

	}

	protected void onHitEntity(EntityHitResult p_37404_) {
		super.onHitEntity(p_37404_);
		Entity entity = p_37404_.getEntity();
		entity.hurt(this.damageSources().thrown(this, this.getOwner()), 0);
	}

	protected void onHit(HitResult p_37488_) {
		super.onHit(p_37488_);
		if (!this.level().isClientSide) {
			if (this.random.nextInt(8) == 0) {
				int i = 1;
				if (this.random.nextInt(32) == 0) {
					i = 4;
				}

				for (int j = 0; j < i; ++j) {
					FancyChicken chicken = ModEntities.FANCY_CHICKEN.get().create(this.level());
					chicken.setAge(-24000);
					chicken.moveTo(this.getX(), this.getY(), this.getZ(), this.getYRot(), 0.0F);
					this.level().addFreshEntity(chicken);
				}
			}

			this.level().broadcastEntityEvent(this, (byte) 3);
			this.discard();
		}

	}
}