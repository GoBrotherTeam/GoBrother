package de.gobrother.network;

import de.gobrother.GoBrother;
import de.gobrother.network.packet.Packet;
import de.gobrother.network.packet.Protocols;
import de.gobrother.network.packet.handshaking.HandshakePacket;
import de.gobrother.network.packet.login.client.EncryptionResponsePacket;
import de.gobrother.network.packet.login.client.LoginStartPacket;
import de.gobrother.network.packet.login.server.EncryptionRequestPacket;
import de.gobrother.network.packet.login.server.LoginSuccessPacket;
import de.gobrother.network.packet.login.server.SetCompressionPacket;
import de.gobrother.network.packet.play.server.JoinGamePacket;
import de.gobrother.network.packet.play.server.PlayerAbilitiesPacket;
import de.gobrother.network.packet.play.server.SpawnPositionPacket;
import de.gobrother.network.packet.status.client.PingPacket;
import de.gobrother.network.packet.status.client.RequestPacket;
import de.gobrother.network.packet.status.server.PongPacket;
import de.gobrother.network.packet.status.server.ResponsePacket;
import io.gomint.GoMint;
import lombok.Getter;
import lombok.Setter;
import org.json.JSONObject;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.util.Arrays;
import java.util.Random;

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

                while (isRunning) {
                    try {
                        Socket socket = serverSocket.accept();

                        new Thread(() -> {
                            try {
                                Server.this.handle(socket);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }).start();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }

            } catch (IOException e) {
                plugin.getLogger().warn("Konnte GoBrother Server nicht auf dem Port " + port + " binden");
                e.printStackTrace();
            } finally {
                try {
                    if (serverSocket != null) serverSocket.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        this.serverThread.start();
    }

    private void handle(Socket socket) throws IOException {
        String username = "";
        String serverAddress = "";
        String id = "";

        Packet.McInputStream input = new Packet.McInputStream(socket.getInputStream());
        Packet.McOutputStream output = new Packet.McOutputStream(socket.getOutputStream());

        Packet.Protocol protocol = Protocols.handshakeProtocol();
        Packet packet = input.readPacket(protocol);

        if (((HandshakePacket) packet).nextState == 1) {
            serverAddress = ((HandshakePacket) packet).serverAddress;
            try {
                protocol = Protocols.statusProtocol();

                while (!socket.isClosed()) {
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
            } catch (Exception e) {
            }
        } else {
            try {
                protocol = Protocols.loginProtocol();
                byte[] verifytoken = new byte[4];
                new Random().nextBytes(verifytoken);

                while (!socket.isClosed()) {
                    packet = input.readPacket(protocol);

                    if (packet instanceof LoginStartPacket) {
                        username = ((LoginStartPacket) packet).name;

                        EncryptionRequestPacket encryptionRequestPacket = new EncryptionRequestPacket();
                        encryptionRequestPacket.serverId = "";
                        encryptionRequestPacket.publicKey = Packet.RSAPublicKey.getEncoded();
                        encryptionRequestPacket.verifyToken = verifytoken;

                        output.writePacket(encryptionRequestPacket, protocol);
                    } else if (packet instanceof EncryptionResponsePacket) {
                        Cipher cipher = Cipher.getInstance("RSA");
                        cipher.init(Cipher.PRIVATE_KEY, Packet.RSAPrivateKey);
                        byte[] key = cipher.doFinal(((EncryptionResponsePacket) packet).sharedSecret);
                        SecretKey sk = new SecretKeySpec(key, "AES");

                        Cipher tmp = Cipher.getInstance("RSA");
                        tmp.init(Cipher.PRIVATE_KEY, Packet.RSAPrivateKey);
                        if (!Arrays.equals(verifytoken, tmp.doFinal(((EncryptionResponsePacket) packet).verifyToken))) {
                            socket.close();
                            return;
                        }

                        String hash = Packet.getServerIdHash("", sk);
                        HttpURLConnection connection = (HttpURLConnection) new URL("https://sessionserver.mojang.com/session/minecraft/hasJoined?username=" + URLEncoder.encode(username) + "&serverId=" + hash + "&ip=" + URLEncoder.encode(serverAddress)).openConnection();
                        if (connection.getResponseCode() == 200) {
                            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                            String text = "";
                            String line;
                            while ((line = reader.readLine()) != null) {
                                text += line;
                            }

                            JSONObject data = new JSONObject(text);
                            id = data.getString("id");
                            id = id.substring(0, 8) + "-" + id.substring(8, 12) + "-" + id.substring(12, 16) + "-" + id.substring(16, 20) + "-" + id.substring(20, 32);
                            username = data.getString("name");
                        } else {
                            socket.close();
                            return;
                        }

                        connection.disconnect();

                        Cipher readCipher = Cipher.getInstance("AES/CFB8/NoPadding");
                        readCipher.init(Cipher.DECRYPT_MODE, sk, new IvParameterSpec(key));
                        Cipher writeCipher = Cipher.getInstance("AES/CFB8/NoPadding");
                        writeCipher.init(Cipher.ENCRYPT_MODE, sk, new IvParameterSpec(key));

                        input = new Packet.McInputStream(new CipherInputStream(input, readCipher));
                        output = new Packet.McOutputStream(new CipherOutputStream(output, writeCipher));

                        SetCompressionPacket setCompressionPacket = new SetCompressionPacket();
                        setCompressionPacket.threshold = 256;
                        output.writePacket(setCompressionPacket, protocol);
                        protocol.setCompressionTreshold(256);

                        LoginSuccessPacket loginSuccessPacket = new LoginSuccessPacket();
                        loginSuccessPacket.username = username;
                        loginSuccessPacket.uuid = id;

                        output.writePacket(loginSuccessPacket, protocol);
                        protocol = Protocols.playProtocol();
                        protocol.setCompressionTreshold(256);
                        break;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }

            try {

                JoinGamePacket joinGamePacket = new JoinGamePacket();
                joinGamePacket.reducedDebugInfo = false;
                joinGamePacket.maxPlayers = plugin.getServer().getMaxPlayers();
                joinGamePacket.gamemode = 1;
                joinGamePacket.levelType = "default";
                joinGamePacket.dimension = 0;
                joinGamePacket.difficulty = 0;
                joinGamePacket.eid = 1;

                output.writePacket(joinGamePacket, protocol);

                SpawnPositionPacket spawnPositionPacket = new SpawnPositionPacket();
                spawnPositionPacket.x = (int) GoMint.instance().getDefaultWorld().getSpawnLocation().getX();
                spawnPositionPacket.y = (int) GoMint.instance().getDefaultWorld().getSpawnLocation().getY();
                spawnPositionPacket.z = (int) GoMint.instance().getDefaultWorld().getSpawnLocation().getZ();

                output.writePacket( spawnPositionPacket, protocol );

                PlayerAbilitiesPacket playerAbilitiesPacket = new PlayerAbilitiesPacket();
                playerAbilitiesPacket.flags = 0;
                playerAbilitiesPacket.flySpeed = (float) 0.05;
                playerAbilitiesPacket.walingSpeed = (float) 0.1;

                output.writePacket( playerAbilitiesPacket, protocol );

                while (!socket.isClosed()) {
                    packet = input.readPacket(protocol);

                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    public void stop() {
        plugin.getLogger().info("Stoppe GoBrother Server");
        setRunning(false);
        serverThread.stop();
    }
}
