package data;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;
import java.util.Vector;

import dataService.StrategyDataService;
import database.CarriageStrategyDBManager;
import database.DistanceSerial;
import database.ExpressFeeStrategyDBManager;
import database.SalaryStrategyDBManager;
import po.strategypo.CarriageFeePO;
import po.strategypo.DistancePO;
import po.strategypo.ExpressFeePO;
import po.strategypo.SalaryPO;

public class StrategyDataImpl extends UnicastRemoteObject implements StrategyDataService{

	ExpressFeeStrategyDBManager efs;
	CarriageStrategyDBManager cfs;
	SalaryStrategyDBManager s;
	DistanceSerial d;
	public StrategyDataImpl() throws RemoteException {
		super();
		
		efs=new ExpressFeeStrategyDBManager();
		cfs=new CarriageStrategyDBManager();
		s=new SalaryStrategyDBManager();
		d=new DistanceSerial();
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ExpressFeePO getExpressFee() throws RemoteException, SQLException {
		// TODO Auto-generated method stub
		ExpressFeePO po=efs.getAll();
		return po;
	}

	public CarriageFeePO getCarriageFee() throws RemoteException, SQLException {
		// TODO Auto-generated method stub
		 
		CarriageFeePO po=cfs.get();
		return po;
	}

	public void updateExpressFeeStrategy(ExpressFeePO efpo) throws RemoteException, SQLException {
		// TODO Auto-generated method stub
		
		efs.updateExpressFeeStrategy(efpo);
	}

	public void updateCarriageFeeStrategy(CarriageFeePO cfpo) throws RemoteException, SQLException {
		// TODO Auto-generated method stub
		
		cfs.updateCarriageStrategy(cfpo);
	}

	public SalaryPO getSalary() throws RemoteException, SQLException {
		// TODO Auto-generated method stub
		
		SalaryPO po=s.getAll();
		return po;
	}

	public void updateSalaryStrategy(SalaryPO po) throws RemoteException, SQLException {
		// TODO Auto-generated method stub
		
		s.updateSalaryStrategy(po);
	}

	/**
	 * 根据距离策略得到距离
	 * 
	 */
	public double getDistance(String city1,String city2) throws FileNotFoundException, ClassNotFoundException, IOException {
		// TODO Auto-generated method stub
		
		DistancePO po=d.read();
		Vector<String> cities = po.getCities();
		Vector<Vector<Object>> data = po.getData();
		int a=0,b=0;
		for(int i=0;i<cities.size();i++){
			if(cities.get(i).equals(city1)){
				a=i;
				break;
			}
		}
		for(int i=0;i<data.size();i++){
			Vector<Object> v = data.get(i);
			if(v.get(0).equals(city2)){
				b=i;

			}
		}
		Vector<Object> v1 = data.get(b);
		double result=Double.parseDouble(v1.get(a+1).toString()) ;
		return result;
	}
	/**
	 * 向逻辑层提供距离策略
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 * @throws FileNotFoundException 
	 */
	public DistancePO getDistanceStrategy() throws FileNotFoundException, ClassNotFoundException, IOException{
		
		DistancePO po=d.read();
		return po;
	}
	
	public void updateDistanceStrategy(DistancePO po) throws FileNotFoundException, IOException {
		// TODO Auto-generated method stub
		
		d.serialize(po);
	}

}
