package baguchan.earthmobsmod.client.render.layer;

import baguchan.earthmobsmod.EarthMobsMod;
import baguchan.earthmobsmod.api.IMuddyPig;
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
import net.minecraft.util.FastColor;
import net.minecraft.world.entity.animal.Pig;
import net.minecraft.world.entity.animal.Sheep;
import net.minecraft.world.item.DyeColor;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class MuddyPigFlowerLayer extends RenderLayer<Pig, PigModel<Pig>> {
	private static final ResourceLocation LOCATION = ResourceLocation.fromNamespaceAndPath(EarthMobsMod.MODID, "textures/entity/muddypig/pig_muddy_flower.png");

	private final MuddyPigModel<Pig> model;

	public MuddyPigFlowerLayer(RenderLayerParent<Pig, PigModel<Pig>> p_174533_, EntityModelSet p_174534_) {
		super(p_174533_);
		this.model = new MuddyPigModel<>(p_174534_.bakeLayer(ModModelLayers.MUDDY_PIG));
	}

	public void render(PoseStack p_117421_, MultiBufferSource p_117422_, int p_117423_, Pig pig, float p_117425_, float p_117426_, float p_117427_, float p_117428_, float p_117429_, float p_117430_) {
        if (pig instanceof IMuddyPig && pig instanceof ISheared) {
            if (((IMuddyPig) pig).isMuddy() && !((ISheared) pig).isSheared()) {
				boolean j;
				if (pig.isInvisible()) {
					Minecraft minecraft = Minecraft.getInstance();
					j = minecraft.shouldEntityAppearGlowing(pig);
					if (j) {
						this.getParentModel().copyPropertiesTo(this.model);
						this.model.prepareMobModel(pig, p_117425_, p_117426_, p_117427_);
						this.model.setupAnim(pig, p_117425_, p_117426_, p_117428_, p_117429_, p_117430_);
						VertexConsumer vertexconsumer = p_117422_.getBuffer(RenderType.outline(LOCATION));
						this.model.renderToBuffer(p_117421_, vertexconsumer, p_117423_, LivingEntityRenderer.getOverlayCoords(pig, 0.0F), FastColor.ARGB32.colorFromFloat(0.0F, 0.0F, 0.0F, 1.0F));
					}

				} else {
					int i;
					if (pig.hasCustomName() && "jeb_".equals(pig.getName().getString())) {
						j = true;
						int k = pig.tickCount / 25 + pig.getId();
						int l = DyeColor.values().length;
						int i1 = k % l;
						int j1 = (k + 1) % l;
						float f = ((float) (pig.tickCount % 25) + p_117427_) / 25.0F;
						int k1 = Sheep.getColor(DyeColor.byId(i1));
						int l1 = Sheep.getColor(DyeColor.byId(j1));
						i = FastColor.ARGB32.lerp(f, k1, l1);
					} else {
						i = Sheep.getColor(((ISheared) pig).getColor());
					}

					coloredCutoutModelCopyLayerRender(this.getParentModel(), this.model, LOCATION, p_117421_, p_117422_, p_117423_, pig, p_117425_, p_117426_, p_117428_, p_117429_, p_117430_, p_117427_, i);
				}
			}
		}
	}
}