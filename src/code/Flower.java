package code;

import javafx.geometry.Point2D;
import javafx.scene.image.ImageView;

public interface Flower {
    int getEnergyLevel();

    void setNectorValue(Boolean nectorValue);

    Point2D getLocation();

    ImageView getImageView();
}
