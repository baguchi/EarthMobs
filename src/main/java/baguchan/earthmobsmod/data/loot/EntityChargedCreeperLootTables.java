package baguchan.earthmobsmod.data.loot;

import baguchan.earthmobsmod.registry.ModBlocks;
import baguchan.earthmobsmod.registry.ModBuiltInLootTables;
import baguchan.earthmobsmod.registry.ModEntities;
import net.minecraft.advancements.predicates.entity.EntityPredicate;
import net.minecraft.advancements.predicates.entity.EntityTypePredicate;
import net.minecraft.core.HolderGetter;
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
import net.minecraft.world.level.storage.loot.providers.number.ints.ContextIntProviders;

import java.util.ArrayList;
import java.util.List;

public class EntityChargedCreeperLootTables implements LootTableSubProvider {

    private static final List<Entry> ENTRIES = List.of(
            new Entry(ModBuiltInLootTables.CHARGED_CREEPER_MELON_GOLEM, ModEntities.MELON_GOLEM.get(), ModBlocks.CARVED_MELON_SHOOT.asItem())
    );

    private final LootTableSubProvider.Context output;
    private final HolderGetter<Item> items;
    private final HolderGetter<EntityType<?>> entities;
    private final HolderGetter<LootTable> loottables;

    public EntityChargedCreeperLootTables(LootTableSubProvider.Context output) {
        this.output = output;
        this.items = output.lookup(Registries.ITEM);
        this.entities = output.lookup(Registries.ENTITY_TYPE);
        this.loottables = output.lookup(Registries.LOOT_TABLE);
    }

    @Override
    public void run() {
        HolderGetter<EntityType<?>> entityTypes = this.entities;
        List<LootPoolEntryContainer.Builder<?>> alternatives = new ArrayList<>(ENTRIES.size());

        for (Entry entry : ENTRIES) {
            output.accept(
                    entry.lootTable,
                    LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ContextIntProviders.exactly(1)).add(LootItem.lootTableItem(entry.item)))
            );
            LootItemCondition.Builder predicate = LootItemEntityPropertyCondition.hasProperties(
                    LootContext.EntityTarget.THIS, EntityPredicate.Builder.entity().entityType(EntityTypePredicate.of(entityTypes, entry.entityType))
            );
            alternatives.add(NestedLootTable.lootTableReference(this.loottables.getOrThrow(entry.lootTable)).when(predicate));
        }
    }

    private record Entry(ResourceKey<LootTable> lootTable, EntityType<?> entityType, Item item) {
    }
}
