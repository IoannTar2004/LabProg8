package server.commands;

import org.example.collections.Dragon;
import server.database.DataBaseDragons;
import server.manager.ObjectsManager;
import server.modules.ServerSender;
import server.multithreading.DataSentException;
import server.run.ServerExchanger;

import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RemoveCommand implements Command {

    @Override
    public ServerSender<?> execute(Object... args) throws DataSentException {
        Dragon dragon = (Dragon) args[0];
        new DataBaseDragons().remove(dragon);
        new ObjectsManager().remove(dragon);

        ExecutorService service = Executors.newFixedThreadPool(3);
        ServerSender<Object[]> serverSender = new ServerSender<>(new Object[]{dragon, "remove"});

        for (Socket socket: ServerExchanger.getSockets()) {
            serverSender.setSocket(socket);
            service.submit(serverSender);
        }
        throw new DataSentException();
    }
}
