package baguchan.earthmobsmod.data;

import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.client.data.models.model.TextureMapping;
import net.minecraft.client.data.models.model.TextureSlot;
import net.minecraft.client.data.models.model.TexturedModel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

import static net.minecraft.client.data.models.model.TextureMapping.getBlockTexture;
import static net.minecraft.client.data.models.model.TexturedModel.createDefault;

public class EarthTexturedModel {
    public static final TexturedModel.Provider ORIENTABLE_MELON = createDefault(EarthTexturedModel::orientableMelon, ModelTemplates.CUBE_ORIENTABLE_TOP_BOTTOM);


    public static TextureMapping orientableMelon(Block block) {
        return new TextureMapping()
                .put(TextureSlot.SIDE, getBlockTexture(Blocks.MELON, "_side"))
                .put(TextureSlot.FRONT, getBlockTexture(block))
                .put(TextureSlot.TOP, getBlockTexture(Blocks.MELON, "_top"))
                .put(TextureSlot.BOTTOM, getBlockTexture(Blocks.MELON, "_top"));
    }
}
