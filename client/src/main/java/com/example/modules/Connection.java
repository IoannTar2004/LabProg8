package com.example.modules;

import java.io.IOException;
import java.net.Socket;
import java.nio.channels.UnresolvedAddressException;

public class Connection {
    private String host;
    private int port;
    private Socket socket;

    public Connection(String host, int port) {
        this.host = host;
        this.port = port;
    }

    /**
     * Run endless tries to connect to server
     * @return open and return socket channel
     * @throws UnresolvedAddressException when host is unknown
     */
    public void waitingForConnection() throws UnresolvedAddressException {
        while (true) {
            try {
                socket = new Socket(host, port);
                return;
            } catch (IOException ignored) {}
        }
    }
}
