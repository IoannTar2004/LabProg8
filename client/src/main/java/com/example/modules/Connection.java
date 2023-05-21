package com.example.modules;

import org.example.transmission.DataToClient;
import org.example.transmission.DataToServer;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;
import java.util.concurrent.atomic.AtomicBoolean;

public class Connection implements Callable<Boolean> {
    private static volatile AtomicBoolean connection = new AtomicBoolean(true);

    private static String host;
    private static int port;
    private static Socket socket;

    public Connection(String host, int port) {
        Connection.host = host;
        Connection.port = port;
    }
    public Connection() {}

    public static void stop() {
        connection.set(false);
    }

    /**
     * Run endless tries to connect to server
     */
    public Boolean call() {
        while (connection.get()) {
            try {
                socket = new Socket(host, port);
                return true;
            } catch (IOException ignored) {} //повторяет подключение
        }
        connection.set(true);
        return false;
    }

    public <S,G> G[] exchange(String[] input, String mode, String login, S... objects) {
        DataToServer<S> sender = new DataToServer<>(input, mode, login, objects);
        System.out.println(sender);

        try {
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            out.writeObject(sender);

            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
            DataToClient<G> result = (DataToClient) in.readObject();
            return result.getArguments();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            new Thread(new FutureTask<>(this)).start();
        }
        return null;
    }
}
