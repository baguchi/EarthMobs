package baguchan.earthmobsmod.data.loot;

import baguchan.earthmobsmod.advancements.criterion.WoolyCowPredicate;
import baguchan.earthmobsmod.registry.ModBlocks;
import baguchan.earthmobsmod.registry.ModBuiltInLootTables;
import baguchan.earthmobsmod.registry.ModEntities;
import baguchan.earthmobsmod.registry.ModItems;
import net.minecraft.advancements.predicates.MinMaxBounds;
import net.minecraft.advancements.predicates.entity.CubeMobPredicate;
import net.minecraft.advancements.predicates.entity.EntityFlagsPredicate;
import net.minecraft.advancements.predicates.entity.EntityPredicate;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.loot.EntityLootSubProvider;
import net.minecraft.data.loot.LootTableSubProvider;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EntityTypes;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.ColorCollection;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.EnchantedCountIncreaseFunction;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.functions.SmeltItemFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemEntityPropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemKilledByPlayerCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceWithEnchantedBonusCondition;
import net.minecraft.world.level.storage.loot.providers.number.floats.ContextFloatProviders;
import net.minecraft.world.level.storage.loot.providers.number.ints.ContextIntProviders;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

public class EntityLootTables extends EntityLootSubProvider {
    private final Set<EntityType<?>> knownEntities = new HashSet<>();


    protected EntityLootTables(LootTableSubProvider.Context output) {
        super(FeatureFlags.REGISTRY.allFlags(), output);
    }

    @Override
    protected void add(EntityType<?> entityType, LootTable.Builder builder) {
        super.add(entityType, builder);
        knownEntities.add(entityType);
    }

