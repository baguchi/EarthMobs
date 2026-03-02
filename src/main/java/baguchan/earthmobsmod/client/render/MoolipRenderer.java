package baguchan.earthmobsmod.client.render;

import baguchan.earthmobsmod.EarthMobsMod;
import baguchan.earthmobsmod.client.ModModelLayers;
import baguchan.earthmobsmod.client.model.MoobloomModel;
import baguchan.earthmobsmod.client.render.layer.CowPlantFlowerLayer;
import baguchan.earthmobsmod.client.render.state.MoobloomRenderState;
import baguchan.earthmobsmod.entity.Moolip;
import baguchan.earthmobsmod.registry.ModBlocks;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.AdultAndBabyModelPair;
import net.minecraft.client.model.animal.cow.BabyCowModel;
import net.minecraft.client.model.animal.cow.CowModel;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.block.BlockModelResolver;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.state.CameraRenderState;
import net.minecraft.resources.Identifier;

public class MoolipRenderer extends MobRenderer<Moolip, MoobloomRenderState, CowModel> {
    private static final Identifier TEXTURE = Identifier.fromNamespaceAndPath(EarthMobsMod.MODID, "textures/entity/moobloom/moolip.png");
    private static final Identifier TEXTURE_BABY = Identifier.fromNamespaceAndPath(EarthMobsMod.MODID, "textures/entity/moobloom/moolip_baby.png");

    private final AdultAndBabyModelPair<CowModel> models;
    private final BlockModelResolver blockModelResolver;

    public MoolipRenderer(EntityRendererProvider.Context context) {
        super(context, new MoobloomModel(context.bakeLayer(ModModelLayers.MOOBLOOM)), 0.5F);
        this.blockModelResolver = context.getBlockModelResolver();
        this.addLayer(new CowPlantFlowerLayer(this));
        this.models = bakeModels(context);
    }

    private static AdultAndBabyModelPair<CowModel> bakeModels(EntityRendererProvider.Context context) {
        return new AdultAndBabyModelPair<>(
                new MoobloomModel(context.bakeLayer(ModModelLayers.MOOBLOOM)), new BabyCowModel(context.bakeLayer(ModModelLayers.MOOBLOOM_BABY))
        );
    }

    @Override
    public void extractRenderState(Moolip entity, MoobloomRenderState state, float partialTicks) {
        super.extractRenderState(entity, state, partialTicks);
        this.blockModelResolver.update(state.plantBlock, ModBlocks.PINK_DAISY.get().defaultBlockState());

    }

    @Override
    public void submit(MoobloomRenderState state, PoseStack poseStack, SubmitNodeCollector submitNodeCollector, CameraRenderState camera) {
        this.model = this.models.getModel(state.isBaby);
        super.submit(state, poseStack, submitNodeCollector, camera);
    }
	@Override
    public MoobloomRenderState createRenderState() {
        return new MoobloomRenderState();
    }


    @Override
    public Identifier getTextureLocation(MoobloomRenderState renderState) {
        if (renderState.isBaby) {
            return TEXTURE_BABY;
        }
        return TEXTURE;
    }
}