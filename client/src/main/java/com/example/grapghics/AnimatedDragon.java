package com.example.grapghics;

import com.example.collections.Dragon;
import com.example.controllers.VisualizationController;
import com.example.modules.DragonTable;
import com.example.modules.StaticData;
import com.example.run.ProxyController;
import javafx.animation.Animation;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import java.io.Serializable;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Class Dragon whose objects stored in Java Collection
 */
public class AnimatedDragon implements Serializable {

    private String color;
    private int strength = 100;
    protected int speed;
    private double x;
    private double y;

    private AnchorPane main;
    private ExecutorService service;

    private final TranslateTransition flyTransition = new TranslateTransition();
    private TranslateTransition bumpTransition = new TranslateTransition();
    private final ImageView image = new ImageView();

    private AtomicBoolean work = new AtomicBoolean(true);

    public AnimatedDragon(String color, String type) {
        this.color = color;
        image.setId(type);
    }

    public void initialize() {
        main = new ProxyController(VisualizationController.class).getField("main");
        service = new ProxyController(VisualizationController.class).getField("service");
        work.set(true);
        setAction();
        try {
            Thread.sleep(100);

            service.submit(() -> {
                image.setTranslateX(Math.random() * 803 - 65);
                image.setTranslateY(Math.random() * 458);
                image.setFitWidth(300);
                image.setFitHeight(250);
                Platform.runLater(() -> main.getChildren().add(image));

                int i = 0;
                while (work.get()) {
                    setImage(i);
                    i++;
                    run();
                    bump();
                    if (i == 15) {
                        i = 0;
                    }
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {throw new RuntimeException(e);}
                }
            });
        } catch (InterruptedException | NullPointerException e) {e.printStackTrace();}
    }

    public void run() {
        x = image.getTranslateX();
        y = image.getTranslateY();
        double[] par = pathParameters();

        if (flyTransition.getStatus() == Animation.Status.RUNNING || bumpTransition.getStatus() == Animation.Status.RUNNING) {
            return;
        }

        double len = Math.sqrt(Math.pow(par[0] - x, 2) + Math.pow(par[1] - y, 2));
        flyTransition.setDuration(Duration.seconds(len / speed));
        flyTransition.setNode(image);
        flyTransition.setToX(par[0]);
        flyTransition.setToY(par[1]);
        if ((par[0] - x) < 0) {
            image.setScaleX(-1);
        } else {
            image.setScaleX(1);
        }
        flyTransition.play();
    }

    public void bump() {
        List<?> list = new ArrayList<>(main.getChildren());
        list.remove(image);
        if (list.size() > 0) {
            ImageView bump = (ImageView) list.stream().filter(i -> distance((ImageView) i, image) < 120).findFirst().orElse(null);
            if (bump != null && bumpTransition.getStatus() == Animation.Status.STOPPED) {
                try {
                    typeAbility(bump);
                } catch (NullPointerException e) {
                    return;
                }
                bumpTransition.setNode(image);
                if (bump.getScaleX() == image.getScaleX()) {
                    if (image.getScaleX() == 1 && x < bump.getTranslateX() || image.getScaleX() == -1 && x > bump.getTranslateX()) {
                        return;
                    }
                    flyTransition.stop();
                    double destination = x + image.getScaleX() * strength;
                    push(destination);
                } else {
                    flyTransition.stop();
                    double destination = x - image.getScaleX() * strength;
                    push(destination);
                }
            }
            strength = 100;
        }
    }

    private void push(double destination) {
        if (destination > 738) {
            bumpTransition.setToX(738);
        } else if (destination < -70) {
            bumpTransition.setToX(-70);
        } else {
            bumpTransition.setToX(destination);
        }
        bumpTransition.play();
    }

    private double distance(ImageView dragon, ImageView dragon1) {
        return Math.sqrt(Math.pow(dragon.getTranslateX() - dragon1.getTranslateX(), 2)
                + Math.pow(dragon.getTranslateY() - dragon1.getTranslateY(), 2));
    }

    private double[] pathParameters() {
        AnchorPane pane = new ProxyController(VisualizationController.class).getField("target");
        ImageView cursor = (ImageView) pane.getChildren().get(1);
        boolean targetNear = Math.sqrt(Math.pow(cursor.getX() - image.getTranslateX(), 2)
                + Math.pow(cursor.getY() - image.getTranslateY(), 2)) < 230;

        if (targetNear && speed == 205) {
            flyTransition.stop();
            speed = 410;
            return new double[] {Math.random() * 803 - 70, Math.random() * 530};
        } else if (flyTransition.getStatus() == Animation.Status.STOPPED) {
            speed = 205;
            return new double[] {Math.random() * 803 - 70, Math.random() * 530};
        }
        return null;
    }

    private void typeAbility(ImageView aDragon) throws NullPointerException {
        if (aDragon.getId().equals("fire") && Math.random() < 0.5) {
            try {
                flyTransition.stop();
                ImageView fire = new ImageView(new Image(getClass().getResource("/images/fire.gif").toURI().toString()));
                fire.setTranslateX(x + 50);
                fire.setTranslateY(y + 40);
                fire.setFitHeight(161);
                fire.setFitWidth(232);
                Platform.runLater(() -> main.getChildren().add(fire));
                Thread.sleep(2000);
                Platform.runLater(() -> main.getChildren().remove(fire));

                throw new NullPointerException();
            } catch (InterruptedException | URISyntaxException e) {
                throw new RuntimeException(e);
            }
        } else if (image.getId().equals("air") || aDragon.getId().equals("air")) {
            throw new NullPointerException();
        } else if (aDragon.getId().equals("underground")) {
            if (image.getId().equals("water")) {
                strength = 80;
            } else {
                strength = 250;
            }
        } else if (image.getId().equals("water")) {
            strength = 20;
            bumpTransition.setDuration(Duration.seconds(0.3));
        }
    }

    public void remove() {
        work.set(false);
        main.getChildren().remove(image);
    }

    public void setAction() {
        image.setOnMouseClicked(event -> {
            Dragon dragon = DragonTable.getDragons().stream().filter(d -> d.getAnimatedDragon().equals(this)).findFirst().orElse(null);
            StaticData.getData().getConnection().sendToServer("remove", dragon);
            remove();
        });
    }

    public void setImage(int i) {
        try {
            image.setImage(new Image(getClass().getResource("/images/" +
                            color + "_dragons/" + color + "_dragon" + i + ".png").toURI()
                    .toString()));
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    public String getColor() {
        return color;
    }

    public String getType() {
        return image.getId();
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setType(String type) {
        image.setId(type);
        strength = 100;
        bumpTransition.setDuration(Duration.seconds(0.5));
    }
}