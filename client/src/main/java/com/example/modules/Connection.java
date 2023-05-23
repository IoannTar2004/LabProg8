package com.example.modules;

import org.example.transmission.DataToClient;
import org.example.transmission.DataToServer;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.concurrent.atomic.AtomicBoolean;

public class Connection {
    private static volatile AtomicBoolean connection = new AtomicBoolean(true);

    private String host;
    private int port;
    private Socket socket;

    public Connection(String host, int port) {
        this.host = host;
        this.port = port;
    }
    public Connection(Socket socket) {
        this.socket = socket;
    }

    public static void stop() {
        connection.set(false);
    }

    /**
     * Run endless tries to connect to server
     */
    public boolean run() {
        while (connection.get()) {
            try {
                socket = new Socket(host, port);
                return true;
            } catch (IOException ignored) {} //повторяет подключение
        }
        connection.set(true);
        return false;
    }

    public <S,G> G exchange(String input, S... objects) {
        DataToServer<S> sender = new DataToServer<>(input, objects);
        System.out.println(sender);

        try {
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            out.writeObject(sender);

            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
            DataToClient<G> result = (DataToClient) in.readObject();
            return result.getResult();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            run();
        }
        return null;
    }

    public void close() {
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getHost() {
        return host;
    }

    public int getPort() {
        return port;
    }

    public Socket getSocket() {
        return socket;
    }
}
