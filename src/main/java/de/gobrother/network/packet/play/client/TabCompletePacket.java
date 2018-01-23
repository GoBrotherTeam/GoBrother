package de.gobrother.network.packet.play.client;

import de.gobrother.network.packet.Packet;

public class TabCompletePacket extends Packet {

    @Field( 0 )
    public String text;

    @Field( 1 )
    public boolean assumeCommand;

    @Field( 2 )
    public boolean hasPosition;

    @Field( value = 3, type = Field.Type.Position)
    public int x, y, z;

}
