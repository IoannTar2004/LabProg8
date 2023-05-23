package server.commands;

import org.example.collections.Dragon;
import org.example.collections.DragonFields;
import org.example.tools.DragonOptions;
import org.example.tools.OutputText;
import server.manager.ObjectsManager;
import server.modules.ServerSender;

import java.util.List;
import java.util.Objects;

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
    public ServerSender<Long> execute(Object... args) {
        Long id = new ObjectsManager().insert((Dragon) args[0]);
        return new ServerSender<>(id);
    }
}
