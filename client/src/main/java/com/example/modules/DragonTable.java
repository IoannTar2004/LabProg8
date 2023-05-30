package com.example.modules;

import com.example.controllers.TableController;
import com.example.run.ProxyController;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.example.collections.ProxyDragon;
import org.example.collections.DragonFields;

import java.io.IOException;
import java.net.Socket;
import java.util.Collections;
import java.util.List;

public class DragonTable {

    private static ObservableList<ProxyDragon> proxyDragons = FXCollections.observableArrayList();

    public void getAndAdd(Socket socket) {
        Connection connection = new Connection(socket);
        connection.sendToServer("show");
        try {
            List<ProxyDragon> proxyDragonList = connection.getFromServer();
            List<Long> dragonsId = proxyDragons.stream().map(ProxyDragon::getId).toList();

            proxyDragonList.stream().forEach(d -> {
                if (Collections.binarySearch(dragonsId, d.getId()) < 0) {
                    add(d);
                }
            });
        } catch (IOException e) {e.printStackTrace();}
    }

    public void add(ProxyDragon proxyDragon) {
        Platform.runLater(() -> {
            ProxyController controller = new ProxyController(TableController.class);
            setRows();

            proxyDragons.add(proxyDragon);
            ((TableView<ProxyDragon>) controller.getField("dragonsTable")).setItems(proxyDragons);
        });
    }

    public void update(ProxyDragon proxyDragon) {
        ProxyDragon proxyDragonIndex = proxyDragons.stream().filter(d -> d.getId() == proxyDragon.getId()).findFirst().orElse(null);
        if (proxyDragonIndex != null) {
            Platform.runLater(() -> {
                ProxyController controller = new ProxyController(TableController.class);
                setRows();

                proxyDragons.set(proxyDragons.indexOf(proxyDragonIndex), proxyDragon);
                ((TableView<ProxyDragon>) controller.getField("dragonsTable")).setItems(proxyDragons);
            });
        }
    }

    public void remove(ProxyDragon proxyDragon) {
        Platform.runLater(() -> {
            ProxyController controller = new ProxyController(TableController.class);

            ProxyDragon collection = proxyDragons.stream().filter(f -> f.getId() == proxyDragon.getId()).findFirst().orElse(null);
            proxyDragons.remove(collection);
            ((TableView<ProxyDragon>) controller.getField("dragonsTable")).setItems(proxyDragons);
        });
    }

    public void setRows() {
        ProxyController controller = new ProxyController(TableController.class);

        for (DragonFields fields : DragonFields.values()) {
        TableColumn<ProxyDragon, ?> column = controller.getField(fields.getField());
        column.setCellValueFactory(new PropertyValueFactory<>(fields.getField()));
    }}

    public static ObservableList<ProxyDragon> getDragons() {
        return proxyDragons;
    }
}
