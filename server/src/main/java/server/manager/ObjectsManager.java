package server.manager;

import com.example.collections.Dragon;

import java.util.List;

public class ObjectsManager extends CollectionManager {

    public void add(Dragon dragon) {
        dragons.add(dragon);
    }

    public void addAll(List<Dragon> dragonList) {
        dragons.addAll(dragonList);
    }

    public void replace(Dragon dragon) {
        Dragon oldDragon = new ObjectsCollectionManager().getDragonById(dragon.getId());
        dragons.set(dragons.indexOf(oldDragon), dragon);
    }

    public void remove(Dragon dragon) {
        try {
            Dragon oldDragon = new ObjectsCollectionManager().getDragonById(dragon.getId());
            dragons.remove(oldDragon);
        } catch (NullPointerException ignored) {}
    }
}
