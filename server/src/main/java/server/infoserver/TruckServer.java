package server.infoserver;

import java.rmi.RemoteException;
import java.rmi.registry.Registry;

import data.infodataimpl.TruckDataImpl;
import server.RMIServer;

public class TruckServer extends RMIServer {

	public void register() throws RemoteException {
		TruckDataImpl centraltruck = new TruckDataImpl();
		Registry registry = createRegistry();
		registry.rebind("central_truck", centraltruck);
		
		System.out.println("Successfully create truck server");
	}
}
