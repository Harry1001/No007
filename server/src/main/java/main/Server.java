package main;

import java.rmi.RemoteException;

import javax.naming.NamingException;

import server.CommodityServer;
import server.FinanceServer;

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
	}
}
