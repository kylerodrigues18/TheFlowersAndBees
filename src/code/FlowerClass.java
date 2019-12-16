package code;

import javafx.geometry.Point2D;
import javafx.scene.image.ImageView;

public class FlowerClass implements Flower{
    Point2D location = null;
    Boolean hasNector = Boolean.TRUE;
    int energyLevel = 0;
    ImageView imageView;

    public FlowerClass(Point2D location, Boolean hasNector, int energyLevel, ImageView imageView) {
        this.location = location;
        this.hasNector = hasNector;
        this.energyLevel = energyLevel;
        this.imageView = imageView;
    }

    @Override
    public int getEnergyLevel() {
        return energyLevel;
    }

    @Override
    public void setNectorValue(Boolean nectorValue) {
        hasNector = nectorValue;
    }

    @Override
    public Point2D getLocation() {
        return location;
    }

    @Override
    public ImageView getImageView() {
        return imageView;
    }
}
