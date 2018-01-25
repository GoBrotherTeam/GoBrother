package de.gobrother.network.packet.play.server;

import de.gobrother.network.packet.Packet;

import java.io.IOException;

public class PlayerAbilitiesPacket extends Packet {

    @Field( 0 )
    public byte flags = 0;

    @Field( 1 )
    public float flySpeed;

    @Field( 2 )
    public float walingSpeed;

    public boolean damageDisabled;
    public boolean canFly;
    public boolean isFlying = false;
    public boolean isCreative;


    @WriterMethod( Field.Type.Flags )
    private void writeFlags(McOutputStream output) throws IOException {

        byte isCreative = (byte) (this.isCreative ? 0x01 : 0);
        byte isFlying = (byte) (this.isFlying ? 0x02 : 0);
        byte canFly = (byte) (this.canFly ? 0x04 : 0);
        byte damageDisabled = (byte) (this.damageDisabled ? 0x08 : 0);

        output.writeByte( ((isCreative == 0x01 ? 0x01 : 0) | (isFlying == 0x02 ? 0x02 : 0) | (canFly == 0x04 ? 0x04 : 0) | (damageDisabled == 0x08 ? 0x08 : 0)) );
    }


}
