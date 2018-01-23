package de.gobrother.network.packet.play.client;

import de.gobrother.network.packet.Packet;

public class PlayerLookPacket extends Packet {

    @Field( 0 )
    public float yaw;

    @Field( 1 )
    public float pitch;

    @Field( 2 )
    public boolean isOnGround;
}
