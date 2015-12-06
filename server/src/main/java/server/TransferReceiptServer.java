package server;

import java.rmi.RemoteException;
import java.rmi.registry.Registry;

import data.TransferReceiptDataImpl;

public class TransferReceiptServer extends RMIServer {

	public void register() throws RemoteException {
		TransferReceiptDataImpl centraltransfer = new TransferReceiptDataImpl();
		Registry registry = createRegistry();
		registry.rebind("central_transfer", centraltransfer);
		
		System.out.println("Successfully create transferreceipt server");
	}
	
}
