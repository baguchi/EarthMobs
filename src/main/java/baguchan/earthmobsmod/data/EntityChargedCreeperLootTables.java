package baguchan.earthmobsmod.data;

import baguchan.earthmobsmod.registry.ModBlocks;
import baguchan.earthmobsmod.registry.ModBuiltInLootTables;
import baguchan.earthmobsmod.registry.ModEntities;
import net.minecraft.advancements.criterion.EntityPredicate;
import net.minecraft.advancements.criterion.EntityTypePredicate;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.loot.LootTableSubProvider;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.entries.LootPoolEntryContainer;
import net.minecraft.world.level.storage.loot.entries.NestedLootTable;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemEntityPropertyCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;

public record EntityChargedCreeperLootTables(HolderLookup.Provider registries) implements LootTableSubProvider {
    private static final List<Entry> ENTRIES = List.of(
            new Entry(ModBuiltInLootTables.CHARGED_CREEPER_MELON_GOLEM, ModEntities.MELON_GOLEM.get(), ModBlocks.CARVED_MELON_SHOOT.asItem())
    );


    @Override
    public void generate(BiConsumer<ResourceKey<LootTable>, LootTable.Builder> output) {
        HolderGetter<EntityType<?>> entityTypes = this.registries.lookupOrThrow(Registries.ENTITY_TYPE);
        List<LootPoolEntryContainer.Builder<?>> alternatives = new ArrayList<>(ENTRIES.size());

        for (Entry entry : ENTRIES) {
            output.accept(
                    entry.lootTable,
                    LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F)).add(LootItem.lootTableItem(entry.item)))
            );
            LootItemCondition.Builder predicate = LootItemEntityPropertyCondition.hasProperties(
                    LootContext.EntityTarget.THIS, EntityPredicate.Builder.entity().entityType(EntityTypePredicate.of(entityTypes, entry.entityType))
            );
            alternatives.add(NestedLootTable.lootTableReference(entry.lootTable).when(predicate));
        }
    }

    private record Entry(ResourceKey<LootTable> lootTable, EntityType<?> entityType, Item item) {
    }
}
