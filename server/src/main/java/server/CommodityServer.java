package server;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import javax.naming.*;

import data.CommodityDataImpl;

public class CommodityServer extends RMIServer{
	
	public void register() throws RemoteException, NamingException{
		System.out.println("Constructing commodity server...");
		CommodityDataImpl centralcommodity = new CommodityDataImpl();
		
		System.out.println("Binding commodity server to registry...");
		Registry registry = createRegistry();
		registry.rebind("central_commodity", centralcommodity);
		
		System.out.println("Waiting for invocations from clients...");
	}
}
