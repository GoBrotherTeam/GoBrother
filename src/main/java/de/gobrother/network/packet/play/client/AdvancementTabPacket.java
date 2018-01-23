package de.gobrother.network.packet.play.client;

import de.gobrother.network.packet.Packet;

public class AdvancementTabPacket extends Packet {

    @Field( 0 )
    public boolean hasID;

    @Field( 1 )
    public String identifier;

}
