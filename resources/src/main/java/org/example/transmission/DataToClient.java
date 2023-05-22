package org.example.transmission;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

/**
 * General class for connecting client and server. It is used to send reply from server to client.
 */
public class DataToClient<T> implements Serializable {
    private List<String> result;
    private T argument;

    public DataToClient(List<String> result, T argument) {
        this.result = result;
        this.argument = argument;
    }

    public List<String> getResult() {
        return result;
    }

    public T getArgument() {
        return argument;
    }

    @Override
    public String toString() {
        return "DataToClient{" +
                "result=" + result +
                ", arguments=" + argument +
                '}';
    }
}
