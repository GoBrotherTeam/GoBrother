package de.gobrother.network.packet;

import de.gobrother.network.packet.client.HandshakePacket;
import de.gobrother.network.packet.client.PingPacket;
import de.gobrother.network.packet.client.RequestPacket;
import de.gobrother.network.packet.server.PongPacket;
import de.gobrother.network.packet.server.ResponsePacket;

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

}
