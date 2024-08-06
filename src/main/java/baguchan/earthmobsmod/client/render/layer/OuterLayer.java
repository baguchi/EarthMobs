package baguchan.earthmobsmod.client.render.layer;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.FastColor;
import net.minecraft.world.entity.LivingEntity;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class OuterLayer<T extends LivingEntity> extends RenderLayer<T, EntityModel<T>> {
	private final ResourceLocation resourceLocation;
	private final EntityModel<T> model;

	public OuterLayer(RenderLayerParent<T, EntityModel<T>> p_174490_, EntityModel<T> p_174491_, ResourceLocation resourceLocation) {
		super(p_174490_);
		this.model = p_174491_;
		this.resourceLocation = resourceLocation;
	}

	public void render(PoseStack p_116924_, MultiBufferSource p_116925_, int p_116926_, T p_116927_, float p_116928_, float p_116929_, float p_116930_, float p_116931_, float p_116932_, float p_116933_) {
		coloredCutoutModelCopyLayerRender(this.getParentModel(), this.model, this.resourceLocation, p_116924_, p_116925_, p_116926_, p_116927_, p_116928_, p_116929_, p_116931_, p_116932_, p_116933_, p_116930_, FastColor.ARGB32.colorFromFloat(1, 1, 1, 1));
	}
}