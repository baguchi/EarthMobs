package baguchan.earthmobsmod.client.render;

import baguchan.earthmobsmod.EarthMobsMod;
import baguchan.earthmobsmod.client.ModModelLayers;
import baguchan.earthmobsmod.client.model.CluckShroomModel;
import baguchan.earthmobsmod.client.render.state.CluckShroomRenderState;
import baguchan.earthmobsmod.entity.CluckShroom;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.AdultAndBabyModelPair;
import net.minecraft.client.model.animal.chicken.BabyChickenModel;
import net.minecraft.client.model.animal.chicken.ChickenModel;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.resources.Identifier;
import net.minecraft.util.Mth;


public class CluckShroomRender<T extends CluckShroom> extends MobRenderer<T, CluckShroomRenderState, ChickenModel> {
    private static final Identifier TEXTURE = Identifier.fromNamespaceAndPath(EarthMobsMod.MODID, "textures/entity/cluck_shroom/cluck_shroom.png");
    private static final Identifier TEXTURE_BROWN = Identifier.fromNamespaceAndPath(EarthMobsMod.MODID, "textures/entity/cluck_shroom/brown_cluck_shroom.png");
    private static final Identifier TEXTURE_BABY = Identifier.fromNamespaceAndPath(EarthMobsMod.MODID, "textures/entity/cluck_shroom/cluck_shroom_baby.png");
    private static final Identifier TEXTURE_BROWN_BABY = Identifier.fromNamespaceAndPath(EarthMobsMod.MODID, "textures/entity/cluck_shroom/brown_cluck_shroom_baby.png");
    private final AdultAndBabyModelPair<ChickenModel> models;

    public CluckShroomRender(EntityRendererProvider.Context context) {
        super(context, new CluckShroomModel(context.bakeLayer(ModModelLayers.CLUCK_SHROOM)), 0.3F);
        this.models = bakeModels(context);
    }

    private static AdultAndBabyModelPair<ChickenModel> bakeModels(EntityRendererProvider.Context context) {
        return new AdultAndBabyModelPair<>(
                new CluckShroomModel(context.bakeLayer(ModModelLayers.CLUCK_SHROOM)), new BabyChickenModel(context.bakeLayer(ModModelLayers.CLUCK_SHROOM_BABY))
        );
    }


    @Override
    public void submit(CluckShroomRenderState state, PoseStack poseStack, SubmitNodeCollector submitNodeCollector, CameraRenderState camera) {
        this.model = this.models.getModel(state.isBaby);
        super.submit(state, poseStack, submitNodeCollector, camera);
    }


    @Override
    public CluckShroomRenderState createRenderState() {
        return new CluckShroomRenderState();
	}

	@Override
    public void extractRenderState(T p_362733_, CluckShroomRenderState p_360515_, float p_361157_) {
        super.extractRenderState(p_362733_, p_360515_, p_361157_);
        p_360515_.type = p_362733_.getCluckShroomType();
        p_360515_.flap = Mth.lerp(p_361157_, p_362733_.oFlap, p_362733_.flap);
        p_360515_.flapSpeed = Mth.lerp(p_361157_, p_362733_.oFlapSpeed, p_362733_.flapSpeed);
    }

    @Override
    public Identifier getTextureLocation(CluckShroomRenderState renderState) {
        if (renderState.isBaby) {
            return renderState.type == CluckShroom.CluckShroomType.BROWN ? TEXTURE_BROWN_BABY : TEXTURE_BABY;

        }

        return renderState.type == CluckShroom.CluckShroomType.BROWN ? TEXTURE_BROWN : TEXTURE;
	}
}