package baguchan.earthmobsmod.registry;

import baguchan.earthmobsmod.EarthMobsMod;
import net.minecraft.network.syncher.EntityDataSerializer;
import net.minecraft.world.item.alchemy.PotionContents;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

public class ModEntityDatas {
    public static final DeferredRegister<EntityDataSerializer<?>> ENTITIE_DATAS = DeferredRegister.create(NeoForgeRegistries.ENTITY_DATA_SERIALIZERS, EarthMobsMod.MODID);

    public static final DeferredHolder<EntityDataSerializer<?>, EntityDataSerializer<PotionContents>> POTIONS = ENTITIE_DATAS.register("potion_contents", () -> EntityDataSerializer.forValueType(PotionContents.STREAM_CODEC));

}