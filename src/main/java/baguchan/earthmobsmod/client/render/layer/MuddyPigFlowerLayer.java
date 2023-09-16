package baguchan.earthmobsmod.client.render.layer;

import baguchan.earthmobsmod.EarthMobsMod;
import baguchan.earthmobsmod.api.IMuddy;
import baguchan.earthmobsmod.api.ISheared;
import baguchan.earthmobsmod.client.ModModelLayers;
import baguchan.earthmobsmod.client.model.MuddyPigModel;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.PigModel;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.animal.Pig;
import net.minecraft.world.entity.animal.Sheep;
import net.minecraft.world.item.DyeColor;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class MuddyPigFlowerLayer extends RenderLayer<Pig, PigModel<Pig>> {
	private static final ResourceLocation LOCATION = new ResourceLocation(EarthMobsMod.MODID, "textures/entity/muddypig/pig_muddy_flower.png");

	private final MuddyPigModel<Pig> model;

	public MuddyPigFlowerLayer(RenderLayerParent<Pig, PigModel<Pig>> p_174533_, EntityModelSet p_174534_) {
		super(p_174533_);
		this.model = new MuddyPigModel<>(p_174534_.bakeLayer(ModModelLayers.MUDDY_PIG));
	}

	public void render(PoseStack p_117421_, MultiBufferSource p_117422_, int p_117423_, Pig pig, float p_117425_, float p_117426_, float p_117427_, float p_117428_, float p_117429_, float p_117430_) {
		if (pig instanceof IMuddy && pig instanceof ISheared) {
			if (((IMuddy) pig).isMuddy() && !((ISheared) pig).isSheared()) {
				if (pig.isInvisible()) {
					Minecraft minecraft = Minecraft.getInstance();
					boolean flag = minecraft.shouldEntityAppearGlowing(pig);
					if (flag) {
						this.getParentModel().copyPropertiesTo(this.model);
						this.model.prepareMobModel(pig, p_117425_, p_117426_, p_117427_);
						this.model.setupAnim(pig, p_117425_, p_117426_, p_117428_, p_117429_, p_117430_);
						VertexConsumer vertexconsumer = p_117422_.getBuffer(RenderType.outline(LOCATION));
						this.model.renderToBuffer(p_117421_, vertexconsumer, p_117423_, LivingEntityRenderer.getOverlayCoords(pig, 0.0F), 0.0F, 0.0F, 0.0F, 1.0F);
					}

				} else {
					float f;
					float f1;
					float f2;
					if (pig.hasCustomName() && "jeb_".equals(pig.getName().getContents())) {
						int i1 = 25;
						int i = pig.tickCount / 25 + pig.getId();
						int j = DyeColor.values().length;
						int k = i % j;
						int l = (i + 1) % j;
						float f3 = ((float) (pig.tickCount % 25) + p_117427_) / 25.0F;
						float[] afloat1 = Sheep.getColorArray(DyeColor.byId(k));
						float[] afloat2 = Sheep.getColorArray(DyeColor.byId(l));
						f = afloat1[0] * (1.0F - f3) + afloat2[0] * f3;
						f1 = afloat1[1] * (1.0F - f3) + afloat2[1] * f3;
						f2 = afloat1[2] * (1.0F - f3) + afloat2[2] * f3;
					} else {
						float[] afloat = Sheep.getColorArray(((ISheared) pig).getColor());
						f = afloat[0];
						f1 = afloat[1];
						f2 = afloat[2];
					}

					coloredCutoutModelCopyLayerRender(this.getParentModel(), this.model, LOCATION, p_117421_, p_117422_, p_117423_, pig, p_117425_, p_117426_, p_117428_, p_117429_, p_117430_, p_117427_, f, f1, f2);
				}
			}
		}
	}
}