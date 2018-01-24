package de.gobrother.network.packet.play.server;

import de.gobrother.network.packet.Packet;

public class AnimatePacket extends Packet {

    @Field( value = 0, type = Field.Type.VarInt)
    public int entityID;

    @Field( value = 1, type = Field.Type.UnsignedByte)
    public byte animation;

}
