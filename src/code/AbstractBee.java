/**
 * Course: SE 2811 - 051
 * Winter 2019
 * Lab 2 - The Flowers and The Bees
 * Names: Milan Kablar and Kyle Rodrigues
 * Modified: 12/17/2019
 */
package code;

import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Abstract class that implements Bee interface and defines multiple methods.
 */
public abstract class AbstractBee implements Bee {
    private Point2D location = null;
    private int energyLevel = 10;
    private int moveDistance = 20;
    private ImageView imageView;

    /**
     * Constructor for AbstractBee.
     * @param location point 2d location in x, y plane
     * @param energyLevel energy levels of the bee
     * @param moveDistance distance the bee moves per increment/step
     * @param imageView image of the bee (different for type)
     */
    public AbstractBee(Point2D location, int energyLevel, int moveDistance, ImageView imageView) {
        this.location = location;
        this.energyLevel = energyLevel;
        this.moveDistance = moveDistance;
        this.imageView = imageView;
        imageView.setLayoutX(location.getX());
        imageView.setLayoutY(location.getY());
    }

    /**
     * Method that controls the bees movement on the 2d plane.
     */
    @Override
    public boolean move() {
        return false;
    }

    /**
     * Mutator method that changes the energy level of the bee.
     * @param energyChange energy to change by
     */
    @Override
    public void changeEnergyLevel(int energyChange) {
        energyLevel = energyLevel + energyChange;
    }

    /**
     * Accessor method for energy level of the bee.
     * @return int value of energy level
     */
    @Override
    public int getEnergyLevel() {
        return energyLevel;
    }

    /**
     * Accessor method for Point2D location.
     * @return Point2D location
     */
    @Override
    public Point2D getLocation() {
        return location;
    }

    /**
     * Set method for Point2D location.
     */
    @Override
    public void setLocation(Point2D location) {
        this.location = location;
        imageView.setLayoutX(location.getX());
        imageView.setLayoutY(location.getY());
    }

    /**
     * Accessor method for imageView for the bee object.
     * @return ImageView object
     */
    @Override
    public ImageView getImageView(){
        return imageView;
    }

    /**
     * Accessor method for retrieving move distance
     * @return moveDistance
     */
    public int getMoveDistance() {
        return moveDistance;
    }
}
