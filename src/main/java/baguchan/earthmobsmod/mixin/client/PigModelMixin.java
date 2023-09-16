package baguchan.earthmobsmod.mixin.client;

import baguchan.earthmobsmod.api.IMuddy;
import net.minecraft.client.model.PigModel;
import net.minecraft.client.model.QuadrupedModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.world.entity.Entity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(PigModel.class)
@OnlyIn(Dist.CLIENT)
public class PigModelMixin<T extends Entity> extends QuadrupedModel<T> {


	protected PigModelMixin(ModelPart p_170857_, boolean p_170858_, float p_170859_, float p_170860_, float p_170861_, float p_170862_, int p_170863_) {
		super(p_170857_, p_170858_, p_170859_, p_170860_, p_170861_, p_170862_, p_170863_);
	}

	@Override
	public void prepareMobModel(T p_104132_, float p_104133_, float p_104134_, float p_104135_) {
		super.prepareMobModel(p_104132_, p_104133_, p_104134_, p_104135_);
		if (p_104132_ instanceof IMuddy) {
			this.body.zRot = ((IMuddy) p_104132_).getBodyRollAngle(p_104135_, -0.16F);
		}
	}
}
