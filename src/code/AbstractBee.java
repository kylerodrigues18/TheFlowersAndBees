package code;

import javafx.geometry.Point2D;
import javafx.scene.image.ImageView;

public abstract class AbstractBee implements Bee {
    Point2D location = null;
    int energyLevel = 10;
    int moveDistance = 20;
    ImageView imageView;

    public AbstractBee(Point2D location, int energyLevel, int moveDistance, ImageView imageView) {
        this.location = location;
        this.energyLevel = energyLevel;
        this.moveDistance = moveDistance;
        this.imageView = imageView;
    }

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

    @Override
    public ImageView getImageView(){
        return this.imageView;
    }


}
