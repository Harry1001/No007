package blfactory;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import businessLogic.infobl.controller.*;
import businessLogic.loginbl.LoginController;
import businessLogic.logisticbl.LogisticController;
import businessLogic.receiptbl.*;
import businessLogic.strategybl.*;
import businessLogic.transportbl.*;
import businessLogicService.infoblservice.*;
import businessLogicService.loginblservice.LoginBLService;
import businessLogicService.logisticblservice.LogisticBLService;
import businessLogicService.receiptblservice.*;
import businessLogicService.strategyblservice.*;
import businessLogicService.transportblservice.*;

/**
 * Created by Harry on 2015/11/21.
 * 业务逻辑对象创建工厂，在界面层需要创建业务逻辑对象或者其他bl使用别的bl时使用
 */

//todo 每个人加上自己的controller，改的时候通知别人别同时修改！！！！！！！！！！！！！！！！！！！！！！！！！！！
public class BLFactory {

    public static AgencyBLService getAgencyBLService() throws MalformedURLException, RemoteException, NotBoundException{
        return new AgencyBLController();
    }

    public static BankAccountBLService getBankAccountBLService() throws MalformedURLException, RemoteException, NotBoundException{
        return new BankAccountBLController();
    }

    public static DriverBLService getDriverBLService() throws MalformedURLException, RemoteException, NotBoundException{
        return new DriverBLController();
    }

    public static StaffBLService getStaffBLService() throws MalformedURLException, RemoteException, NotBoundException{
        return new StaffBLController();
    }

    public static TruckBLService getTruckBLService() throws MalformedURLException, RemoteException, NotBoundException{
        return new TruckBLController();
    }

    public static UserAccoutBLService getUserAccountBLService() throws MalformedURLException, RemoteException, NotBoundException{
        return new UserAccountBLController();
    }
    
    public static ChargeReceiptBLService getChargeReceiptBLService() throws MalformedURLException, RemoteException, NotBoundException{
    	return new ChargeReceiptController();
    }
    
    public static DepotInReceiptBLService getDepotInReceiptBLService() throws MalformedURLException, RemoteException, NotBoundException{
    	return new DepotInReceiptController();
    }
    
    public static DepotOutReceiptBLService getDepotOutReceiptBLService() throws MalformedURLException, RemoteException, NotBoundException{
    	return new DepotOutReceiptController();
    }
    
    public static DespatchReceiptBLService getDespatchReceiptBLService() throws MalformedURLException, RemoteException, NotBoundException{
    	return new DespatchReceiptController();
    }
    
    public static EntruckReceiptBLService getEntruckReceiptBLService() throws MalformedURLException, RemoteException, NotBoundException{
    	return new EntruckReceiptController();
    }
    
    public static HubArrivalReceiptBLService getHubArrivalReceiptBLService() throws MalformedURLException, RemoteException, NotBoundException{
    	return new HubArrivalReceiptController();
    }
    
    public static StoreArrivalReceiptBLService getStoreArrivalReceiptBLService() throws MalformedURLException, RemoteException, NotBoundException{
    	return new StoreArrivalReceiptController();
    }
    
    public static PayReceiptBLService getPayReceiptBLService() throws MalformedURLException, RemoteException, NotBoundException{
    	return new PayReceiptController();
    }
    
    public static ReceiveReceiptBLService getReceiveReceiptBLService() throws MalformedURLException, RemoteException, NotBoundException{
    	return new ReceiveReceiptController();
    }
    
    public static TransferReceiptBLService getTransferReceiptBLService() throws MalformedURLException, RemoteException, NotBoundException{
    	return new TransferReceiptController();
    }
    
    public static SendReceiptBLService getSendReceiptBLService() throws MalformedURLException, RemoteException, NotBoundException{
    	return new SendReceiptController();
    }
    
    public static LoginBLService getLoginBLService(){
    	return new LoginController();
    }
    
    public static LogisticBLService getLogisticBLService() throws MalformedURLException, RemoteException, NotBoundException{
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

    public static CalCarriageService getCalCarriageService() throws MalformedURLException, RemoteException, NotBoundException{
        return new CalCarriageFeeBL();
    }

    public static CalExpressfeeService getCalExpressfeeService() throws MalformedURLException, RemoteException, NotBoundException{
        return new CalExpressFeeBL();
    }

    public static CalSalaryService getCalSalaryService() throws MalformedURLException, RemoteException, NotBoundException{
        return new CalSalaryBL();
    }

    public static SalaryStrategyBLService getSalaryBLService() throws RemoteException, NotBoundException, MalformedURLException {
        return new StrategyBL();
    }

    public static FeeStrategyBLService getFeeBLService() throws RemoteException, NotBoundException, MalformedURLException {
        return new StrategyBL();
    }
}
