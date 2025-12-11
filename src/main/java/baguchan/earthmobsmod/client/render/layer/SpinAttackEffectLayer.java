package baguchan.earthmobsmod.client.render.layer;

import baguchan.earthmobsmod.client.model.LivingSpinAttackEffectModel;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.client.renderer.feature.ModelFeatureRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.Identifier;

public class SpinAttackEffectLayer<T extends LivingEntityRenderState, M extends EntityModel<T>> extends RenderLayer<T, M> {
    public static final Identifier TEXTURE = Identifier.withDefaultNamespace("textures/entity/trident_riptide.png");
    public static final String BOX = "box";
    private final LivingSpinAttackEffectModel model;

    public SpinAttackEffectLayer(RenderLayerParent<T, M> p_174540_, EntityModelSet p_174541_) {
        super(p_174540_);
        this.model = new LivingSpinAttackEffectModel(p_174541_.bakeLayer(ModelLayers.PLAYER_SPIN_ATTACK));
    }

    public static LayerDefinition createLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();
        partdefinition.addOrReplaceChild("box", CubeListBuilder.create().texOffs(0, 0).addBox(-8.0F, -16.0F, -8.0F, 16.0F, 32.0F, 16.0F), PartPose.ZERO);
        return LayerDefinition.create(meshdefinition, 64, 64);
    }

    @Override
    public void submit(PoseStack poseStack, SubmitNodeCollector submitNodeCollector, int i, T entityRenderState, float v, float v1) {
        if (entityRenderState.isAutoSpinAttack) {
            submitNodeCollector.submitModel(this.model, entityRenderState, poseStack, this.model.renderType(TEXTURE), i, OverlayTexture.NO_OVERLAY, entityRenderState.outlineColor, (ModelFeatureRenderer.CrumblingOverlay) null);
        }

    }
}