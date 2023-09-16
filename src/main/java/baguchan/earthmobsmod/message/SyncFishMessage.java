package baguchan.earthmobsmod.message;

import baguchan.earthmobsmod.entity.TropicalSlime;
import net.minecraft.client.Minecraft;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class SyncFishMessage {
    private int entityId;
    private CompoundTag compoundTag;

    public SyncFishMessage(Entity entity, CompoundTag compoundTag) {
        this.entityId = entity.getId();
        this.compoundTag = compoundTag;
    }

    public SyncFishMessage(int id, CompoundTag compoundTag) {
        this.entityId = id;
        this.compoundTag = compoundTag;
    }

    public void serialize(FriendlyByteBuf buffer) {
        buffer.writeInt(this.entityId);
        buffer.writeNbt(this.compoundTag);
    }

    public static SyncFishMessage deserialize(FriendlyByteBuf buffer) {
        int entityId = buffer.readInt();
        CompoundTag compoundTag = buffer.readNbt();

        return new SyncFishMessage(entityId, compoundTag);
    }

    public static boolean handle(SyncFishMessage message, Supplier<NetworkEvent.Context> contextSupplier) {
        NetworkEvent.Context context = contextSupplier.get();
        if (context.getDirection().getReceptionSide() == LogicalSide.CLIENT) {
            context.enqueueWork(() -> {
                Level level = Minecraft.getInstance().player.level();
                if (level == null) {
                    return;
                }
                Entity entity = level.getEntity(message.entityId);
                if (entity != null) {
                    if (entity instanceof TropicalSlime tropicalSlime) {
                        tropicalSlime.setFishData(message.compoundTag);
                    }
                }
            });
        }

        return true;
    }
}