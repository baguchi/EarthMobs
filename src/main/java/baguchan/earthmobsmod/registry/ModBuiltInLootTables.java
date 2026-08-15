package baguchan.earthmobsmod.registry;

import baguchan.earthmobsmod.EarthMobsMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.ColorCollection;
import net.minecraft.world.level.storage.loot.LootTable;

public class ModBuiltInLootTables {
    public static final ResourceKey<LootTable> FANCY_CHICKEN_LAY = register("gameplay/fancy_chicken_lay");
    public static final ResourceKey<LootTable> CLUCK_SHROOM_LAY = register("gameplay/cluck_shroom_lay");
    public static final ResourceKey<LootTable> CHARGED_CREEPER_MELON_GOLEM = register("charged_creeper/melon_golem");
    public static final ResourceKey<LootTable> SHEAR_MUDDY_PIG = register("shearing/muddy_pig");

    public static final ColorCollection<ResourceKey<LootTable>> SHEAR_DYED_MUDDY_PIG = ColorCollection.NAMES.map(color -> register("shearing/muddy_pig/" + color));
    public static final ColorCollection<ResourceKey<LootTable>> HORNED_SHEEP = ColorCollection.NAMES.map(color -> register("entities/horned_sheep/" + color));

    private static ResourceKey<LootTable> register(String name) {
        return ResourceKey.create(Registries.LOOT_TABLE, Identifier.fromNamespaceAndPath(EarthMobsMod.MODID, name));
    }
}
