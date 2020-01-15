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
    private int moveRight;
    private int moveDown;
    /**
     * Constructor for DumbBee which uses AbstractBee's constructor through super.
     */
    public DumbBee(Point2D location, int energyPoints, int moveDistance, ImageView imageView) {
        super(location, energyPoints, moveDistance, imageView);
        moveRight = 1;
        moveDown = 1;
    }

    /**
     * Method that makes the bee move in a repeating pattern (square or circle).
     */
    @Override
    public boolean move() {
        double currentX = getLocation().getX();
        double currentY = getLocation().getY();
        double nextX;
        double nextY = currentY;

        nextX = currentX + moveRight*getMoveDistance();

        if(nextX > 550) {
            moveRight = -1;
            nextX = 550;
            nextY = currentY + moveDown*getImageView().getFitHeight()/2;
        } else if (nextX < 0) {
            moveRight = 1;
            nextX = 0;
            nextY = currentY + moveDown*getImageView().getFitHeight()/2;
        }

        if(nextY > 500 + getImageView().getFitHeight()/2) {
            moveDown = -1;
            nextY = 500;
        } else if(nextY < 0) {
            moveDown = 1;
            nextY = 0;
        }
        setLocation(new Point2D(nextX, nextY));

        changeEnergyPoints(-1);

        return false;
    }
}
