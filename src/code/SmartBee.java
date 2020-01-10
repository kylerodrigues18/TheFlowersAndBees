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
    public void move(Point2D flowerLocation) {
        Point2D currentLocation = getLocation();
        double angle = currentLocation.angle(flowerLocation);
        System.out.println(angle);
        Point2D newLocation = new Point2D(Math.cos(angle) * getMoveDistance(), Math.sin(angle) * getMoveDistance());
        setLocation(newLocation);
        imageView.setLayoutX(newLocation.getX());
        imageView.setLayoutY(newLocation.getY());
    }
}
