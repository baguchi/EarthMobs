package baguchan.earthmobsmod.message;

import baguchan.earthmobsmod.api.IMuddy;
import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.Entity;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

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

    public static void handle(MudMessage packet, Supplier<NetworkEvent.Context> context) {
        if (context.get().getDirection().getReceptionSide() == LogicalSide.CLIENT) {
            context.get().enqueueWork(() -> {
                Entity entity = Minecraft.getInstance().level.getEntity(packet.entityId);
                if (entity instanceof IMuddy imoss) {
                    imoss.setMuddy(packet.muddy);
                    imoss.setColorData(packet.colorData);
                }
            });
        }
        context.get().setPacketHandled(true);
    }
}