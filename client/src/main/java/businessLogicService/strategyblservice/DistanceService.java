package businessLogicService.strategyblservice;

import java.rmi.RemoteException;
import java.sql.SQLException;

import vo.strategyvo.DistanceVO;

public interface DistanceService {

	/**
	 * 制定城市间距离常量
	 * @throws RemoteException 
	 * @throws SQLException 
	 */
	public void setDistance(DistanceVO vo) throws RemoteException, SQLException;
	
	/**
	 * 得到城市间距离
	 * @throws RemoteException 
	 */
	public double getDistance(String city1,String city2) throws RemoteException;
}
