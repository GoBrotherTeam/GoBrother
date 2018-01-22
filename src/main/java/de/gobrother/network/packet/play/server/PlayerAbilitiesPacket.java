package de.gobrother.network.packet.play.server;

import de.gobrother.network.packet.Packet;

public class PlayerAbilitiesPacket extends Packet {

    @Field( 0 )
    public byte flags;

    @Field( 1 )
    public float flySpeed;

    @Field( 2 )
    public float walingSpeed;

}
