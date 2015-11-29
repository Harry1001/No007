package server;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RMIServer {
	
static int port = 8888;
	
	protected static Registry createRegistry(){
		Registry registry = null;
		try {
			registry = LocateRegistry.getRegistry(port);
			registry.list();
		} catch (final Exception e) {
			try {
				registry = LocateRegistry.createRegistry(port);
			} catch (RemoteException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		return registry;
	}

}
