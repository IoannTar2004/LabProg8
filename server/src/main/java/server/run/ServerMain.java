package server.run;

import server.database.DataBaseDragons;
import server.manager.ObjectsManager;
import server.multithreading.Consumer;
import server.multithreading.Producer;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServerMain {
    private static List<Socket> sockets = Collections.synchronizedList(new LinkedList<>());

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Port: ");
        int port = scanner.nextInt();

        new ObjectsManager().addAll(new DataBaseDragons().getAll());
        System.out.println("Running!");
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            ExecutorService consumers = Executors.newCachedThreadPool();
            consumers.submit(new Consumer());
            while (true) {
                Socket socket = serverSocket.accept();
                sockets.add(socket);
                new Thread(new Producer(socket)).start();
            }
        } catch (IOException e) {e.printStackTrace();}
    }

    public static List<Socket> getSockets() {
        return sockets;
    }
}
