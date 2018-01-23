package de.gobrother.network.packet.play.client;

import de.gobrother.network.packet.Packet;

public class ConfirmTransactionPacket extends Packet {

    @Field( 0 )
    public byte windowID;

    @Field( 1 )
    public short actionNumber;

    @Field( 2 )
    public boolean accepted;

}
