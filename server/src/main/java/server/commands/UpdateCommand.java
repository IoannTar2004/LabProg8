package server.commands;

import org.example.collections.ProxyDragon;
import server.database.DataBaseDragons;
import server.manager.ObjectsManager;
import server.modules.ServerSender;
import server.multithreading.DataSentException;
import server.run.ServerExchanger;

import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class UpdateCommand implements Command {
    @Override
    public ServerSender<Object[]> execute(Object... args) throws DataSentException {
        ProxyDragon proxyDragon = (ProxyDragon) args[0];
        new DataBaseDragons().merge(proxyDragon);
        new ObjectsManager().replace(proxyDragon);

        ExecutorService service = Executors.newFixedThreadPool(3);
        ServerSender<Object[]> serverSender = new ServerSender<>(new Object[]{proxyDragon, "update"});

        for (Socket socket: ServerExchanger.getSockets()) {
            serverSender.setSocket(socket);
            service.submit(serverSender);
        }
        throw new DataSentException();
    }
}
