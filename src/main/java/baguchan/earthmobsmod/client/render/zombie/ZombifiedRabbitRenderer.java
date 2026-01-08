package baguchan.earthmobsmod.client.render.zombie;

import baguchan.earthmobsmod.EarthMobsMod;
import baguchan.earthmobsmod.client.render.state.ZombifiedRabbitRenderState;
import baguchan.earthmobsmod.entity.ZombifiedRabbit;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.ChatFormatting;
import net.minecraft.client.model.AdultAndBabyModelPair;
import net.minecraft.client.model.animal.rabbit.AdultRabbitModel;
import net.minecraft.client.model.animal.rabbit.BabyRabbitModel;
import net.minecraft.client.model.animal.rabbit.RabbitModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.state.CameraRenderState;
import net.minecraft.resources.Identifier;


public class ZombifiedRabbitRenderer<T extends ZombifiedRabbit> extends MobRenderer<T, ZombifiedRabbitRenderState, RabbitModel> {
    private static final Identifier TEXTURE = Identifier.fromNamespaceAndPath(EarthMobsMod.MODID, "textures/entity/zombified_rabbit/zombified_rabbit.png");
    private static final Identifier BABY_TEXTURE = Identifier.fromNamespaceAndPath(EarthMobsMod.MODID, "textures/entity/zombified_rabbit/zombified_rabbit_baby.png");
    private final AdultAndBabyModelPair<RabbitModel> models;

    public ZombifiedRabbitRenderer(EntityRendererProvider.Context context) {
        super(context, new AdultRabbitModel(context.bakeLayer(ModelLayers.RABBIT)), 0.3F);
        this.models = bakeModels(context);
    }

    private static AdultAndBabyModelPair<RabbitModel> bakeModels(EntityRendererProvider.Context context) {
        return new AdultAndBabyModelPair<>(
                new AdultRabbitModel(context.bakeLayer(ModelLayers.RABBIT)), new BabyRabbitModel(context.bakeLayer(ModelLayers.RABBIT_BABY))
        );
    }


    @Override
    public void submit(ZombifiedRabbitRenderState state, PoseStack poseStack, SubmitNodeCollector submitNodeCollector, CameraRenderState camera) {
        this.model = this.models.getModel(state.isBaby);
        super.submit(state, poseStack, submitNodeCollector, camera);
    }

    @Override
    public Identifier getTextureLocation(ZombifiedRabbitRenderState renderState) {
        if (renderState.isBaby) {
            return BABY_TEXTURE;
        }
        return TEXTURE;
    }

    @Override
    public ZombifiedRabbitRenderState createRenderState() {
        return new ZombifiedRabbitRenderState();
    }

    public void extractRenderState(T p_363386_, ZombifiedRabbitRenderState p_362192_, float p_365470_) {
        super.extractRenderState(p_363386_, p_362192_, p_365470_);
        p_362192_.jumpCompletion = p_363386_.getJumpCompletion(p_365470_);
        p_362192_.isToast = "Toast".equals(ChatFormatting.stripFormatting(p_363386_.getName().getString()));
        p_362192_.variant = p_363386_.getVariant();
    }

    @Override
    protected boolean isShaking(ZombifiedRabbitRenderState p_115304_) {
        return super.isShaking(p_115304_);
    }
}