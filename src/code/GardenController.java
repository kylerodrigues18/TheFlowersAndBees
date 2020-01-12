/**
 * Course: SE 2811 - 051
 * Winter 2019
 * Lab 2 - The Flowers and The Bees
 * Names: Milan Kablar and Kyle Rodrigues
 * Modified: 12/17/2019
 */
package code;

import javafx.fxml.FXML;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;

import java.util.ArrayList;

/**
 * Controller class which initializes bees and flowers.
 */
public class GardenController {

    private ArrayList<GardenFlower> flowers;
    private ArrayList<AbstractBee> bees;
    private int flowerCounter;

    @FXML
    private Pane gardenPane;

    /**
     * Method that initializes gardenPane and calls flowers and bees to be initialized.
     */
    @FXML
    public void initialize() {
        gardenPane.setStyle("-fx-background-color: linear-gradient(to bottom right, derive(forestgreen, 20%), derive(forestgreen, -40%));");

        initFlowers(4);
        for (GardenFlower flower : flowers) {
            gardenPane.getChildren().add(flower.getImageView());
        }

        initBees(3);
        for (AbstractBee bee : bees) {
            gardenPane.getChildren().add(bee.getImageView());
        }
        gardenPane.setFocusTraversable(true);
    }

    /**
     * Method that creates flower objects with random image view and energy level.
     *
     * @param num how many flowers will be initialized
     */
    private void initFlowers(int num) {
        ChooseImage chooseImage = new ChooseImage();
        flowers = new ArrayList<>();
        flowerCounter = 0;
        for (int i = 0; i < num; i++) {
            //set location and energy

            int x = (int) (Math.random() * 510);
            int y = (int) (Math.random() * 510);
            boolean collisionFlag = true;

            while (collisionFlag) {
                collisionFlag = false;
                for (Flower flower : flowers) {
                    if (Math.abs(x - flower.getLocation().getX()) <= 25 &&
                            Math.abs(y - flower.getLocation().getY()) <= 25) {
                        x = (int) (Math.random() * 510);
                        y = (int) (Math.random() * 510);
                        collisionFlag = true;
                    }
                }
            }

            int energy = (int) (Math.random() * 3) + 1;

            // sets half the flowers to have negative energy
            // when the bee collides with a negative flower, it will decrease energy of the bee
            if (i % 2 == 0) {
                energy = energy * -1;
            }
            Point2D location = new Point2D(x, y);
            ImageView flower = new ImageView(new Image(chooseImage.getFlowerFile()));
            flower.setPreserveRatio(true);
            flower.setFitWidth(30.0);
            flower.setX(x);
            flower.setY(y);
            GardenFlower gardenFlower = new GardenFlower(location, true, energy, flower);
            flowers.add(gardenFlower);
        }
    }

    /**
     * Method that creates bee objects with random image view and energy level.
     *
     * @param num how many bees will be initialized
     */
    private void initBees(int num) {
        ChooseImage chooseImage = new ChooseImage();
        bees = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            //set location and energy
            int x = (int) (Math.random() * 510);
            int y = (int) (Math.random() * 510);

            boolean collisionFlag = true;

            while (collisionFlag) {
                collisionFlag = false;
                for (Bee bee : bees) {
                    if (Math.abs(x - bee.getLocation().getX()) <= 25 &&
                            Math.abs(y - bee.getLocation().getY()) <= 25) {
                        x = (int) (Math.random() * 510);
                        y = (int) (Math.random() * 510);
                        collisionFlag = true;
                    }
                }
            }


            int energyLevel = (int) (Math.random() * 3) + 1;
            int moveDistance = 10;

            Point2D location = new Point2D(x, y);
            ImageView beeImage = new ImageView(new Image(chooseImage.getBeeFile()));
            beeImage.setPreserveRatio(true);
            beeImage.setFitWidth(30.0);
            if (i == 0) {
                SmartBee smartBee = new SmartBee(location, energyLevel, moveDistance, beeImage);
                smartBee.setLocation(new Point2D(x, y));
                bees.add(smartBee);
            } else {
                DumbBee dumbBee = new DumbBee(location, energyLevel, moveDistance, beeImage);
                dumbBee.setLocation(new Point2D(x, 200));
                bees.add(dumbBee);
            }
        }
    }

    /**
     * Method that is called on key press to simulate movement of bees.
     * Also handles collisions that occurs
     *
     * @param keyEvent KeyEvent object
     */
    @FXML
    public void onKeyPressed(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.SPACE || keyEvent.getCode() == KeyCode.RIGHT) {
            // Loop through each bee to make them move
            for (int i = 0; i < bees.size(); i++) {
                // Loop through each flower for collisions
                for (GardenFlower gardenFlower : flowers) {
                    Point2D tempFlowerLocation = gardenFlower.getLocation();
                    // If collision, change bee energy level
                    if (bees.get(i).getLocation().distance(tempFlowerLocation) < 15) {
                        bees.get(i).changeEnergyLevel(gardenFlower.getEnergyLevel());
                        if (i == 0) {
                            flowerCounter++;
                            if (flowerCounter >= bees.size()) {
                                flowerCounter = 0;
                            }
                        }
                    }
                }
                // Loop through each bee for collisions, also turns bee invisible if it is out of energy
                for (int a = 0; a < bees.size(); a++) {
                    if (i != a) {
                        Point2D firstBeelocation = bees.get(i).getLocation();
                        Point2D secondBeeLocation = bees.get(a).getLocation();
                        if (firstBeelocation.distance(secondBeeLocation) < 15) {
                            bees.get(a).changeEnergyLevel(-1);
                        }
                    }
                }
                Point2D flowerLocation = flowers.get(flowerCounter).getLocation();
                bees.get(i).move(flowerLocation);
            }
            // Remove bees that have no energy (energy <= 0)
            for (int i = 0; i < bees.size(); i++) {
                if(bees.get(i).getEnergyLevel() <= 0) {
                    bees.get(i).getImageView().setVisible(false);
                    bees.remove(i);
                    i--;
                }
            }
        }
    }
}
