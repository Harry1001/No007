package server;

import java.rmi.RemoteException;
import java.rmi.registry.Registry;

import data.DepotOutReceiptDataImpl;

public class DepotOutReceiptServer extends RMIServer{

	public void register() throws RemoteException {
		DepotOutReceiptDataImpl centraldepotout = new DepotOutReceiptDataImpl();
		Registry registry = createRegistry();
		registry.rebind("central_depotout", centraldepotout);
		
		System.out.println("Successfully create depotoutreceipt server");
	}
	
}
