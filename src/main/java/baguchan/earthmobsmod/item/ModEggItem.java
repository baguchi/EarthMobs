package baguchan.earthmobsmod.item;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import java.util.function.Supplier;

public class ModEggItem extends Item {
	private final Supplier<? extends EntityType<? extends ThrowableItemProjectile>> typeSupplier;

	public ModEggItem(Supplier<? extends EntityType<? extends ThrowableItemProjectile>> type, Properties p_41126_) {
		super(p_41126_);
		this.typeSupplier = type;
	}

    @Override
    public InteractionResult use(Level level, Player player, InteractionHand hand) {
        ItemStack itemstack = player.getItemInHand(hand);
        level.playSound((Player) null, player.getX(), player.getY(), player.getZ(), SoundEvents.EGG_THROW, SoundSource.PLAYERS, 0.5F, 0.4F / (level.getRandom().nextFloat() * 0.4F + 0.8F));
        if (!level.isClientSide) {
            ThrowableItemProjectile thrownegg = typeSupplier.get().create(level, EntitySpawnReason.MOB_SUMMONED);
            thrownegg.setPos(player.getX(), player.getEyeY() - 0.1F, player.getZ());
            thrownegg.setOwner(player);
			thrownegg.setItem(itemstack);
            thrownegg.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, 1.5F, 1.0F);
            level.addFreshEntity(thrownegg);
		}

        player.awardStat(Stats.ITEM_USED.get(this));
        if (!player.getAbilities().instabuild) {
			itemstack.shrink(1);
		}

        return InteractionResult.SUCCESS;
	}
}