package server;

import java.rmi.RemoteException;
import java.rmi.registry.Registry;

import data.ChargeReceiptDataImpl;

public class ChargeReceiptServer extends RMIServer {

	public void register() throws RemoteException{
		System.out.println("Constructing chargeReceipt server...");
		ChargeReceiptDataImpl centralchargeReceipt=new ChargeReceiptDataImpl();
		
		System.out.println("Binding chargeReceipt server to registry...");
		Registry registry=createRegistry();
		registry.rebind("central_chargeReceipt",centralchargeReceipt);
		
		System.out.println("Waiting for invocations from clients...");
	}
}
