package baguchan.earthmobsmod.client.render.layer;

import baguchan.earthmobsmod.client.model.FurnaceGolemModel;
import baguchan.earthmobsmod.entity.FurnaceGolem;
import com.google.common.collect.ImmutableMap;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.resources.ResourceLocation;

import java.util.Map;

public class FurnaceGolemCrackinessLayer<T extends FurnaceGolem> extends RenderLayer<T, FurnaceGolemModel<T>> {
    private static final Map<FurnaceGolem.Crackiness, ResourceLocation> resourceLocations = ImmutableMap.of(FurnaceGolem.Crackiness.LOW, new ResourceLocation("textures/entity/iron_golem/iron_golem_crackiness_low.png"), FurnaceGolem.Crackiness.MEDIUM, new ResourceLocation("textures/entity/iron_golem/iron_golem_crackiness_medium.png"), FurnaceGolem.Crackiness.HIGH, new ResourceLocation("textures/entity/iron_golem/iron_golem_crackiness_high.png"));

    public FurnaceGolemCrackinessLayer(RenderLayerParent<T, FurnaceGolemModel<T>> p_117135_) {
        super(p_117135_);
    }

    public void render(PoseStack p_117148_, MultiBufferSource p_117149_, int p_117150_, T p_117151_, float p_117152_, float p_117153_, float p_117154_, float p_117155_, float p_117156_, float p_117157_) {
        if (!p_117151_.isInvisible()) {
            FurnaceGolem.Crackiness irongolem$crackiness = p_117151_.getCrackiness();
            if (irongolem$crackiness != FurnaceGolem.Crackiness.NONE) {
                ResourceLocation resourcelocation = resourceLocations.get(irongolem$crackiness);
                renderColoredCutoutModel(this.getParentModel(), resourcelocation, p_117148_, p_117149_, p_117150_, p_117151_, 1.0F, 1.0F, 1.0F);
            }
        }
    }
}