package test;

import java.rmi.RemoteException;

public class RMITestImpl implements RMITestService{

	public void show(String string) throws RemoteException {
		System.out.println(string);
	}

}
