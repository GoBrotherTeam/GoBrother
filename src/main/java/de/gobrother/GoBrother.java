package de.gobrother;

import de.gobrother.packets.Packet;
import de.gobrother.packets.Packet.*;
import de.gobrother.packets.Protocols;
import de.gobrother.packets.handshake.HandshakePacket;
import de.gobrother.utils.Binary;
import io.gomint.plugin.Plugin;
import io.gomint.plugin.PluginName;
import io.gomint.plugin.Version;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

@PluginName("GoBrother")
@Version(major = 1, minor = 0)
public class GoBrother extends Plugin {

    @Override
    public void onInstall() {
        ServerSocket socket = null;
        try {
            socket = new ServerSocket(25565);
            while(true) {
                try{
                    Socket socket1 = socket.accept();
                    if (socket1 == null) {
                        System.exit(0);
                    }

                    McInputStream input = new McInputStream(socket1.getInputStream());
                    McOutputStream output = new McOutputStream(socket1.getOutputStream());

                    Protocol protocol = Protocols.HandshakeProtocol();
                    Packet packet = input.readPacket(protocol);

                    if(((HandshakePacket) packet).nextState == 1) {
                        ByteArrayOutputStream tmp = new ByteArrayOutputStream();
                        new DataOutputStream(tmp).writeByte(0x00);
                        String desc = "{\"description\":{\"text\":\"" + getServer().getMotd() + "\"},\"players\":{\"max\":" + getServer().getMaxPlayers() + ",\"online\":" + getServer().getPlayers().size() + ",\"sample\":[]},\"version\":{\"name\":\"1.12.2\",\"protocol\":340}}";
                        Binary.writeVarInt(new DataOutputStream(tmp), desc.length());
                        new DataOutputStream(tmp).writeBytes(desc);

                        Binary.writeVarInt(output, tmp.size());
                        output.write(tmp.toByteArray());
                    } else {

                    }

                    //System.out.println(packet);

                } catch (Exception ex){
                    ex.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                socket.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

}
