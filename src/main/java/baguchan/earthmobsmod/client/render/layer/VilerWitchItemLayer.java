package baguchan.earthmobsmod.client.render.layer;

import baguchan.earthmobsmod.client.model.VilerWitchModel;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.CrossedArmsItemLayer;
import net.minecraft.client.renderer.entity.state.WitchRenderState;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class VilerWitchItemLayer extends CrossedArmsItemLayer<WitchRenderState, VilerWitchModel<WitchRenderState>> {
    public VilerWitchItemLayer(RenderLayerParent<WitchRenderState, VilerWitchModel<WitchRenderState>> p_234926_) {
        super(p_234926_);
	}

    protected void applyTranslation(WitchRenderState p_383222_, PoseStack p_382897_) {
        if (p_383222_.isHoldingPotion) {
            this.getParentModel().root().translateAndRotate(p_382897_);
            this.getParentModel().getHead().translateAndRotate(p_382897_);
            this.getParentModel().getNose().translateAndRotate(p_382897_);
            p_382897_.translate(0.0625F, 0.25F, 0.0F);
            p_382897_.mulPose(Axis.ZP.rotationDegrees(180.0F));
            p_382897_.mulPose(Axis.XP.rotationDegrees(140.0F));
            p_382897_.mulPose(Axis.ZP.rotationDegrees(10.0F));
            p_382897_.mulPose(Axis.XP.rotationDegrees(180.0F));
        } else {
            super.applyTranslation(p_383222_, p_382897_);
		}
	}
}
