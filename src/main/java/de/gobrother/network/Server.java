package de.gobrother.network;

import de.gobrother.GoBrother;
import de.gobrother.network.packet.Packet;
import de.gobrother.network.packet.Protocols;
import de.gobrother.network.packet.client.HandshakePacket;
import de.gobrother.network.packet.client.PingPacket;
import de.gobrother.network.packet.client.RequestPacket;
import de.gobrother.network.packet.server.PongPacket;
import de.gobrother.network.packet.server.ResponsePacket;
import de.gobrother.utils.Binary;
import lombok.Getter;
import lombok.Setter;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    private Thread serverThread;

    @Getter
    @Setter
    private boolean isRunning;

    private GoBrother plugin;
    private int port;

    public Server(GoBrother plugin, int port) {
        this.plugin = plugin;
        this.port = port;

        this.isRunning = false;
    }

    public void start() {
        setRunning(true);

        this.serverThread = new Thread(() -> {
            ServerSocket serverSocket = null;
            try {
                plugin.getLogger().info("Starte GoBrother Server auf dem Port " + port);
                serverSocket = new ServerSocket(port);

                while(isRunning) {
                    try {
                        Socket socket = serverSocket.accept();

                        Packet.McInputStream input = new Packet.McInputStream(socket.getInputStream());
                        Packet.McOutputStream output = new Packet.McOutputStream(socket.getOutputStream());

                        Packet.Protocol protocol = Protocols.handshakeProtocol();
                        Packet packet = input.readPacket(protocol);

                        if(((HandshakePacket) packet).nextState == 1) {
                            try {
                                protocol = Protocols.statusProtocol();
                                while(!socket.isClosed()) {
                                    packet = input.readPacket(protocol);

                                    if (packet instanceof PingPacket) {
                                        PongPacket pongPacket = new PongPacket();
                                        pongPacket.payload = ((PingPacket) packet).payload;

                                        output.writePacket(pongPacket, protocol);
                                    } else if (packet instanceof RequestPacket) {
                                        ResponsePacket responsePacket = new ResponsePacket();
                                        responsePacket.jsonResponse = "{\"description\":{\"text\":\"" +
                                                plugin.getServer().getMotd() + "\"},\"players\":{\"max\":" +
                                                plugin.getServer().getMaxPlayers() + ",\"online\":" +
                                                plugin.getServer().getPlayers().size() + ",\"sample\":[]},\"version\":{\"name\":\"" +
                                                GoBrother.MCPC_VERSION + "\",\"protocol\":" + GoBrother.MCPC_PROTOCOL + "}}";

                                        output.writePacket(responsePacket, protocol);
                                    }
                                }
                            } catch (Exception e) { }
                        } else {

                        }

                    } catch (IOException ex){
                        ex.printStackTrace();
                    }
                }

            } catch (IOException e) {
                plugin.getLogger().warn("Konnte GoBrother Server nicht auf dem Port " + port + " binden");
                e.printStackTrace();
            } finally {
                try {
                    if(serverSocket != null) serverSocket.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        this.serverThread.start();
    }

    public void stop() {
        plugin.getLogger().info("Stoppe GoBrother Server");
        setRunning(false);
        serverThread.stop();
    }
}
