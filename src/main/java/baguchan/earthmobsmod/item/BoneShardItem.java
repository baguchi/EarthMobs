package baguchan.earthmobsmod.item;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;
import java.util.List;

public class BoneShardItem extends Item {
	public BoneShardItem(Properties p_41126_) {
		super(p_41126_);
	}

	public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> components, TooltipFlag flag) {
		PotionUtils.addPotionTooltip(stack, components, 1.0F, level == null ? 20.0F : level.tickRateManager().tickrate());
	}
}