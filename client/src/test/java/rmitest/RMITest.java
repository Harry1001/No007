package rmitest;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class RMITest {

	public static void main(String[] args) throws MalformedURLException, RemoteException, NotBoundException {
		String url = "rmi://114.212.42.182:8888/central_test";
		RMITestService rmiTest = (RMITestService)Naming.lookup(url);
		rmiTest.show("No007 is a good team");
	}
}
