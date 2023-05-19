import server.multithreading.Consumer;
import org.example.tools.OutputText;
import server.database.DataBaseInitialization;
import server.multithreading.Producer;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServerExchanger {
    public static void main(String[] args) {
        DataBaseInitialization.connect(OutputText.database("url"),
                OutputText.database("login"),OutputText.database("password"));
        Scanner scanner = new Scanner(System.in);
        System.out.print("Port: ");
        int port = scanner.nextInt();
        System.out.println("Running!");
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            serverSocket.setReuseAddress(true);
            ExecutorService consumers = Executors.newCachedThreadPool();
            consumers.submit(new Consumer());
            while (true) {
                Socket socket = serverSocket.accept();
                new Thread(new Producer(socket)).start();
            }
        } catch (IOException e) {e.printStackTrace();}
    }
}
