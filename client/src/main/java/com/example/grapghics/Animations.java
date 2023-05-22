package com.example.grapghics;

import javafx.animation.PathTransition;
import javafx.animation.ScaleTransition;
import javafx.scene.Node;
import javafx.scene.shape.Line;
import javafx.util.Duration;

public class Animations {

    public void scaleTransition(Duration duration, Node node, double fromX, double fromY, double toX, double toY) {
        ScaleTransition transition = new ScaleTransition(duration, node);
        transition.setFromX(fromX);
        transition.setFromY(fromY);

        transition.setToX(toX);
        transition.setToY(toY);
        transition.play();
    }

    public void pathTransition(Duration duration, Node node, double startX, double startY, double toX, double toY) {
        Line line = new Line(startX, startY, toX, toY);
        PathTransition transition = new PathTransition(duration, line, node);
        transition.play();
    }
}
