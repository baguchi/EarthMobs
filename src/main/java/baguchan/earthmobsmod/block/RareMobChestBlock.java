package baguchan.earthmobsmod.block;

import baguchan.earthmobsmod.EarthMobsMod;
import net.minecraft.resources.ResourceLocation;

public class RareMobChestBlock extends MobChestBlock {
    public static final ResourceLocation RARE = new ResourceLocation(EarthMobsMod.MODID, "textures/entity/chest/rare_chest.png");

    public RareMobChestBlock(Properties properties) {
        super(properties);
    }

    @Override
    public ResourceLocation getTexture() {
        return RARE;
    }
}
