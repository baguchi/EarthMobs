package baguchan.earthmobsmod.util;

import com.google.common.collect.Maps;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.DyeColor;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public class DyeUtil {
	private static final Map<DyeColor, float[]> COLORARRAY_BY_COLOR = Maps.<DyeColor, float[]>newEnumMap(Arrays.stream(DyeColor.values()).collect(Collectors.toMap((p_29868_) -> {
		return p_29868_;
	}, DyeUtil::createColor)));

	private static float[] createColor(DyeColor p_29866_) {
		if (p_29866_ == DyeColor.WHITE) {
			return new float[]{0.9019608F, 0.9019608F, 0.9019608F};
		} else {
			float[] afloat = p_29866_.getTextureDiffuseColors();
			float f = 0.75F;
			return new float[]{afloat[0] * 0.75F, afloat[1] * 0.75F, afloat[2] * 0.75F};
		}
	}

	private static float[] getColorArray(DyeColor p_29830_) {
		return COLORARRAY_BY_COLOR.get(p_29830_);
	}

	public static DyeColor getRandomColor(RandomSource p_29843_) {
		return DyeColor.byId(p_29843_.nextInt(DyeColor.values().length));
	}

}
