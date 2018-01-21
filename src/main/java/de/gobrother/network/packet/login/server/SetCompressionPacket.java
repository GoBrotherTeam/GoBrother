package de.gobrother.network.packet.login.server;

import de.gobrother.network.packet.Packet;
import de.gobrother.network.packet.Packet.*;

public class SetCompressionPacket extends Packet {

    @Field(value = 0, type = Field.Type.VarInt)
    public int threshold;

}
