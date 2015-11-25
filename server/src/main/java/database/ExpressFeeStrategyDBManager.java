package database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import po.strategypo.ExpressFeePO;

public class ExpressFeeStrategyDBManager extends DBManager{

	/**
	 * 向数据库中添加计算快递费的策略
	 * @param po
	 * @throws SQLException
	 */
	public void addExpressFeeStrategy(ExpressFeePO po) throws SQLException{
		double ep=po.getEcoPrice();
		double stdp=po.getStdPrice();
		double sp=po.getSpePrice();
		
		String expressStrategyInsert="INSERT INTO Expressfee"
				+ " VALUES ("+ep+","+stdp+","+sp+")";
		
		Connection connection= connectToDB();
		
		Statement statement=connection.createStatement();
		statement.executeUpdate(expressStrategyInsert);
		
		stopconnection(connection);
	}
	
	/**
	 * 修改快递费用计算策略
	 * @param po
	 * @throws SQLException
	 */
	public void updateExpressFeeStrategy(ExpressFeePO po) throws SQLException{
		
		String expressfeeDelete="TRUNCATE TABLE Expressfee";
		
		Connection connection =connectToDB();
		Statement statement=connection.createStatement();
		statement.executeUpdate(expressfeeDelete);
		stopconnection(connection);
		
		addExpressFeeStrategy(po);
	}
	
	/**
	 * 获取快递费用策略
	 * @return
	 * @throws SQLException
	 */
	public ExpressFeePO getAll() throws SQLException{
		ExpressFeePO po ;
		String expressfeeGet="SELECT * FROM Expressfee";
		
		Connection connection =connectToDB();
		Statement statement=connection.createStatement();
		ResultSet resultset = statement.executeQuery(expressfeeGet);
		resultset.next();
		po = new ExpressFeePO(resultset.getDouble(1), resultset.getDouble(2), resultset.getDouble(3));
		return po;
	}
}
