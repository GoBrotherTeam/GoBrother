package de.gobrother.network.packet.play.client;

import de.gobrother.network.packet.Packet;

public class ChatPacket extends Packet {

    @Field( 0 )
    public String message;

}
