package org.example.collections;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

/**
 * Class Dragon whose objects stored in Java Collection
 */
public class Dragon implements Serializable {
    private long id;
    private String user;
    private String name;
    private Coordinates coordinates;
    private Integer age;
    private Color color;
    private DragonType type;
    private DragonCharacter character;
    private DragonCave cave;
    private Timestamp creationDate;

    private transient String colorString;
    private transient String typeString;
    private transient String characterString;

    /**
     *
     * @param id "Long"; 12-digit number
     * @param name notNull string
     * @param coordinates {@link Coordinates coordinates (x, y)}
     * @param age integer positive number
     * @param color {@link Color color}
     * @param type {@link DragonType type}
     * @param character {@link DragonCharacter character}
     * @param cave fractional number separated by a dot {@link DragonCave}
     */
    public Dragon(long id, String user, String name, Coordinates coordinates, int age, Color color, DragonType type,
                  DragonCharacter character, DragonCave cave) {
        this.id = id;
        this.user = user;
        this.name = name;
        this.coordinates = coordinates;
        this.age = age;
        this.color = color;
        this.type = type;
        this.character = character;
        this.cave = cave;
        creationDate = new Timestamp(new Date().getTime());
    }

    public Dragon() {
        creationDate = new Timestamp(new Date().getTime());
    }

    @Override
    public String toString() {
        return "| id: " + this.id + " | " +
                "Имя: " + this.name + " | "+
                "координаты: " + this.coordinates.toString() + " | " +
                "возраст: " + this.age + " | " +
                "цвет: " + this.color.getColor() + " | " +
                "тип: " + this.type.getType() + " | " +
                "характер: " + this.character.getCharacter() + " | " +
                "глубина пещеры: " + this.cave.getDepth() + " | " +
                "дата создания: " + this.getCreationDate();
    }

    public String getName() {return name;}

    public long getId() {return id;}

    public String getUser() {
        return user;
    }

    public String getCoordinates() {
        return coordinates.toString();
    }
    public int getX() {return coordinates.getX();}
    public Long getY() {return coordinates.getY();}
    public Long getSumCoordinate() {
        try {
            return coordinates.getX() + coordinates.getY();
        } catch (NumberFormatException e) {
            return Long.MAX_VALUE;
        }
    }

    public Integer getAge() {
        return age;
    }

    public Timestamp getCreationDate() {return creationDate;}

    public Color getColor() {
        return color;
    }
    public DragonType getType() {
        return type;
    }
    public DragonCharacter getCharacter() {
        return character;
    }

    public Double getCave() {
        return cave.getDepth();
    }

    public String getColorString() {
        return colorString;
    }
    public String getTypeString() {
        return typeString;
    }
    public String getCharacterString() {
        return characterString;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }
    public void setAge(Integer age) {
        this.age = age;
    }
    public void setColor(Color color) {
        this.color = color;
    }
    public void setType(DragonType type) {
        this.type = type;
    }
    public void setCharacter(DragonCharacter character) {
        this.character = character;
    }
    public void setCave(DragonCave cave) {
        this.cave = cave;
    }

    public void setCreationDate(Timestamp creationDate) {
        this.creationDate = creationDate;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setUserLogin(String user_login) {
        this.user = user_login;
    }
}