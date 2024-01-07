package baguchan.earthmobsmod.registry;

import baguchan.earthmobsmod.EarthMobsMod;
import baguchan.earthmobsmod.blockentity.MobChestBlockEntity;
import com.mojang.datafixers.types.Type;
import net.minecraft.Util;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.util.datafix.fixes.References;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModBlockEntitys {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(BuiltInRegistries.BLOCK_ENTITY_TYPE, EarthMobsMod.MODID);

    public static final Supplier<BlockEntityType<? extends MobChestBlockEntity>> MOB_CHEST = BLOCK_ENTITIES.register("mob_chest", () -> register(EarthMobsMod.prefix("mob_chest").toString(), BlockEntityType.Builder.of(MobChestBlockEntity::new, ModBlocks.COMMON_MOB_CHEST.get(), ModBlocks.UNCOMMON_MOB_CHEST.get(), ModBlocks.RARE_MOB_CHEST.get())));

    private static <T extends BlockEntity> BlockEntityType<T> register(String p_200966_0_, BlockEntityType.Builder<T> p_200966_1_) {
        Type<?> type = Util.fetchChoiceType(References.BLOCK_ENTITY, p_200966_0_);
        return p_200966_1_.build(type);
    }
}