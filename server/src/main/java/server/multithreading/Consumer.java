package server.multithreading;

import server.modules.ServerInvoker;
import server.modules.ServerSender;
import server.modules.ServerReader;

/**
 * poll task from task queue
 */
public class Consumer implements Runnable {

    @Override
    public void run() {
        while (true) {
            try {
                ServerReader reader = TaskQueue.poll();
                com.example.run.DataToClient<?> dataToClient = ServerInvoker.invoke(reader.getCommand(),reader.getObjects());
                new Thread(new ServerSender(dataToClient, reader.getSocket())).start();

                synchronized (TaskQueue.getQueue()) {
                    TaskQueue.getQueue().notify();
                }
            } catch (NullPointerException e) {
                synchronized (TaskQueue.getQueue()) {
                    try {
                        TaskQueue.getQueue().wait();
                    } catch (InterruptedException ignored) {}
                }
            } catch (DataSentException ignored) {} //Данные уже отправлены. Перейти к следующему task'у.
        }
    }
}
