package server.manager;

import org.example.collections.ProxyDragon;

import java.util.HashMap;
import java.util.Map;

/**
 * class for processing dragon's fields
 */
public class ObjectsElements extends CollectionManager {

    /**
     * Method which can build string with dragon's elements in relation on entered numbers.
     * @param proxyDragon
     * @param fields if filter by elements is required
     */
    public String element(ProxyDragon proxyDragon, String... fields) {
        if (fields.length == 1) {return proxyDragon.toString();}
        else {
            Map<String, String> elements = new HashMap<>();

            elements.put("1", "id: " + proxyDragon.getId());
            elements.put("2", "Имя: " + proxyDragon.getName());
            elements.put("3", "координаты: " + proxyDragon.getCoordinates());
            elements.put("4", "возраст: " + proxyDragon.getAge());
            elements.put("5", "цвет: " + proxyDragon.getColor());
            elements.put("6", "тип: " + proxyDragon.getType());
            elements.put("7", "характер: " + proxyDragon.getCharacter());
            elements.put("8", "глубина пещеры: " + proxyDragon.getCave());
            elements.put("9", "дата создания: " + proxyDragon.getCreation());

            String show = "| ";
            boolean unknownNumber = false;
            for (int i = 1; i < fields.length; i++) {
                if (elements.get(fields[i]) != null) {
                    show = show + elements.get(fields[i]) + " | ";
                    unknownNumber = true;
                }
            }
            if  (unknownNumber) {return show;}
        }
        return "";
    }
}
