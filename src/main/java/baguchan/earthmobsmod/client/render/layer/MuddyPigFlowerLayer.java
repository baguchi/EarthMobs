package baguchan.earthmobsmod.client.render.layer;

import baguchan.earthmobsmod.EarthMobsMod;
import baguchan.earthmobsmod.client.ModModelLayers;
import baguchan.earthmobsmod.client.model.MuddyPigModel;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.context.ContextKey;
import net.minecraft.world.entity.animal.Sheep;
import net.minecraft.world.item.DyeColor;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class MuddyPigFlowerLayer<T extends LivingEntityRenderState, S extends EntityModel<T>> extends RenderLayer<T, S> {
	private static final ResourceLocation LOCATION = ResourceLocation.fromNamespaceAndPath(EarthMobsMod.MODID, "textures/entity/muddypig/pig_muddy_flower.png");
    public static final ContextKey<DyeColor> FLOWER_DYE = new ContextKey<>(ResourceLocation.fromNamespaceAndPath(EarthMobsMod.MODID, "flower_dye"));

    private final MuddyPigModel model;

    public MuddyPigFlowerLayer(RenderLayerParent<T, S> p_174533_, EntityModelSet p_174534_) {
		super(p_174533_);
        this.model = new MuddyPigModel(p_174534_.bakeLayer(ModModelLayers.MUDDY_PIG));
	}

    @Override
    public void render(PoseStack p_117349_, MultiBufferSource p_117350_, int p_117351_, T entityRenderState, float p_117353_, float p_117354_) {
        DyeColor dyeColor = entityRenderState.getRenderDataOrDefault(FLOWER_DYE, DyeColor.PINK);
        boolean mud = entityRenderState.getRenderDataOrDefault(MuddyPigMudLayer.IS_MUD, false);
        boolean sheared = entityRenderState.getRenderDataOrDefault(MuddyPigMudLayer.IS_SHEARED, true);

        if (mud && !sheared) {
            if (entityRenderState.isInvisible) {
                Minecraft minecraft = Minecraft.getInstance();
                boolean flag = entityRenderState.appearsGlowing;
                if (flag) {
                    this.model.setupAnim(entityRenderState);
                    VertexConsumer vertexconsumer = p_117350_.getBuffer(RenderType.outline(LOCATION));
                    this.model.renderToBuffer(p_117349_, vertexconsumer, p_117351_, LivingEntityRenderer.getOverlayCoords(entityRenderState, 0.0F));
                }

            } else {
                int i;
                i = Sheep.getColor(dyeColor);
                coloredCutoutModelCopyLayerRender(model, LOCATION, p_117349_, p_117350_, p_117351_, entityRenderState, i);
			}
		}
	}
}