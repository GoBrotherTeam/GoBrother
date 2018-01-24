package de.gobrother.network.packet.play.server;

import de.gobrother.network.packet.Packet;

public class ChangeGameStatePacket extends Packet {

    @Field( value = 0, type = Field.Type.UnsignedByte)
    public byte reason;

    @Field( 1 )
    public float value;

}
