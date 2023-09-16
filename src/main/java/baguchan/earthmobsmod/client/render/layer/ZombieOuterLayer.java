package baguchan.earthmobsmod.client.render.layer;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.ZombieModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ZombieOuterLayer<T extends Zombie> extends RenderLayer<T, ZombieModel<T>> {
	private final ResourceLocation resourceLocation;
	private final ZombieModel<T> model;

	public ZombieOuterLayer(RenderLayerParent<T, ZombieModel<T>> p_174490_, ZombieModel p_174491_, ResourceLocation resourceLocation) {
		super(p_174490_);
		this.model = p_174491_;
		this.resourceLocation = resourceLocation;
	}

	public void render(PoseStack p_116924_, MultiBufferSource p_116925_, int p_116926_, T p_116927_, float p_116928_, float p_116929_, float p_116930_, float p_116931_, float p_116932_, float p_116933_) {
		coloredCutoutModelCopyLayerRender(this.getParentModel(), this.model, this.resourceLocation, p_116924_, p_116925_, p_116926_, p_116927_, p_116928_, p_116929_, p_116931_, p_116932_, p_116933_, p_116930_, 1.0F, 1.0F, 1.0F);
	}
}