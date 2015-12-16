package businessLogicService.strategyblservice;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.rmi.RemoteException;
import java.sql.SQLException;

import vo.strategyvo.DistanceVO;

public interface DistanceService {

	/**
	 * 制定城市间距离常量
	 * @throws RemoteException 
	 * @throws SQLException 
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	public void setDistance(DistanceVO vo) throws RemoteException, SQLException, FileNotFoundException, IOException;
	
	/**
	 * 得到城市间距离
	 * @throws RemoteException 
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 * @throws FileNotFoundException 
	 */
	public double getDistance(String city1,String city2) throws RemoteException, FileNotFoundException, ClassNotFoundException, IOException;
}
