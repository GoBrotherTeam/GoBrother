package de.gobrother.network.packet.play.client;

import de.gobrother.network.packet.Packet;

public class PlayerPacket extends Packet {

    @Field( 0 )
    public boolean isOnGround;

}
