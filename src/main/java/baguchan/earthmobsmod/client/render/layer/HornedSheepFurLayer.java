package baguchan.earthmobsmod.client.render.layer;

import baguchan.earthmobsmod.client.model.HornedSheepFurModel;
import baguchan.earthmobsmod.client.model.HornedSheepModel;
import baguchan.earthmobsmod.client.render.state.HornedSheepRenderState;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.ARGB;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.animal.Sheep;
import net.minecraft.world.item.DyeColor;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class HornedSheepFurLayer extends RenderLayer<HornedSheepRenderState, HornedSheepModel<HornedSheepRenderState>> {
	private static final ResourceLocation SHEEP_FUR_LOCATION = ResourceLocation.withDefaultNamespace("textures/entity/sheep/sheep_fur.png");
	private final EntityModel<HornedSheepRenderState> adultModel;
	private final EntityModel<HornedSheepRenderState> babyModel;

	public HornedSheepFurLayer(RenderLayerParent<HornedSheepRenderState, HornedSheepModel<HornedSheepRenderState>> p_362577_, EntityModelSet p_362840_) {
		super(p_362577_);
		this.adultModel = new HornedSheepFurModel(p_362840_.bakeLayer(ModelLayers.SHEEP_WOOL));
		this.babyModel = new HornedSheepFurModel(p_362840_.bakeLayer(ModelLayers.SHEEP_BABY_WOOL));
	}

	@Override
	public void render(PoseStack p_360648_, MultiBufferSource p_361412_, int p_361724_, HornedSheepRenderState p_362704_, float p_363845_, float p_360883_) {
		if (!p_362704_.isSheared) {
			EntityModel<HornedSheepRenderState> entitymodel = p_362704_.isBaby ? this.babyModel : this.adultModel;
			if (p_362704_.isInvisible) {
				if (p_362704_.appearsGlowing) {
					entitymodel.setupAnim(p_362704_);
					VertexConsumer vertexconsumer = p_361412_.getBuffer(RenderType.outline(SHEEP_FUR_LOCATION));
					entitymodel.renderToBuffer(p_360648_, vertexconsumer, p_361724_, LivingEntityRenderer.getOverlayCoords(p_362704_, 0.0F), -16777216);
				}
			} else {
				int i;
				if (p_362704_.customName != null && "jeb_".equals(p_362704_.customName.getString())) {
					int j = 25;
					int k = Mth.floor(p_362704_.ageInTicks);
					int l = k / 25 + p_362704_.id;
					int i1 = DyeColor.values().length;
					int j1 = l % i1;
					int k1 = (l + 1) % i1;
					float f = ((float) (k % 25) + Mth.frac(p_362704_.ageInTicks)) / 25.0F;
					int l1 = Sheep.getColor(DyeColor.byId(j1));
					int i2 = Sheep.getColor(DyeColor.byId(k1));
					i = ARGB.lerp(f, l1, i2);
				} else {
					i = Sheep.getColor(p_362704_.woolColor);
				}

				coloredCutoutModelCopyLayerRender(entitymodel, SHEEP_FUR_LOCATION, p_360648_, p_361412_, p_361724_, p_362704_, i);
			}
		}
	}
}
