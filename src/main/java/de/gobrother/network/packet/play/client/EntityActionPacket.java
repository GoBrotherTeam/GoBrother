package de.gobrother.network.packet.play.client;

import de.gobrother.network.packet.Packet;

public class EntityActionPacket extends Packet {

    @Field( 0 )
    public int entityID;

    @Field( 1 )
    public int actionID;

    @Field( 2 )
    public int jumpBoost;

}
