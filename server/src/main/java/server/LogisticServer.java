package server;

import java.rmi.RemoteException;
import java.rmi.registry.Registry;

import data.LogisticDataImpl;

public class LogisticServer extends RMIServer{

	public void register() throws RemoteException{
		System.out.println("Constructing logistic server...");
		LogisticDataImpl centrallogistic=new LogisticDataImpl();
		
		System.out.println("Binding logistic server to registry...");
		Registry registry=createRegistry();
		registry.rebind("central_logistic",centrallogistic);
		
		System.out.println("Waiting for invocations from clients...");
	}
}
