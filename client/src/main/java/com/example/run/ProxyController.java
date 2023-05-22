package com.example.run;

import javafx.fxml.Initializable;
import javafx.scene.Node;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Class for monitoring controller's fields and its events
 */

public class ProxyController {

    private static Map<Class<?>, Initializable> controllers = new HashMap<>();
    private final Initializable controllerClass;

    public ProxyController(Class<?> key) {
        controllerClass = controllers.get(key);
    }

    public <T> T getField(String field) {
        try {
            Field field1 = controllerClass.getClass().getDeclaredField(field);
            field1.setAccessible(true);
            return (T) field1.get(controllerClass);
        } catch (NoSuchFieldException | IllegalAccessException e) {e.printStackTrace();}
        return null;
    }

    public Field[] getAllFields() {
        Field[] fields = controllerClass.getClass().getDeclaredFields();
        Arrays.stream(fields).forEach(f -> f.setAccessible(true));

        return fields;
    }

    public Node[] getNodes(String... fieldsName) {
        Node[] nodes = new Node[fieldsName.length];
        try {
            for (int i = 0; i < fieldsName.length; i++) {
                Field field = controllerClass.getClass().getDeclaredField(fieldsName[i]);
                field.setAccessible(true);
                nodes[i] = (Node) field.get(controllerClass);
            }
        } catch (NoSuchFieldException | IllegalAccessException e) {e.printStackTrace();}
        return nodes;
    }

    public static void setController(Class<?> key, Initializable initializable) {
        controllers.put(key, initializable);
    }

    public Initializable getControllerClass() {
        return controllerClass;
    }
}
