package server;

import java.rmi.RemoteException;
import java.rmi.registry.Registry;

import data.DespatchReceiptDataImpl;

public class DespatchReceiptServer extends RMIServer {

	public void register() throws RemoteException {
		DespatchReceiptDataImpl centraldespatch = new DespatchReceiptDataImpl();
		Registry registry = createRegistry();
		registry.rebind("central_despatch", centraldespatch);
		
		System.out.println("Successfully create despatchreceipt server");
	}
	
}
