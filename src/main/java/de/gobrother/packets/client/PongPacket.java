package de.gobrother.packets.client;

import de.gobrother.packets.Packet;

public class PongPacket extends Packet {

    @Field(0)
    public int payload;

}
