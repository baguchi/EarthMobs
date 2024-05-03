package baguchan.earthmobsmod.message;

import baguchan.earthmobsmod.EarthMobsMod;
import baguchan.earthmobsmod.api.IMoss;
import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.world.entity.Entity;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import net.neoforged.neoforge.network.handling.IPayloadHandler;

public class MossMessage implements CustomPacketPayload, IPayloadHandler<MossMessage> {

    public static final StreamCodec<FriendlyByteBuf, MossMessage> STREAM_CODEC = CustomPacketPayload.codec(
            MossMessage::write, MossMessage::new
    );
    public static final CustomPacketPayload.Type<MossMessage> TYPE = CustomPacketPayload.createType(EarthMobsMod.prefix("moss").toString());

    private final int entityId;
    private final boolean moss;

    public MossMessage(int entityId, boolean moss) {
        this.entityId = entityId;
        this.moss = moss;
    }

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    public void write(FriendlyByteBuf buf) {
        buf.writeInt(this.entityId);
        buf.writeBoolean(this.moss);
    }

    public MossMessage(FriendlyByteBuf buf) {
        this(buf.readInt(), buf.readBoolean());
    }

    public void handle(MossMessage message, IPayloadContext context) {
        context.enqueueWork(() -> {
            Entity entity = Minecraft.getInstance().player.level().getEntity(message.entityId);
                if (entity instanceof IMoss imoss) {
                    imoss.setMoss(message.moss);
                }
            });
    }
}