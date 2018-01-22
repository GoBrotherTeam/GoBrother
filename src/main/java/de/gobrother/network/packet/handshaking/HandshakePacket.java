package de.gobrother.network.packet.handshaking;

import de.gobrother.network.packet.Packet;

public class HandshakePacket extends Packet {

    @Field(value = 0, type = Field.Type.VarInt)
    public int protocolVersion;

    @Field(1)
    public String serverAddress;

    @Field(value = 2, type = Field.Type.UnsignedShort)
    public int serverPort;

    @Field(value = 3, type = Field.Type.VarInt)
    public int nextState;

}
