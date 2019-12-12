package sample;

import javafx.geometry.Point2D;

public interface Flower {
    Point2D location = null;
    Boolean hasNector = Boolean.TRUE;
    int energyLevel = 0;

    int getEnergyLevel();

    void setNectorValue(Boolean nectorValue);

    Point2D getLocation();
}
