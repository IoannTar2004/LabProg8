package server.manager;

import org.example.collections.Dragon;

import java.util.List;

public class ObjectsCollectionManager extends CollectionManager {
    public Dragon getDragonById(long id) {
        Dragon dragon = dragons.stream().filter(dragon1 -> dragon1.getId() == id)
                .findFirst().orElse(null);
        if (dragon != null) {
            return dragon;
        }
        throw new NullPointerException();
    }

    public List<Dragon> getAll() {return dragons;}
}
