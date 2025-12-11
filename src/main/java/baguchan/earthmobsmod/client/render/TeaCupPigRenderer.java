package baguchan.earthmobsmod.client.render;

import baguchan.earthmobsmod.EarthMobsMod;
import baguchan.earthmobsmod.api.IMuddyPig;
import baguchan.earthmobsmod.client.ModModelLayers;
import baguchan.earthmobsmod.client.model.TeaCupPigModel;
import baguchan.earthmobsmod.client.render.state.TeaCupPigRenderState;
import baguchan.earthmobsmod.entity.TeaCupPig;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.Identifier;


public class TeaCupPigRenderer extends MobRenderer<TeaCupPig, TeaCupPigRenderState, TeaCupPigModel<TeaCupPigRenderState>> {
    private static final Identifier TEXTURE = Identifier.fromNamespaceAndPath(EarthMobsMod.MODID, "textures/entity/teacup_pig/teacup_pig.png");
    private static final Identifier MUD_TEXTURE = Identifier.fromNamespaceAndPath(EarthMobsMod.MODID, "textures/entity/teacup_pig/teacup_pig_mud.png");


    public TeaCupPigRenderer(EntityRendererProvider.Context p_174304_) {
        super(p_174304_, new TeaCupPigModel<>(p_174304_.bakeLayer(ModModelLayers.TEACUP_PIG)), 0.25F);
    }

    @Override
    public TeaCupPigRenderState createRenderState() {
        return new TeaCupPigRenderState();
    }

    @Override
    public void extractRenderState(TeaCupPig p_362733_, TeaCupPigRenderState p_360515_, float p_361157_) {
        super.extractRenderState(p_362733_, p_360515_, p_361157_);
        p_360515_.mud = p_362733_ instanceof IMuddyPig muddyPig && muddyPig.isMuddy();
        p_360515_.pot = p_362733_.isOnPot();
    }

    @Override
    protected void scale(TeaCupPigRenderState p_115314_, PoseStack p_115315_) {
        float scale = p_115314_.isBaby ? 0.6F : 1.0F;
        p_115315_.scale(scale, scale, scale);
        super.scale(p_115314_, p_115315_);
    }

    @Override
    public Identifier getTextureLocation(TeaCupPigRenderState p_114482_) {
        boolean mud = p_114482_.mud;
        if (mud) {
            return MUD_TEXTURE;
        }
        return TEXTURE;
    }
}
