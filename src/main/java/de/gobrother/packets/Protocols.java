package de.gobrother.packets;

import de.gobrother.packets.client.HandshakePacket;
import de.gobrother.packets.client.PongPacket;

public class Protocols {

    public static Packet.Protocol handshakeProtocol() {
        return new Packet.Protocol().registerClientPacket(0, HandshakePacket.class);
    }

    public static Packet.Protocol pongProtocol() {
        return new Packet.Protocol().registerClientPacket(1, PongPacket.class);
    }

}
