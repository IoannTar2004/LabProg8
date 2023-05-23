package org.example.collections;

import java.io.Serializable;

/**
 * Enums of dragon's type
 */
public enum DragonType implements Serializable {
    WATER("water"), UNDERGROUND("underground"), AIR("air"), FIRE("fire");

    private String type;
    DragonType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    /**
     *
     * @param type type in Russian
     * @return DragonType
     */
    public static DragonType getEnumType(String type) {
        if (type.equals("water")) {return WATER;}
        else if (type.equals("underground")) {return UNDERGROUND;}
        else if (type.equals("air")) {return AIR;}
        else if (type.equals("fire")) {return FIRE;}

        return null;
    }

}
