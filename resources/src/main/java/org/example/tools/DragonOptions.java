package org.example.tools;

import org.example.collections.ProxyDragon;
import org.example.collections.DragonFields;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Class contains some useful methods to process dragon objects.
 */
public class DragonOptions {
    /**
     * Universal method that can run '{@link Checks Check}' methods in relation to {@link DragonFields}.
     * @param fieldName run defined method in relation to this argument
     * @param input it is what you entered
     * @return any object if you write down correctly
     */
    public Object dragonProcessing(DragonFields fieldName, String input) {
        Class<Checks> checksClass = Checks.class;
        Method method;

        Object obj;
        Checks checks = new Checks(input);
        try {
            method = checksClass.getMethod(fieldName.getField() + "Checker");
            obj = method.invoke(checks);
            if (obj != null) {
                return obj;
            }
        } catch (Exception ignored) {}
        return null;
    }

    /**
     * Adds or changes dragon's fields
     * @param proxyDragon that you need to change
     * @param fields which field you want to change
     * @param element value of field
     * @return changed dragon
     */
    public ProxyDragon dragonInput(ProxyDragon proxyDragon, DragonFields fields, Object element) {
        Class<ProxyDragon> dragonClass = ProxyDragon.class;
        try {
            Field field = dragonClass.getDeclaredField(fields.getField());
            field.setAccessible(true);
            field.set(proxyDragon, element);
        } catch (NoSuchFieldException | IllegalAccessException ignored) {}
        return proxyDragon;
    }
}
