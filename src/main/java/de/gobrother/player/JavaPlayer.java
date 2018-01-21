package de.gobrother.player;

import de.gobrother.network.packet.Packet;

import java.io.IOException;
import java.net.Socket;

public class JavaPlayer {

    private String address;
    private int port;

    private Socket socket;
    private Packet.McInputStream inputStream;
    private Packet.McOutputStream outputStream;

    private int status;

    public JavaPlayer(Socket socket) {
        this.status = 0;

        this.socket = socket;

        try {
            this.inputStream = new Packet.McInputStream(socket.getInputStream());
            this.outputStream = new Packet.McOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.address = socket.getInetAddress().getHostName();
        this.port = socket.getPort();
    }

    public void proccess() {

    }

}
