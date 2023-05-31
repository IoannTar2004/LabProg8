package server.commands;

import com.example.run.DataToClient;
import server.database.DataBaseUsers;
import server.database.Users;
import server.modules.ServerSender;

import java.util.List;

public class UserAccess implements Command {

    @Override
    public DataToClient<String> execute(Object... args) {
        List<Users> list = new DataBaseUsers().getAll();
        Users user = list.stream().filter(d -> d.getLogin().equals(args[1]) && d.getPassword().
                equals(args[2])).findFirst().orElse(null);

        if (user != null) {
            return new DataToClient<>("access");
        }

        if(args[0].equals("newUser")) {
            return new DataToClient<>("existedUser");
        } else if (args[0].equals("existedUser")) {
            return new DataToClient<>("UserNotFound");
        }
        return null;
    }
}
