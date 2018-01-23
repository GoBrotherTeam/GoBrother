package de.gobrother.network.packet.play.server;

import de.gobrother.network.packet.Packet;

public class KeepAlivePacket extends Packet {

    @Field(0)
    public long keepAliveId;

}
