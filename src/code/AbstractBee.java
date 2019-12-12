package code;

import javafx.geometry.Point2D;

public abstract class AbstractBee implements Bee {
    Point2D location = null;
    int energyLevel = 10;
    int moveDistance = 20;

    @Override
    public void move() {

    }

    @Override
    public void changeEnergyLevel(int energyLevel) {
        this.energyLevel = energyLevel;
    }

    @Override
    public int getEnergyLevel() {
        return energyLevel;
    }

    public AbstractBee(Point2D location, int energyLevel, int moveDistance) {
        this.location = location;
        this.energyLevel = energyLevel;
        this.moveDistance = moveDistance;
    }
}
