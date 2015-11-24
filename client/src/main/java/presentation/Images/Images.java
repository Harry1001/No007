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

    public static final ImageIcon LOGIN_IMAGE=createIcon("ImageFiles/LOGIN_IMAGE.JPG");
    public static final ImageIcon USERHEAD=createIcon("ImageFiles/userHead.png");
    public static final ImageIcon RECEIPT_IMAGE=createIcon("ImageFiles/receipt.png");
    public static final ImageIcon SALARY_IMAGE=createIcon("ImageFiles/salary.png");
    public static final ImageIcon MONEY_DISTANCE_IMAGE=createIcon("ImageFiles/money_distance.png");
    public static final ImageIcon STAFF_IMAGE=createIcon("ImageFiles/staff.png");
    public static final ImageIcon AGENCY_IMAGE=createIcon("ImageFiles/agency.png");
    public static final ImageIcon RECORD_IMAGE=createIcon("ImageFiles/record.png");
    public static final ImageIcon BAOBIAO_IMAGE=createIcon("ImageFiles/baobiao.png");
}
