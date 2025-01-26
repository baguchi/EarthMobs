package baguchan.earthmobsmod.client.render.layer;

import baguchan.earthmobsmod.EarthMobsMod;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.SheepFurModel;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.entity.state.SheepRenderState;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.context.ContextKey;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class MossSheepLayer<T extends SheepRenderState> extends RenderLayer<T, EntityModel<T>> {
    private static final ResourceLocation SHEEP_FUR_LOCATION = ResourceLocation.fromNamespaceAndPath(EarthMobsMod.MODID, "textures/entity/sheep_moss.png");
    private final EntityModel<SheepRenderState> adultModel;
    private final EntityModel<SheepRenderState> babyModel;
    public static final ContextKey<Boolean> MOSS = new ContextKey<>(ResourceLocation.fromNamespaceAndPath(EarthMobsMod.MODID, "fmoss"));

    public MossSheepLayer(RenderLayerParent<T, EntityModel<T>> p_174533_, EntityModelSet p_174534_) {
        super(p_174533_);
        this.adultModel = new SheepFurModel(p_174534_.bakeLayer(ModelLayers.SHEEP_WOOL));
        this.babyModel = new SheepFurModel(p_174534_.bakeLayer(ModelLayers.SHEEP_BABY_WOOL));
    }

    public void render(PoseStack p_117421_, MultiBufferSource p_117422_, int p_117423_, T p_117424_, float p_117425_, float p_117426_) {
        EntityModel<SheepRenderState> entitymodel = p_117424_.isBaby ? this.babyModel : this.adultModel;
        boolean moss = p_117424_.getRenderDataOrDefault(MOSS, false);

        if (!p_117424_.isSheared && moss) {
            if (p_117424_.isInvisible) {
                boolean flag = p_117424_.appearsGlowing;
                    if (flag) {
                        entitymodel.setupAnim(p_117424_);
                        VertexConsumer vertexconsumer = p_117422_.getBuffer(RenderType.outline(SHEEP_FUR_LOCATION));
                        entitymodel.renderToBuffer(p_117421_, vertexconsumer, p_117423_, LivingEntityRenderer.getOverlayCoords(p_117424_, 0.0F));
                    }

                } else {

                coloredCutoutModelCopyLayerRender(entitymodel, SHEEP_FUR_LOCATION, p_117421_, p_117422_, p_117423_, p_117424_, -1);
            }
        }
    }
}