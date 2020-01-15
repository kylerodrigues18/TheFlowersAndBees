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
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
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
    private boolean gameoverFlag = false;
    private int imageSize = 50;
    private int moveDistance = 10;
    private int collisionPoints = 5;
    private int energyPoints = 300;
    private int nectarPoints = 10;
    private int numBees = 5;
    private int numFlowers = 10;
    private int tick = 0;

    @FXML
    private Pane gardenPane;
    @FXML
    private Label arrowKeyLabel;
    @FXML
    private ImageView gameoverImage;
    @FXML
    private ListView infoPanel;

    /**
     * Method that initializes gardenPane and calls flowers and bees to be initialized.
     */
    @FXML
    public void initialize() {
        gardenPane.setStyle("-fx-background-color: linear-gradient(to bottom right, derive(forestgreen, 20%), derive(forestgreen, -40%));");

        // init infoPanel data
        infoPanel.getItems().add("We have 2 types of Bees: ");
        infoPanel.getItems().add("-Red: moves to flowers");
        infoPanel.getItems().add("-Yellow: moves inline");
        infoPanel.getItems().add("We have 2 types of Flowers: ");
        infoPanel.getItems().add("-Pink: replenishes bee energy");
        infoPanel.getItems().add("-Purple: drains bee energy");

        arrowKeyLabel.toFront();
        arrowKeyLabel.setVisible(true);

        gardenPane.setFocusTraversable(true);
        gameoverImage.setFitWidth(250);
        gameoverImage.setX(gameoverImage.getX() - 25);
        gameoverImage.setVisible(false);

        initFlowers(numFlowers);
        for (GardenFlower flower : flowers) {
            gardenPane.getChildren().add(flower.getImageView());
        }

        initBees(numBees);
        for (AbstractBee bee : bees) {
            gardenPane.getChildren().add(bee.getImageView());
        }
    }

    /**
     * Method that creates flower objects with random image view and energy level.
     *
     * @param num how many flowers will be initialized
     */
    private void initFlowers(int num) {
        flowers = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            //set location and energy

            int x = (int) (Math.random() * 510);
            int y = (int) (Math.random() * 510);
            boolean collisionFlag = true;

            while (collisionFlag) {
                collisionFlag = false;
                for (Flower flower : flowers) {
                    if (Math.abs(x - flower.getLocation().getX()) <= imageSize &&
                            Math.abs(y - flower.getLocation().getY()) <= imageSize) {
                        x = (int) (Math.random() * 510);
                        y = (int) (Math.random() * 510);
                        collisionFlag = true;
                    }
                }
            }

            int randomNectarPoints = (int) (Math.random() * nectarPoints) + 5;

            // sets half the flowers to have negative energy
            // when the bee collides with a negative flower, it will decrease energy of the bee
            ImageView flowerImage;
            if (i % 2 == 0) {
                flowerImage = new ImageView(new Image("\\images\\" + "flower-1.png"));
            } else {
                nectarPoints = nectarPoints * -1;
                flowerImage = new ImageView(new Image("\\images\\" + "flower-2.png"));
            }
            Point2D location = new Point2D(x, y);
            flowerImage.setPreserveRatio(true);
            flowerImage.setFitWidth(imageSize);
            flowerImage.setX(x);
            flowerImage.setY(y);
            GardenFlower gardenFlower = new GardenFlower(location, randomNectarPoints, flowerImage);
            flowers.add(gardenFlower);
        }
    }

    /**
     * Method that creates bee objects with random image view and energy level.
     *
     * @param num how many bees will be initialized
     */
    private void initBees(int num) {
        bees = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            //set location and energy
            int x = (int) (Math.random() * 510);
            int y = (int) (Math.random() * 510);

            boolean collisionFlag = true;

            while (collisionFlag) {
                collisionFlag = false;
                for (Bee bee : bees) {
                    if (Math.abs(x - bee.getLocation().getX()) <= imageSize &&
                            Math.abs(y - bee.getLocation().getY()) <= imageSize) {
                        x = (int) (Math.random() * 510);
                        y = (int) (Math.random() * 510);
                        collisionFlag = true;
                    }
                }
            }


            int randomEnergyPoints = (int) (Math.random() * energyPoints) + 1;

            Point2D location = new Point2D(x, y);

            if (i % 2 == 0) {
                ImageView beeImage = new ImageView(new Image("\\images\\" + "bee-1.png"));
                beeImage.setPreserveRatio(true);
                beeImage.setFitHeight(imageSize);
                SmartBee smartBee = new SmartBee(location, randomEnergyPoints, moveDistance, beeImage);
                smartBee.setTargetFlower(flowers.get((int) (Math.random() * flowers.size())));
                bees.add(smartBee);
            } else {
                ImageView beeImage = new ImageView(new Image("\\images\\" + "bee-2.png"));
                beeImage.setPreserveRatio(true);
                beeImage.setFitHeight(imageSize);
                DumbBee dumbBee = new DumbBee(location, randomEnergyPoints, moveDistance, beeImage);
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
        if ((keyEvent.getCode() == KeyCode.SPACE || keyEvent.getCode() == KeyCode.RIGHT) && !gameoverFlag) {
            tick++;

            // Loop through each bee to make them move
            for (int i = 0; i < bees.size(); i++) {

                // Loop through each bee for collisions, also turns bee invisible if it is out of energy
                for (int a = 0; a < bees.size(); a++) {
                    if (i != a) {
                        Point2D firstBeeLocation = bees.get(i).getLocation();
                        Point2D secondBeeLocation = bees.get(a).getLocation();
                        if (firstBeeLocation.distance(secondBeeLocation) < imageSize / 2.0) {
                            bees.get(a).changeEnergyPoints(collisionPoints);
                        }
                    }
                }

                boolean targetReached = bees.get(i).move();

                // sets targetFlower nectar to zero and changes targetFlower to random one.
                if (targetReached && bees.get(i) instanceof SmartBee) {
                    bees.get(i).changeEnergyPoints(((SmartBee) bees.get(i)).getTargetFlower().getNectarPoints());
                    ((SmartBee) bees.get(i)).getTargetFlower().setNectarPoints(0);

                    Flower targetFlower = flowers.get((int) (Math.random() * flowers.size()));
                    ((SmartBee) bees.get(i)).setTargetFlower(targetFlower);
                }
                // Checks for DumbBee collision with flower
                if (bees.get(i) instanceof DumbBee) {
                    for (int f = 0; f < flowers.size(); f++) {
                        if (flowers.get(f).getLocation().distance(bees.get(i).getLocation()) < imageSize / 2.0) {
                            bees.get(i).changeEnergyPoints(flowers.get(f).getNectarPoints());
                            flowers.get(i).setNectarPoints(0);
                        }
                    }
                }
            }

            // Remove bees that have no energy (energy <= 0)
            for (int i = 0; i < bees.size(); i++) {
                if (bees.get(i).getEnergyPoints() <= 0) {
                    bees.get(i).getImageView().setVisible(false);
                    bees.remove(i);
                    i--;
                }
            }

            updateInfoPanel();

            // ends game when all bees are dead.
            if (bees.size() == 0) {
                gameoverFlag = true;
                gameoverImage.toFront();
                gameoverImage.setVisible(true);
            }
        }
    }

    /**
     * Updates info panel
     */
    private void updateInfoPanel() {
        infoPanel.getItems().clear();
        infoPanel.setMouseTransparent(true);
        infoPanel.setFocusTraversable(false);
        infoPanel.getItems().add("Ticks: " + tick);
        infoPanel.getItems().add("Bees: ");

        // Update InfoView
        for (int i = 0; i < bees.size(); i++) {
            String beeInfo = "X: " + bees.get(i).getLocation().getX() + " Y: " + bees.get(i).getLocation().getY() + " Energy: " + bees.get(i).getEnergyPoints();
            infoPanel.getItems().add(beeInfo);
        }

        infoPanel.getItems().add("Flowers: ");
        for (int i = 0; i < flowers.size(); i++) {
            String flowerInfo = "X: " + flowers.get(i).getLocation().getX() + " Y: " + flowers.get(i).getLocation().getY() + " Nectar: " + flowers.get(i).getNectarPoints();
            infoPanel.getItems().add(flowerInfo);
        }
    }
}
