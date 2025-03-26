package baguchan.earthmobsmod.item;

import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraft.world.item.component.TooltipDisplay;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class BoneShardItem extends Item {
	public BoneShardItem(Properties p_41126_) {
		super(p_41126_);
	}

	@Override
	public void appendHoverText(ItemStack p_41421_, TooltipContext p_339594_, TooltipDisplay p_399753_, Consumer<Component> p_41423_, TooltipFlag p_41424_) {
		super.appendHoverText(p_41421_, p_339594_, p_399753_, p_41423_, p_41424_);
		List<MobEffectInstance> list = new ArrayList<>();
		PotionContents suspicioussteweffects = p_41421_.getOrDefault(DataComponents.POTION_CONTENTS, PotionContents.EMPTY);

		for (MobEffectInstance suspicioussteweffects$entry : suspicioussteweffects.getAllEffects()) {
			list.add(suspicioussteweffects$entry);
		}

		PotionContents.addPotionTooltip(list, p_41423_, 1.0F, p_339594_.tickRate());
	}
}