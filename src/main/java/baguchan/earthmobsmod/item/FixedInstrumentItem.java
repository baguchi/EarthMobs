package baguchan.earthmobsmod.item;

import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Instrument;
import net.minecraft.world.item.InstrumentItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;

public class FixedInstrumentItem extends InstrumentItem {
	private TagKey<Instrument> instruments;

	public FixedInstrumentItem(Item.Properties tab, TagKey<Instrument> goatHorns) {
		super(tab, goatHorns);
		this.instruments = goatHorns;
	}

	@Override
	public UseAnim getUseAnimation(ItemStack p_220133_) {
		return UseAnim.TOOT_HORN;
	}
}
