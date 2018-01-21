package de.gobrother.network.packet.login.client;

import de.gobrother.network.packet.Packet;

public class LoginStartPacket extends Packet {

    @Field(0)
    public String name;

}
