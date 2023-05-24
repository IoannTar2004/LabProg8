package server.commands;

import server.database.DataBaseUsers;
import server.database.Users;
import server.modules.ServerSender;
import server.commands.Command;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserAccess implements Command {

    @Override
    public ServerSender<String> execute(Object... args) {
        List<Users> list = new DataBaseUsers().getAll();
        Users user = list.stream().filter(d -> d.getLogin().equals(args[1]) && d.getPassword().
                equals(args[2])).findFirst().orElse(null);

        if (user != null) {
            return new ServerSender<>("access");
        }

        if(args[0].equals("newUser")) {
            return new ServerSender<>("existedUser");
        } else if (args[0].equals("existedUser")) {
            return new ServerSender<>("UserNotFound");
        }
        return null;
    }
}
