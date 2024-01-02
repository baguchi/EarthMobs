package baguchan.earthmobsmod.message;

import baguchan.earthmobsmod.EarthMobsMod;
import baguchan.earthmobsmod.api.IMoss;
import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.neoforged.neoforge.network.handling.PlayPayloadContext;

public class MossMessage implements CustomPacketPayload {
    public static final ResourceLocation ID = EarthMobsMod.prefix("moss");

    private final int entityId;
    private final boolean moss;

    public MossMessage(int entityId, boolean moss) {
        this.entityId = entityId;
        this.moss = moss;
    }

    @Override
    public ResourceLocation id() {
        return ID;
    }

    public void write(FriendlyByteBuf buf) {
        buf.writeInt(this.entityId);
        buf.writeBoolean(this.moss);
    }

    public MossMessage(FriendlyByteBuf buf) {
        this(buf.readInt(), buf.readBoolean());
    }

    public static void handle(MossMessage message, PlayPayloadContext context) {
        context.workHandler().execute(() -> {
            Entity entity = Minecraft.getInstance().player.level().getEntity(message.entityId);
                if (entity instanceof IMoss imoss) {
                    imoss.setMoss(message.moss);
                }
            });
    }
}