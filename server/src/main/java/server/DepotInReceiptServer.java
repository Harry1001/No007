package server;

import java.rmi.RemoteException;
import java.rmi.registry.Registry;

import data.DepotInReceiptDataImpl;

public class DepotInReceiptServer extends RMIServer{

	public void register() throws RemoteException {
		DepotInReceiptDataImpl centraldepotin = new DepotInReceiptDataImpl();
		Registry registry = createRegistry();
		registry.rebind("central_depotin", centraldepotin);
		
		System.out.println("Successfully create depotinreceipt server");
	}
	
}
