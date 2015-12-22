package database;

import java.sql.Connection;
import java.sql.ResultSet;
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
				+" VALUES ("+bp+", "+tp+", "+pp+")";
		
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
		String carriageDelete="TRUNCATE TABLE carriagefee";
		
		Connection connection=connectToDB();
		Statement statement=connection.createStatement();
		statement.executeUpdate(carriageDelete);
		stopconnection(connection);
		
		addCarriageStrategy(po);
	}
	/**
	 * 获得运费计算的策略
	 * @return
	 * @throws SQLException
	 */
	public CarriageFeePO get() throws SQLException{
		String carriageGet="SELECT * FROM carriagefee";
		Connection connection=connectToDB();
		Statement statement=connection.createStatement();
		ResultSet resultset=statement.executeQuery(carriageGet);
		resultset.next();
		
		CarriageFeePO po=new CarriageFeePO(resultset.getDouble(1), resultset.getDouble(2), resultset.getDouble(3));
		return po;
	}
}
