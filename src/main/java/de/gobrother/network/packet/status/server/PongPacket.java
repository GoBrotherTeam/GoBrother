package de.gobrother.network.packet.status.server;

import de.gobrother.network.packet.Packet;

public class PongPacket extends Packet {

    @Field(0)
    public long payload;

}
