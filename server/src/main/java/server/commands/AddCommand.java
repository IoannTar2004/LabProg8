package server.commands;

import org.example.collections.ProxyDragon;
import server.database.DataBaseDragons;
import server.database.IdGenerator;
import server.manager.ObjectsManager;
import server.modules.ServerSender;
import server.multithreading.DataSentException;
import server.run.ServerExchanger;

import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Add object to collection.
 */
public class AddCommand implements Command {

    /**
     * Add object to collection.
     *
     * @param args
     */
    @Override
    public ServerSender<Object[]> execute(Object... args) throws DataSentException {
        ProxyDragon proxyDragon = (ProxyDragon) args[0];
        proxyDragon.setId(IdGenerator.getId());
        new DataBaseDragons().merge(proxyDragon);
        new ObjectsManager().add(proxyDragon);

        ExecutorService service = Executors.newFixedThreadPool(3);
        ServerSender<Object[]> serverSender = new ServerSender<>(new Object[]{proxyDragon, "add"});

        for (Socket socket: ServerExchanger.getSockets()) {
            serverSender.setSocket(socket);
            service.submit(serverSender);
        }
        throw new DataSentException();
    }
}
