package test;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class RMITestImpl extends UnicastRemoteObject implements RMITestService{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected RMITestImpl() throws RemoteException {
		super();
	}

	public void show(String string) throws RemoteException {
		System.out.println(string);
	}

}