    @Override
    public void generate() {
        HolderGetter<EntityType<?>> entityTypes = this.entityTypes;
        HolderGetter<Enchantment> enchantments = this.output.lookup(Registries.ENCHANTMENT);


        this.add(ModEntities.BONE_SPIDER.get(), LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ContextIntProviders.exactly(1)).add(LootItem.lootTableItem(Items.STRING).apply(SetItemCountFunction.setCount(ContextIntProviders.between(0, 2))).apply(EnchantedCountIncreaseFunction.lootingMultiplier(enchantments, ContextFloatProviders.between(0, 1)))))
                .withPool(LootPool.lootPool().setRolls(ContextIntProviders.exactly(1)).add(LootItem.lootTableItem(ModItems.BONE_SPIDER_EYE).apply(SetItemCountFunction.setCount(ContextIntProviders.between(-1, 1))).apply(EnchantedCountIncreaseFunction.lootingMultiplier(enchantments, ContextFloatProviders.between(0, 1)))).when(LootItemKilledByPlayerCondition.killedByPlayer()))
                .withPool(LootPool.lootPool().setRolls(ContextIntProviders.exactly(1)).add(LootItem.lootTableItem(ModItems.BONE_SHARD).apply(SetItemCountFunction.setCount(ContextIntProviders.between(0, 5))).apply(EnchantedCountIncreaseFunction.lootingMultiplier(enchantments, ContextFloatProviders.between(0, 1)))))
        );
        this.add(ModEntities.BOULDERING_ZOMBIE.get(), LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ContextIntProviders.exactly(1)).add(LootItem.lootTableItem(ModItems.HARDER_FLESH).apply(SetItemCountFunction.setCount(ContextIntProviders.between(0, 2))).apply(EnchantedCountIncreaseFunction.lootingMultiplier(enchantments, ContextFloatProviders.between(0, 1)))))
                .withPool(LootPool.lootPool().setRolls(ContextIntProviders.exactly(1)).add(LootItem.lootTableItem(Items.IRON_INGOT)).add(LootItem.lootTableItem(Items.CARROT)).add(LootItem.lootTableItem(Items.POTATO).apply(SmeltItemFunction.smelted().when(this.shouldSmeltLoot()))).when(LootItemKilledByPlayerCondition.killedByPlayer()).when(LootItemRandomChanceWithEnchantedBonusCondition.randomChanceAndLootingBoost(enchantments, 0.025F, 0.01F))).withPool(LootPool.lootPool().add(LootItem.lootTableItem(Items.MUSIC_DISC_LAVA_CHICKEN)).when(LootItemKilledByPlayerCondition.killedByPlayer()).when(LootItemEntityPropertyCondition.hasProperties(LootContext.EntityTarget.THIS, EntityPredicate.Builder.entity().flags(EntityFlagsPredicate.Builder.flags().setIsBaby(true)).vehicle(EntityPredicate.Builder.entity().of(entityTypes, EntityTypes.CHICKEN))))));
        this.add(ModEntities.BOULDERING_DROWNED.get(), LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ContextIntProviders.exactly(1)).add(LootItem.lootTableItem(ModItems.HARDER_FLESH).apply(SetItemCountFunction.setCount(ContextIntProviders.between(0, 2))).apply(EnchantedCountIncreaseFunction.lootingMultiplier(enchantments, ContextFloatProviders.between(0, 1)))))
                .withPool(LootPool.lootPool().setRolls(ContextIntProviders.exactly(1)).add(LootItem.lootTableItem(Items.COPPER_INGOT)).add(LootItem.lootTableItem(Items.CARROT)).add(LootItem.lootTableItem(Items.POTATO).apply(SmeltItemFunction.smelted().when(this.shouldSmeltLoot()))).when(LootItemKilledByPlayerCondition.killedByPlayer()).when(LootItemRandomChanceWithEnchantedBonusCondition.randomChanceAndLootingBoost(enchantments, 0.025F, 0.01F))).withPool(LootPool.lootPool().add(LootItem.lootTableItem(Items.MUSIC_DISC_LAVA_CHICKEN)).when(LootItemKilledByPlayerCondition.killedByPlayer()).when(LootItemEntityPropertyCondition.hasProperties(LootContext.EntityTarget.THIS, EntityPredicate.Builder.entity().flags(EntityFlagsPredicate.Builder.flags().setIsBaby(true)).vehicle(EntityPredicate.Builder.entity().of(entityTypes, EntityTypes.CHICKEN))))));
        this.add(ModEntities.LOBBER_ZOMBIE.get(), LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ContextIntProviders.exactly(1)).add(LootItem.lootTableItem(Items.ROTTEN_FLESH).apply(SetItemCountFunction.setCount(ContextIntProviders.between(0, 4))).apply(EnchantedCountIncreaseFunction.lootingMultiplier(enchantments, ContextFloatProviders.between(0, 1)))))
                .withPool(LootPool.lootPool().setRolls(ContextIntProviders.exactly(1)).add(LootItem.lootTableItem(Items.IRON_INGOT)).add(LootItem.lootTableItem(Items.POISONOUS_POTATO)).when(LootItemKilledByPlayerCondition.killedByPlayer()).when(LootItemRandomChanceWithEnchantedBonusCondition.randomChanceAndLootingBoost(enchantments, 0.025F, 0.01F))).withPool(LootPool.lootPool().add(LootItem.lootTableItem(Items.MUSIC_DISC_LAVA_CHICKEN)).when(LootItemKilledByPlayerCondition.killedByPlayer()).when(LootItemEntityPropertyCondition.hasProperties(LootContext.EntityTarget.THIS, EntityPredicate.Builder.entity().flags(EntityFlagsPredicate.Builder.flags().setIsBaby(true)).vehicle(EntityPredicate.Builder.entity().of(entityTypes, EntityTypes.CHICKEN))))));
        this.add(ModEntities.LOBBER_DROWNED.get(), LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ContextIntProviders.exactly(1)).add(LootItem.lootTableItem(Items.ROTTEN_FLESH).apply(SetItemCountFunction.setCount(ContextIntProviders.between(0, 4))).apply(EnchantedCountIncreaseFunction.lootingMultiplier(enchantments, ContextFloatProviders.between(0, 1)))))
                .withPool(LootPool.lootPool().setRolls(ContextIntProviders.exactly(1)).add(LootItem.lootTableItem(Items.COPPER_INGOT)).add(LootItem.lootTableItem(Items.POISONOUS_POTATO)).when(LootItemKilledByPlayerCondition.killedByPlayer()).when(LootItemRandomChanceWithEnchantedBonusCondition.randomChanceAndLootingBoost(enchantments, 0.025F, 0.01F))).withPool(LootPool.lootPool().add(LootItem.lootTableItem(Items.MUSIC_DISC_LAVA_CHICKEN)).when(LootItemKilledByPlayerCondition.killedByPlayer()).when(LootItemEntityPropertyCondition.hasProperties(LootContext.EntityTarget.THIS, EntityPredicate.Builder.entity().flags(EntityFlagsPredicate.Builder.flags().setIsBaby(true)).vehicle(EntityPredicate.Builder.entity().of(entityTypes, EntityTypes.CHICKEN))))));
        this.add(ModEntities.CLUCK_SHROOM.get(), LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ContextIntProviders.exactly(1)).add(LootItem.lootTableItem(Items.FEATHER).apply(SetItemCountFunction.setCount(ContextIntProviders.between(0, 2))).apply(EnchantedCountIncreaseFunction.lootingMultiplier(enchantments, ContextFloatProviders.between(0, 1))))).withPool(LootPool.lootPool().setRolls(ContextIntProviders.exactly(1)).add(LootItem.lootTableItem(Items.CHICKEN).apply(SmeltItemFunction.smelted().when(this.shouldSmeltLoot())).apply(EnchantedCountIncreaseFunction.lootingMultiplier(enchantments, ContextFloatProviders.between(0, 1))))));
        this.add(ModEntities.FANCY_CHICKEN.get(), LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ContextIntProviders.exactly(1)).add(LootItem.lootTableItem(ModItems.FANCY_FEATHER).apply(SetItemCountFunction.setCount(ContextIntProviders.between(0, 2))).apply(EnchantedCountIncreaseFunction.lootingMultiplier(enchantments, ContextFloatProviders.between(0, 1))))).withPool(LootPool.lootPool().setRolls(ContextIntProviders.exactly(1)).add(LootItem.lootTableItem(Items.CHICKEN).apply(SmeltItemFunction.smelted().when(this.shouldSmeltLoot())).apply(EnchantedCountIncreaseFunction.lootingMultiplier(enchantments, ContextFloatProviders.between(0, 1))))));
        this.add(ModEntities.HORNED_SHEEP.get(), LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ContextIntProviders.exactly(1)).add(LootItem.lootTableItem(Items.MUTTON).apply(SetItemCountFunction.setCount(ContextIntProviders.between(1, 2))).apply(SmeltItemFunction.smelted().when(this.shouldSmeltLoot())).apply(EnchantedCountIncreaseFunction.lootingMultiplier(enchantments, ContextFloatProviders.between(0, 1))))).withPool(createSheepDispatchPool(BuiltInLootTables.SHEAR_DYED_SHEEP.map(this.lootTables::getOrThrow))));
        ColorCollection.zipApply(
                ModBuiltInLootTables.HORNED_SHEEP,
                Blocks.WOOL,
                (dye, wool) -> this.add(ModEntities.HORNED_SHEEP.get(), dye, LootTable.lootTable().withPool(LootPool.lootPool().add(LootItem.lootTableItem(wool)))));

        this.add(ModEntities.HUSK_RABBIT.get(), LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ContextIntProviders.exactly(1)).add(LootItem.lootTableItem(Items.ROTTEN_FLESH).apply(SetItemCountFunction.setCount(ContextIntProviders.exactly(1))))).withPool(LootPool.lootPool().setRolls(ContextIntProviders.exactly(1)).add(LootItem.lootTableItem(ModItems.HUSK_RABBIT_FOOT)).when(LootItemKilledByPlayerCondition.killedByPlayer()).when(LootItemRandomChanceWithEnchantedBonusCondition.randomChanceAndLootingBoost(enchantments, 0.1F, 0.03F))));
        this.add(ModEntities.ZOMBIFIED_RABBIT.get(), LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ContextIntProviders.exactly(1)).add(LootItem.lootTableItem(Items.ROTTEN_FLESH).apply(SetItemCountFunction.setCount(ContextIntProviders.exactly(1))))).withPool(LootPool.lootPool().setRolls(ContextIntProviders.exactly(1)).add(LootItem.lootTableItem(ModItems.ZOMBIFIED_RABBIT_FOOT)).when(LootItemKilledByPlayerCondition.killedByPlayer()).when(LootItemRandomChanceWithEnchantedBonusCondition.randomChanceAndLootingBoost(enchantments, 0.1F, 0.03F))));
        this.add(ModEntities.HYPER_RABBIT.get(), LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ContextIntProviders.exactly(1)).add(LootItem.lootTableItem(Items.RABBIT_HIDE).apply(SetItemCountFunction.setCount(ContextIntProviders.between(0, 1))).apply(EnchantedCountIncreaseFunction.lootingMultiplier(enchantments, ContextFloatProviders.between(0, 1))))).withPool(LootPool.lootPool().setRolls(ContextIntProviders.exactly(1)).add(LootItem.lootTableItem(Items.RABBIT).apply(SetItemCountFunction.setCount(ContextIntProviders.exactly(1))).apply(SmeltItemFunction.smelted().when(this.shouldSmeltLoot())).apply(EnchantedCountIncreaseFunction.lootingMultiplier(enchantments, ContextFloatProviders.between(0, 1))))).withPool(LootPool.lootPool().setRolls(ContextIntProviders.exactly(1)).add(LootItem.lootTableItem(ModItems.HYPER_RABBIT_FOOT)).when(LootItemKilledByPlayerCondition.killedByPlayer()).when(LootItemRandomChanceWithEnchantedBonusCondition.randomChanceAndLootingBoost(enchantments, 0.1F, 0.03F))));
        this.add(ModEntities.JUMBO_RABBIT.get(), LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ContextIntProviders.exactly(1)).add(LootItem.lootTableItem(Items.RABBIT_HIDE).apply(SetItemCountFunction.setCount(ContextIntProviders.between(0, 4))).apply(EnchantedCountIncreaseFunction.lootingMultiplier(enchantments, ContextFloatProviders.between(0, 1))))).withPool(LootPool.lootPool().setRolls(ContextIntProviders.exactly(1)).add(LootItem.lootTableItem(Items.RABBIT).apply(SetItemCountFunction.setCount(ContextIntProviders.exactly(1))).apply(SmeltItemFunction.smelted().when(this.shouldSmeltLoot())).apply(EnchantedCountIncreaseFunction.lootingMultiplier(enchantments, ContextFloatProviders.between(0, 1))))).withPool(LootPool.lootPool().setRolls(ContextIntProviders.exactly(1)).add(LootItem.lootTableItem(Items.RABBIT_FOOT)).when(LootItemKilledByPlayerCondition.killedByPlayer()).when(LootItemRandomChanceWithEnchantedBonusCondition.randomChanceAndLootingBoost(enchantments, 0.3F, 0.03F))));
        this.add(ModEntities.WOOLY_COW.get(),
                LootTable.lootTable()
                        .withPool(
                                LootPool.lootPool()
                                        .setRolls(ContextIntProviders.exactly(1))
                                        .add(
                                                LootItem.lootTableItem(Items.LEATHER)
                                                        .apply(SetItemCountFunction.setCount(ContextIntProviders.between(0, 2)))
                                                        .apply(EnchantedCountIncreaseFunction.lootingMultiplier(enchantments, ContextFloatProviders.between(0, 1)))
                                        )
                        )
                        .withPool(
                                LootPool.lootPool()
                                        .setRolls(ContextIntProviders.exactly(1))
                                        .add(
                                                LootItem.lootTableItem(Items.BEEF)
                                                        .apply(SetItemCountFunction.setCount(ContextIntProviders.between(1, 3)))
                                                        .apply(SmeltItemFunction.smelted().when(this.shouldSmeltLoot()))
                                                        .apply(EnchantedCountIncreaseFunction.lootingMultiplier(enchantments, ContextFloatProviders.between(0, 1)))
                                        )
                        ).withPool(createWoolyCowDispatchPool(Items.WOOL.white())));
        this.add(ModEntities.UMBRA_COW.get(),
                LootTable.lootTable()
                        .withPool(
                                LootPool.lootPool()
                                        .setRolls(ContextIntProviders.exactly(1))
                                        .add(
                                                LootItem.lootTableItem(Items.LEATHER)
                                                        .apply(SetItemCountFunction.setCount(ContextIntProviders.between(0, 2)))
                                                        .apply(EnchantedCountIncreaseFunction.lootingMultiplier(enchantments, ContextFloatProviders.between(0, 1)))
                                        )
                        )
                        .withPool(
                                LootPool.lootPool()
                                        .setRolls(ContextIntProviders.exactly(1))
                                        .add(
                                                LootItem.lootTableItem(Items.BEEF)
                                                        .apply(SetItemCountFunction.setCount(ContextIntProviders.between(1, 3)))
                                                        .apply(SmeltItemFunction.smelted().when(this.shouldSmeltLoot()))
                                                        .apply(EnchantedCountIncreaseFunction.lootingMultiplier(enchantments, ContextFloatProviders.between(0, 1)))
                                        )
                        ).withPool(createWoolyCowDispatchPool(Items.WOOL.black())));
        this.add(ModEntities.MOOBLOOM.get(),
                LootTable.lootTable()
                        .withPool(
                                LootPool.lootPool()
                                        .setRolls(ContextIntProviders.exactly(1))
                                        .add(
                                                LootItem.lootTableItem(Items.LEATHER)
                                                        .apply(SetItemCountFunction.setCount(ContextIntProviders.between(0, 2)))
                                                        .apply(EnchantedCountIncreaseFunction.lootingMultiplier(enchantments, ContextFloatProviders.between(0, 1)))
                                        )
                        )
                        .withPool(
                                LootPool.lootPool()
                                        .setRolls(ContextIntProviders.exactly(1))
                                        .add(
                                                LootItem.lootTableItem(Items.BEEF)
                                                        .apply(SetItemCountFunction.setCount(ContextIntProviders.between(1, 3)))
                                                        .apply(SmeltItemFunction.smelted().when(this.shouldSmeltLoot()))
                                                        .apply(EnchantedCountIncreaseFunction.lootingMultiplier(enchantments, ContextFloatProviders.between(0, 1)))
                                        )
                        ));
        this.add(ModEntities.MOOLIP.get(),
                LootTable.lootTable()
                        .withPool(
                                LootPool.lootPool()
                                        .setRolls(ContextIntProviders.exactly(1))
                                        .add(
                                                LootItem.lootTableItem(Items.LEATHER)
                                                        .apply(SetItemCountFunction.setCount(ContextIntProviders.between(0, 2)))
                                                        .apply(EnchantedCountIncreaseFunction.lootingMultiplier(enchantments, ContextFloatProviders.between(0, 1)))
                                        )
                        )
                        .withPool(
                                LootPool.lootPool()
                                        .setRolls(ContextIntProviders.exactly(1))
                                        .add(
                                                LootItem.lootTableItem(Items.BEEF)
                                                        .apply(SetItemCountFunction.setCount(ContextIntProviders.between(1, 3)))
                                                        .apply(SmeltItemFunction.smelted().when(this.shouldSmeltLoot()))
                                                        .apply(EnchantedCountIncreaseFunction.lootingMultiplier(enchantments, ContextFloatProviders.between(0, 1)))
                                        )
                        ));

        this.add(ModEntities.MELON_GOLEM.get(), LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ContextIntProviders.exactly(1)).add(LootItem.lootTableItem(Items.SNOWBALL).apply(SetItemCountFunction.setCount(ContextIntProviders.between(0, 15))))).withPool(LootPool.lootPool().setRolls(ContextIntProviders.exactly(1)).add(LootItem.lootTableItem(ModBlocks.CARVED_MELON)).when(LootItemKilledByPlayerCondition.killedByPlayer()).when(LootItemRandomChanceWithEnchantedBonusCondition.randomChanceAndLootingBoost(enchantments, 0.025F, 0.01F))));


        this.add(ModEntities.FURNACE_GOLEM.get(),
                LootTable.lootTable()
                        .withPool(
                                LootPool.lootPool()
                                        .setRolls(ContextIntProviders.exactly(1))
                                        .add(
                                                LootItem.lootTableItem(Items.IRON_INGOT)
                                                        .apply(SetItemCountFunction.setCount(ContextIntProviders.between(2, 4)))
                                        )
                        ));

        this.add(ModEntities.JOLLY_LLAMA.get(),
                LootTable.lootTable()
                        .withPool(
                                LootPool.lootPool()
                                        .setRolls(ContextIntProviders.exactly(1))
                                        .add(
                                                LootItem.lootTableItem(Items.LEATHER)
                                                        .apply(SetItemCountFunction.setCount(ContextIntProviders.between(0, 2)))
                                                        .apply(EnchantedCountIncreaseFunction.lootingMultiplier(enchantments, ContextFloatProviders.between(0, 1)))
                                        )
                        ));

        this.add(ModEntities.SKELETON_WOLF.get(), LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ContextIntProviders.exactly(1)).add(LootItem.lootTableItem(Items.BONE).apply(SetItemCountFunction.setCount(ContextIntProviders.between(0, 2))).apply(EnchantedCountIncreaseFunction.lootingMultiplier(enchantments, ContextFloatProviders.between(0, 1))))));
        this.add(ModEntities.WITHER_SKELETON_WOLF.get(), LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ContextIntProviders.exactly(1))
                        .add(LootItem.lootTableItem(Items.BONE).apply(SetItemCountFunction.setCount(ContextIntProviders.between(0, 2))).apply(EnchantedCountIncreaseFunction.lootingMultiplier(enchantments, ContextFloatProviders.between(0, 1)))))
                .withPool(LootPool.lootPool().setRolls(ContextIntProviders.exactly(1))
                        .add(LootItem.lootTableItem(Items.COAL).apply(SetItemCountFunction.setCount(ContextIntProviders.between(0, 1))).apply(EnchantedCountIncreaseFunction.lootingMultiplier(enchantments, ContextFloatProviders.between(0, 1))))));

        this.add(ModEntities.ZOMBIFIED_PIG.get(),
                LootTable.lootTable()
                        .withPool(
                                LootPool.lootPool()
                                        .setRolls(ContextIntProviders.exactly(1))
                                        .add(
                                                LootItem.lootTableItem(Items.ROTTEN_FLESH)
                                                        .apply(SetItemCountFunction.setCount(ContextIntProviders.between(0, 2)))
                                                        .apply(EnchantedCountIncreaseFunction.lootingMultiplier(enchantments, ContextFloatProviders.between(0, 1)))
                                        )
                        ));
        this.add(
                ModEntities.TROPICAL_SLIME.get(),
                LootTable.lootTable()
                        .withPool(
                                LootPool.lootPool()
                                        .setRolls(ContextIntProviders.exactly(1))
                                        .add(
                                                LootItem.lootTableItem(ModItems.TROPICAL_BALL.get())
                                                        .apply(SetItemCountFunction.setCount(ContextIntProviders.between(0, 2)))
                                                        .apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.enchantments, ContextFloatProviders.between(0.0F, 1.0F)))
                                                        .when(this.killedByFrog().invert())
                                        )
                                        .add(
                                                LootItem.lootTableItem(ModItems.TROPICAL_BALL.get())
                                                        .apply(SetItemCountFunction.setCount(ContextIntProviders.exactly(1)))
                                                        .when(this.killedByFrog())
                                        )
                                        .when(
                                                LootItemEntityPropertyCondition.hasProperties(
                                                        LootContext.EntityTarget.THIS, EntityPredicate.Builder.entity().cubeMob(CubeMobPredicate.sized(MinMaxBounds.Ints.exactly(1)))
                                                )
                                        )
                        )
        );

        this.add(ModEntities.TEACUP_PIG.get(),
                LootTable.lootTable()
                        .withPool(
                                LootPool.lootPool()
                                        .setRolls(ContextIntProviders.exactly(1))
                                        .add(
                                                LootItem.lootTableItem(Items.PORKCHOP)
                                                        .apply(SetItemCountFunction.setCount(ContextIntProviders.between(0, 1)))
                                                        .apply(EnchantedCountIncreaseFunction.lootingMultiplier(enchantments, ContextFloatProviders.between(0, 1)))
                                        )
                        ));
        this.add(ModEntities.MAGMA_COW.get(),
                LootTable.lootTable()
                        .withPool(
                                LootPool.lootPool()
                                        .setRolls(ContextIntProviders.exactly(1))
                                        .add(
                                                LootItem.lootTableItem(Items.BLACKSTONE)
                                                        .apply(SetItemCountFunction.setCount(ContextIntProviders.between(0, 3)))
                                                        .apply(EnchantedCountIncreaseFunction.lootingMultiplier(enchantments, ContextFloatProviders.between(0, 1)))
                                        )
                        )
                        .withPool(
                                LootPool.lootPool()
                                        .setRolls(ContextIntProviders.exactly(1))
                                        .add(
                                                LootItem.lootTableItem(Items.NETHERRACK)
                                                        .apply(SetItemCountFunction.setCount(ContextIntProviders.between(0, 2)))
                                                        .apply(EnchantedCountIncreaseFunction.lootingMultiplier(enchantments, ContextFloatProviders.between(0, 1)))
                                        )
                        ));

        this.add(
                ModEntities.VILER_WITCH.get(),
                LootTable.lootTable()
                        .withPool(
                                LootPool.lootPool()
                                        .setRolls(ContextIntProviders.between(1, 3))
                                        .add(
                                                LootItem.lootTableItem(Items.GLOWSTONE_DUST)
                                                        .apply(SetItemCountFunction.setCount(ContextIntProviders.between(0, 2)))
                                                        .apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.enchantments, ContextFloatProviders.between(0.0F, 1.0F)))
                                        )
                                        .add(
                                                LootItem.lootTableItem(Items.SUGAR)
                                                        .apply(SetItemCountFunction.setCount(ContextIntProviders.between(0, 2)))
                                                        .apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.enchantments, ContextFloatProviders.between(0.0F, 1.0F)))
                                        )
                                        .add(
                                                LootItem.lootTableItem(ModItems.BONE_SPIDER_EYE)
                                                        .apply(SetItemCountFunction.setCount(ContextIntProviders.between(0, 2)))
                                                        .apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.enchantments, ContextFloatProviders.between(0.0F, 1.0F)))
                                        )
                                        .add(
                                                LootItem.lootTableItem(Items.GLASS_BOTTLE)
                                                        .apply(SetItemCountFunction.setCount(ContextIntProviders.between(0, 2)))
                                                        .apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.enchantments, ContextFloatProviders.between(0.0F, 1.0F)))
                                        )
                                        .add(
                                                LootItem.lootTableItem(Items.GUNPOWDER)
                                                        .apply(SetItemCountFunction.setCount(ContextIntProviders.between(0, 2)))
                                                        .apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.enchantments, ContextFloatProviders.between(0.0F, 1.0F)))
                                        )
                                        .add(
                                                LootItem.lootTableItem(Items.STICK)
                                                        .setWeight(2)
                                                        .apply(SetItemCountFunction.setCount(ContextIntProviders.between(0, 2)))
                                                        .apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.enchantments, ContextFloatProviders.between(0.0F, 1.0F)))
                                        )
                        )
                        .withPool(
                                LootPool.lootPool()
                                        .setRolls(ContextIntProviders.exactly(1))
                                        .add(
                                                LootItem.lootTableItem(Items.REDSTONE)
                                                        .apply(SetItemCountFunction.setCount(ContextIntProviders.between(4, 8)))
                                                        .apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.enchantments, ContextFloatProviders.between(0.0F, 1.0F)))
                                        )
                                        .add(
                                                LootItem.lootTableItem(Items.LAPIS_LAZULI)
                                                        .apply(SetItemCountFunction.setCount(ContextIntProviders.between(2, 6)))
                                                        .apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.enchantments, ContextFloatProviders.between(0.0F, 1.0F)))
                                        )
                        )
        );
    }

    public static LootPool.Builder createWoolyCowDispatchPool(Item item) {

        return LootPool.lootPool().add(LootItem.lootTableItem(item)).when(LootItemEntityPropertyCondition.hasProperties(LootContext.EntityTarget.THIS, EntityPredicate.Builder.entity().put(WoolyCowPredicate.CODEC, new WoolyCowPredicate(Optional.of(false)))));
    }

    @Override
    protected Stream<EntityType<?>> getKnownEntityTypes() {
        return this.knownEntities.stream();
    }
}
