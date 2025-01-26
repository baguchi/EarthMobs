package baguchan.earthmobsmod.registry;

import baguchan.earthmobsmod.EarthMobsMod;
import baguchan.earthmobsmod.block.CarvedMelonBlock;
import baguchan.earthmobsmod.block.TropicalSlimeBlock;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.StandingAndWallBlockItem;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.Objects;
import java.util.function.Function;
import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(EarthMobsMod.MODID);

    public static final DeferredBlock<LiquidBlock> MUD = registerWithoutItem("mud", (prop) -> new LiquidBlock(ModFluids.MUD.value(), prop), () -> BlockBehaviour.Properties.of().mapColor(MapColor.WATER).replaceable().noCollission().strength(100.0F).pushReaction(PushReaction.DESTROY).noLootTable().liquid().sound(SoundType.EMPTY));

    public static final DeferredBlock<Block> RUBY = register("ruby_block", (prop) -> new Block(prop.requiresCorrectToolForDrops().strength(5.0F, 6.0F).sound(SoundType.METAL)), BlockBehaviour.Properties::of);

    public static final DeferredBlock<Block> CARVED_MELON = register("carved_melon", (prop) -> new CarvedMelonBlock(prop.strength(1.0F).sound(SoundType.WOOD)), BlockBehaviour.Properties::of);
    public static final DeferredBlock<Block> CARVED_MELON_SHOOT = register("carved_melon_shoot", (prop) -> new CarvedMelonBlock(prop.strength(1.0F).sound(SoundType.WOOD)), BlockBehaviour.Properties::of);
    public static final DeferredBlock<Block> TROPICAL_SLIME_BLOCK = register("tropical_slime_block", (prop) -> new TropicalSlimeBlock(prop.mapColor(MapColor.COLOR_BLUE).friction(0.8F).noOcclusion().sound(SoundType.SLIME_BLOCK)), BlockBehaviour.Properties::of);

    public static final DeferredBlock<FlowerBlock> BUTTERCUP = register("buttercup", (prop) -> new FlowerBlock(MobEffects.ABSORPTION, 30, prop.noCollission().pushReaction(PushReaction.DESTROY).offsetType(BlockBehaviour.OffsetType.XZ).instabreak().sound(SoundType.GRASS)), () -> BlockBehaviour.Properties.of());
    public static final DeferredBlock<FlowerBlock> PINK_DAISY = register("pink_daisy", (prop) -> new FlowerBlock(MobEffects.REGENERATION, 10, prop.noCollission().pushReaction(PushReaction.DESTROY).offsetType(BlockBehaviour.OffsetType.XZ).instabreak().sound(SoundType.GRASS)), () -> BlockBehaviour.Properties.of());

    public static final DeferredBlock<Block> POTTED_BUTTERCUP = registerWithoutItem("potted_buttercup", (prop) -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, BUTTERCUP, prop), () -> BlockBehaviour.Properties.of().instabreak().noOcclusion().pushReaction(PushReaction.DESTROY));
    public static final DeferredBlock<Block> POTTED_PINK_DAISY = registerWithoutItem("potted_pink_daisy", (prop) -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, PINK_DAISY, prop), () -> BlockBehaviour.Properties.of().instabreak().noOcclusion().pushReaction(PushReaction.DESTROY));


    private static <T extends Block> DeferredBlock<Block> registerWithoutItem(String name, Supplier<Block.Properties> properties) {
        return registerWithoutItem(name, Block::new, properties);
    }

    private static <T extends Block> DeferredBlock<T> registerWithoutItem(String name, Function<Block.Properties, T> builder, Supplier<Block.Properties> properties) {
        return registerWithoutItem(name, createKey(name), builder, properties);
    }

    private static <T extends Block> DeferredBlock<T> registerWithoutItem(String name, ResourceKey<Block> key, Function<Block.Properties, T> builder, Supplier<Block.Properties> properties) {
        return BLOCKS.register(name, () -> builder.apply(properties.get().setId(key)));
    }

    private static <T extends Block> DeferredBlock<Block> register(String name, Supplier<Block.Properties> properties) {
        return register(name, Block::new, properties);
    }

    private static <T extends Block> DeferredBlock<T> register(String name, Function<Block.Properties, T> builder, Supplier<Block.Properties> properties) {
        return register(name, createKey(name), builder, properties);
    }

    private static <T extends Block> DeferredBlock<T> register(String name, ResourceKey<Block> key, Function<Block.Properties, T> builder, Supplier<Block.Properties> properties) {
        return baseRegister(name, key, builder, properties, (deferredBlock) -> registerBlockItem(deferredBlock, name));
    }

    private static ResourceKey<Block> createKey(String name) {
        return ResourceKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(EarthMobsMod.MODID, name));
    }

    private static <T extends Block> DeferredBlock<T> baseRegister(String name, ResourceKey<Block> key, Function<Block.Properties, T> builder, Supplier<Block.Properties> properties, Function<DeferredBlock<T>, Supplier<? extends Item>> item) {
        DeferredBlock<T> registered = BLOCKS.register(name, () -> builder.apply(properties.get().setId(key)));
        ModItems.ITEMS.register(name, item.apply(registered));
        return registered;
    }

    private static <T extends Block> DeferredBlock<T> registerTorchBlock(String name, Function<BlockBehaviour.Properties, T> block, DeferredBlock<T> wallTorchBlock, BlockBehaviour.Properties properties) {
        DeferredBlock<T> ret = BLOCKS.register(name, () -> block.apply(properties.setId(ResourceKey.create(Registries.BLOCK, EarthMobsMod.prefix(name)))));
        ModItems.ITEMS.registerItem(name, itemProps -> new StandingAndWallBlockItem(ret.get(), wallTorchBlock.get(), Direction.DOWN, itemProps.useBlockDescriptionPrefix()), new Item.Properties());
        return ret;

    }

    private static <T extends Block> Supplier<BlockItem> registerBlockItem(final DeferredBlock<T> deferredBlock, String name) {
        return () -> {
            DeferredBlock<T> block = Objects.requireNonNull(deferredBlock);
            Item.Properties properties = new Item.Properties().setId(ResourceKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(EarthMobsMod.MODID, name))).useBlockDescriptionPrefix();
            return new BlockItem(block.get(), properties);
        };
    }

	public static void initFire() {
		FireBlock fireblock = (FireBlock) Blocks.FIRE;
		fireblock.setFlammable(ModBlocks.BUTTERCUP.get(), 60, 100);
		fireblock.setFlammable(ModBlocks.PINK_DAISY.get(), 60, 100);
	}
}
