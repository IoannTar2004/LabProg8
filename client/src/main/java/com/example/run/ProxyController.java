package com.example.run;

import javafx.scene.Node;

import java.lang.reflect.Field;
import java.util.Arrays;

/**
 * Class for monitoring controller's fields and its events
 */

public abstract class ProxyController {

    protected static Controller controller;

    public static <T> T getField(String field) {
        Class<Controller> controllerClass = Controller.class;
        try {
            Field field1 = controllerClass.getDeclaredField(field);
            field1.setAccessible(true);
            return (T) field1.get(controller);
        } catch (NoSuchFieldException | IllegalAccessException e) {e.printStackTrace();}
        return null;
    }

    public static Field[] getAllFields() {
        Class<Controller> controllerClass = Controller.class;
        Field[] fields = controllerClass.getDeclaredFields();
        Arrays.stream(fields).forEach(f -> f.setAccessible(true));

        return fields;
    }

    public static Node[] getNodes(String... fieldsName) {
        Node[] nodes = new Node[fieldsName.length];

        Class<Controller> controllerClass = Controller.class;
        for (int i = 0; i < fieldsName.length; i++) {
            try {
                Field field = controllerClass.getDeclaredField(fieldsName[i]);
                field.setAccessible(true);
                nodes[i] = (Node) field.get(controller);
            } catch (NoSuchFieldException | IllegalAccessException e) {e.printStackTrace();}
        }
        return nodes;
    }

    public static void setController(Controller controller) {
        ProxyController.controller = controller;
    }
}
