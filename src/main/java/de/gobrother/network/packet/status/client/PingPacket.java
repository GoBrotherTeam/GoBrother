package de.gobrother.network.packet.status.client;

import de.gobrother.network.packet.Packet;

public class PingPacket extends Packet {

    @Field(0)
    public long payload;

}