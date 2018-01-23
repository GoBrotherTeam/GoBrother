package de.gobrother.network.packet.play.client;

import de.gobrother.network.packet.Packet;

public class ClientStatusPacket extends Packet {

    @Field( 0 )
    public int actionID;

}
