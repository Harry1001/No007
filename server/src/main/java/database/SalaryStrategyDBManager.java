package database;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import po.strategypo.SalaryPO;

public class SalaryStrategyDBManager extends DBManager{

	public void addSalaryStrategy(SalaryPO po) throws SQLException{
		int acbs=po.getAccountantBS();
		int adbs=po.getAdministerBS();
		int dbs=po.getDriverBS();
		int hbs=po.getHubsalesmanBS();
		int mbs=po.getMailerBS();
		int manbs=po.getManagerBS();
		int skbs=po.getStorekeeperBS();
		int ssbs=po.getStoresalesmanBS();
		int dal=po.getDriverAl();
		int mal=po.getMailerAl();
		
		String salaryInsert="INSERT INTO Salary"
				+"VALUES ("+acbs+","+adbs+","+dbs+","+hbs+","+mbs+","+manbs+","+skbs+","+ssbs+","
				+dal+","+mal+")";
		Connection connection=connectToDB();
		Statement statement=connection.createStatement();
		statement.executeUpdate(salaryInsert);
		stopconnection(connection);
	}
}
