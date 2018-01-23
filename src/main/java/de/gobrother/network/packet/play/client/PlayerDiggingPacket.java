package de.gobrother.network.packet.play.client;

import de.gobrother.network.packet.Packet;

public class PlayerDiggingPacket extends Packet {

    @Field( 0 )
    public int status;

    @Field( value = 1, type = Field.Type.Position)
    public int x, y, z;

    @Field( 2 )
    public byte face;

}
