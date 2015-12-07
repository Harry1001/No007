package server;

import java.rmi.RemoteException;
import java.rmi.registry.Registry;

import data.RecordDataImpl;

public class RecordServer extends RMIServer{

	public void register() throws RemoteException{
		RecordDataImpl centralrecord=new RecordDataImpl();
		Registry registry=createRegistry();
		registry.rebind("central_record", centralrecord);
		
		System.out.println("Successfully create record server");
	}
}
