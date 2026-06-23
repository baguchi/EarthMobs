package baguchan.earthmobsmod.registry;

import baguchan.earthmobsmod.EarthMobsMod;
import baguchan.earthmobsmod.advancements.criterion.WoolyCowPredicate;
import com.mojang.serialization.Codec;
import net.minecraft.advancements.predicates.entity.EntitySubPredicate;
import net.minecraft.core.registries.BuiltInRegistries;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModEntitySubPredicates {
    public static final DeferredRegister<Codec<? extends EntitySubPredicate>> ENTITIE_SUB_PREDICATES = DeferredRegister.create(BuiltInRegistries.ENTITY_SUB_PREDICATE_TYPE, EarthMobsMod.MODID);
    public static final DeferredHolder<Codec<? extends EntitySubPredicate>, Codec<WoolyCowPredicate>> WOOLY_COW = ENTITIE_SUB_PREDICATES.register("wooly_cow", () -> WoolyCowPredicate.CODEC);

}
