package server.manager;

import org.example.collections.Dragon;
import org.example.collections.DragonFields;
import org.example.tools.DragonOptions;
import server.database.DataBaseStuds;
import server.tools.IdGenerator;

public class ObjectsManager extends CollectionManager {

    public void insert(String login, Object... args) {

        Dragon dragon = new Dragon();

        for(DragonFields fields: DragonFields.values()) {
            if (args[fields.ordinal()] != null) {
                dragon = new DragonOptions().dragonInput(dragon, fields, args[fields.ordinal()]);
            }
        }
        dragon.setUserLogin(login);
        dragon.setId(IdGenerator.generate());
        new DataBaseStuds().insert(dragon);
        dragons.add(dragon);
    }

    public Long insert(Dragon dragon) {
        Long id = IdGenerator.generate();
        dragon.setId(id);
        new DataBaseStuds().insert(dragon);
        dragons.add(dragon);

        return id;
    }

    public void addFromDataBase(Dragon dragon) {
        dragons.add(dragon);
    }

    public long fullLength() {
        return dragons.size();
    }
    public long ownerLength(String login) {
        return new ObjectsCollectionManager().getAll().stream().filter(dragon1 -> dragon1.getUser().equals(login)).count();
    }

    public void remove(Dragon dragon) {
        dragons.remove(dragon);
    }

    public void clear(String login) {
        int i = 0;
        while (i < dragons.size()) {
            if (login.equals(dragons.get(i).getUser())) {
                dragons.remove(i);
                continue;
            }
            i++;
        }
    }

    public void replace(Object... args) {
        long id = Long.parseLong((String) args[0]);
        Dragon dragon = new ObjectsCollectionManager().getDragonById(id);
        for(DragonFields fields: DragonFields.values()) {
            if (args[fields.ordinal()+1] != null) {
                dragon = new DragonOptions().dragonInput(dragon, fields, args[fields.ordinal()+1]);
            }
        }
    }
}
