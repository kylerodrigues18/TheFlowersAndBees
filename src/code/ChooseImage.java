/**
 * Course: SE 2811 - 051
 * Winter 2019
 * Lab 2 - The Flowers and The Bees
 * Names: Milan Kablar and Kyle Rodrigues
 * Modified: 12/17/2019
 */
package code;

/**
 * Class that chooses a random image filename for the Bee and Flower.
 */
public class ChooseImage {

    private String fileLocation = "\\images\\";

    /**
     * Method that chooses random filename for flowers 1-6
     * @return String of image filename
     */
    public String getFlowerFile() {
        int fileExtension = ((int) (Math.random() * 6)) + 1;
        return fileLocation + "flower-" + fileExtension + ".jpg";    }

    /**
     * Method that chooses random filename for bees 1-2
     * @return String of image filename
     */
    public String getBeeFile() {
        int fileExtension = ((int) (Math.random() * 2)) + 1;
        return fileLocation + "bee-" + fileExtension + ".jpg";
    }

}
