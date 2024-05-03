package baguchan.earthmobsmod.message;

import baguchan.earthmobsmod.EarthMobsMod;
import baguchan.earthmobsmod.api.IMuddyPig;
import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.world.entity.Entity;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import net.neoforged.neoforge.network.handling.IPayloadHandler;

public class MudMessage implements CustomPacketPayload, IPayloadHandler<MudMessage> {

    public static final StreamCodec<FriendlyByteBuf, MudMessage> STREAM_CODEC = CustomPacketPayload.codec(
            MudMessage::write, MudMessage::new
    );
    public static final CustomPacketPayload.Type<MudMessage> TYPE = CustomPacketPayload.createType(EarthMobsMod.prefix("mud").toString());

    private final int entityId;
    private final boolean muddy;
    private final byte colorData;

    public MudMessage(int entityId, boolean muddy, byte colorData) {
        this.entityId = entityId;
        this.muddy = muddy;
        this.colorData = colorData;
    }

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    public void write(FriendlyByteBuf buf) {
        buf.writeInt(this.entityId);
        buf.writeBoolean(this.muddy);
        buf.writeByte(this.colorData);
    }

    public MudMessage(FriendlyByteBuf buf) {
        this(buf.readInt(), buf.readBoolean(), buf.readByte());
    }

    public void handle(MudMessage message, IPayloadContext context) {
        context.enqueueWork(() -> {
            Entity entity = Minecraft.getInstance().level.getEntity(message.entityId);
                if (entity instanceof IMuddyPig imoss) {
                    imoss.setMuddy(message.muddy);
                    imoss.setColorData(message.colorData);
                }
            });

    }
}