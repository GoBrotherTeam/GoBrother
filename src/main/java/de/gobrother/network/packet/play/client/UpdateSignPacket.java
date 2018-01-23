package de.gobrother.network.packet.play.client;

import de.gobrother.network.packet.Packet;

public class UpdateSignPacket extends Packet {

    @Field( value = 0, type = Field.Type.VarInt)
    public int x, y, z;

    @Field( 1 )
    public String line1;

    @Field( 2 )
    public String line2;

    @Field( 3 )
    public String line3;

    @Field( 4 )
    public String line4;

}
