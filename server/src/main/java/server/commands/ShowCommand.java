package server.commands;

import com.example.collections.Dragon;
import server.manager.ObjectsCollectionManager;
import server.modules.ServerSender;

import java.util.LinkedList;
import java.util.List;

/**
 * Prints all objects in collection
 */
public class ShowCommand implements Command {

    /**
     * Prints objects in collection. If arguments are absent it prints all elements.
     * It can print some fields in relation to numbers.
     */
    @Override
    public ServerSender<List<Dragon>> execute(Object... args) {
        List<Dragon> dragonList = new LinkedList<>(new ObjectsCollectionManager().getAll());
        return new ServerSender<>(dragonList);
    }
}
