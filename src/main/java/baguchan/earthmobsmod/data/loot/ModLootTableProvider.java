package baguchan.earthmobsmod.data.loot;

import net.minecraft.core.registries.SingleRegistryBootstrap;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;

import java.util.List;
import java.util.Set;

public class ModLootTableProvider {
    public static SingleRegistryBootstrap<LootTable> create() {
        return new LootTableProvider(Set.of(), List.of(
                new LootTableProvider.SubProviderEntry(BlockLootTables::new, LootContextParamSets.BLOCK),
                new LootTableProvider.SubProviderEntry(EntityLootTables::new, LootContextParamSets.ENTITY),
                new LootTableProvider.SubProviderEntry(EntityChargedCreeperLootTables::new, LootContextParamSets.ENTITY)
        ));
    }
}