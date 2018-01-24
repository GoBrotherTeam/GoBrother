package de.gobrother.network.packet.play.server;

import de.gobrother.network.packet.Packet;

import java.io.IOException;

public class SpawnPositionPacket extends Packet {

    @Field(value = 0, type = Field.Type.Position)
    public int x;
    public int y;
    public int z;

    @WriterMethod(Field.Type.Position)
    private void writePosition(McOutputStream output, int x) throws IOException {
        output.writeLong( ((x & 0x3FFFFFF) << 38) | ((y & 0xFFF) << 26) | (z & 0x3FFFFFF));
    }

    @ReaderMethod(Field.Type.Position)
    private long readPosition(McInputStream input) throws IOException {
        long val = input.readLong();

        int x = (int) (val >> 38);
        int y = (int) ((val >> 26) & 0xFFF);
        int z = (int) (val << 38 >> 38);

        return x | y | z;
    }

}
