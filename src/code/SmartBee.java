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

/**
 * Class which inherits methods & attributes from AbstractBee and overrides move() method.
 */
public class SmartBee extends AbstractBee {
    Flower targetFlower;

    /**
     * Constructor for SmartBee which uses AbstractBee's constructor through super.
     */
    public SmartBee(Point2D location, int energyLevel, int moveDistance, ImageView imageView) {
        super(location, energyLevel, moveDistance, imageView);
    }

    /**
     * Method that makes the bee move towards the closest flower.
     */
    @Override
    public boolean move() {
        Point2D flowerLocation = targetFlower.getLocation();
        Point2D beeLocation = getLocation();
        double distance = beeLocation.distance(flowerLocation);
        if(distance < 10) {
            setLocation(new Point2D(flowerLocation.getX(), flowerLocation.getY()));
            return true;
        } else {
            changeEnergyLevel(-1);
            double changeInX = (beeLocation.getX() - flowerLocation.getX());
            double changeInY = (beeLocation.getY() - flowerLocation.getY());
            setLocation(new Point2D(beeLocation.getX() + 5*(changeInX/changeInX), beeLocation.getY() + 5*(changeInY/changeInY)));
            return false;
        }
    }

    public void setTargetFlower(Flower flower) {
        targetFlower = flower;
    }

    public Flower getTargetFlower() {
        return targetFlower;
    }
}
