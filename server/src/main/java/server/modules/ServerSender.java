package server.modules;


import com.example.run.DataToClient;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ServerSender<T> implements Runnable {

    private T result;
    private Socket socket;

    public ServerSender(T result) {
        this.result = result;
    }

    public T getResult() {
        return result;
    }

    @Override
    public void run() {
        try {
            DataToClient<T> dataToClient = new DataToClient<>(result);
            ObjectOutputStream stream = new ObjectOutputStream(socket.getOutputStream());
            stream.writeObject(dataToClient);

        } catch (IOException ignored) {} //возникает, когда клиент отключается

    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    @Override
    public String toString() {
        return "ServerSender{" +
                "result=" + result +
                '}';
    }
}