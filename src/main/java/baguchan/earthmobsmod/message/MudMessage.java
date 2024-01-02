package baguchan.earthmobsmod.message;

import baguchan.earthmobsmod.EarthMobsMod;
import baguchan.earthmobsmod.api.IMuddyPig;
import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.neoforged.neoforge.network.handling.PlayPayloadContext;

public class MudMessage implements CustomPacketPayload {
    public static final ResourceLocation ID = EarthMobsMod.prefix("moss");
    private final int entityId;
    private final boolean muddy;
    private final byte colorData;

    public MudMessage(int entityId, boolean muddy, byte colorData) {
        this.entityId = entityId;
        this.muddy = muddy;
        this.colorData = colorData;
    }

    @Override
    public ResourceLocation id() {
        return ID;
    }

    public void write(FriendlyByteBuf buf) {
        buf.writeInt(this.entityId);
        buf.writeBoolean(this.muddy);
        buf.writeByte(this.colorData);
    }

    public MudMessage(FriendlyByteBuf buf) {
        this(buf.readInt(), buf.readBoolean(), buf.readByte());
    }

    public static void handle(MudMessage message, PlayPayloadContext context) {
        context.workHandler().execute(() -> {
            Entity entity = Minecraft.getInstance().level.getEntity(message.entityId);
                if (entity instanceof IMuddyPig imoss) {
                    imoss.setMuddy(message.muddy);
                    imoss.setColorData(message.colorData);
                }
            });

    }
}