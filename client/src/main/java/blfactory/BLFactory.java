package blfactory;

import businessLogic.infobl.controller.*;
import businessLogic.loginbl.LoginController;
import businessLogic.logisticbl.LogisticController;
import businessLogic.receiptbl.ReceiptController;
import businessLogic.strategybl.CalCarriageFeeBL;
import businessLogic.strategybl.CalExpressFeeBL;
import businessLogic.strategybl.CalSalaryBL;
import businessLogic.transportbl.ArriveHubController;
import businessLogic.transportbl.ArriveStoreController;
import businessLogic.transportbl.DespatchController;
import businessLogic.transportbl.EntruckController;
import businessLogic.transportbl.ReceiveController;
import businessLogic.transportbl.SendController;
import businessLogic.transportbl.TransferController;
import businessLogicService.infoblservice.*;
import businessLogicService.loginblservice.LoginBLService;
import businessLogicService.logisticblservice.LogisticBLService;
import businessLogicService.receiptblservice.ReceiptBLService;
import businessLogicService.strategyblservice.CalCarriageService;
import businessLogicService.strategyblservice.CalExpressfeeService;
import businessLogicService.strategyblservice.CalSalaryService;
import businessLogicService.transportblservice.ArriveHubBLService;
import businessLogicService.transportblservice.ArriveStoreBLService;
import businessLogicService.transportblservice.DespatchBLService;
import businessLogicService.transportblservice.EntruckBLService;
import businessLogicService.transportblservice.ReceiveBLService;
import businessLogicService.transportblservice.SendBLService;
import businessLogicService.transportblservice.TransferBLService;

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
    
    public static LoginBLService getLoginBLService(){
    	return new LoginController();
    }
    
    public static LogisticBLService getLogisticBLService(){
		return new LogisticController();   	
    }
    
    public static ArriveHubBLService getArriveHubBLService(){
    	return new ArriveHubController();
    }
    
    public static ArriveStoreBLService getArriveStoreBLService(){
    	return new ArriveStoreController();
    }
    
    public static DespatchBLService getDespatchBLService(){
    	return new DespatchController();
    }
    
    public static EntruckBLService getEntruckBLService(){
    	return new EntruckController();
    }
    
    public static ReceiveBLService getReceiveBLService(){
    	return new ReceiveController();
    }
    
    public static SendBLService getSendBLService(){
    	return new SendController();
    }
    
    public static TransferBLService getTransferBLService(){
    	return new TransferController();
    }

    public static CalCarriageService getCalCarriageService(){
        return new CalCarriageFeeBL();
    }

    public static CalExpressfeeService getCalExpressfeeService(){
        return new CalExpressFeeBL();
    }

    public static CalSalaryService getCalSalaryService(){
        return new CalSalaryBL();
    }
}
