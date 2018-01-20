package de.gobrother.packets.server;

import de.gobrother.packets.Packet;

public class JoinGamePacket extends Packet {

    @Field(value = 0)
    public int entityId;

    @Field(value = 1, type = Field.Type.UnsignedByte)
    public int gamemode;

    @Field(value = 2)
    public byte dimension;

    @Field(value = 3, type = Field.Type.UnsignedByte)
    public int difficulty;

    @Field(value = 4, type = Field.Type.UnsignedByte)
    public int maxPlayers;

    @Field(value = 5)
    public String levelType;

    @Field(value = 6)
    public boolean reducedDebugInfo;

}
