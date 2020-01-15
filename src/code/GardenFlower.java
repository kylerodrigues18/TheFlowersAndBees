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
    private Point2D location;
    private int nectarPoints;
    ImageView imageView;

    public GardenFlower(Point2D location, int nectarPoints, ImageView imageView) {
        this.location = location;
        this.nectarPoints = nectarPoints;
        this.imageView = imageView;
    }

    @Override
    public int getNectarPoints() {
        return nectarPoints;
    }

    @Override
    public void setNectarPoints(int nectarPoints) {
        this.nectarPoints = nectarPoints;
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
