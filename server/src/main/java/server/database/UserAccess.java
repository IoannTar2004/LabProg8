package server.database;

import server.modules.ServerSender;
import server.commands.Command;

import java.net.Socket;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserAccess implements Command {

    @Override
    public ServerSender<String> execute(String mode, String[] command, String login, Object... args) {
        if(mode.equals("newUser")) {
            return newUser(login, (String) args[0]);
        } else if (mode.equals("existedUser")) {
            return existedUser(login, (String) args[0]);
        }
        return null;
    }

    private static ServerSender<String> newUser(String login, String password) {
        DataBaseStuds studs = new DataBaseStuds();
        try {
            PreparedStatement statement = studs.getConnection().prepareStatement("INSERT INTO users VALUES (?,?)");
            statement.setString(1, login);
            statement.setString(2, password);
            statement.execute();
            return new ServerSender<>(new String[]{"access"});
        } catch (SQLException e) {
            return new ServerSender<>(new String[]{"existedUser"});
        }
    }

    private static ServerSender<String> existedUser(String login, String password) {
        DataBaseStuds studs = new DataBaseStuds();
        try {
            ResultSet set = studs.getConnection().createStatement().executeQuery("SELECT * FROM users");
            while (set.next()) {
                if (set.getString(1).equals(login) && set.getString(2).equals(password)) {
                    return new ServerSender<>(new String[]{"access"});
                }
            }
            return new ServerSender<>(new String[]{"UserNotFound"});
        } catch (SQLException e) {e.printStackTrace();}
        return null;
    }
}
