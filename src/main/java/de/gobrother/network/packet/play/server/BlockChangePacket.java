package de.gobrother.network.packet.play.server;

import de.gobrother.network.packet.Packet;

public class BlockChangePacket extends Packet {

    @Field( value = 0, type = Field.Type.Position)
    public int x, y, z;

    @Field( value = 1, type = Field.Type.VarInt)
    public int blockID;

}
