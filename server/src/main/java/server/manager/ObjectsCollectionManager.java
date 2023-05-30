package server.manager;

import org.example.collections.ProxyDragon;

import java.util.List;

public class ObjectsCollectionManager extends CollectionManager {
    public ProxyDragon getDragonById(long id) {
        ProxyDragon proxyDragon = proxyDragons.stream().filter(dragon1 -> dragon1.getId() == id)
                .findFirst().orElse(null);
        if (proxyDragon != null) {
            return proxyDragon;
        }
        throw new NullPointerException();
    }

    public List<ProxyDragon> getAll() {return proxyDragons;}
}
