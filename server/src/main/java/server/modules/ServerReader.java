package server.modules;

import org.example.transmission.DataToServer;
import server.commands.*;
import server.database.UserAccess;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ServerReader {
    private static Map<String, Command> commands = new HashMap<>();
    static {
        commands.put("user_access", new UserAccess());
        commands.put("show", new ShowCommand());
    }

    private Command command;
    private String commandString;
    private String mode;
    private Object[] objects;
    private String login;
    private Socket socket;

    public ServerReader(String[] commandString) {
        this.command = commands.get(commandString[0]);
    }

    public ServerReader() {}

    public boolean read(Socket socket) {
        try {
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
            DataToServer<?> dataToServer = (DataToServer<?>) in.readObject();

            command = commands.get(dataToServer.getCommandString());
            commandString = dataToServer.getCommandString();
            mode = dataToServer.getMode();
            objects = dataToServer.getObjects();
            login = dataToServer.getLogin();
            this.socket = socket;

            System.out.println(Thread.currentThread().getName()+ " - " + this);

            return true;
        } catch (IOException e) {
            try {
                socket.close();
            } catch (NullPointerException | IOException ignored) {}
        } catch (ClassNotFoundException e) {e.printStackTrace();}
        return false;
    }

    public Command getCommand() {
        return command;
    }

    public static Command getCommand(String commandString) {
        try {
            return commands.get(commandString);
        } catch (NullPointerException ignored) {} //Возникает, когда команда не найдена. Игнорирую.
        return null;
    }

    public String getCommandString() {
        return commandString;
    }

    public String getMode() {
        return mode;
    }

    public Object[] getObjects() {
        return objects;
    }

    public Socket getSocket() {
        return socket;
    }

    public String getLogin() {
        return login;
    }

    @Override
    public String toString() {
        return "ServerReader{" +
                "command=" + command +
                ", commandString=" + commandString +
                ", mode='" + mode + '\'' +
                ", objects=" + Arrays.toString(objects) +
                '}';
    }
}
