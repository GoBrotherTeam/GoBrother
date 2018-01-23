package de.gobrother.network.packet.play.client;

import de.gobrother.network.packet.Packet;
import io.gomint.server.inventory.item.ItemStack;

public class ClickWindowPacket extends Packet {

    @Field( value = 0, type = Field.Type.UnsignedByte)
    public int windowID;

    @Field( 1 )
    public short slot;

    @Field( 2 )
    public byte button;

    @Field( 3 )
    public short actionNumber;

    @Field( value = 4 , type = Field.Type.VarInt)
    public int mode;

    // Könnte ein Fehler veruhrsachen

    @Field( 5 )
    public ItemStack clieckedItem;

}
