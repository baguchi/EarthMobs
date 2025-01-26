package baguchan.earthmobsmod.client.render.layer;

import baguchan.earthmobsmod.client.model.LivingSpinAttackEffectModel;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;

public class SpinAttackEffectLayer<T extends LivingEntityRenderState, M extends EntityModel<T>> extends RenderLayer<T, M> {
    public static final ResourceLocation TEXTURE = ResourceLocation.withDefaultNamespace("textures/entity/trident_riptide.png");
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
    public void render(PoseStack p_117349_, MultiBufferSource p_117350_, int p_117351_, T p_361554_, float p_117353_, float p_117354_) {
        if (p_361554_.isAutoSpinAttack) {
            VertexConsumer vertexconsumer = p_117350_.getBuffer(this.model.renderType(TEXTURE));
            this.model.setupAnim(p_361554_);
            this.model.renderToBuffer(p_117349_, vertexconsumer, p_117351_, OverlayTexture.NO_OVERLAY);
        }
    }
}