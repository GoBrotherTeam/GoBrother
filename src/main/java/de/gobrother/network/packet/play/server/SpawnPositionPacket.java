package de.gobrother.network.packet.play.server;

import de.gobrother.network.packet.Packet;

public class SpawnPositionPacket extends Packet {

    @Field(0)
    public int x, y ,z;

}
