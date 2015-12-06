package server;

import java.rmi.RemoteException;
import java.rmi.registry.Registry;

import data.EntruckReceiptDataImpl;

public class EntruckReceiptServer extends RMIServer {

	public void register() throws RemoteException {
		EntruckReceiptDataImpl centralentruck = new EntruckReceiptDataImpl();
		Registry registry = createRegistry();
		registry.rebind("central_entruck", centralentruck);
		
		System.out.println("Successfully create entruckreceipt server");
	}
	
}
