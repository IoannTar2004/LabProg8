package com.example.modules;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import com.example.collections.Dragon;

public class StaticData {
    private static StaticData staticData;
    private String login;
    private Connection connection;
    private ObservableList<Dragon> dragons = FXCollections.observableArrayList();

    public static StaticData getData() {
        if (staticData == null) {
            staticData = new StaticData();
        }
        return staticData;
    }

    private StaticData() {}

    public String getLogin() {
        return login;
    }

    public Connection getConnection() {
        return connection;
    }

    public ObservableList<Dragon> getDragons() {
        return dragons;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public void add(Dragon dragon) {
        dragons.add(dragon);
        //new DragonTable().fill(dragon);
    }
}
