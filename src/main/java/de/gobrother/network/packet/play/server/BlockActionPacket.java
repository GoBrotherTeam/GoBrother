package de.gobrother.network.packet.play.server;

import de.gobrother.network.packet.Packet;

public class BlockActionPacket extends Packet {

    @Field( value = 0, type = Field.Type.Position)
    public int x, y, z;

    @Field( value = 1, type = Field.Type.UnsignedByte)
    public byte actionID;

    @Field( value = 2, type = Field.Type.UnsignedByte)
    public byte actionParam;

    @Field( value = 3, type = Field.Type.VarInt)
    public int blockType;

}
