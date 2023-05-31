package server.modules;

import com.example.run.DataToClient;
import server.commands.Command;
import server.multithreading.DataSentException;

import java.util.List;
import java.util.Objects;

public class ServerInvoker {
    /**
     * This method receives entered or read from script command and splits by spaces.
     * It works while command is not "exit".
     */
    public static DataToClient<?> invoke(Command command, Object... args) throws DataSentException {
        try {
            return command.execute(args);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        return null;
    }
}
