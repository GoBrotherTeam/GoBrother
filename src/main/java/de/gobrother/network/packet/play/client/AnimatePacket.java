package de.gobrother.network.packet.play.client;

import de.gobrother.network.packet.Packet;

public class AnimatePacket extends Packet {

    @Field( value = 0, type = Field.Type.VarInt)
    public int hand;
}
