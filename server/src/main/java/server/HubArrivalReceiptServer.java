package server;

import java.rmi.RemoteException;
import java.rmi.registry.Registry;

import data.HubArrivalReceiptDataImpl;

public class HubArrivalReceiptServer extends RMIServer {

	public void register() throws RemoteException {
		HubArrivalReceiptDataImpl centralhubarrival = new HubArrivalReceiptDataImpl();
		Registry registry = createRegistry();
		registry.rebind("central_hubarrival", centralhubarrival);
		
		System.out.println("Successfully create hubarrivalreceipt server");
	}
	
}
