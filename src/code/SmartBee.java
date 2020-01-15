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
    public SmartBee(Point2D location, int energyPoints, int moveDistance, ImageView imageView) {
        super(location, energyPoints, moveDistance, imageView);
    }

    /**
     * Method that makes the bee move towards the closest flower.
     */
    @Override
    public boolean move() {
        Point2D flowerLocation = targetFlower.getLocation();
        Point2D beeLocation = getLocation();
        double distance = beeLocation.distance(flowerLocation);
        changeEnergyPoints(-1);
        if(distance < getMoveDistance()) {
            setLocation(new Point2D(flowerLocation.getX(), flowerLocation.getY()));
            return true;
        } else {
            if(flowerLocation.getY() - getMoveDistance()/2.0 > beeLocation.getY()) {
                setLocation(new Point2D(beeLocation.getX(), beeLocation.getY() + getMoveDistance()));
                return false;
            } else if(flowerLocation.getY() + getMoveDistance()/2.0 < beeLocation.getY()) {
                setLocation(new Point2D(beeLocation.getX(), beeLocation.getY() - getMoveDistance()));
                return false;
            } else if(flowerLocation.getX() > beeLocation.getX()) {
                setLocation(new Point2D(beeLocation.getX() + getMoveDistance(), beeLocation.getY()));
                return false;
            } else if(flowerLocation.getX() <= beeLocation.getX()){
                setLocation(new Point2D(beeLocation.getX() - getMoveDistance(), beeLocation.getY()));
                return false;
            }
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
