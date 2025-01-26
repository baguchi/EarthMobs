package baguchan.earthmobsmod.item;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.stats.Stats;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.WaterAnimal;
import net.minecraft.world.entity.animal.axolotl.Axolotl;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUseAnimation;
import net.minecraft.world.level.Level;

public class TropicalBallItem extends Item {
    public TropicalBallItem(Properties p_41126_) {
        super(p_41126_);
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity livingEntity) {
        super.finishUsingItem(stack, level, livingEntity);
        if (livingEntity instanceof ServerPlayer serverplayer) {
            CriteriaTriggers.CONSUME_ITEM.trigger(serverplayer, stack);
            serverplayer.awardStat(Stats.ITEM_USED.get(this));
        }

        if (!(livingEntity instanceof Player player) || !player.isCreative()) {

            stack.shrink(1);
        }

        livingEntity.addEffect(new MobEffectInstance(MobEffects.WATER_BREATHING, 300));
        livingEntity.setAirSupply(Mth.clamp(livingEntity.getAirSupply() + 600, 0, livingEntity.getMaxAirSupply()));

        return stack;
    }

    @Override
    public InteractionResult interactLivingEntity(ItemStack stack, Player player, LivingEntity livingEntity, InteractionHand p_41401_) {
        if (livingEntity instanceof WaterAnimal || livingEntity instanceof Axolotl) {
            if (!livingEntity.isInWaterOrBubble() && livingEntity.getAirSupply() < livingEntity.getMaxAirSupply()) {
                if (player instanceof ServerPlayer serverplayer) {
                    CriteriaTriggers.CONSUME_ITEM.trigger(serverplayer, stack);
                    serverplayer.awardStat(Stats.ITEM_USED.get(this));
                }
                if (!player.isCreative()) {
                    stack.shrink(1);
                }
                livingEntity.setAirSupply(Mth.clamp(livingEntity.getAirSupply() + 1200, 0, livingEntity.getMaxAirSupply()));

            }
        }
        return super.interactLivingEntity(stack, player, livingEntity, p_41401_);
    }
    @Override
    public ItemUseAnimation getUseAnimation(ItemStack p_41358_) {
        return ItemUseAnimation.DRINK;
    }

}