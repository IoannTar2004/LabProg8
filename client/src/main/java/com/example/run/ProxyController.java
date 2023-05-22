package com.example.run;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.*;

/**
 * Class for monitoring controller's fields and its events
 */

public class ProxyController {

    private static Map<Class<?>, Initializable> controllers = new HashMap<>();
    private final Initializable controllerClass;


    public static Map<Class<?>, Initializable> getControllers() {
        return controllers;
    }

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
        Arrays.stream(fields).filter(f -> f.isAnnotationPresent(FXML.class)).forEach(f -> f.setAccessible(true));

        return fields;
    }

    public Object[] getFields(String... fieldsName) {
        Object[] list = new Object[fieldsName.length];
        try {
            for (int i = 0; i < fieldsName.length; i++) {
                Field field = controllerClass.getClass().getDeclaredField(fieldsName[i]);
                field.setAccessible(true);
                list[i] = field.get(controllerClass);
            }
        } catch (NoSuchFieldException | IllegalAccessException e) {e.printStackTrace();}
        return list;
    }

    public static void setController(Class<?> key, Initializable initializable) {
        controllers.put(key, initializable);
    }

    public Initializable getControllerClass() {
        return controllerClass;
    }

    public static void changeScene(Node node, String fxml) {
        Stage stage = (Stage) node.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(ClientMain.class.getResource(fxml));
        try {
            Scene scene = new Scene(fxmlLoader.load());
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {e.printStackTrace();}
    }

    @Override
    public String toString() {
        return "ProxyController{" +
                "controllerClass=" + controllerClass.getClass().getName() +
                '}';
    }
}
