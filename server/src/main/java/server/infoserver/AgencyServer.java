package server.infoserver;

import java.rmi.RemoteException;
import java.rmi.registry.Registry;

import data.infodataimpl.AgencyDataImpl;
import server.RMIServer;

public class AgencyServer extends RMIServer {

	public void register() throws RemoteException {
		AgencyDataImpl centralagency = new AgencyDataImpl();
		Registry registry = createRegistry();
		registry.rebind("central_agency", centralagency);
		
		System.out.println("Successfully create agency server");
	}
}
