package baguchan.earthmobsmod.client.render.layer;

import baguchan.earthmobsmod.client.model.FurnaceGolemModel;
import baguchan.earthmobsmod.client.render.state.FurnaceGolemRenderState;
import com.google.common.collect.ImmutableMap;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.SubmitNodeCollector;
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

    @Override
    public void submit(PoseStack poseStack, SubmitNodeCollector submitNodeCollector, int i, T t, float v, float v1) {
        if (!t.isInvisible) {
            Crackiness.Level irongolem$crackiness = t.crackiness;
            if (irongolem$crackiness != Crackiness.Level.NONE) {
                ResourceLocation resourcelocation = resourceLocations.get(irongolem$crackiness);
                renderColoredCutoutModel(this.getParentModel(), resourcelocation, poseStack, submitNodeCollector, i, t, -1, 1);
            }
        }
    }
}