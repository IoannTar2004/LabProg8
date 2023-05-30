package com.example.run;

import java.io.Serializable;
import java.util.Arrays;

/**
 * General class for connecting client and server. It is used to send request from client to server.
 */
public class DataToServer<T> implements Serializable {
    private final String commandString;
    private final T[] objects;

    @SafeVarargs
    public DataToServer(String commandString, T... objects) {
        this.commandString = commandString;
        this.objects = objects;
    }

    public String getCommandString() {
        return commandString;
    }

    public Object[] getObjects() {
        return objects;
    }

    @Override
    public String toString() {
        return "DataToServer{" +
                "commandString=" + commandString +
                ", objects=" + Arrays.toString(objects) +
                '}';
    }
}
