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
 * Interface for the Flower to implement which requires method stubs to be defined.
 */
public interface Flower {
    int getNectarPoints();

    void setNectarPoints(int nectarLevel);

    Point2D getLocation();

    ImageView getImageView();
}
