package de.gobrother.network.packet.play.client;

import de.gobrother.network.packet.Packet;

import java.io.IOException;

public class PlayerAbilitiesPacket extends Packet {

    @Field( 0 )
    public byte flags;

    @Field( 1 )
    public float flySpeed;

    @Field( 2 )
    public float walingSpeed;

    public boolean damageDisabled;
    public boolean canFly;
    public boolean isFlying = false;
    public boolean isCreative;

    @ReaderMethod( Field.Type.Flags )
    private byte readFlags(McInputStream input) throws IOException {
        byte flags = input.readByte();
        return (byte) ((flags == 0x01 ? 0x01 : 0) | (flags == 0x02 ? 0x02 : 0) | (flags == 0x04 ? 0x04 : 0) | (flags == 0x08 ? 0x08 : 0) );
    }

}
