package org.example.collections;

import java.io.Serializable;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Enums of dragon's color
 */
public enum Color implements Serializable {
    BLACK("black"), BLUE("blue"), YELLOW("yellow");

    private String color;
    Color(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    /**
     *
     * @param color color in Russian
     * @return color
     */
    public static Color getEnumColor(String color) {
        if (color.equals("black")) {return BLACK;}
        else if (color.equals("blue")) {return BLUE;}
        else if (color.equals("yellow")) {return YELLOW;}

        return null;
    }
}
