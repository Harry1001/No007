package test;

import java.rmi.RemoteException;
import java.rmi.registry.Registry;

import javax.naming.NamingException;

import server.RMIServer;

public class RMITestServer extends RMIServer{

	public void register() throws RemoteException, NamingException{
		System.out.println("Constructing test server...");
		RMITestImpl centraltest = new RMITestImpl();
		
		System.out.println("Binding test server to registry...");
		Registry registry = createRegistry();
		registry.rebind("central_test", centraltest);
		
		System.out.println("Waiting for invocations from clients...");
	}
	
	public static void main(String[] args) throws RemoteException, NamingException{
		RMITestServer rmiTestServer = new RMITestServer();
		rmiTestServer.register();
	}
	
}
