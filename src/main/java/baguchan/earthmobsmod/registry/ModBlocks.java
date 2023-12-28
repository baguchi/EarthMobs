package baguchan.earthmobsmod.registry;

import baguchan.earthmobsmod.EarthMobsMod;
import baguchan.earthmobsmod.block.CarvedMelonBlock;
import baguchan.earthmobsmod.block.TropicalSlimeBlock;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.Objects;
import java.util.function.Function;
import java.util.function.Supplier;

public class ModBlocks {
	public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, EarthMobsMod.MODID);

    public static final RegistryObject<LiquidBlock> MUD = noItemRegister("mud", () -> new LiquidBlock(ModFluids.MUD, BlockBehaviour.Properties.of(Material.WATER).noCollission().strength(100.0F).noLootTable()));

    public static final RegistryObject<Block> RUBY = register("ruby_block", () -> new Block(BlockBehaviour.Properties.of(Material.METAL, MaterialColor.COLOR_RED).requiresCorrectToolForDrops().strength(5.0F, 6.0F).sound(SoundType.METAL)));

    public static final RegistryObject<Block> CARVED_MELON = register("carved_melon", () -> new CarvedMelonBlock(BlockBehaviour.Properties.of(Material.VEGETABLE, MaterialColor.COLOR_LIGHT_GREEN).strength(1.0F).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> CARVED_MELON_SHOOT = register("carved_melon_shoot", () -> new CarvedMelonBlock(BlockBehaviour.Properties.of(Material.VEGETABLE, MaterialColor.COLOR_LIGHT_GREEN).strength(1.0F).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> TROPICAL_SLIME_BLOCK = register("tropical_slime_block", () -> new TropicalSlimeBlock(BlockBehaviour.Properties.of(Material.CLAY, MaterialColor.GRASS).friction(0.8F).noOcclusion().sound(SoundType.SLIME_BLOCK)));

    public static final RegistryObject<FlowerBlock> BUTTERCUP = register("buttercup", () -> new FlowerBlock(MobEffects.ABSORPTION, 30, BlockBehaviour.Properties.of(Material.PLANT).noCollission().instabreak().sound(SoundType.GRASS)));
    public static final RegistryObject<FlowerBlock> PINK_DAISY = register("pink_daisy", () -> new FlowerBlock(MobEffects.REGENERATION, 10, BlockBehaviour.Properties.of(Material.PLANT).noCollission().instabreak().sound(SoundType.GRASS)));


	private static <T extends Block> RegistryObject<T> baseRegister(String name, Supplier<? extends T> block, Function<RegistryObject<T>, Supplier<? extends Item>> item) {
		RegistryObject<T> register = BLOCKS.register(name, block);
		ModItems.ITEMS.register(name, item.apply(register));
		return register;
	}

	private static <T extends Block> RegistryObject<T> noItemRegister(String name, Supplier<? extends T> block) {
		RegistryObject<T> register = BLOCKS.register(name, block);
		return register;
	}

	private static <B extends Block> RegistryObject<B> register(String name, Supplier<? extends Block> block) {
		return (RegistryObject<B>) baseRegister(name, block, (object) -> ModBlocks.registerBlockItem(object));
	}

	private static <T extends Block> Supplier<BlockItem> registerBlockItem(final RegistryObject<T> block) {
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
