package baguchan.earthmobsmod.client.render;

import baguchan.earthmobsmod.EarthMobsMod;
import baguchan.earthmobsmod.client.ModModelLayers;
import baguchan.earthmobsmod.client.model.FurnaceGolemModel;
import baguchan.earthmobsmod.client.render.layer.FurnaceGolemCrackinessLayer;
import baguchan.earthmobsmod.client.render.state.FurnaceGolemRenderState;
import baguchan.earthmobsmod.entity.FurnaceGolem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class FurnaceGolemRenderer extends MobRenderer<FurnaceGolem, FurnaceGolemRenderState, FurnaceGolemModel<FurnaceGolemRenderState>> {
    private static final ResourceLocation GOLEM_LOCATION = ResourceLocation.fromNamespaceAndPath(EarthMobsMod.MODID, "textures/entity/furnace_golem/furnace_golem.png");

    public FurnaceGolemRenderer(EntityRendererProvider.Context p_174188_) {
        super(p_174188_, new FurnaceGolemModel(p_174188_.bakeLayer(ModelLayers.IRON_GOLEM)), 0.7F);
        this.addLayer(new FurnaceGolemCrackinessLayer(this));
    }

    public ResourceLocation getTextureLocation(FurnaceGolemRenderState p_362083_) {
        return GOLEM_LOCATION;
    }

    public FurnaceGolemRenderState createRenderState() {
        return new FurnaceGolemRenderState();
    }

    public void extractRenderState(FurnaceGolem p_364735_, FurnaceGolemRenderState p_365108_, float p_365449_) {
        super.extractRenderState(p_364735_, p_365108_, p_365449_);
        p_365108_.attackTicksRemaining = (float) p_364735_.getAttackAnimationTick() > 0.0F ? (float) p_364735_.getAttackAnimationTick() - p_365449_ : 0.0F;
        p_365108_.active = p_364735_.isFurnaceActive();
        p_365108_.crackiness = p_364735_.getCrackiness();
    }

    protected void setupRotations(FurnaceGolemRenderState p_360361_, PoseStack p_115015_, float p_115016_, float p_115017_) {
        super.setupRotations(p_360361_, p_115015_, p_115016_, p_115017_);
        if (!((double) p_360361_.walkAnimationSpeed < 0.01)) {
            float f = 13.0F;
            float f1 = p_360361_.walkAnimationPos + 6.0F;
            float f2 = (Math.abs(f1 % 13.0F - 6.5F) - 3.25F) / 3.25F;
            p_115015_.mulPose(Axis.ZP.rotationDegrees(6.5F * f2));
        }
    }
}
