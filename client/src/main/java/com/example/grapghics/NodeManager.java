package com.example.grapghics;

import javafx.scene.Node;
import javafx.scene.control.Labeled;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.ResourceBundle;
import java.util.function.Consumer;

import static com.example.run.ProxyController.getField;

public class NodeManager {

    public <T extends Labeled> void setText(ResourceBundle resourceBundle, String[] fields, String[] keys) {
        int i = 0;
        for (String field: fields) {
            T node = getField(field);
            node.setText(resourceBundle.getString(keys[i]));
            i++;
        }
    }

    public void forEach(Node[] fields, Consumer<? super Node> action) {
        try {
            Arrays.stream(fields).forEach(action);
        } catch (Exception e) {e.printStackTrace();}
    }
}
