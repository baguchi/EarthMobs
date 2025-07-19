package baguchan.earthmobsmod.client.render.layer;

import baguchan.earthmobsmod.client.ModModelLayers;
import baguchan.earthmobsmod.client.model.HornedSheepFurModel;
import baguchan.earthmobsmod.client.model.HornedSheepModel;
import baguchan.earthmobsmod.client.render.state.HornedSheepRenderState;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.DyeColor;

public class HornedSheepWoolUndercoatLayer extends RenderLayer<HornedSheepRenderState, HornedSheepModel<HornedSheepRenderState>> {
    private static final ResourceLocation SHEEP_FUR_LOCATION = ResourceLocation.withDefaultNamespace("textures/entity/sheep/sheep_wool_undercoat.png");
    private final EntityModel<HornedSheepRenderState> adultModel;
    private final EntityModel<HornedSheepRenderState> babyModel;

    public HornedSheepWoolUndercoatLayer(RenderLayerParent<HornedSheepRenderState, HornedSheepModel<HornedSheepRenderState>> p_362577_, EntityModelSet p_362840_) {
        super(p_362577_);
        this.adultModel = new HornedSheepFurModel(p_362840_.bakeLayer(ModModelLayers.HORNED_SHEEP_UNDERCOAT));
        this.babyModel = new HornedSheepFurModel(p_362840_.bakeLayer(ModModelLayers.HORNED_SHEEP_BABY_UNDERCOAT));
    }

    @Override
    public void render(PoseStack p_406212_, MultiBufferSource p_406331_, int p_406201_, HornedSheepRenderState p_406356_, float p_406239_, float p_406245_) {
        if (!p_406356_.isInvisible && (p_406356_.isJebSheep() || p_406356_.woolColor != DyeColor.WHITE)) {
            EntityModel<HornedSheepRenderState> entitymodel = p_406356_.isBaby ? this.babyModel : this.adultModel;
            coloredCutoutModelCopyLayerRender(entitymodel, SHEEP_FUR_LOCATION, p_406212_, p_406331_, p_406201_, p_406356_, p_406356_.getWoolColor());
        }
    }
}
