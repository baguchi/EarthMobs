package baguchan.earthmobsmod.advancements.criterion;

import baguchan.earthmobsmod.entity.WoolyCow;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.advancements.predicates.entity.EntitySubPredicate;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.phys.Vec3;
import org.jspecify.annotations.Nullable;

import java.util.Optional;

public record WoolyCowPredicate(Optional<Boolean> sheared) implements EntitySubPredicate {
    public static final Codec<WoolyCowPredicate> CODEC = RecordCodecBuilder.create((i) -> i.group(Codec.BOOL.optionalFieldOf("sheared").forGetter(WoolyCowPredicate::sheared)).apply(i, WoolyCowPredicate::new));

    public boolean matches(Entity entity, ServerLevel level, @Nullable Vec3 position) {
        boolean var10000;
        if (entity instanceof WoolyCow sheep) {
            var10000 = !this.sheared.isPresent() || sheep.isSheared() == (Boolean) this.sheared.get();
        } else {
            var10000 = false;
        }

        return var10000;
    }

    public static WoolyCowPredicate hasWool() {
        return new WoolyCowPredicate(Optional.of(false));
    }
}
