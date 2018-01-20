package de.gobrother;

import de.gobrother.config.Config;
import de.gobrother.packets.Packet;
import de.gobrother.packets.Packet.*;
import de.gobrother.packets.Protocols;
import de.gobrother.packets.client.HandshakePacket;
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

    private Config config;
    private Thread serverThread;

    @Override
    public void onInstall() {
        getLogger().info("Starte GoBrother v" + getVersion().toString());

        File goBrotherDir = new File("plugins/GoBrother/");
        goBrotherDir.mkdirs();

        File configFile = new File("plugins/GoBrother/config.cfg");

        this.config = new Config();
        try {
            this.config.initialize(configFile);
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.serverThread = new Thread(new Runnable() {
            @Override
            public void run() {
                ServerSocket socket = null;
                try {
                    getLogger().info("Starte GoBrother Server auf dem Port " + config.getPort());
                    socket = new ServerSocket(config.getPort());

                    while(true) {
                        try{
                            Socket socket1 = socket.accept();

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

                        } catch (IOException ex){
                            ex.printStackTrace();
                        }
                    }

                } catch (IOException e) {
                    getLogger().warn("Konnte GoBrother Server nicht auf dem Port " + config.getPort() + " binden");
                    e.printStackTrace();
                } finally {
                    try {
                        socket.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        this.serverThread.start();
    }

}
