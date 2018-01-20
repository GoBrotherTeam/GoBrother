package de.kcodeyt;

import com.google.gson.Gson;
import de.kcodeyt.packets.Packet;
import de.kcodeyt.packets.Packet.*;
import de.kcodeyt.packets.Protocols;
import de.kcodeyt.packets.handshake.HandshakePacket;
import de.kcodeyt.utils.Binary;
import io.gomint.plugin.Plugin;
import io.gomint.plugin.PluginName;
import io.gomint.plugin.Version;

import javax.xml.crypto.Data;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@PluginName("GoBrother")
@Version(major = 1, minor = 0)
public class GoBrother extends Plugin {

    public static void main(String... args) {
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
                        String desc = "{\"description\":{\"text\":\"AMK\"},\"players\":{\"max\":20,\"online\":1,\"sample\":[]},\"version\":{\"name\":\"1.12.2\",\"protocol\":340}}";
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
