package com.example.modules;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.example.collections.ProxyDragon;

public class StaticData {
    private static StaticData staticData;
    private String login;
    private Connection connection;
    private ObservableList<ProxyDragon> proxyDragons = FXCollections.observableArrayList();

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

    public ObservableList<ProxyDragon> getDragons() {
        return proxyDragons;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public void add(ProxyDragon proxyDragon) {
        proxyDragons.add(proxyDragon);
        //new DragonTable().fill(dragon);
    }
}
