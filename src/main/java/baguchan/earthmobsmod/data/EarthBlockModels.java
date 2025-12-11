package baguchan.earthmobsmod.data;

import baguchan.earthmobsmod.registry.ModBlocks;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelOutput;
import net.minecraft.client.data.models.blockstates.BlockModelDefinitionGenerator;
import net.minecraft.client.data.models.model.ModelInstance;
import net.minecraft.client.data.models.model.TextureMapping;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.block.Block;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class EarthBlockModels extends BlockModelGenerators {
    public EarthBlockModels(Consumer<BlockModelDefinitionGenerator> p_387996_, ItemModelOutput p_387053_, BiConsumer<Identifier, ModelInstance> p_387066_) {
        super(p_387996_, p_387053_, p_387066_);
    }

    @Override
    public void run() {
        this.createCrossBlockWithDefaultItem(ModBlocks.BUTTERCUP.get(), PlantType.NOT_TINTED);
        this.createCrossBlockWithDefaultItem(ModBlocks.PINK_DAISY.get(), PlantType.NOT_TINTED);
        this.createHorizontallyRotatedBlock(ModBlocks.CARVED_MELON.get(), EarthTexturedModel.ORIENTABLE_MELON);
        this.createHorizontallyRotatedBlock(ModBlocks.CARVED_MELON_SHOOT.get(), EarthTexturedModel.ORIENTABLE_MELON);
        this.createTrivialCube(ModBlocks.RUBY.get());
    }

    public void createCrossBlock(Block p_388360_, BlockModelGenerators.PlantType p_386631_, TextureMapping p_388352_) {
        Identifier resourcelocation = p_386631_.getCross().extend().renderType("cutout").build().create(p_388360_, p_388352_, this.modelOutput);
        this.blockStateOutput.accept(createSimpleBlock(p_388360_, plainVariant(resourcelocation)));
    }

}
