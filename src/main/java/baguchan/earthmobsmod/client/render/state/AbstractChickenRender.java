package baguchan.earthmobsmod.client.render.state;

import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.entity.AgeableMobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.state.ChickenRenderState;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.animal.Chicken;

public abstract class AbstractChickenRender<T extends Chicken, S extends ChickenRenderState, M extends EntityModel<S>> extends AgeableMobRenderer<T, S, M> {

    public AbstractChickenRender(EntityRendererProvider.Context p_174304_, M p_174305_, M baby, float p_174306_) {
        super(p_174304_, p_174305_, baby, p_174306_);
    }

    @Override
    public void extractRenderState(T p_362733_, S p_360515_, float p_361157_) {
        super.extractRenderState(p_362733_, p_360515_, p_361157_);
        p_360515_.flap = Mth.lerp(p_361157_, p_362733_.oFlap, p_362733_.flap);
        p_360515_.flapSpeed = Mth.lerp(p_361157_, p_362733_.oFlapSpeed, p_362733_.flapSpeed);

    }
}
