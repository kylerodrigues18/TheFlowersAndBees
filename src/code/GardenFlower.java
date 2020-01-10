/**
 * Course: SE 2811 - 051
 * Winter 2019
 * Lab 2 - The Flowers and The Bees
 * Names: Milan Kablar and Kyle Rodrigues
 * Modified: 12/17/2019
 */
package code;

import javafx.geometry.Point2D;
import javafx.scene.image.ImageView;

public class GardenFlower implements Flower{
    private Point2D location = null;
    private boolean hasNector = true;
    private int energyLevel = 0;
    ImageView imageView;

    public GardenFlower(Point2D location, boolean hasNector, int energyLevel, ImageView imageView) {
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
    public void setNectorValue(boolean nectorValue) {
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
