package de.gobrother.network.packet.play.client;

import de.gobrother.network.packet.Packet;

public class KeepAlivePacket extends Packet {

    @Field(0)
    public long keepAliveId;

}
