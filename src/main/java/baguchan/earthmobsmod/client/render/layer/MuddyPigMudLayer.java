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
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
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
    public void render(PoseStack p_117349_, MultiBufferSource p_117350_, int p_117351_, T entityRenderState, float p_117353_, float p_117354_) {
        boolean mud = entityRenderState.getRenderDataOrDefault(IS_MUD, false);
        boolean sheared = entityRenderState.getRenderDataOrDefault(IS_SHEARED, true);
        MuddyPigModel pigModel = entityRenderState.isBaby ? this.babyModel : this.model;

        if (mud) {
            if (entityRenderState.isInvisible) {
                Minecraft minecraft = Minecraft.getInstance();
                boolean flag = entityRenderState.appearsGlowing;
                if (flag) {
                    pigModel.setupAnim(entityRenderState);
                    VertexConsumer vertexconsumer = p_117350_.getBuffer(RenderType.outline(MUD_LOCATION));
                    pigModel.renderToBuffer(p_117349_, vertexconsumer, p_117351_, LivingEntityRenderer.getOverlayCoords(entityRenderState, 0.0F));
                }

            } else {
                coloredCutoutModelCopyLayerRender(pigModel, MUD_LOCATION, p_117349_, p_117350_, p_117351_, entityRenderState, -1);
            }
		}
	}
}