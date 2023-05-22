package com.example.grapghics;

import com.example.run.ProxyController;
import javafx.scene.Node;
import javafx.scene.control.Labeled;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.function.Consumer;

public class NodeManager {

    public <T extends Labeled> void setText(Class<?> clas, String bundle, Locale locale, String[] fields, String[] keys) {
        ProxyController controller = new ProxyController(clas);
        int i = 0;
        for (String field: fields) {
            T node = controller.getField(field);
            node.setText(ResourceBundle.getBundle(bundle, locale).getString(keys[i]));
            i++;
        }
    }
}
