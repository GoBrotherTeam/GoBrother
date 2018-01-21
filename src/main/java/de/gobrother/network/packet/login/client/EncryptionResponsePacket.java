package de.gobrother.network.packet.login.client;

import de.gobrother.network.packet.Packet;

public class EncryptionResponsePacket extends Packet {

    @Field(0)
    public byte[] sharedSecret;

    @Field(1)
    public byte[] verifyToken;

}
