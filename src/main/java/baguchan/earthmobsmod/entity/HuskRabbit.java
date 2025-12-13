package baguchan.earthmobsmod.entity;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.animal.rabbit.Rabbit;
import net.minecraft.world.entity.monster.zombie.Husk;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;

public class HuskRabbit extends ZombifiedRabbit {
    public HuskRabbit(EntityType<? extends Rabbit> p_29656_, Level p_29657_) {
        super(p_29656_, p_29657_);
    }

    @Override
    public boolean doHurtTarget(ServerLevel p_478036_, Entity p_480930_) {
        boolean flag = super.doHurtTarget(p_478036_, p_480930_);
        if (flag && this.getMainHandItem().isEmpty() && p_480930_ instanceof LivingEntity) {
            float f = p_478036_.getCurrentDifficultyAt(this.blockPosition()).getEffectiveDifficulty();
            ((LivingEntity) p_480930_).addEffect(new MobEffectInstance(MobEffects.HUNGER, 100 * (int) f), this);
        }

        return flag;
    }

    public void makeRider(ServerLevelAccessor p_479493_, DifficultyInstance p_481210_, EntitySpawnReason p_482098_) {
        Husk zombie = EntityType.HUSK.create(this.level(), EntitySpawnReason.JOCKEY);
        if (zombie != null) {
            zombie.setBaby(true);
            zombie.snapTo(this.getX(), this.getY(), this.getZ(), this.getYRot(), 0.0F);
            zombie.finalizeSpawn(p_479493_, p_481210_, p_482098_, null);
            zombie.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(Items.WOODEN_SPEAR));
            zombie.startRiding(this, false, false);
        }
    }
}
