package de.gobrother.network.packet;

import de.gobrother.network.packet.handshaking.HandshakePacket;
import de.gobrother.network.packet.login.client.EncryptionResponsePacket;
import de.gobrother.network.packet.login.client.LoginStartPacket;
import de.gobrother.network.packet.login.server.DisconnectPacket;
import de.gobrother.network.packet.login.server.EncryptionRequestPacket;
import de.gobrother.network.packet.login.server.LoginSuccessPacket;
import de.gobrother.network.packet.login.server.SetCompressionPacket;
import de.gobrother.network.packet.play.server.JoinGamePacket;
import de.gobrother.network.packet.play.server.PlayerAbilitiesPacket;
import de.gobrother.network.packet.play.server.SpawnPositionPacket;
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
                registerServerPacket( 0x13, PlayerAbilitiesPacket.class );
    }

}
