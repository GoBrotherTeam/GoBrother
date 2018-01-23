package de.gobrother.network.packet.play.server;

import de.gobrother.network.packet.Packet;

public class EnchantItemPacket extends Packet {

    @Field( 0 )
    public byte windowID;

    @Field( 1 )
    public byte enchantment;
}
