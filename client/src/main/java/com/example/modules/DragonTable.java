package com.example.modules;

import com.example.controllers.TableController;
import com.example.grapghics.AnimatedDragon;
import com.example.run.ProxyController;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import com.example.collections.Dragon;
import com.example.collections.DragonFields;

import java.io.IOException;
import java.net.Socket;
import java.util.Collections;
import java.util.List;

public class DragonTable {

    private static ObservableList<Dragon> dragons = FXCollections.observableArrayList();

    public void getAndAdd(Socket socket) {
        Connection connection = new Connection(socket);
        connection.sendToServer("show");
        try {
            List<Dragon> dragonList = connection.getFromServer();
            List<Long> dragonsId = dragons.stream().map(Dragon::getId).toList();

            dragonList.stream().forEach(d -> {
                if (Collections.binarySearch(dragonsId, d.getId()) < 0) {
                    add(d);
                }
            });
        } catch (IOException e) {e.printStackTrace();}
    }

    public void add(Dragon dragon) {
        Platform.runLater(() -> {
            ProxyController controller = new ProxyController(TableController.class);
            setRows();

            dragon.start();
            dragons.add(dragon);
            ((TableView<Dragon>) controller.getField("dragonsTable")).setItems(dragons);
        });
    }

    public void update(Dragon dragon) {
        Dragon dragonIndex = dragons.stream().filter(d -> d.getId() == dragon.getId()).findFirst().orElse(null);
        if (dragonIndex != null) {
            Platform.runLater(() -> {
                ProxyController controller = new ProxyController(TableController.class);
                setRows();

                try {
                    dragon.setAnimatedDragon(dragonIndex.getAnimatedDragon());
                } catch (NullPointerException ignored) {}
                dragon.update();
                dragons.set(dragons.indexOf(dragonIndex), dragon);
                ((TableView<Dragon>) controller.getField("dragonsTable")).setItems(dragons);
            });
        }
    }

    public void remove(Dragon dragon) {
        Platform.runLater(() -> {
            ProxyController controller = new ProxyController(TableController.class);

            Dragon collection = dragons.stream().filter(f -> f.getId() == dragon.getId()).findFirst().orElse(null);
            try {
                collection.finish();
            } catch (NullPointerException ignored) {}
            dragons.remove(collection);
            ((TableView<Dragon>) controller.getField("dragonsTable")).setItems(dragons);
        });
    }

    public void setRows() {
        ProxyController controller = new ProxyController(TableController.class);

        for (DragonFields fields : DragonFields.values()) {
        TableColumn<Dragon, ?> column = controller.getField(fields.getField());
        column.setCellValueFactory(new PropertyValueFactory<>(fields.getField()));
    }}

    public static ObservableList<Dragon> getDragons() {
        return dragons;
    }
}
