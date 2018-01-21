package de.gobrother.network.packet.login.server;

import de.gobrother.network.packet.Packet;

public class EncryptionRequestPacket extends Packet {

    @Field(0)
    public String serverId;

    @Field(1)
    public byte[] publicKey;

    @Field(2)
    public byte[] verifyToken;

}
