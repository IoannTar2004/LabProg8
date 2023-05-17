package com.example.graphics;

import javafx.event.ActionEvent;
import javafx.scene.control.ChoiceBox;

public class Translation {
    private ChoiceBox<String> box;

    public Translation(ChoiceBox<String> box) {
        this.box = box;
    }

    public void changeLanguage(ActionEvent event) {
        switch (box.getValue()) {

        }
    }
}
