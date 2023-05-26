package com.example.grapghics;

import com.example.controllers.VisualizationController;
import com.example.run.ClientMain;
import com.example.run.ProxyController;
import javafx.event.ActionEvent;
import javafx.scene.control.ChoiceBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;

public class ImageSelection {

    public void changeObjectImage(ActionEvent event) {
        ProxyController controller = new ProxyController(VisualizationController.class);
        ImageView imageView = controller.getField("object");
        int index = ((ChoiceBox<String>) controller.getField("colorChoice")).getSelectionModel().getSelectedIndex();

        if (index == 0) {
            imageView.setImage(new Image(ClientMain.class.getResource("black_dragon.png").toString()));
        } else if (index == 1) {
            imageView.setImage(new Image(ClientMain.class.getResource("yellow_dragon.png").toString()));
        } else if (index == 2) {
            imageView.setImage(new Image("images/blue_dragon.png"));
        }

    }
    public void changeBackgroundImage(ActionEvent event) {
        ProxyController controller = new ProxyController(VisualizationController.class);
        ImageView imageView = controller.getField("object");
        int index = ((ChoiceBox<String>) controller.getField("typeChoice")).getSelectionModel().getSelectedIndex();

        try {
            if (index == 0) {
                imageView.setImage(new Image(getClass().getResource("images/water_background.jpg").toURI().toString()));
            } else if (index == 1) {
                imageView.setImage(new Image(getClass().getResource("images/water_background.jpg").toURI().toString()));
            } else if (index == 2) {
                imageView.setImage(new Image(getClass().getResource("images/water_background.jpg").toURI().toString()));
            }
        } catch (URISyntaxException e) {}

    }
}
