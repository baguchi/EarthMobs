package baguchan.earthmobsmod.entity;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.Cow;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

public class EarthCow extends Cow {

    public static final ResourceLocation COW_LOOT_TABLE = new ResourceLocation("minecraft", "entities/cow");

    public EarthCow(EntityType<? extends EarthCow> p_28285_, Level p_28286_) {
        super(p_28285_, p_28286_);
    }

    @Nullable
    @Override
    public Cow getBreedOffspring(ServerLevel p_148890_, AgeableMob p_148891_) {
        return (Cow) this.getType().create(p_148890_);
    }

    @Override
    protected ResourceLocation getDefaultLootTable() {
        return COW_LOOT_TABLE;
    }

    public boolean canMate(Animal p_27569_) {
        if (p_27569_ == this) {
            return false;
        } else if (!(p_27569_ instanceof EarthCow)) {
            return false;
        } else {
            return this.isInLove() && p_27569_.isInLove();
        }
    }
}
