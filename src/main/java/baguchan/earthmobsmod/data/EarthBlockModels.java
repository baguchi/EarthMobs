package baguchan.earthmobsmod.data;

import baguchan.earthmobsmod.registry.ModBlocks;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelOutput;
import net.minecraft.client.data.models.blockstates.BlockStateGenerator;
import net.minecraft.client.data.models.model.ModelInstance;
import net.minecraft.client.data.models.model.TextureMapping;
import net.minecraft.client.data.models.model.TexturedModel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class EarthBlockModels extends BlockModelGenerators {
    public EarthBlockModels(Consumer<BlockStateGenerator> p_387996_, ItemModelOutput p_387053_, BiConsumer<ResourceLocation, ModelInstance> p_387066_) {
        super(p_387996_, p_387053_, p_387066_);
    }

    @Override
    public void run() {
        this.createCrossBlock(ModBlocks.BUTTERCUP.get(), PlantType.NOT_TINTED);
        this.createCrossBlock(ModBlocks.PINK_DAISY.get(), PlantType.NOT_TINTED);
        this.createHorizontallyRotatedBlock(ModBlocks.CARVED_MELON.get(), TexturedModel.ORIENTABLE);
        this.createHorizontallyRotatedBlock(ModBlocks.CARVED_MELON_SHOOT.get(), TexturedModel.ORIENTABLE);
        this.createTrivialCube(ModBlocks.RUBY.get());
    }


    public void createCrossBlock(Block p_388360_, BlockModelGenerators.PlantType p_386631_, TextureMapping p_388352_) {
        ResourceLocation resourcelocation = p_386631_.getCross().extend().renderType("cutout").build().create(p_388360_, p_388352_, this.modelOutput);
        this.blockStateOutput.accept(createSimpleBlock(p_388360_, resourcelocation));
    }

}
