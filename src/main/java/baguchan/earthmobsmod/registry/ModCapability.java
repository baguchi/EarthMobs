package baguchan.earthmobsmod.registry;

import baguchan.earthmobsmod.EarthMobsMod;
import baguchan.earthmobsmod.attachment.ShadowAttachment;
import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.function.Supplier;

public class ModCapability {
    public static final DeferredRegister<AttachmentType<?>> ATTACHMENT_TYPES = DeferredRegister.create(NeoForgeRegistries.Keys.ATTACHMENT_TYPES, EarthMobsMod.MODID);

    public static final Supplier<AttachmentType<ShadowAttachment>> SHADOW_ATTACH = ATTACHMENT_TYPES.register(
            "shadow", () -> AttachmentType.builder(ShadowAttachment::new).build());
}
