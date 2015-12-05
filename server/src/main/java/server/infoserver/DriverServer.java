package server.infoserver;

import java.rmi.RemoteException;
import java.rmi.registry.Registry;

import data.infodataimpl.DriverDataImpl;
import server.RMIServer;

public class DriverServer extends RMIServer {

	public void register() throws RemoteException {
		DriverDataImpl centraldriver = new DriverDataImpl();
		Registry registry = createRegistry();
		registry.rebind("central_driver", centraldriver);
		
		System.out.println("Successfully create driver server");
	}
}
