package code;

import java.nio.file.Path;
import java.nio.file.Paths;

public class ChooseImage {

    private Path currentRelativePath = Paths.get("");
    private String currentPath = currentRelativePath.toAbsolutePath().toString();
    private String imageFolderLocation = "\\src\\code\\images\\";

    private String fileLocation = currentPath + imageFolderLocation;

    private String getFilePath() {
        return fileLocation;
    }

    public String getFlowerFile() {
        int fileExtension = ((int) (Math.random() * 6)) + 1;
        return fileLocation + "flower-" + fileExtension + ".jpg";    }

    public String getBeeFile() {
        int fileExtension = ((int) (Math.random() * 3)) + 1;
        return fileLocation + "bee-" + fileExtension + ".jpg";
    }

}
