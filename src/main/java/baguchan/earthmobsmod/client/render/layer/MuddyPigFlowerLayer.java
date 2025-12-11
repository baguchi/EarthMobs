package baguchan.earthmobsmod.client.render.layer;

import baguchan.earthmobsmod.EarthMobsMod;
import baguchan.earthmobsmod.client.ModModelLayers;
import baguchan.earthmobsmod.client.model.MuddyPigModel;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.color.ColorLerper;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.client.renderer.feature.ModelFeatureRenderer;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.resources.Identifier;
import net.minecraft.util.context.ContextKey;
import net.minecraft.world.item.DyeColor;


public class MuddyPigFlowerLayer<T extends LivingEntityRenderState, S extends EntityModel<T>> extends RenderLayer<T, S> {
    private static final Identifier LOCATION = Identifier.fromNamespaceAndPath(EarthMobsMod.MODID, "textures/entity/muddypig/pig_muddy_flower.png");
    public static final ContextKey<DyeColor> FLOWER_DYE = new ContextKey<>(Identifier.fromNamespaceAndPath(EarthMobsMod.MODID, "flower_dye"));

    private final MuddyPigModel model;
    private final MuddyPigModel babyModel;

    public MuddyPigFlowerLayer(RenderLayerParent<T, S> p_174533_, EntityModelSet p_174534_) {
		super(p_174533_);
        this.model = new MuddyPigModel(p_174534_.bakeLayer(ModModelLayers.MUDDY_PIG));
        this.babyModel = new MuddyPigModel(p_174534_.bakeLayer(ModModelLayers.MUDDY_PIG_BABY));
	}

    @Override
    public void submit(PoseStack poseStack, SubmitNodeCollector submitNodeCollector, int i, T entityRenderState, float v, float v1) {
        DyeColor dyeColor = entityRenderState.getRenderDataOrDefault(FLOWER_DYE, DyeColor.PINK);
        boolean mud = entityRenderState.getRenderDataOrDefault(MuddyPigMudLayer.IS_MUD, false);
        boolean sheared = entityRenderState.getRenderDataOrDefault(MuddyPigMudLayer.IS_SHEARED, true);
        MuddyPigModel pigModel = entityRenderState.isBaby ? this.babyModel : this.model;

        if (mud && !sheared) {
            if (entityRenderState.isInvisible) {
                Minecraft minecraft = Minecraft.getInstance();
                boolean flag = entityRenderState.appearsGlowing();
                if (flag) {
                    pigModel.setupAnim(entityRenderState);
                    submitNodeCollector.submitModel(pigModel, entityRenderState, poseStack, RenderTypes.outline(LOCATION), i, LivingEntityRenderer.getOverlayCoords(entityRenderState, 0.0F), -16777216, (TextureAtlasSprite) null, entityRenderState.outlineColor, (ModelFeatureRenderer.CrumblingOverlay) null);
                }

            } else {
                coloredCutoutModelCopyLayerRender(pigModel, LOCATION, poseStack, submitNodeCollector, i, entityRenderState, getWoolColor(dyeColor), 0);
            }
        }
    }

    public int getWoolColor(DyeColor woolColor) {
        return ColorLerper.Type.SHEEP.getColor(woolColor);
    }
}