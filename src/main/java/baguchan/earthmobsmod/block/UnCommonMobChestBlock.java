package baguchan.earthmobsmod.block;

import baguchan.earthmobsmod.EarthMobsMod;
import net.minecraft.resources.ResourceLocation;

public class UnCommonMobChestBlock extends MobChestBlock {
    public static final ResourceLocation UNCOMMON = new ResourceLocation(EarthMobsMod.MODID, "textures/entity/chest/uncommon_chest.png");

    public UnCommonMobChestBlock(Properties properties) {
        super(properties);
    }

    @Override
    public ResourceLocation getTexture() {
        return UNCOMMON;
    }
}
