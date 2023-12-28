package baguchan.earthmobsmod.message;

import baguchan.earthmobsmod.EarthMobsMod;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;

public class ModPackets {


    public static final String PROTOCOL_VERSION = "2";
    public static final SimpleChannel CHANNEL = NetworkRegistry.ChannelBuilder.named(
            EarthMobsMod.prefix("channel")
    ).networkProtocolVersion(() -> "2").clientAcceptedVersions("2"::equals).serverAcceptedVersions("2"::equals).simpleChannel();

    public static void setupMessages() {
        CHANNEL.messageBuilder(MudMessage.class, 0).decoder(MudMessage::readFromPacket).encoder(MudMessage::writeToPacket).consumerMainThread(MudMessage::handle).add();
        CHANNEL.messageBuilder(MossMessage.class, 1).decoder(MossMessage::readFromPacket).encoder(MossMessage::writeToPacket).consumerMainThread(MossMessage::handle).add();
    }
}