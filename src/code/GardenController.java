package code;

import javafx.fxml.FXML;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.HashMap;

public class GardenController {

    private ArrayList<FlowerClass> flowers;

    @FXML
    private Pane gardenPane;

    @FXML
    public void initialize() {
        gardenPane.setStyle("-fx-background-color: linear-gradient(to bottom right, derive(forestgreen, 20%), derive(forestgreen, -40%));");

        initFlowers(20);
        for (FlowerClass flower: flowers) {
            gardenPane.getChildren().add(flower.getImageView());
        }
    }

    private void initFlowers(int num) {
        ChooseImage chooseImage = new ChooseImage();
        flowers = new ArrayList<>();
        for(int i = 0; i < num; i++) {
            //set location and energy
            int x = (int) (Math.random() * 601);
            int y = (int) (Math.random() * 601);
            int energy = (int) (Math.random() * 3) + 1;
            if (i % 2 == 0) {
                energy = energy * -1;
            }
            Point2D location = new Point2D(x, y);
            ImageView flower = new ImageView(new Image(chooseImage.getFlowerFile()));
            flower.setPreserveRatio(true);
            flower.setFitWidth(50.0);
            flower.setX(x);
            flower.setY(y);
            FlowerClass flowerClass = new FlowerClass(location, true, energy, flower);
            flowers.add(flowerClass);
        }
    }


}
