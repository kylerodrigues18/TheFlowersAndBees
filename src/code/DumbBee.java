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
 * Class that inherits methods & attributes from AbstractBee and overrides move() method.
 */
public class DumbBee extends AbstractBee {
    private Boolean moveRight;
    /**
     * Constructor for DumbBee which uses AbstractBee's constructor through super.
     */
    public DumbBee(Point2D location, int energyLevel, int moveDistance, ImageView imageView) {
        super(location, energyLevel, moveDistance, imageView);
        moveRight = true;
    }

    /**
     * Method that makes the bee move in a repeating pattern (square or circle).
     */
    @Override
    public void move(Point2D flowerLocation) {
        double currentLocation = getLocation().getX();
        System.out.println(currentLocation);
        double nextX = (moveRight) ? currentLocation + getMoveDistance() : currentLocation - getMoveDistance();

        if(nextX > 550) {
            moveRight = false;
            nextX = 549;
        } else if (nextX < 0) {
            moveRight = true;
            nextX = 0;
        }
        System.out.println("yee");
        setLocation(new Point2D(nextX, getLocation().getY()));
    }
    public void setReferenceBee(AbstractBee bee) {

    }
}
