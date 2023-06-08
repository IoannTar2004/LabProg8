package server.commands;

import com.example.run.DataToClient;
import server.multithreading.DataSentException;

import java.io.Serializable;

/**
 * interfaces for classes which are responsible to command
 */
public interface Command extends Serializable {
    /**
     * @param args arguments are supplied to execute(). Contains some special objects.
     */
    DataToClient<?> execute(Object... args) throws DataSentException;
}
