package code;

import javafx.geometry.Point2D;

public interface Bee {
    void move();
    void changeEnergyLevel(int energyLevel);
    int getEnergyLevel();
}
