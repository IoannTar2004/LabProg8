package org.example.transmission;

import java.io.Serializable;
import java.util.Arrays;

/**
 * General class for connecting client and server. It is used to send request from client to server.
 */
public class DataToServer<T> implements Serializable {
    private final String commandString;
    private final String mode;
    private final T[] objects;

    @SafeVarargs
    public DataToServer(String commandString, String mode, T... objects) {
        this.commandString = commandString;
        this.mode = mode;
        this.objects = objects;
    }

    public String getCommandString() {
        return commandString;
    }

    public String getMode() {
        return mode;
    }

    public Object[] getObjects() {
        return objects;
    }

    @Override
    public String toString() {
        return "DataToServer{" +
                "commandString=" + commandString +
                ", mode='" + mode + '\'' +
                ", objects=" + Arrays.toString(objects) +
                '}';
    }
}
