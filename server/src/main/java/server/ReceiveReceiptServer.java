package server;

import java.rmi.RemoteException;
import java.rmi.registry.Registry;

import data.ReceiveReceiptDataImpl;

public class ReceiveReceiptServer extends RMIServer {

	public void register() throws RemoteException {
		ReceiveReceiptDataImpl centralreceive = new ReceiveReceiptDataImpl();
		Registry registry = createRegistry();
		registry.rebind("central_receive", centralreceive);
		
		System.out.println("Successfully create receivereceipt server");
	}
	
}
