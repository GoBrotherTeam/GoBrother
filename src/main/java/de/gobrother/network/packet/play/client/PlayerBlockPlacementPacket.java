package de.gobrother.network.packet.play.client;

import de.gobrother.network.packet.Packet;

public class PlayerBlockPlacementPacket extends Packet {

    @Field( value = 0, type = Field.Type.Position)
    public int x, y, z;

    @Field( 1 )
    public int face;

    @Field( 2 )
    public int hand;

    @Field( 3 )
    public float cursorPositionX;

    @Field( 4 )
    public float cursorPositionY;

    @Field( 5 )
    public float cursorPositionZ;


}

