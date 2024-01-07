package baguchan.earthmobsmod.event;

import baguchan.earthmobsmod.EarthMobsMod;
import baguchan.earthmobsmod.registry.ModBlockEntitys;
import baguchan.earthmobsmod.registry.ModBlocks;
import baguchan.earthmobsmod.registry.ModItems;
import com.google.common.collect.ImmutableMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraft.world.level.ItemLike;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.village.WandererTradesEvent;

import java.util.List;

@Mod.EventBusSubscriber(modid = EarthMobsMod.MODID)
public class TradeEvents {
    @SubscribeEvent
    public static void wanderTradeEvent(WandererTradesEvent event) {
        List<VillagerTrades.ItemListing> trades = event.getGenericTrades();

        trades.add(new SuppliesForRuby(ModBlocks.COMMON_MOB_CHEST.get(), 1, 1, 2, new ResourceLocation(EarthMobsMod.MODID, "supplies/common")));
        trades.add(new SuppliesForRuby(ModBlocks.UNCOMMON_MOB_CHEST.get(), 3, 1, 2, new ResourceLocation(EarthMobsMod.MODID, "supplies/uncommon")));
        trades.add(new SuppliesForRuby(ModBlocks.RARE_MOB_CHEST.get(), 6, 1, 2, new ResourceLocation(EarthMobsMod.MODID, "supplies/rare")));

    }

    private static Int2ObjectMap<VillagerTrades.ItemListing[]> gatAsIntMap(ImmutableMap<Integer, VillagerTrades.ItemListing[]> p_221238_0_) {
        return new Int2ObjectOpenHashMap<>(p_221238_0_);
    }


    static class SuppliesForRuby implements VillagerTrades.ItemListing {
        private final ItemLike itemStack;
        private final int emeraldCost;
        private final int maxUses;
        private final int villagerXp;
        private final float priceMultiplier;
        private final ResourceLocation lootTable;


        public SuppliesForRuby(ItemLike p_35758_, int p_35759_, int p_35761_, int p_35762_, ResourceLocation lootTable) {
            this.itemStack = p_35758_;
            this.emeraldCost = p_35759_;
            this.maxUses = p_35761_;
            this.villagerXp = p_35762_;
            this.lootTable = lootTable;
            this.priceMultiplier = 0.05F;
        }

        public MerchantOffer getOffer(Entity p_35771_, RandomSource p_35772_) {
            ItemStack stack = new ItemStack(this.itemStack.asItem(), 1);
            CompoundTag compoundTag = new CompoundTag();
            compoundTag.putString("LootTable", this.lootTable.toString());

            BlockItem.setBlockEntityData(stack, ModBlockEntitys.MOB_CHEST.get(), compoundTag);

            return new MerchantOffer(new ItemStack(ModItems.RUBY.get(), this.emeraldCost), stack, this.maxUses, this.villagerXp, this.priceMultiplier);
        }

        public boolean tryAddLootTable(CompoundTag p_309634_) {
            ResourceLocation resourcelocation = this.lootTable;
            if (resourcelocation == null) {
                return false;
            } else {

                return true;
            }
        }
    }
}
