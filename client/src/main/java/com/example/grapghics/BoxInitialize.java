package com.example.grapghics;

import com.example.controllers.TableController;
import javafx.scene.control.ChoiceBox;

public class BoxInitialize {

    @SafeVarargs
    public final void initialize(Class<?> clas, ChoiceBox<String>... boxes) {
        boxes[0].setItems(Translation.getAllLanguages());
        boxes[0].setValue(Translation.getLanguage());
        new Translation(clas).changeLanguage(null);

        boxes[1].getSelectionModel().selectFirst();
        boxes[2].getSelectionModel().selectFirst();
        boxes[3].getSelectionModel().selectFirst();

        boxes[0].setOnAction(new Translation(clas)::changeLanguage);
    }
}
