package server.database;

import com.example.collections.Dragon;
import server.manager.ObjectsCollectionManager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class IdGenerator {
    private static long id;

    public static Long getId() {
        if (id == 0) {
            List<Long> ids = new ArrayList<>(new ObjectsCollectionManager().getAll().stream().map(Dragon::getId).toList());
            Collections.sort(ids);
            if (ids.size() > 0) {
                return ids.get(ids.size() - 1) + 1;
            }
        }
        return id + 1;
    }
}
