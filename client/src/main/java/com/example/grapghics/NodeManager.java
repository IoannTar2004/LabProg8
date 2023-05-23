package com.example.grapghics;

import com.example.run.ProxyController;
import javafx.scene.control.Labeled;
import javafx.scene.control.TextField;
import javafx.scene.layout.Border;

import java.util.Locale;
import java.util.ResourceBundle;

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

    public void textFieldError(TextField textField) {
        textField.setStyle("-fx-border-color: red;" +
                "-fx-border-width: 2px;");
    }
}
