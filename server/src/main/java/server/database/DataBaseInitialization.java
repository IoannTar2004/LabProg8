package server.database;

import org.example.collections.*;
import org.example.tools.Checks;
import server.manager.ObjectsManager;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public abstract class DataBaseInitialization {
    private Connection connection;

    private static Map<String, Connection> connections = new HashMap<>();

    public DataBaseInitialization(String url, String user, String password) {
        String key = url + "_" + user + "_" + password;
        this.connection = connections.get(key);
    }

    public Connection getConnection() {
        return connection;
    }

    public static void connect(String[] resources) {
        String key = resources[0] + "_" + resources[1] + "_" + resources[2];
        if (connections.get(key) == null) {
            try {
                Class.forName("org.postgresql.Driver");
                Connection connection = DriverManager.getConnection(resources[0], resources[1], resources[2]);
                connections.put(key, connection);
                readFromDataBase(connection);
            } catch (ClassNotFoundException | SQLException e) {e.printStackTrace();}
        }
    }

    private static void readFromDataBase(Connection connection) throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet set = statement.executeQuery("SELECT * FROM dragons");

        while (set.next()) {
            Dragon dragon = new Dragon(set.getLong(1), set.getString(2), set.getString(3),
                    new Checks(set.getString(4)).coordinatesChecker(), set.getInt(5),
                    Color.getEnumColor(set.getString(6)), DragonType.getEnumType(set.getString(7)),
                    DragonCharacter.getEnumCharacter(set.getString(8)), new DragonCave(set.getLong(9)));
            dragon.setCreationDate(set.getTimestamp(10));
            new ObjectsManager().addFromDataBase(dragon);
        }
    }
}
