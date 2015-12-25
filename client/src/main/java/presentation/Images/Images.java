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

    public static final Image LOGIN_IMAGE=createImage("ImageFiles/login.jpg");
    public static final ImageIcon USERHEAD=createIcon("ImageFiles/userHead.png");
    public static final ImageIcon RECEIPT_IMAGE=createIcon("ImageFiles/receipt.png");
    public static final ImageIcon SALARY_IMAGE=createIcon("ImageFiles/salary.png");
    public static final ImageIcon MONEY_DISTANCE_IMAGE=createIcon("ImageFiles/money_distance.png");
    public static final ImageIcon STAFF_IMAGE=createIcon("ImageFiles/staff.png");
    public static final ImageIcon AGENCY_IMAGE=createIcon("ImageFiles/agency.png");
    public static final ImageIcon RECORD_IMAGE=createIcon("ImageFiles/record.png");
    public static final ImageIcon BAOBIAO_IMAGE=createIcon("ImageFiles/baobiao.png");
    public static final ImageIcon SEND_IMAGE=createIcon("ImageFiles/send.png");
    public static final ImageIcon RECEIVE_IMAGE=createIcon("ImageFiles/receive.png");
    public static final ImageIcon DESPATCH_IMAGE=createIcon("ImageFiles/despatch.png");
    public static final ImageIcon ENTRUCK_IMAGE=createIcon("ImageFiles/entruck.png");
    public static final ImageIcon STORE_ARRIVE_IMAGE=createIcon("ImageFiles/storearrive.png");
    public static final ImageIcon CHARGE_IMAGE=createIcon("ImageFiles/charge.png");
    public static final ImageIcon TRUCK_IMAGE=createIcon("ImageFiles/truck.png");
    public static final ImageIcon DRIVER_IMAGE=createIcon("ImageFiles/driver.png");
    public static final ImageIcon HUB_ARRIVE_IMAGE=createIcon("ImageFiles/hubarrive.png");
    public static final ImageIcon TRANSFER_IMAGE=createIcon("ImageFiles/transfer.png");
    public static final ImageIcon BANK_ACCOUNT_IMAGE=createIcon("ImageFiles/bankaccount.png");
    public static final ImageIcon CHENBEN_IMAGE=createIcon("ImageFiles/chengben.png");
    public static final ImageIcon DEPOT_CHAKAN_IMAGE=createIcon("ImageFiles/depotchakan.png");
    public static final ImageIcon DEPOTIN_IMAGE=createIcon("ImageFiles/depotin.png");
    public static final ImageIcon DEPOTOUT_IMAGE=createIcon("ImageFiles/depotout.png");
    public static final ImageIcon DEPOT_PANDIAN_IMAGE=createIcon("ImageFiles/depotpandian.png");
    public static final ImageIcon QICHU_IMAGE=createIcon("ImageFiles/qichu.png");
    public static final ImageIcon SHOURU_IMAGE=createIcon("ImageFiles/shouru.png");
    public static final ImageIcon USER_ACCOUNT_IMAGE=createIcon("ImageFiles/useraccount.png");
    public static final ImageIcon BACKGROUND_IMAGE=createIcon("ImageFiles/背景.jpg");
     

}
