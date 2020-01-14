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
    private int flowerCount = 0;
    private boolean gameover = false;



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
        infoPanel.getItems().add("-Curly Antenna: moves to flowers");
        infoPanel.getItems().add("-Circle Antenna: moves inline");
        infoPanel.getItems().add("We have 2 types of Flowers: ");
        infoPanel.getItems().add("-White: replenishes bee energy");
        infoPanel.getItems().add("-Purple: drains bee energy");

        initFlowers(10);
        for (GardenFlower flower : flowers) {
            gardenPane.getChildren().add(flower.getImageView());
        }

        initBees(5);
        for (AbstractBee bee : bees) {
            gardenPane.getChildren().add(bee.getImageView());
        }
        gardenPane.setFocusTraversable(true);

        gameoverImage.setFitHeight(150);
        gameoverImage.setVisible(false);
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
                    if (Math.abs(x - flower.getLocation().getX()) <= 30 &&
                            Math.abs(y - flower.getLocation().getY()) <= 30) {
                        x = (int) (Math.random() * 510);
                        y = (int) (Math.random() * 510);
                        collisionFlag = true;
                    }
                }
            }

            int energy = (int) (Math.random() * 10) + 10;

            // sets half the flowers to have negative energy
            // when the bee collides with a negative flower, it will decrease energy of the bee
            ImageView flowerImage;
            if (i % 2 == 0) {
                flowerImage = new ImageView(new Image("\\images\\" + "flower-1.jpg"));
            } else {
                energy = energy * -1;
                flowerImage = new ImageView(new Image("\\images\\" + "flower-2.jpg"));
            }
            Point2D location = new Point2D(x, y);
            flowerImage.setPreserveRatio(true);
            flowerImage.setFitWidth(30.0);
            flowerImage.setX(x);
            flowerImage.setY(y);
            GardenFlower gardenFlower = new GardenFlower(location, true, energy, flowerImage);
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
                    if (Math.abs(x - bee.getLocation().getX()) <= 30 &&
                            Math.abs(y - bee.getLocation().getY()) <= 30) {
                        x = (int) (Math.random() * 510);
                        y = (int) (Math.random() * 510);
                        collisionFlag = true;
                    }
                }
            }


            int energyLevel = (int) (Math.random() * 100) + 1;
            int moveDistance = 10;

            Point2D location = new Point2D(x, y);

            if (i % 2 == 0) {
                ImageView beeImage = new ImageView(new Image("\\images\\" + "bee-1.jpg"));
                beeImage.setPreserveRatio(true);
                beeImage.setFitWidth(30.0);
                DumbBee dumbBee = new DumbBee(location, energyLevel, moveDistance, beeImage);
                bees.add(dumbBee);
            } else {
                ImageView beeImage = new ImageView(new Image("\\images\\" + "bee-2.jpg"));
                beeImage.setPreserveRatio(true);
                beeImage.setFitWidth(30.0);
                SmartBee smartBee = new SmartBee(location, energyLevel, moveDistance, beeImage);
                smartBee.setTargetFlower(flowers.get((int) (Math.random()*flowers.size())));
                bees.add(smartBee);
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
        if ((keyEvent.getCode() == KeyCode.SPACE || keyEvent.getCode() == KeyCode.RIGHT) && !gameover) {
            // Loop through each bee to make them move
            for (int i = 0; i < bees.size(); i++) {

                // Loop through each bee for collisions, also turns bee invisible if it is out of energy
                for (int a = 0; a < bees.size(); a++) {
                    if (i != a) {
                        Point2D firstBeeLocation = bees.get(i).getLocation();
                        Point2D secondBeeLocation = bees.get(a).getLocation();
                        if (firstBeeLocation.distance(secondBeeLocation) < 15) {
                            bees.get(a).changeEnergyLevel(-5);
                        }
                    }
                }

                boolean targetReached = bees.get(i).move();

                if(targetReached && bees.get(i) instanceof SmartBee) {
                    bees.get(i).changeEnergyLevel(((SmartBee) bees.get(i)).getTargetFlower().getEnergyLevel());
                    ((SmartBee) bees.get(i)).getTargetFlower().setNectorValue(false);

                    Flower targetFlower = flowers.get((int) (Math.random()*flowers.size()));

                    while(!targetFlower.hasNector()) {
                        targetFlower = flowers.get((int) (Math.random()*flowers.size()));
                    }
                    flowerCount++;

                    if(flowerCount == flowers.size() - 1) {
                        gameover = true;
                        gameoverImage.toFront();
                        gameoverImage.setVisible(true);
                    }

                    ((SmartBee) bees.get(i)).setTargetFlower(targetFlower);
                }
            }
            // Remove bees that have no energy (energy <= 0)
            for (int i = 0; i < bees.size(); i++) {
                if(bees.get(i).getEnergyLevel() <= 0) {
                    bees.get(i).getImageView().setVisible(false);
                    bees.remove(i);
                    i--;
                }
            }
            infoPanel.getItems().clear();
            infoPanel.setMouseTransparent( true );
            infoPanel.setFocusTraversable( false );
            infoPanel.getItems().add("Bees: ");
            // Update InfoView
            for (int i = 0; i < bees.size(); i++) {
                String s = "";
                s = "X: "+ bees.get(i).getLocation().getX() + " Y: " + bees.get(i).getLocation().getY() + " Energy: " + bees.get(i).getEnergyLevel();
                infoPanel.getItems().add(s);
            }

            infoPanel.getItems().add("Flowers: ");
            for (int i = 0; i < flowers.size(); i++) {
                String s = "";
                s = "X: "+ flowers.get(i).getLocation().getX() + " Y: " + flowers.get(i).getLocation().getY() + " Energy: " + flowers.get(i).getEnergyLevel();
                infoPanel.getItems().add(s);
            }

            infoPanel.getItems().add("");
            infoPanel.getItems().add("We have 2 types of Bees: ");
            infoPanel.getItems().add("-Curly Antenna: moves to flowers");
            infoPanel.getItems().add("-Circle Antenna: moves inline");
            infoPanel.getItems().add("We have 2 types of Flowers: ");
            infoPanel.getItems().add("-White: replenishes bee energy");
            infoPanel.getItems().add("-Purple: drains bee energy");
        }
    }
}
