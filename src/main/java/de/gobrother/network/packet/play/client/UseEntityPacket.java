package de.gobrother.network.packet.play.client;

import de.gobrother.network.packet.Packet;

public class UseEntityPacket extends Packet {

    @Field( value = 0, type = Field.Type.VarInt)
    public int target;

    @Field( value = 1, type = Field.Type.VarInt)
    public int type;

    @Field( 2 )
    public float x;

    @Field( 3 )
    public float y;

    @Field( 4 )
    public float z;

    @Field( value = 5, type = Field.Type.VarInt)
    public int hand;

}
