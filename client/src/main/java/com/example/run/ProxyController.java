package com.example.run;

import java.lang.reflect.Field;

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

    public static Field[] getFields() {
        Class<Controller> controllerClass = Controller.class;
        return controllerClass.getDeclaredFields();
    }

    public static void setController(Controller controller) {
        ProxyController.controller = controller;
    }
}
