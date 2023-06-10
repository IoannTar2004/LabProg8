package com.example.controllers;

import com.example.collections.Dragon;
import com.example.modules.DragonTable;
import com.example.run.ProxyController;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.WindowEvent;

import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class VisualizationController implements Initializable, CloseAction {

    protected final ExecutorService service = Executors.newCachedThreadPool();

    @FXML
    private AnchorPane target;

    @FXML
    protected AnchorPane main;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            target();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
        ProxyController.setController(VisualizationController.class, this);
        DragonTable.getDragons().forEach(Dragon::start);
    }

    private final EventHandler<WindowEvent> closeEvent = event -> {
        DragonTable.getDragons().forEach(d -> d.finish());
        service.shutdown();
        ProxyController.remove(VisualizationController.class);
    };

    @Override
    public EventHandler<WindowEvent> close() {
        return closeEvent;
    }

    private void target() throws URISyntaxException{
        ImageView view = new ImageView(new Image(getClass().getResource("/images/target.png").toURI().toString()));
        target.getChildren().add(view);
        view.setFitHeight(60);
        view.setFitWidth(90);
        view.setDisable(true);

        target.setOnMouseMoved(cursor -> {
            if (cursor.isDragDetect()) {
                view.setX(cursor.getX() - 43);
                view.setY(cursor.getY() - 28);
            }
            target.setOnMousePressed(press -> {
                ImageView gun;
                try {
                    gun = new ImageView(new Image(getClass().getResource("/images/gun.png").toURI().toString()));
                    gun.setFitHeight(95);
                    gun.setFitWidth(109.25);
                    gun.setDisable(true);
                } catch (URISyntaxException e) {
                    throw new RuntimeException(e);
                }
                target.getChildren().add(1,gun);
                gun.setX(cursor.getX() - 58);
                gun.setY(cursor.getY() - 40);
                new Thread(() -> {
                    try{
                        Thread.sleep(60);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    Platform.runLater(() -> target.getChildren().remove(gun));
                }).start();
            });
        });
    }
}