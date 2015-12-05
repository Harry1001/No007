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

    public static final ImageIcon LOGIN_IMAGE=createIcon("client/ImageFiles/LOGIN_IMAGE.JPG");
    public static final ImageIcon USERHEAD=createIcon("client/ImageFiles/userHead.png");
    public static final ImageIcon RECEIPT_IMAGE=createIcon("client/ImageFiles/receipt.png");
    public static final ImageIcon SALARY_IMAGE=createIcon("client/ImageFiles/salary.png");
    public static final ImageIcon MONEY_DISTANCE_IMAGE=createIcon("client/ImageFiles/money_distance.png");
    public static final ImageIcon STAFF_IMAGE=createIcon("client/ImageFiles/staff.png");
    public static final ImageIcon AGENCY_IMAGE=createIcon("client/ImageFiles/agency.png");
    public static final ImageIcon RECORD_IMAGE=createIcon("client/ImageFiles/record.png");
    public static final ImageIcon BAOBIAO_IMAGE=createIcon("client/ImageFiles/baobiao.png");
    public static final ImageIcon SEND_IMAGE=createIcon("client/ImageFiles/send.png");
    public static final ImageIcon RECEIVE_IMAGE=createIcon("client/ImageFiles/receive.png");
    public static final ImageIcon DESPATCH_IMAGE=createIcon("client/ImageFiles/despatch.png");
    public static final ImageIcon ENTRUCK_IMAGE=createIcon("client/ImageFiles/entruck.png");
    public static final ImageIcon STORE_ARRIVE_IMAGE=createIcon("client/ImageFiles/storearrive.png");
    public static final ImageIcon CHARGE_IMAGE=createIcon("client/ImageFiles/charge.png");
    public static final ImageIcon TRUCK_IMAGE=createIcon("client/ImageFiles/truck.png");
    public static final ImageIcon DRIVER_IMAGE=createIcon("client/ImageFiles/driver.png");
    public static final ImageIcon HUB_ARRIVE_IMAGE=createIcon("client/ImageFiles/hubarrive.png");
    public static final ImageIcon TRANSFER_IMAGE=createIcon("client/ImageFiles/transfer.png");
    public static final ImageIcon BANK_ACCOUNT_IMAGE=createIcon("client/ImageFiles/bankaccount.png");
    public static final ImageIcon CHENBEN_IMAGE=createIcon("client/ImageFiles/chengben.png");
    public static final ImageIcon DEPOT_CHAKAN_IMAGE=createIcon("client/ImageFiles/depotchakan.png");
    public static final ImageIcon DEPOTIN_IMAGE=createIcon("client/ImageFiles/depotin.png");
    public static final ImageIcon DEPOTOUT_IMAGE=createIcon("client/ImageFiles/depotout.png");
    public static final ImageIcon DEPOT_PANDIAN_IMAGE=createIcon("client/ImageFiles/depotpandian.png");
    public static final ImageIcon QICHU_IMAGE=createIcon("client/ImageFiles/qichu.png");
    public static final ImageIcon SHOURU_IMAGE=createIcon("client/ImageFiles/shouru.png");
    public static final ImageIcon USER_ACCOUNT_IMAGE=createIcon("client/ImageFiles/useraccount.png");

}
