package de.gobrother.network.packet.play.server;

import de.gobrother.network.packet.Packet;

public class ChatPacket extends Packet {

    @Field( 0 )
    public String message;

    @Field( 1 )
    public byte position;

}
