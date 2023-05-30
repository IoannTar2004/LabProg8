package server.manager;

import org.example.collections.ProxyDragon;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Controls the collection
 */
public abstract class CollectionManager {
   protected static List<ProxyDragon> proxyDragons = Collections.synchronizedList(new LinkedList<>());
}
