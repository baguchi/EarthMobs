package baguchan.earthmobsmod.message;

import baguchan.earthmobsmod.api.IMoss;
import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.Entity;
import net.neoforged.fml.LogicalSide;
import net.neoforged.neoforge.network.NetworkEvent;

public class MossMessage {
    private final int entityId;
    private final boolean moss;

    public MossMessage(int entityId, boolean moss) {
        this.entityId = entityId;
        this.moss = moss;
    }

    public static void writeToPacket(MossMessage packet, FriendlyByteBuf buf) {
        buf.writeInt(packet.entityId);
        buf.writeBoolean(packet.moss);
    }

    public static MossMessage readFromPacket(FriendlyByteBuf buf) {
        return new MossMessage(buf.readInt(), buf.readBoolean());
    }

    public void handle(NetworkEvent.Context context) {
        if (context.getDirection().getReceptionSide() == LogicalSide.CLIENT) {
            context.enqueueWork(() -> {
                Entity entity = Minecraft.getInstance().player.level().getEntity(this.entityId);
                if (entity instanceof IMoss imoss) {
                    imoss.setMoss(moss);
                }
            });
        }
        context.setPacketHandled(true);
    }
}