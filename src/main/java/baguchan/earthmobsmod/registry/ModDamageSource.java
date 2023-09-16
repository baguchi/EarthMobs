package baguchan.earthmobsmod.registry;

import baguchan.earthmobsmod.EarthMobsMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.damagesource.DamageType;

public interface ModDamageSource {
    ResourceKey<DamageType> BURNING = ResourceKey.create(Registries.DAMAGE_TYPE, new ResourceLocation(EarthMobsMod.MODID, "burning_attack"));
}
