package server.commands;

import com.example.collections.Dragon;
import com.example.run.DataToClient;
import server.database.DataBaseDragons;
import server.database.IdGenerator;
import server.manager.ObjectsManager;
import server.modules.ServerSender;
import server.multithreading.DataSentException;
import server.run.ServerMain;

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
    public DataToClient<Object[]> execute(Object... args) throws DataSentException {
        Dragon dragon = (Dragon) args[0];
        dragon.setId(IdGenerator.getId());
        new DataBaseDragons().merge(dragon);
        new ObjectsManager().add(dragon);

        ExecutorService service = Executors.newFixedThreadPool(3);
        DataToClient<Object[]> dataToClient = new DataToClient<>(new Object[]{dragon, "add"});

        for (Socket socket: ServerMain.getSockets()) {
            ServerSender serverSender = new ServerSender(dataToClient, socket);
            service.submit(serverSender);
        }
        throw new DataSentException();
    }
}
