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

    /**
     * Constructor for DumbBee which uses AbstractBee's constructor through super.
     */
    public DumbBee(Point2D location, int energyLevel, int moveDistance, ImageView imageView) {
        super(location, energyLevel, moveDistance, imageView);
    }

    /**
     * Method that makes the bee move in a repeating pattern (square or circle).
     */
    @Override
    public void move() {

    }
}
