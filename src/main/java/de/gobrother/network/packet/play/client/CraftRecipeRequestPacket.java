package de.gobrother.network.packet.play.client;

import de.gobrother.network.packet.Packet;

public class CraftRecipeRequestPacket extends Packet {

    @Field( 0 )
    public byte windowID;

    @Field( 1 )
    public int recipe;

    @Field( 2 )
    public boolean makeAll;
}
