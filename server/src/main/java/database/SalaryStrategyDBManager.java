package database;

import java.sql.Connection;
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
		String salaryUpdate="UPDATE Salary"
				+"SET AccounantBaseSalary ="+po.getAccountantBS()+"SET AdnimisterBaseSalary ="+po.getAdministerBS()
				+"SET DriverBaseSalary ="+po.getDriverBS()+"SET HubsalesmanBaseSalary ="+po.getHubsalesmanBS()
				+"SET MailerBaseSalary ="+po.getMailerBS()+"SET ManagerBaseSalary ="+po.getManagerBS()
				+"SET StorekeeperBaseSalary ="+po.getStorekeeperBS()+"SET StoresalesmanBaseSalary ="+po.getStoresalesmanBS()
				+"SET DriverAllowance ="+po.getDriverAl()+"SET MailerAllowance ="+po.getMailerAl();
		Connection connection=connectToDB();
		Statement statement=connection.createStatement();
		statement.executeUpdate(salaryUpdate);
		stopconnection(connection);
	}
	
	/*public SalaryPO getAll() throws SQLException{
		String salaryGet="SELECT * FROM Salary";
		Connection connection=connectToDB();
		Statement statement=connection.createStatement();
		
	}*/
}
