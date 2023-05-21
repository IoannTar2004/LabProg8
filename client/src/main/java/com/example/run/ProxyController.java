package com.example.run;

import com.example.controllers.RegistrationController;
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

    private static Map<String, Initializable> controllers = new HashMap<>();
    protected Initializable controller;

    public ProxyController(String classname) {
        controller = controllers.get(classname);
    }
    public <T> T getField(String field) {
        try {
            Field field1 = controller.getClass().getDeclaredField(field);
            field1.setAccessible(true);
            return (T) field1.get(controller);
        } catch (NoSuchFieldException | IllegalAccessException e) {e.printStackTrace();}
        return null;
    }

    public Field[] getAllFields() {
        Field[] fields = controller.getClass().getDeclaredFields();
        Arrays.stream(fields).forEach(f -> f.setAccessible(true));

        return fields;
    }

    public Node[] getNodes(String... fieldsName) {
        Node[] nodes = new Node[fieldsName.length];
        try {
            for (int i = 0; i < fieldsName.length; i++) {
                Field field = controller.getClass().getDeclaredField(fieldsName[i]);
                field.setAccessible(true);
                nodes[i] = (Node) field.get(controller);
            }
        } catch (NoSuchFieldException | IllegalAccessException e) {e.printStackTrace();}
        return nodes;
    }

    public static void setController(String key, Initializable initializable) {
        controllers.put(key, initializable);
    }
}
