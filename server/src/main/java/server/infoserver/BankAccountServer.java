package server.infoserver;

import java.rmi.RemoteException;
import java.rmi.registry.Registry;

import data.infodataimpl.BankAccountDataImpl;
import server.RMIServer;

public class BankAccountServer extends RMIServer {

	public void register() throws RemoteException {
		BankAccountDataImpl centralbankaccount = new BankAccountDataImpl();
		Registry registry = createRegistry();
		registry.rebind("central_bankaccount", centralbankaccount);
		
		System.out.println("Successfully create bankaccount server");
	}
}
