package org.example.collections;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Class Dragon whose objects stored in Java Collection
 */
@Entity
@Table(name = "dragons")
public class Dragon implements Serializable {
    private long id;
    private String user;
    private String name;
    private Coordinates coordinates;
    private Integer age;
    private String color;
    private String type;
    private String character;
    private DragonCave cave;
    private Timestamp creationDate;

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
    public Dragon(long id, String user, String name, Coordinates coordinates, int age, String color, String type,
                  String character, DragonCave cave) {
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
                "цвет: " + this.color + " | " +
                "тип: " + this.type + " | " +
                "характер: " + this.character + " | " +
                "глубина пещеры: " + this.cave.getDepth() + " | " +
                "дата создания: " + this.getCreationDate();
    }

    @Id
    public long getId() {
        return id;
    }

    public String getUser() {
        return user;
    }

    public String getName() {
        return name;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public Integer getAge() {
        return age;
    }

    public String getColor() {
        return color;
    }

    public String getType() {
        return type;
    }

    public String getCharacter() {
        return character;
    }

    public DragonCave getCave() {
        return cave;
    }

    public Timestamp getCreationDate() {
        return creationDate;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setUser(String user) {
        this.user = user;
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

    public void setColor(String color) {
        this.color = color;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setCharacter(String character) {
        this.character = character;
    }

    public void setCave(DragonCave cave) {
        this.cave = cave;
    }

    public void setCreationDate(Timestamp creationDate) {
        this.creationDate = creationDate;
    }
}