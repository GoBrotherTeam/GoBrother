package de.gobrother.network.packet.play.client;

import de.gobrother.network.packet.Packet;

public class ClientSettingsPacket extends Packet {

    @Field( 0 )
    public String locale;

    @Field( 1 )
    public byte viewDistance;

    @Field( value = 2 , type = Field.Type.VarInt )
    public int chatMode;

    @Field( 3 )
    public boolean chatColors;

    @Field( value = 4, type = Field.Type.UnsignedByte)
    public int displayedSkinParts;

    @Field( value = 5, type = Field.Type.VarInt )
    public int mainHand;

}
