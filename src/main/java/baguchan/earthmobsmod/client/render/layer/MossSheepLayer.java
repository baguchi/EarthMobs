package baguchan.earthmobsmod.client.render.layer;

import baguchan.earthmobsmod.EarthMobsMod;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.animal.sheep.SheepFurModel;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.entity.state.SheepRenderState;
import net.minecraft.client.renderer.feature.ModelFeatureRenderer;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.resources.Identifier;
import net.minecraft.util.context.ContextKey;


public class MossSheepLayer<T extends SheepRenderState> extends RenderLayer<T, EntityModel<T>> {
    private static final Identifier SHEEP_FUR_LOCATION = Identifier.fromNamespaceAndPath(EarthMobsMod.MODID, "textures/entity/sheep_moss.png");
    private static final Identifier SHEEP_FUR_BABY_LOCATION = Identifier.fromNamespaceAndPath(EarthMobsMod.MODID, "textures/entity/sheep_moss_baby.png");
    private final EntityModel<SheepRenderState> adultModel;
    private final EntityModel<SheepRenderState> babyModel;
    public static final ContextKey<Boolean> MOSS = new ContextKey<>(Identifier.fromNamespaceAndPath(EarthMobsMod.MODID, "moss"));

    public MossSheepLayer(RenderLayerParent<T, EntityModel<T>> p_174533_, EntityModelSet p_174534_) {
        super(p_174533_);
        this.adultModel = new SheepFurModel(p_174534_.bakeLayer(ModelLayers.SHEEP_WOOL));
        this.babyModel = new SheepFurModel(p_174534_.bakeLayer(ModelLayers.SHEEP_BABY_WOOL));
    }

    @Override
    public void submit(PoseStack poseStack, SubmitNodeCollector submitNodeCollector, int i, SheepRenderState hornedSheepRenderState, float v, float v1) {
        boolean moss = hornedSheepRenderState.getRenderDataOrDefault(MOSS, false);

        if (!hornedSheepRenderState.isSheared && moss) {
            EntityModel<SheepRenderState> entitymodel = hornedSheepRenderState.isBaby ? this.babyModel : this.adultModel;
            if (hornedSheepRenderState.isInvisible) {
                if (hornedSheepRenderState.appearsGlowing()) {
                    submitNodeCollector.submitModel(entitymodel, hornedSheepRenderState, poseStack, RenderTypes.outline(hornedSheepRenderState.isBaby ? SHEEP_FUR_BABY_LOCATION : SHEEP_FUR_LOCATION), i, LivingEntityRenderer.getOverlayCoords(hornedSheepRenderState, 0.0F), -16777216, (TextureAtlasSprite) null, hornedSheepRenderState.outlineColor, (ModelFeatureRenderer.CrumblingOverlay) null);
                }
            } else {
                coloredCutoutModelCopyLayerRender(entitymodel, hornedSheepRenderState.isBaby ? SHEEP_FUR_BABY_LOCATION : SHEEP_FUR_LOCATION, poseStack, submitNodeCollector, i, hornedSheepRenderState, -1, 2);
            }
        }
    }
}