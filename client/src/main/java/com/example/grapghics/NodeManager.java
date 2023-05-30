package com.example.grapghics;

import com.example.run.ProxyController;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Labeled;
import javafx.scene.control.TextField;
import org.example.collections.ProxyDragon;

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

    @SafeVarargs
    public final void boxInitialize(Class<?> clas, ChoiceBox<String>... boxes) {
        boxes[0].setItems(Translation.getAllLanguages());
        boxes[0].setValue(Translation.getLanguage());
        new Translation(clas).changeLanguage(null);

        boxes[1].getSelectionModel().selectFirst();
        boxes[2].getSelectionModel().selectFirst();
        boxes[3].getSelectionModel().selectFirst();

        boxes[0].setOnAction(new Translation(clas)::changeLanguage);
    }

    @FXML
    public void enterAgain(Class<?> controllerClass) {
        ProxyController proxyController = new ProxyController(controllerClass);
        String[] fields = {"nameField", "xField", "yField", "ageField", "caveField"};
        for (String field: fields) {
            TextField textField = proxyController.getField(field);
            textField.setStyle("");
            textField.setPromptText(ResourceBundle.getBundle("properties.Table", Translation.getLocale()).getString(field));
        }
    }

    public void setItems(ProxyDragon proxyDragon) {

    }
}
