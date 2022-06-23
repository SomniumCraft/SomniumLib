package ru.somniumcraft.somniumlib.Util;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

public class ImageUtils {

    public BufferedImage loadFromURL(String url) throws IOException {
        BufferedImage bufferedImage = null;
        bufferedImage = ImageIO.read(new URL(url));

        return bufferedImage;
    }

}
