package server;

import java.rmi.RemoteException;
import java.rmi.registry.Registry;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import data.FinanceDataImpl;

public class FinanceServer extends RMIServer{

	public void register() throws RemoteException, NamingException{
		System.out.println("Constructing finance server...");
		FinanceDataImpl centralfinance = new FinanceDataImpl();
		
		System.out.println("Binding finance server to registry...");
		Registry registry = createRegistry();
		registry.rebind("central_finance", centralfinance);
	
		System.out.println("Waiting for invocations from clients...");
	}
}
