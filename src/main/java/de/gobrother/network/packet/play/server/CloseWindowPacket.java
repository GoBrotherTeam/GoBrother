package de.gobrother.network.packet.play.server;

import de.gobrother.network.packet.Packet;

public class CloseWindowPacket extends Packet {

    @Field( value = 0 , type = Field.Type.UnsignedByte )
    public int windowID;

}
