package baguchan.earthmobsmod.registry;

import baguchan.earthmobsmod.EarthMobsMod;
import baguchan.earthmobsmod.capability.ShadowCapability;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.neoforge.capabilities.EntityCapability;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.function.Supplier;

public class ModCapability {
    public static final DeferredRegister<AttachmentType<?>> ATTACHMENT_TYPES = DeferredRegister.create(NeoForgeRegistries.Keys.ATTACHMENT_TYPES, EarthMobsMod.MODID);

    public static final Supplier<AttachmentType<ShadowCapability>> SHADOW_ATTACH = ATTACHMENT_TYPES.register(
            "shadow", () -> AttachmentType.builder(ShadowCapability::new).build());
    public static final EntityCapability<ShadowCapability, Void> SHADOW =
            EntityCapability.createVoid(
                    // Provide a name to uniquely identify the capability.
                    new ResourceLocation(EarthMobsMod.MODID, "shadow"),
                    ShadowCapability.class);
}
