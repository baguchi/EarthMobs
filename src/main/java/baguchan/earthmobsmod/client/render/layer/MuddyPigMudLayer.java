package baguchan.earthmobsmod.client.render.layer;

import baguchan.earthmobsmod.EarthMobsMod;
import baguchan.earthmobsmod.client.ModModelLayers;
import baguchan.earthmobsmod.client.model.MuddyPigModel;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.client.renderer.feature.ModelFeatureRenderer;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.context.ContextKey;


public class MuddyPigMudLayer<T extends LivingEntityRenderState, S extends EntityModel<T>> extends RenderLayer<T, S> {
	private static final ResourceLocation MUD_LOCATION = ResourceLocation.fromNamespaceAndPath(EarthMobsMod.MODID, "textures/entity/muddypig/muddy_pig.png");
	private static final ResourceLocation DRY_MUD_LOCATION = ResourceLocation.fromNamespaceAndPath(EarthMobsMod.MODID, "textures/entity/muddypig/dry_muddy_pig.png");

    public static final ContextKey<Boolean> IS_MUD = new ContextKey<>(ResourceLocation.fromNamespaceAndPath(EarthMobsMod.MODID, "mud"));
    public static final ContextKey<Boolean> IS_SHEARED = new ContextKey<>(ResourceLocation.fromNamespaceAndPath(EarthMobsMod.MODID, "sheared"));

    private final MuddyPigModel model;
    private final MuddyPigModel babyModel;

    public MuddyPigMudLayer(RenderLayerParent<T, S> p_174533_, EntityModelSet p_174534_) {
		super(p_174533_);
        this.model = new MuddyPigModel(p_174534_.bakeLayer(ModModelLayers.MUDDY_PIG));
        this.babyModel = new MuddyPigModel(p_174534_.bakeLayer(ModModelLayers.MUDDY_PIG_BABY));
    }

    @Override
    public void submit(PoseStack poseStack, SubmitNodeCollector submitNodeCollector, int i, T entityRenderState, float v, float v1) {
        boolean mud = entityRenderState.getRenderDataOrDefault(IS_MUD, false);
        MuddyPigModel pigModel = entityRenderState.isBaby ? this.babyModel : this.model;

        if (mud) {
            if (entityRenderState.isInvisible) {
                if (entityRenderState.appearsGlowing()) {
                    submitNodeCollector.submitModel(pigModel, entityRenderState, poseStack, RenderType.outline(MUD_LOCATION), i, LivingEntityRenderer.getOverlayCoords(entityRenderState, 0.0F), -16777216, (TextureAtlasSprite) null, entityRenderState.outlineColor, (ModelFeatureRenderer.CrumblingOverlay) null);
                }
            } else {
                coloredCutoutModelCopyLayerRender(pigModel, MUD_LOCATION, poseStack, submitNodeCollector, i, entityRenderState, -1, 1);
            }
        }
    }
}