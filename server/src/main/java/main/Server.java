package main;

import java.rmi.RemoteException;

import javax.naming.NamingException;

import server.ChargeReceiptServer;
import server.CommodityServer;
import server.DepotInReceiptServer;
import server.DepotOutReceiptServer;
import server.DespatchReceiptServer;
import server.EntruckReceiptServer;
import server.FinanceServer;
import server.HubArrivalReceiptServer;
import server.LogisticServer;
import server.PayReceiptServer;
import server.ReceiveReceiptServer;
import server.RecordServer;
import server.SendReceiptServer;
import server.StoreArrivalReceiptServer;
import server.StrategyServer;
import server.TransferReceiptServer;
import server.infoserver.AgencyServer;
import server.infoserver.BankAccountServer;
import server.infoserver.DriverServer;
import server.infoserver.StaffServer;
import server.infoserver.TruckServer;
import server.infoserver.UserAccountServer;

public class Server {

	public static void main(String[] args) throws RemoteException, NamingException{
		/**
		 * 启动库存服务器
		 */
		CommodityServer centralCommodity = new CommodityServer();
		centralCommodity.register();
		
		/**
		 * 启动财务服务器
		 */
		FinanceServer centralfinance = new FinanceServer();
		centralfinance.register();
		
		/**
		 * 启动物流服务器
		 */
		LogisticServer centralLogistic = new LogisticServer();
		centralLogistic.register();
		
		/**
		 * 启动收款单服务器
		 */
		ChargeReceiptServer centralCharge = new ChargeReceiptServer();
		centralCharge.register();
		
		/**
		 * 启动入库单服务器
		 */
		DepotInReceiptServer centralDepotIn = new DepotInReceiptServer();
		centralDepotIn.register();
		
		/**
		 * 启动出库单服务器
		 */
		DepotOutReceiptServer centralDepotOut = new DepotOutReceiptServer();
		centralDepotOut.register();
		
		/**
		 * 启动派件单服务器
		 */
		DespatchReceiptServer centralDespatch = new DespatchReceiptServer();
		centralDespatch.register();
		
		/**
		 * 启动装车单服务器
		 */
		EntruckReceiptServer centralEntruck = new EntruckReceiptServer();
		centralEntruck.register();
		
		/**
		 * 启动中转中心到达单服务器
		 */
		HubArrivalReceiptServer centralHubArrival = new HubArrivalReceiptServer();
		centralHubArrival.register();
		
		/**
		 * 启动付款单服务器
		 */
		PayReceiptServer centralPay = new PayReceiptServer();
		centralPay.register();
		
		/**
		 * 启动收件单服务器
		 */
		ReceiveReceiptServer centralReceive =  new ReceiveReceiptServer();
		centralReceive.register();
		
		/**
		 * 启动寄件单服务器
		 */
		SendReceiptServer centralSend = new SendReceiptServer();
		centralSend.register();
		
		/**
		 * 启动营业厅到达单服务器
		 */
		StoreArrivalReceiptServer centralStoreArrival = new StoreArrivalReceiptServer();
		centralStoreArrival.register();
		
		/**
		 * 启动中转单服务器
		 */
		TransferReceiptServer centralTransfer = new TransferReceiptServer();
		centralTransfer.register();
		
		/**
		 * 启动info服务器
		 */
		AgencyServer agencyServer = new AgencyServer();
		agencyServer.register();
		
		BankAccountServer bankAccountServer = new BankAccountServer();
		bankAccountServer.register();
		
		DriverServer driverServer = new DriverServer();
		driverServer.register();
		
		StaffServer staffServer = new StaffServer();
		staffServer.register();
		
		TruckServer truckServer = new TruckServer();
		truckServer.register();
		
		UserAccountServer userAccountServer = new UserAccountServer();
		userAccountServer.register();
		
		/**
		 * 启动策略服务器
		 */
		StrategyServer strategyServer = new StrategyServer();
		strategyServer.register();
		
		/**
		 * 启动日志服务器
		 */
		RecordServer recordServer = new RecordServer();
		recordServer.register();
	}
}
