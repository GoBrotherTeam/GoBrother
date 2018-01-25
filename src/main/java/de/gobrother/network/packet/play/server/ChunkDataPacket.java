package de.gobrother.network.packet.play.server;

import de.gobrother.network.packet.Packet;

public class ChunkDataPacket extends Packet {

    @Field( 0 )
    public int chunkX;

    @Field( 1 )
    public int chunkZ;

    @Field( 2 )
    public boolean groundUp;

    @Field( value = 3, type = Field.Type.VarInt)
    public int primaryBitMask;

    @Field( 4 )
    public String playload;

}
