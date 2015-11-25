package database;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import po.strategypo.CarriageFeePO;

public class CarriageStrategyDBManager extends DBManager{

	/**
	 * 添加计算运费的策略
	 * @param po
	 * @throws SQLException
	 */
	public void addCarriageStrategy(CarriageFeePO po) throws SQLException{
		double bp=po.getBusPrice();
		double pp=po.getPlanePrice();
		double tp=po.getTrainPrice();
		
		String carriageInsert="INSERT INTO carriagefee"
				+" VALUES ("+bp+","+pp+","+tp+")";
		
		Connection connection=connectToDB();
		
		Statement statement=connection.createStatement();
		statement.executeUpdate(carriageInsert);
		
		stopconnection(connection);
	}
	/**
	 * 修改运费计算的策略
	 * @param po
	 * @throws SQLException
	 */
	public void updateCarriageStrategy(CarriageFeePO po) throws SQLException{
		String carriageDelete="TRUNCATE TABLE carriage";
		
		Connection connection=connectToDB();
		Statement statement=connection.createStatement();
		statement.executeUpdate(carriageDelete);
		stopconnection(connection);
		
		addCarriageStrategy(po);
	}
	
	/*public CarriageFeePO get(){
		String 
	}*/
}
