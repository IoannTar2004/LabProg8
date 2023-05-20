package com.example.grapghics;

import javafx.scene.control.Labeled;

import java.util.ResourceBundle;

import static com.example.run.ProxyController.getField;

public class TextSetter {
    public static <T extends Labeled> void set(ResourceBundle resourceBundle, String[] fields, String[] keys) {
        int i = 0;
        for (String field: fields) {
            T node = getField(field);
            node.setText(resourceBundle.getString(keys[i]));
            i++;
        }
    }
}
