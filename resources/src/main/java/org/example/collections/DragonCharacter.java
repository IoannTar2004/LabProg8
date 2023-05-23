package org.example.collections;

import java.io.Serializable;

/**
 * Enums of dragon's character
 */
public enum DragonCharacter implements Serializable {
    CUNNING("cunning"), EVIL("evil"), CHAOTIC("chaotic");

    private String character;
    DragonCharacter(String character) {
        this.character = character;
    }

    public String getCharacter() {
        return character;
    }

    /**
     *
     * @param character character in Russian
     * @return DragonCharacter
     */
    public static DragonCharacter getEnumCharacter(String character) {
        if (character.matches("\\s*Хитрый\\s*")) {return CUNNING;}
        else if (character.matches("\\s*Злой\\s*")) {return EVIL;}
        else if (character.matches("\\s*Хаотичный\\s*")) {return CHAOTIC;}

        return null;
    }

}
