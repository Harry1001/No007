package presentation.Images;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.FileInputStream;

/**
 * Created by Harry on 2015/11/23.
 */
public class Images {
    private static Image createImage(String path){
        try {
            return ImageIO.read(new FileInputStream(path));
        } catch (Exception e){
            System.out.println("read image error");
            return null;
        }
    }

    private static ImageIcon createIcon(String path){
        return new ImageIcon(path);
    }

    public static final Image LOGIN_IMAGE=createImage("imagesFiles/LOGIN_IMAGE.jpg");
}
