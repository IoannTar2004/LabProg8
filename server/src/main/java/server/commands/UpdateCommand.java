package server.commands;

import com.example.collections.Dragon;
import com.example.run.DataToClient;
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
    public DataToClient<Object[]> execute(Object... args) throws DataSentException {
        Dragon dragon = (Dragon) args[0];
        new DataBaseDragons().merge(dragon);
        new ObjectsManager().replace(dragon);

        ExecutorService service = Executors.newFixedThreadPool(3);
        DataToClient<Object[]> dataToClient = new DataToClient<>(new Object[]{dragon, "update"});

        for (Socket socket: ServerExchanger.getSockets()) {
            ServerSender serverSender = new ServerSender(dataToClient, socket);
            service.submit(serverSender);
        }
        throw new DataSentException();
    }
}
