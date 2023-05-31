package server.modules;


import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ServerSender implements Runnable {

    private com.example.run.DataToClient<?> dataToClient;
    private Socket socket;

    public ServerSender(com.example.run.DataToClient<?> dataToClient, Socket socket) {
        this.dataToClient = dataToClient;
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
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
                "data=" + dataToClient +
                '}';
    }
}