package de.gobrother.network.packet;

import de.gobrother.network.packet.handshaking.HandshakePacket;
import de.gobrother.network.packet.login.client.EncryptionResponsePacket;
import de.gobrother.network.packet.login.client.LoginStartPacket;
import de.gobrother.network.packet.login.server.DisconnectPacket;
import de.gobrother.network.packet.login.server.EncryptionRequestPacket;
import de.gobrother.network.packet.login.server.LoginSuccessPacket;
import de.gobrother.network.packet.login.server.SetCompressionPacket;
import de.gobrother.network.packet.play.client.*;
import de.gobrother.network.packet.play.client.AnimatePacket;
import de.gobrother.network.packet.play.client.ChatPacket;
import de.gobrother.network.packet.play.client.CloseWindowPacket;
import de.gobrother.network.packet.play.client.ConfirmTransactionPacket;
import de.gobrother.network.packet.play.client.KeepAlivePacket;
import de.gobrother.network.packet.play.server.*;
import de.gobrother.network.packet.play.server.PlayerAbilitiesPacket;
import de.gobrother.network.packet.status.client.PingPacket;
import de.gobrother.network.packet.status.client.RequestPacket;
import de.gobrother.network.packet.status.server.PongPacket;
import de.gobrother.network.packet.status.server.ResponsePacket;

public class Protocols {

    public static Packet.Protocol handshakeProtocol() {
        return new Packet.Protocol().registerClientPacket(0, HandshakePacket.class);
    }

    public static Packet.Protocol statusProtocol() {
        return new Packet.Protocol().registerClientPacket(0, RequestPacket.class).
                registerServerPacket(0, ResponsePacket.class).
                registerClientPacket(1, PingPacket.class).
                registerServerPacket(1, PongPacket.class);
    }

    public static Packet.Protocol loginProtocol() {
        return new Packet.Protocol().registerServerPacket(0, DisconnectPacket.class).
                registerServerPacket(1, EncryptionRequestPacket.class).
                registerServerPacket(2, LoginSuccessPacket.class).
                registerServerPacket(3, SetCompressionPacket.class).
                registerClientPacket(0, LoginStartPacket.class).
                registerClientPacket(1, EncryptionResponsePacket.class);
    }

    public static Packet.Protocol playProtocol() {
        return new Packet.Protocol().registerServerPacket(0x23, JoinGamePacket.class).
                registerServerPacket( 0x46, SpawnPositionPacket.class ).
                registerServerPacket( 0x2C, de.gobrother.network.packet.play.server.PlayerAbilitiesPacket.class ).
                registerClientPacket( 0x13, de.gobrother.network.packet.play.client.PlayerAbilitiesPacket.class ).

                registerServerPacket(0x1F, de.gobrother.network.packet.play.server.KeepAlivePacket.class).
                registerClientPacket(0x0B, de.gobrother.network.packet.play.client.KeepAlivePacket.class).
                registerClientPacket( 0x37, AdvancementTabPacket.class ).

                registerClientPacket( 0x1D, de.gobrother.network.packet.play.client.AnimatePacket.class).
                registerServerPacket( 0x06, de.gobrother.network.packet.play.server.AnimatePacket.class ).

                registerServerPacket( 0x0F, de.gobrother.network.packet.play.server.ChatPacket.class ).
                registerClientPacket( 0x02, de.gobrother.network.packet.play.client.ChatPacket.class ).

                registerClientPacket( 0x07, ClickWindowPacket.class ).
                registerClientPacket( 0x04, ClientSettingsPacket.class ).
                registerClientPacket( 0x03, ClientStatusPacket.class ).

                registerServerPacket( 0x12, de.gobrother.network.packet.play.server.CloseWindowPacket.class ).
                registerClientPacket( 0x08, de.gobrother.network.packet.play.client.CloseWindowPacket.class ).

                registerServerPacket( 0x11, de.gobrother.network.packet.play.server.ConfirmTransactionPacket.class ).
                registerClientPacket( 0x05, de.gobrother.network.packet.play.client.ConfirmTransactionPacket.class ).
                registerClientPacket( 0x12, CraftRecipeRequestPacket.class ).
                registerClientPacket( 0x1B, CreativeInventoryActionPacket.class ).
                registerServerPacket( 0x06, EnchantItemPacket.class ).
                registerClientPacket( 0x15, EntityActionPacket.class ).
                registerClientPacket( 0x1A, HeldItemChangePacket.class ).
                registerClientPacket( 0x1F, PlayerBlockPlacementPacket.class ).
                registerClientPacket( 0x14, PlayerDiggingPacket.class ).
                registerClientPacket( 0x0F, PlayerLookPacket.class ).
                registerClientPacket( 0x0C, PlayerPacket.class ).
                registerClientPacket( 0x0E, PlayerPositionAndLookPacket.class ).
                registerClientPacket( 0x0D, PlayerPositionPacket.class ).
                registerClientPacket( 0x01, TabCompletePacket.class ).
                registerClientPacket( 0x00, TeleportConfirmPacket.class ).
                registerClientPacket( 0x1C, UpdateSignPacket.class ).
                registerClientPacket( 0x0A, UseEntityPacket.class ).
                registerClientPacket( 0x20, UseItemPacket.class ).
                registerServerPacket( 0x0A, BlockActionPacket.class ).
                registerServerPacket( 0x08, BlockBreakAnimationPacket.class ).
                registerServerPacket( 0x0B, BlockChangePacket.class ).
                registerServerPacket( 0x1E, ChangeGameStatePacket.class ).
                registerServerPacket( 0x20, ChunkDataPacket.class );

    }

}
