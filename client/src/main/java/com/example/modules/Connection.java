package com.example.modules;

import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.atomic.AtomicBoolean;

public class Connection implements Runnable {
    private static volatile AtomicBoolean connection = new AtomicBoolean(true);

    private String host;
    private int port;
    private Socket socket;

    public Connection(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public static void stop() {
        connection.set(false);
    }

    /**
     * Run endless tries to connect to server
     */
    public void run() {
        while (connection.get()) {
            try {
                socket = new Socket(host, port);
                new Registration().cancelConnection();
                return;
            } catch (IOException ignored) {} //повторяет подключение
        }
        connection.set(true);
    }
}
