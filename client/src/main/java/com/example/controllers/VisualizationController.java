package com.example.controllers;

import com.example.collections.Dragon;
import com.example.grapghics.AnimatedDragon;
import com.example.modules.DragonTable;
import com.example.run.ProxyController;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.stage.WindowEvent;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class VisualizationController implements Initializable, CloseAction {

    protected final ExecutorService service = Executors.newCachedThreadPool();

    @FXML
    protected AnchorPane main;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        ProxyController.setController(VisualizationController.class, this);
        DragonTable.getDragons().forEach(Dragon::start);
    }

    private final EventHandler<WindowEvent> closeEvent = event -> {
        DragonTable.getDragons().forEach(d -> d.finish());
        ProxyController.remove(VisualizationController.class);
    };

    @Override
    public EventHandler<WindowEvent> close() {
        return closeEvent;
    }
}