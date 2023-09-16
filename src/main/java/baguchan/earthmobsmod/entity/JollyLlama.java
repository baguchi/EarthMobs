package baguchan.earthmobsmod.entity;

import baguchan.earthmobsmod.registry.ModEntities;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.horse.Llama;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

public class JollyLlama extends Llama {
    public JollyLlama(EntityType<? extends JollyLlama> p_30750_, Level p_30751_) {
        super(p_30750_, p_30751_);
    }

    @Nullable
    @Override
    public Llama getBreedOffspring(ServerLevel p_149545_, AgeableMob p_149546_) {
        return ModEntities.JOLLY_LLAMA.get().create(p_149545_);
    }
}
