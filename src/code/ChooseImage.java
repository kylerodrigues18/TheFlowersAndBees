package code;

public class ChooseImage {

    private String fileLocation = "\\images\\";

    public String getFlowerFile() {
        int fileExtension = ((int) (Math.random() * 6)) + 1;
        return fileLocation + "flower-" + fileExtension + ".jpg";    }

    public String getBeeFile() {
        int fileExtension = ((int) (Math.random() * 2)) + 1;
        return fileLocation + "bee-" + fileExtension + ".jpg";
    }

}
