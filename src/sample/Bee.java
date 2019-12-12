package sample;

import javafx.geometry.Point2D;

public interface Bee {
    Point2D location = null;
    int energyLevel = 10;
    int moveDistance = 20;

    void move();
}
