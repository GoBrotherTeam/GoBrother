package de.gobrother.network.packet.login.server;

import de.gobrother.network.packet.Packet;

public class DisconnectPacket extends Packet {

    @Field(0)
    public String reason;

}
