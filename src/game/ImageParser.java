package game;

import javax.imageio.ImageIO;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

/**
 * The game.ImageParser class.
 **/
public class ImageParser {

    /**
     * The imageFromString method.
     *
     * @param s a type string
     * @return a new image
     * @throws IOException if the image no valid
     */
    public Image imageFromString(String s) throws IOException {
        BufferedImage img = null;
        try {
            InputStream inputStream = ClassLoader.getSystemClassLoader().getResourceAsStream(s);
            img = ImageIO.read(inputStream);

        } catch (IOException e) {
            System.out.println(e);
            return null;
        }
        return img;
    }
}
