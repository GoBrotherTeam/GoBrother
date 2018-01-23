package de.gobrother.network.packet.play.client;

import de.gobrother.network.packet.Packet;

public class HeldItemChangePacket extends Packet {

    @Field( 0 )
    public short slot;

}
