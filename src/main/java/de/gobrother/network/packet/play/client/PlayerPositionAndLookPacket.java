package de.gobrother.network.packet.play.client;

import de.gobrother.network.packet.Packet;

public class PlayerPositionAndLookPacket extends Packet {

    @Field( 0 )
    public double x;

    @Field( 1 )
    public double feetY;

    @Field( 2 )
    public double z;

    @Field( 3 )
    public float yaw;

    @Field( 4 )
    public float pitch;

    @Field( 5 )
    public boolean isOnGround;

}
