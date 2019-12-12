package sample;

import javafx.geometry.Point2D;

public interface Bee {
    Point2D location = null;
    int health = 0;

    void move();
}
