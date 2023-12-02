package baguchan.earthmobsmod.message;

import baguchan.earthmobsmod.api.IMuddyPig;
import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.Entity;
import net.neoforged.fml.LogicalSide;
import net.neoforged.neoforge.network.NetworkEvent;

public class MudMessage {
    private final int entityId;
    private final boolean muddy;
    private final byte colorData;

    public MudMessage(int entityId, boolean muddy, byte colorData) {
        this.entityId = entityId;
        this.muddy = muddy;
        this.colorData = colorData;
    }

    public static void writeToPacket(MudMessage packet, FriendlyByteBuf buf) {
        buf.writeInt(packet.entityId);
        buf.writeBoolean(packet.muddy);
        buf.writeByte(packet.colorData);
    }

    public static MudMessage readFromPacket(FriendlyByteBuf buf) {
        return new MudMessage(buf.readInt(), buf.readBoolean(), buf.readByte());
    }

    public void handle(NetworkEvent.Context context) {
        if (context.getDirection().getReceptionSide() == LogicalSide.CLIENT) {
            context.enqueueWork(() -> {
                Entity entity = Minecraft.getInstance().level.getEntity(this.entityId);
                if (entity instanceof IMuddyPig imoss) {
                    imoss.setMuddy(muddy);
                    imoss.setColorData(colorData);
                }
            });
        }
        context.setPacketHandled(true);
    }
}