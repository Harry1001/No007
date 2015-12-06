package server;

import java.rmi.RemoteException;
import java.rmi.registry.Registry;

import data.SendReceiptDataImpl;

public class SendReceiptServer extends RMIServer {

	public void register() throws RemoteException {
		SendReceiptDataImpl centralsend = new SendReceiptDataImpl();
		Registry registry = createRegistry();
		registry.rebind("central_send", centralsend);
		
		System.out.println("Successfully create sendreceipt server");
	}
	
}
