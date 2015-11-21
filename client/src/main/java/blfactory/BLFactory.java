package blfactory;

import businessLogic.infobl.controller.*;
import businessLogic.receiptbl.ReceiptController;
import businessLogicService.infoblservice.*;
import businessLogicService.receiptblservice.ReceiptBLService;

/**
 * Created by Harry on 2015/11/21.
 * 业务逻辑对象创建工厂，在界面层需要创建业务逻辑对象或者其他bl使用别的bl时使用
 */

//todo 每个人加上自己的controller，改的时候通知别人别同时修改！！！！！！！！！！！！！！！！！！！！！！！！！！！
public class BLFactory {

    public static AgencyBLService getAgencyBLService(){
        return new AgencyBLController();
    }

    public static BankAccountBLService getBankAccountBLService(){
        return new BankAccountBLController();
    }

    public static DriverBLService getDriverBLService(){
        return new DriverBLController();
    }

    public static StaffBLService getStaffBLService(){
        return new StaffBLController();
    }

    public static TruckBLService getTruckBLService(){
        return new TruckBLController();
    }

    public static UserAccoutBLService getUserAccountBLService(){
        return new UserAccountBLController();
    }

    public static ReceiptBLService getReceiptBLService(){
        return new ReceiptController();
    }
}
