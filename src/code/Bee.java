package code;

import javafx.geometry.Point2D;
import javafx.scene.image.ImageView;

public interface Bee {
    void move();
    void changeEnergyLevel(int energyLevel);
    int getEnergyLevel();
    ImageView getImageView();
}
