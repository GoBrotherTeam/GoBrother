package de.gobrother.packets.server;

import de.gobrother.packets.Packet;

public class ResponsePacket extends Packet {

    @Field(0)
    public String jsonResponse;

}
