package baguchan.earthmobsmod.client.render.layer;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class OuterLayer<T extends LivingEntityRenderState> extends RenderLayer<T, EntityModel<T>> {
    private final ResourceLocation location;
	private final EntityModel<T> model;
    private final EntityModel<T> babyModel;

    public OuterLayer(RenderLayerParent<T, EntityModel<T>> p_174490_, ResourceLocation location, EntityModel<T> entityModel, EntityModel<T> babyModel) {
		super(p_174490_);
        this.location = location;
        this.model = entityModel;
        this.babyModel = babyModel;
	}

    public void render(PoseStack p_116913_, MultiBufferSource p_116914_, int p_116915_, T p_361730_, float p_116917_, float p_116918_) {
        EntityModel<T> drownedmodel = p_361730_.isBaby ? this.babyModel : this.model;
        coloredCutoutModelCopyLayerRender(drownedmodel, location, p_116913_, p_116914_, p_116915_, p_361730_, -1);
	}
}