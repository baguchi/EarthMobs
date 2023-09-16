package baguchan.earthmobsmod.client.render;

import baguchan.earthmobsmod.EarthMobsMod;
import baguchan.earthmobsmod.api.IMuddy;
import baguchan.earthmobsmod.client.ModModelLayers;
import baguchan.earthmobsmod.client.model.TeaCupPigModel;
import baguchan.earthmobsmod.entity.TeaCupPig;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class TeaCupPigRenderer<T extends TeaCupPig> extends MobRenderer<T, TeaCupPigModel<T>> {
    private static final ResourceLocation TEXTURE = new ResourceLocation(EarthMobsMod.MODID, "textures/entity/teacup_pig/teacup_pig.png");
    private static final ResourceLocation MUD_TEXTURE = new ResourceLocation(EarthMobsMod.MODID, "textures/entity/teacup_pig/teacup_pig_mud.png");


    public TeaCupPigRenderer(EntityRendererProvider.Context p_174304_) {
        super(p_174304_, new TeaCupPigModel<>(p_174304_.bakeLayer(ModModelLayers.TEACUP_PIG)), 0.25F);
    }

    @Override
    protected void scale(T p_115314_, PoseStack p_115315_, float p_115316_) {
        float scale = p_115314_.isBaby() ? 0.6F : 1.0F;
        p_115315_.scale(scale, scale, scale);
        super.scale(p_115314_, p_115315_, p_115316_);
    }

    @Override
    public ResourceLocation getTextureLocation(T p_114482_) {
        if (p_114482_ instanceof IMuddy muddy) {
            if (muddy.isMuddy()) {
                return MUD_TEXTURE;
            }
        }
        return TEXTURE;
    }
}
