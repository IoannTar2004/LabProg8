package server.commands;

import org.example.collections.Dragon;
import server.manager.ObjectsManager;
import server.modules.ServerSender;
import server.multithreading.DataSentException;
import server.run.ServerExchanger;

import java.net.Socket;
import java.util.List;
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
    public ServerSender<Dragon> execute(Object... args) throws DataSentException {
        Dragon dragon = (Dragon) args[0];
        Long id = new ObjectsManager().insert(dragon);
        dragon.setId(id);

        ExecutorService service = Executors.newFixedThreadPool(3);
        ServerSender<Object[]> serverSender = new ServerSender<>(new Object[]{dragon, "add"});

        for (Socket socket: ServerExchanger.getSockets()) {
            serverSender.setSocket(socket);
            service.submit(serverSender);
        }
        throw new DataSentException();
    }
}
