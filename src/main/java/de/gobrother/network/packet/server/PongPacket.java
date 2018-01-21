package de.gobrother.network.packet.server;

import de.gobrother.network.packet.Packet;

public class PongPacket extends Packet {

    @Field(0)
    public long payload;

}
