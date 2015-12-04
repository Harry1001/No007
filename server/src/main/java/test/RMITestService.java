package test;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RMITestService extends Remote{

	public void show(String string) throws RemoteException;
}
