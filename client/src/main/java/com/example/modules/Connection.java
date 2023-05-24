package com.example.modules;

import javafx.application.Platform;
import org.example.collections.Dragon;
import org.example.transmission.DataToClient;
import org.example.transmission.DataToServer;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Arrays;
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
    public Connection(Socket socket) {
        this.socket = socket;
    }

    public static void stop() {
        connection.set(false);
    }

    /**
     * Run endless tries to connect to server
     */
    public boolean connect() {
        while (connection.get()) {
            try {
                socket = new Socket(host, port);
                return true;
            } catch (IOException ignored) {} //повторяет подключение
        }
        connection.set(true);
        return false;
    }

    @SafeVarargs
    public final <S> void sendToServer(String input, S... objects) {
        DataToServer<S> sender = new DataToServer<>(input, objects);
        try {
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            out.writeObject(sender);
        } catch (IOException e) {
            connect();
        }
    }

    public <G> G getFromServer() throws IOException{
        try {
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
            DataToClient<G> result = (DataToClient) in.readObject();
            return result.getResult();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void run() {
        Class<DragonTable> dragonTableClass = DragonTable.class;
        while (true) {
            try {
                Object[] objects = getFromServer();
                String action = (String) objects[1];
                Platform.runLater(() -> {
                    try {
                        dragonTableClass.getDeclaredMethod(action, Dragon.class).
                                invoke(new DragonTable(), objects[0]);
                    } catch (ReflectiveOperationException e) {
                        e.printStackTrace();
                    }
                });
            } catch (IOException e) {
                if (!connect()) {
                    return;
                }
            }
        }
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
