package database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import po.strategypo.SalaryPO;

public class SalaryStrategyDBManager extends DBManager{

	/**
	 * 添加薪水策略
	 * @param po
	 * @throws SQLException
	 */
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
	
	public void updateSalaryStrategy(SalaryPO po) throws SQLException{
		String salaryUpdate = "UPDATE Salary"
				+" SET accountantBaseSalary ="+po.getAccountantBS()+", administerBaseSalary ="+po.getAdministerBS()
				+", driverBaseSalary ="+po.getDriverBS()+", hubsalesmanBaseSalary ="+po.getHubsalesmanBS()
				+", mailerBaseSalary ="+po.getMailerBS()+", managerBaseSalary ="+po.getManagerBS()
				+", storekeeperBaseSalary ="+po.getStorekeeperBS()+", storesalesmanBaseSalary ="+po.getStoresalesmanBS()
				+", driverAllowance ="+po.getDriverAl()+", mailerAllowance ="+po.getMailerAl();
		Connection connection=connectToDB();
		Statement statement=connection.createStatement();
		statement.executeUpdate(salaryUpdate);
		stopconnection(connection);
	}
	
	public SalaryPO getAll() throws SQLException{
		String salaryGet="SELECT * FROM Salary";
		Connection connection=connectToDB();
		Statement statement=connection.createStatement();
		ResultSet resultset=statement.executeQuery(salaryGet);
		resultset.next();
		SalaryPO po=new SalaryPO(resultset.getInt(1),resultset.getInt(2),resultset.getInt(3),
				resultset.getInt(4),resultset.getInt(5),resultset.getInt(6),resultset.getInt(7),
				resultset.getInt(8),resultset.getInt(9),resultset.getInt(10));
		return po;
	}
}
