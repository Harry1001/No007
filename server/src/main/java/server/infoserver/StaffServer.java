package server.infoserver;

import java.rmi.RemoteException;
import java.rmi.registry.Registry;

import data.infodataimpl.StaffDataImpl;
import server.RMIServer;

public class StaffServer extends RMIServer {

	public void register() throws RemoteException {
		StaffDataImpl centralstaff = new StaffDataImpl();
		Registry registry = createRegistry();
		registry.rebind("central_staff", centralstaff);
		
		System.out.println("Successfully create staff server");
	}
}
