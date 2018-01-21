package de.gobrother.network.packet.login.server;

import de.gobrother.network.packet.Packet;

public class LoginSuccessPacket extends Packet {

    @Field(0)
    public String uuid;

    @Field(1)
    public String username;

}
