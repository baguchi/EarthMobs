package baguchan.earthmobsmod.client.render.layer;

import baguchan.earthmobsmod.client.ModModelLayers;
import baguchan.earthmobsmod.client.model.BabyHornedSheepModel;
import baguchan.earthmobsmod.client.model.HornedSheepFurModel;
import baguchan.earthmobsmod.client.model.HornedSheepModel;
import baguchan.earthmobsmod.client.render.state.HornedSheepRenderState;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.feature.ModelFeatureRenderer;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.resources.Identifier;

public class HornedSheepWoolUndercoatLayer extends RenderLayer<HornedSheepRenderState, HornedSheepModel<HornedSheepRenderState>> {
    private static final Identifier SHEEP_FUR_LOCATION = Identifier.withDefaultNamespace("textures/entity/sheep/sheep_wool_undercoat.png");
    private final EntityModel<HornedSheepRenderState> adultModel;
    private final EntityModel<HornedSheepRenderState> babyModel;

    public HornedSheepWoolUndercoatLayer(RenderLayerParent<HornedSheepRenderState, HornedSheepModel<HornedSheepRenderState>> p_362577_, EntityModelSet p_362840_) {
        super(p_362577_);
        this.adultModel = new HornedSheepFurModel(p_362840_.bakeLayer(ModModelLayers.HORNED_SHEEP_UNDERCOAT));
        this.babyModel = new BabyHornedSheepModel<>(p_362840_.bakeLayer(ModModelLayers.HORNED_SHEEP_BABY_UNDERCOAT));
    }

    @Override
    public void submit(PoseStack poseStack, SubmitNodeCollector submitNodeCollector, int i, HornedSheepRenderState hornedSheepRenderState, float v, float v1) {
        if (!hornedSheepRenderState.isSheared && !hornedSheepRenderState.isBaby) {
            EntityModel<HornedSheepRenderState> entitymodel = hornedSheepRenderState.isBaby ? this.babyModel : this.adultModel;
            if (hornedSheepRenderState.isInvisible) {
                if (hornedSheepRenderState.appearsGlowing()) {
                    submitNodeCollector.submitModel(entitymodel, hornedSheepRenderState, poseStack, RenderTypes.outline(SHEEP_FUR_LOCATION), i, LivingEntityRenderer.getOverlayCoords(hornedSheepRenderState, 0.0F), -16777216, (TextureAtlasSprite) null, hornedSheepRenderState.outlineColor, (ModelFeatureRenderer.CrumblingOverlay) null);
                }
            } else {
                coloredCutoutModelCopyLayerRender(entitymodel, SHEEP_FUR_LOCATION, poseStack, submitNodeCollector, i, hornedSheepRenderState, hornedSheepRenderState.getWoolColor(), 1);
            }
        }
    }
}
