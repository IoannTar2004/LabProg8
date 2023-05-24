package server.modules;

import org.example.transmission.DataToServer;
import server.commands.*;
import server.commands.UserAccess;

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
        commands.put("add", new AddCommand());
        commands.put("update", new UpdateCommand());
    }

    private Command command;
    private Object[] objects;
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
            objects = dataToServer.getObjects();
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

    public Object[] getObjects() {
        return objects;
    }

    public Socket getSocket() {
        return socket;
    }

    @Override
    public String toString() {
        return "ServerReader{" +
                "command=" + command +
                ", objects=" + Arrays.toString(objects) +
                '}';
    }
}
