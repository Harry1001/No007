package main;

import java.rmi.RemoteException;

import javax.naming.NamingException;

import server.CommodityServer;
import server.FinanceServer;

public class Server {

	public static void main(String[] args) throws RemoteException, NamingException{
		CommodityServer centralCommodity = new CommodityServer();
		centralCommodity.register();
		
//		FinanceServer centralfinance = new FinanceServer();
//		centralfinance.register();
	}
}
