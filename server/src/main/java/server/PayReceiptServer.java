package server;

import java.rmi.RemoteException;
import java.rmi.registry.Registry;

import data.PayReceiptDataImpl;

public class PayReceiptServer extends RMIServer {

	public void register() throws RemoteException {
		PayReceiptDataImpl centralpay = new PayReceiptDataImpl();
		Registry registry = createRegistry();
		registry.rebind("central_pay", centralpay);
		
		System.out.println("Successfully create payreceipt server");
	}
	
}
