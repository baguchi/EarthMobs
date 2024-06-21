package baguchan.earthmobsmod.registry;

import net.minecraft.world.entity.raid.Raid;
import net.neoforged.fml.common.asm.enumextension.EnumProxy;

public class RaiderEnumParams {
    @SuppressWarnings("unused")
    public static final EnumProxy<Raid.RaiderType> VILER_WITCH = new EnumProxy<>(
            Raid.RaiderType.class, ModEntities.VILER_WITCH, new int[]{0, 0, 1, 1, 2, 2, 2, 2}
    );
}
