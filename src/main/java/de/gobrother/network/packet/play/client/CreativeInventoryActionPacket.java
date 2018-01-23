package de.gobrother.network.packet.play.client;

import de.gobrother.network.packet.Packet;
import io.gomint.server.inventory.item.ItemStack;

public class CreativeInventoryActionPacket extends Packet {

    @Field( 0 )
    public short slot;

    @Field( 1 )
    public ItemStack itemStack;
}
