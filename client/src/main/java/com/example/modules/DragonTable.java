package com.example.modules;

import com.example.controllers.TableController;
import com.example.run.ProxyController;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.example.collections.Dragon;
import org.example.collections.DragonFields;

import java.net.Socket;
import java.util.List;

public class DragonTable {
    private String login;
    private Socket socket;

    public DragonTable(Socket socket, String login) {
        this.socket = socket;
        this.login = login;
    }

    public void getAndFill() {
        List<Dragon> dragons = new Connection(socket).<String, List<Dragon>>exchange("show", "user");
        dragons.forEach(this::fill);
    }

    public void fill(Dragon dragon) {
        ProxyController controller = new ProxyController(TableController.class);

        for (DragonFields fields: DragonFields.values()) {
            TableColumn<Dragon, ?> column = controller.getField(fields.getField());
            column.setCellValueFactory(new PropertyValueFactory<>(fields.getField()));
        }

        ((TableView<Dragon>) controller.getField("dragonsTable")).getItems().add(dragon);
    }
}
