package ru.somniumcraft.somniumlib.Util;

import lombok.experimental.UtilityClass;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

@UtilityClass
public class ImageUtils {
    public BufferedImage loadFromURL(String url) throws IOException {
        BufferedImage bufferedImage;
        bufferedImage = ImageIO.read(new URL(url));

        return bufferedImage;
    }

}
