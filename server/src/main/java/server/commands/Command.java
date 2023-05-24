package server.commands;

import server.modules.ServerSender;
import server.multithreading.DataSentException;

import java.io.Serializable;

/**
 * interfaces for classes which are responsible to command
 */
public interface Command extends Serializable {
    /**
     * @param args    arguments are supplied to execute(). Contains some special objects.
     */
    ServerSender<?> execute(Object... args) throws DataSentException;
}
