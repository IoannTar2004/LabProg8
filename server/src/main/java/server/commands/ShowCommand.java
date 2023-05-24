package server.commands;

import org.example.collections.Dragon;
import org.example.tools.OutputText;
import server.manager.ObjectsCollectionManager;
import server.manager.ObjectsElements;
import server.manager.ObjectsManager;
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
