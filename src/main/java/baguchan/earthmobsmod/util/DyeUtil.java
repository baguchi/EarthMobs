package baguchan.earthmobsmod.util;

import com.google.common.collect.Maps;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.DyeColor;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public class DyeUtil {
	public static DyeColor getRandomColor(RandomSource p_29843_) {
		return DyeColor.byId(p_29843_.nextInt(DyeColor.values().length));
	}

}
