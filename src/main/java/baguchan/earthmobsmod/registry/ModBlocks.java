package baguchan.earthmobsmod.registry;

import baguchan.earthmobsmod.EarthMobsMod;
import baguchan.earthmobsmod.block.CarvedMelonBlock;
import baguchan.earthmobsmod.block.TropicalSlimeBlock;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.Objects;
import java.util.function.Function;
import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(BuiltInRegistries.BLOCK, EarthMobsMod.MODID);

    public static final Supplier<LiquidBlock> MUD = noItemRegister("mud", () -> new LiquidBlock(ModFluids.MUD, BlockBehaviour.Properties.of().mapColor(MapColor.WATER).replaceable().noCollission().strength(100.0F).pushReaction(PushReaction.DESTROY).noLootTable().liquid().sound(SoundType.EMPTY)));

    public static final Supplier<Block> RUBY = register("ruby_block", () -> new Block(BlockBehaviour.Properties.of().requiresCorrectToolForDrops().strength(5.0F, 6.0F).sound(SoundType.METAL)));

    public static final Supplier<Block> CARVED_MELON = register("carved_melon", () -> new CarvedMelonBlock(BlockBehaviour.Properties.of().strength(1.0F).sound(SoundType.WOOD)));
    public static final Supplier<Block> CARVED_MELON_SHOOT = register("carved_melon_shoot", () -> new CarvedMelonBlock(BlockBehaviour.Properties.of().strength(1.0F).sound(SoundType.WOOD)));
    public static final Supplier<Block> TROPICAL_SLIME_BLOCK = register("tropical_slime_block", () -> new TropicalSlimeBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BLUE).friction(0.8F).noOcclusion().sound(SoundType.SLIME_BLOCK)));

    public static final Supplier<FlowerBlock> BUTTERCUP = register("buttercup", () -> new FlowerBlock(MobEffects.ABSORPTION, 30, BlockBehaviour.Properties.of().noCollission().pushReaction(PushReaction.DESTROY).offsetType(BlockBehaviour.OffsetType.XZ).instabreak().sound(SoundType.GRASS)));
    public static final Supplier<FlowerBlock> PINK_DAISY = register("pink_daisy", () -> new FlowerBlock(MobEffects.REGENERATION, 10, BlockBehaviour.Properties.of().noCollission().pushReaction(PushReaction.DESTROY).offsetType(BlockBehaviour.OffsetType.XZ).instabreak().sound(SoundType.GRASS)));

    public static final Supplier<Block> POTTED_BUTTERCUP = BLOCKS.register("potted_buttercup", () -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, BUTTERCUP, BlockBehaviour.Properties.of().instabreak().noOcclusion().pushReaction(PushReaction.DESTROY)));
    public static final Supplier<Block> POTTED_PINK_DAISY = BLOCKS.register("potted_pink_daisy", () -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, PINK_DAISY, BlockBehaviour.Properties.of().instabreak().noOcclusion().pushReaction(PushReaction.DESTROY)));


    private static <T extends Block> Supplier<T> baseRegister(String name, Supplier<? extends T> block, Function<Supplier<T>, Supplier<? extends Item>> item) {
        Supplier<T> register = BLOCKS.register(name, block);
		ModItems.ITEMS.register(name, item.apply(register));
		return register;
	}

    private static <T extends Block> Supplier<T> noItemRegister(String name, Supplier<? extends T> block) {
        Supplier<T> register = BLOCKS.register(name, block);
		return register;
	}

    private static <B extends Block> Supplier<B> register(String name, Supplier<? extends Block> block) {
        return (Supplier<B>) baseRegister(name, block, (object) -> ModBlocks.registerBlockItem(object));
	}

    private static <T extends Block> Supplier<BlockItem> registerBlockItem(final Supplier<T> block) {
		return () -> {
			return new BlockItem(Objects.requireNonNull(block.get()), new Item.Properties());
		};
	}

	public static void initFire() {
		FireBlock fireblock = (FireBlock) Blocks.FIRE;
		fireblock.setFlammable(ModBlocks.BUTTERCUP.get(), 60, 100);
		fireblock.setFlammable(ModBlocks.PINK_DAISY.get(), 60, 100);
	}
}
