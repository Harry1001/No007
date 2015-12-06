package server;

import java.rmi.RemoteException;
import java.rmi.registry.Registry;

import data.StoreArrivalReceiptDataImpl;

public class StoreArrivalReceiptServer extends RMIServer {

	public void register() throws RemoteException {
		StoreArrivalReceiptDataImpl centralstorearrival = new StoreArrivalReceiptDataImpl();
		Registry registry = createRegistry();
		registry.rebind("central_storearrival", centralstorearrival);
		
		System.out.println("Successfully create storearrivalreceipt server");
	}
	
}
