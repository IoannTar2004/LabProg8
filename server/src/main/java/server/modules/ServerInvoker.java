package server.modules;

import server.commands.Command;

import java.util.List;
import java.util.Objects;

public class ServerInvoker {
    /**
     * This method receives entered or read from script command and splits by spaces.
     * It works while command is not "exit".
     */
    public static ServerSender<?> invoke(Command command, Object... args) {
        try {
            return command.execute(args);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        return null;
    }
}
