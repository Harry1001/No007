package server;

import java.rmi.AccessException;
import java.rmi.RemoteException;
import java.rmi.registry.Registry;

import data.StrategyDataImpl;

public class StrategyServer extends RMIServer{

	public void register() throws AccessException, RemoteException{
		StrategyDataImpl centralstrategy=new StrategyDataImpl();
		Registry registry=createRegistry();
		registry.rebind("central_strategy", centralstrategy);
		
		System.out.println("Successfully create strategy server");
	}
}
