package baguchan.earthmobsmod.block;

import baguchan.earthmobsmod.EarthMobsMod;
import net.minecraft.resources.ResourceLocation;

public class CommonMobChestBlock extends MobChestBlock {
    public static final ResourceLocation COMMON = new ResourceLocation(EarthMobsMod.MODID, "textures/entity/chest/common_chest.png");

    public CommonMobChestBlock(Properties properties) {
        super(properties);
    }

    @Override
    public ResourceLocation getTexture() {
        return COMMON;
    }
}
