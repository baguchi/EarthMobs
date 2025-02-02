package baguchan.earthmobsmod.registry;

import baguchan.earthmobsmod.EarthMobsMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.LootTable;

public class ModBuiltInLootTables {
    public static final ResourceKey<LootTable> FANCY_CHICKEN_LAY = register("gameplay/fancy_chicken_lay");
    public static final ResourceKey<LootTable> CLUCK_SHROOM_LAY = register("gameplay/cluck_shroom_lay");

    private static ResourceKey<LootTable> register(String name) {
        return ResourceKey.create(Registries.LOOT_TABLE, ResourceLocation.fromNamespaceAndPath(EarthMobsMod.MODID, name));
    }
}
