package baguchan.earthmobsmod.client.render.layer;

import baguchan.earthmobsmod.client.model.FurnaceGolemModel;
import baguchan.earthmobsmod.client.render.state.FurnaceGolemRenderState;
import com.google.common.collect.ImmutableMap;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Crackiness;

import java.util.Map;

public class FurnaceGolemCrackinessLayer<T extends FurnaceGolemRenderState> extends RenderLayer<T, FurnaceGolemModel<T>> {
    private static final Map<Crackiness.Level, ResourceLocation> resourceLocations = ImmutableMap.of(Crackiness.Level.LOW, ResourceLocation.withDefaultNamespace("textures/entity/iron_golem/iron_golem_crackiness_low.png"), Crackiness.Level.MEDIUM, ResourceLocation.withDefaultNamespace("textures/entity/iron_golem/iron_golem_crackiness_medium.png"), Crackiness.Level.HIGH, ResourceLocation.withDefaultNamespace("textures/entity/iron_golem/iron_golem_crackiness_high.png"));

    public FurnaceGolemCrackinessLayer(RenderLayerParent<T, FurnaceGolemModel<T>> p_117135_) {
        super(p_117135_);
    }

    public void render(PoseStack p_117148_, MultiBufferSource p_117149_, int p_117150_, T p_117151_, float p_117152_, float p_117153_) {
        if (!p_117151_.isInvisible) {
            Crackiness.Level irongolem$crackiness = p_117151_.crackiness;
            if (irongolem$crackiness != Crackiness.Level.NONE) {
                ResourceLocation resourcelocation = resourceLocations.get(irongolem$crackiness);
                renderColoredCutoutModel(this.getParentModel(), resourcelocation, p_117148_, p_117149_, p_117150_, p_117151_, -1);
            }
        }
    }
}