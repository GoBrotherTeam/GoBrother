package de.gobrother.network.packet.server;

import de.gobrother.network.packet.Packet;

public class JoinGamePacket extends Packet {

    @Field(0)
    public int entityId;

    @Field(value = 1, type = Field.Type.UnsignedByte)
    public int gamemode;

    @Field(2)
    public byte dimension;

    @Field(value = 3, type = Field.Type.UnsignedByte)
    public int difficulty;

    @Field(value = 4, type = Field.Type.UnsignedByte)
    public int maxPlayers;

    @Field(5)
    public String levelType;

    @Field(6)
    public boolean reducedDebugInfo;

}
