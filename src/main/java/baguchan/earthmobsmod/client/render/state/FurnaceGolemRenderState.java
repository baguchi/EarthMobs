package baguchan.earthmobsmod.client.render.state;

import net.minecraft.client.renderer.entity.state.IronGolemRenderState;
import net.minecraft.world.entity.Crackiness;

public class FurnaceGolemRenderState extends IronGolemRenderState {
    public boolean active;
    public float attackTicksRemaining;
    public Crackiness.Level crackiness = Crackiness.Level.NONE;
}
