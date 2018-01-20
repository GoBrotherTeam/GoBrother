package de.gobrother.packets;

import de.gobrother.packets.client.HandshakePacket;

public class Protocols {

    public static Packet.Protocol HandshakeProtocol() {
        return new Packet.Protocol().registerClientPacket(0, HandshakePacket.class);
    }

}
