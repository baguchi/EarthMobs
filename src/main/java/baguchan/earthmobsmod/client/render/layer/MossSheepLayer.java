package baguchan.earthmobsmod.client.render.layer;

import baguchan.earthmobsmod.EarthMobsMod;
import baguchan.earthmobsmod.api.IMoss;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.SheepFurModel;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.FastColor;
import net.minecraft.world.entity.animal.Sheep;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class MossSheepLayer<T extends Sheep> extends RenderLayer<T, EntityModel<T>> {
    private static final ResourceLocation SHEEP_FUR_LOCATION = ResourceLocation.fromNamespaceAndPath(EarthMobsMod.MODID, "textures/entity/sheep_moss.png");
    private final SheepFurModel<T> model;

    public MossSheepLayer(RenderLayerParent<T, EntityModel<T>> p_174533_, EntityModelSet p_174534_) {
        super(p_174533_);
        this.model = new SheepFurModel<>(p_174534_.bakeLayer(ModelLayers.SHEEP_FUR));
    }

    public void render(PoseStack p_117421_, MultiBufferSource p_117422_, int p_117423_, T p_117424_, float p_117425_, float p_117426_, float p_117427_, float p_117428_, float p_117429_, float p_117430_) {
        if (!p_117424_.isSheared() && p_117424_ instanceof IMoss moss) {
            if (moss.isMoss()) {
                if (p_117424_.isInvisible()) {
                    Minecraft minecraft = Minecraft.getInstance();
                    boolean flag = minecraft.shouldEntityAppearGlowing(p_117424_);
                    if (flag) {
                        this.getParentModel().copyPropertiesTo(this.model);
                        this.model.prepareMobModel(p_117424_, p_117425_, p_117426_, p_117427_);
                        this.model.setupAnim(p_117424_, p_117425_, p_117426_, p_117428_, p_117429_, p_117430_);
                        VertexConsumer vertexconsumer = p_117422_.getBuffer(RenderType.outline(SHEEP_FUR_LOCATION));
                        this.model.renderToBuffer(p_117421_, vertexconsumer, p_117423_, LivingEntityRenderer.getOverlayCoords(p_117424_, 0.0F), FastColor.ARGB32.colorFromFloat(0.0F, 0.0F, 0.0F, 1.0F));
                    }

                } else {

                    coloredCutoutModelCopyLayerRender(this.getParentModel(), this.model, SHEEP_FUR_LOCATION, p_117421_, p_117422_, p_117423_, p_117424_, p_117425_, p_117426_, p_117428_, p_117429_, p_117430_, p_117427_, FastColor.ARGB32.colorFromFloat(1.0F, 1.0F, 1.0F, 1.0F));
                }
            }
        }
    }
}