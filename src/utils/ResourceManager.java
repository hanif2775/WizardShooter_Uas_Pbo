package utils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class ResourceManager {
    public static BufferedImage loadImage(String path) {
        try {
            return ImageIO.read(ResourceManager.class.getResourceAsStream("/" + path));
        } catch (IOException | NullPointerException e) {
            System.err.println("Failed to load image: " + path);
            return null;
        }
    }
}
