package de.kcodeyt.packets.status.server;

import de.kcodeyt.packets.Packet;
import de.kcodeyt.packets.Packet.*;

public class ResponsePacket extends Packet {

    @Field(0)
    public String jsonResponse;

}
