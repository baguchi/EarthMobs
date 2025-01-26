package baguchan.earthmobsmod.client.render;

import baguchan.earthmobsmod.EarthMobsMod;
import baguchan.earthmobsmod.client.ModModelLayers;
import baguchan.earthmobsmod.client.model.TeaCupPigModel;
import baguchan.earthmobsmod.client.render.layer.MuddyPigMudLayer;
import baguchan.earthmobsmod.entity.TeaCupPig;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class TeaCupPigRenderer<T extends TeaCupPig> extends MobRenderer<T, LivingEntityRenderState, TeaCupPigModel<LivingEntityRenderState>> {
    private static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(EarthMobsMod.MODID, "textures/entity/teacup_pig/teacup_pig.png");
    private static final ResourceLocation MUD_TEXTURE = ResourceLocation.fromNamespaceAndPath(EarthMobsMod.MODID, "textures/entity/teacup_pig/teacup_pig_mud.png");


    public TeaCupPigRenderer(EntityRendererProvider.Context p_174304_) {
        super(p_174304_, new TeaCupPigModel<>(p_174304_.bakeLayer(ModModelLayers.TEACUP_PIG)), 0.25F);
    }

    @Override
    public LivingEntityRenderState createRenderState() {
        return new LivingEntityRenderState();
    }

    @Override
    protected void scale(LivingEntityRenderState p_115314_, PoseStack p_115315_) {
        float scale = p_115314_.isBaby ? 0.6F : 1.0F;
        p_115315_.scale(scale, scale, scale);
        super.scale(p_115314_, p_115315_);
    }

    @Override
    public ResourceLocation getTextureLocation(LivingEntityRenderState p_114482_) {
        boolean mud = p_114482_.getRenderDataOrDefault(MuddyPigMudLayer.IS_MUD, false);
        if (mud) {
            return MUD_TEXTURE;
        }
        return TEXTURE;
    }
}
