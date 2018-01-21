package de.gobrother.network.packet.status.server;

import de.gobrother.network.packet.Packet;

public class ResponsePacket extends Packet {

    @Field(0)
    public String jsonResponse;

}
