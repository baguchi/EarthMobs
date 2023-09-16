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

	public void appendHoverText(ItemStack p_42988_, @Nullable Level p_42989_, List<Component> p_42990_, TooltipFlag p_42991_) {
		PotionUtils.addPotionTooltip(p_42988_, p_42990_, 1.0F);
	}
}