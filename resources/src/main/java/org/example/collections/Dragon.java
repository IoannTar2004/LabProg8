package org.example.collections;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import jakarta.persistence.*;

/**
 * Class Dragon whose objects stored in Java Collection
 */
@Entity
@Table(name = "dragons")
public class Dragon implements Serializable {
    @Id
    private long id;
    @Column
    private String login;
    @Column
    private String name;
    @Column
    private String coordinates;
    @Column
    private Integer age;
    @Column
    private String color;
    @Column
    private String type;
    @Column
    private String character;
    @Column
    private Double cave;
    @Column
    private Timestamp creation;

    /**
     *
     * @param name notNull string
     * @param coordinates {@link Coordinates coordinates (x, y)}
     * @param age integer positive number
     * @param color {@link Color color}
     * @param type {@link DragonType type}
     * @param character {@link DragonCharacter character}
     * @param cave fractional number separated by a dot {@link DragonCave}
     */
    public Dragon(long id, String login, String name, String coordinates, int age, String color, String type,
                  String character, Double cave) {
        this.id = id;
        this.login = login;
        this.name = name;
        this.coordinates = coordinates;
        this.age = age;
        this.color = color;
        this.type = type;
        this.character = character;
        this.cave = cave;
    }

    public Dragon() {

    }

    @Override
    public String toString() {
        return "Dragon{" +
                "id=" + id +
                ", user='" + login + '\'' +
                ", name='" + name + '\'' +
                ", coordinates='" + coordinates + '\'' +
                ", age=" + age +
                ", color='" + color + '\'' +
                ", type='" + type + '\'' +
                ", character='" + character + '\'' +
                ", cave=" + cave +
                ", creation=" + creation +
                '}';
    }

    public long getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getName() {
        return name;
    }

    public String getCoordinates() {
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

    public Double getCave() {
        return cave;
    }

    public Timestamp getCreation() {
        return creation;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCoordinates(String coordinates) {
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

    public void setCave(Double cave) {
        this.cave = cave;
    }

    public void setCreation(Timestamp creation) {
        this.creation = creation;
    }
}