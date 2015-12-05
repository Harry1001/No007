package server.infoserver;

import java.rmi.RemoteException;
import java.rmi.registry.Registry;

import data.infodataimpl.UserAccountDataImpl;
import server.RMIServer;

public class UserAccountServer extends RMIServer {

	public void register() throws RemoteException {
		UserAccountDataImpl centraluseraccount = new UserAccountDataImpl();
		Registry registry = createRegistry();
		registry.rebind("central_useraccount", centraluseraccount);
		
		System.out.println("Successfully create useraccount server");
	}
}
