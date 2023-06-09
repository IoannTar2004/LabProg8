package server.manager;

import com.example.collections.Dragon;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Controls the collection
 */
public abstract class CollectionManager {
   protected static List<Dragon> dragons = Collections.synchronizedList(new LinkedList<>());
}
