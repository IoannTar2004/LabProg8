package com.example.run;

import java.io.Serializable;
import java.util.List;

/**
 * General class for connecting client and server. It is used to send reply from server to client.
 */
public class DataToClient<T> implements Serializable {
    private T result;

    public DataToClient(T result) {
        this.result = result;
    }

    public T getResult() {
        return result;
    }

    @Override
    public String toString() {
        return "DataToClient{" +
                "result=" + result +
                '}';
    }
}
