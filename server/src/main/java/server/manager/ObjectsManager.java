package server.manager;

import org.example.collections.ProxyDragon;

import java.util.List;

public class ObjectsManager extends CollectionManager {

    public void add(ProxyDragon proxyDragon) {
        proxyDragons.add(proxyDragon);
    }

    public void addAll(List<ProxyDragon> proxyDragonList) {
        proxyDragons.addAll(proxyDragonList);
    }

    public void replace(ProxyDragon proxyDragon) {
        ProxyDragon oldProxyDragon = new ObjectsCollectionManager().getDragonById(proxyDragon.getId());
        proxyDragons.set(proxyDragons.indexOf(oldProxyDragon), proxyDragon);
    }

    public void remove(ProxyDragon proxyDragon) {
        proxyDragons.remove(proxyDragon);
    }
}
